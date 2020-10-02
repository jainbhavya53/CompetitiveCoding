import java.util.Scanner;
import java.util.*;

// TODO: Fix bug, still giving wromg answer
// https://www.codechef.com/LRNDSA08/problems/ENOC1
class NuttelaPath{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i = 0;i<t;i++){
            int u,v;
            int n = in.nextInt();
            int q = in.nextInt();
            int vertex[] = new int[n+1];
            List<Integer> edges[] = new List[n+1];
            vertex[0] = -1;
            for(int j = 1;j<=n;j++){
                vertex[j] = in.nextInt();
                edges[j] = new ArrayList<>();
            }
            for(int j = 0;j<n-1;j++){
                u = in.nextInt();
                v = in.nextInt();
                edges[u].add(v);
            }

            DFS obj = new DFS(vertex,edges);
            for(int j = 0;j<q;j++){
		int result[] = obj.lcaAndXor(in.nextInt(),in.nextInt());
                System.out.println("lca -> " + result[0] + "; xor -> " + result[1]);
            }
        }
    }
    
    static class DFS{
        List<Integer>[] edges;
        final int LOGN = 25;
        int parent[][];
        int val[][];
        int vertex[];
        int height[];
        int n;

        public DFS(int[] vertex, List<Integer>[] edges){
            this.edges = edges;
            this.vertex = vertex;
            this.n = edges.length-1;
            this.parent = new int[n+1][LOGN];
            this.val = new int[n+1][LOGN];
            this.height = new int[n+1];
            height[0] = -1;
            dfs(1,0,-1);
            fillParent();
        }

        private void dfs(int c,int p,int h){
            parent[c][0] = p;
            height[c] = h + 1;
            val[c][0] = 0; 

            for(int edge:edges[c]){
                dfs(edge,c,height[c]);
            }
        }

        private void fillParent(){
            for(int i = 1;i<LOGN;i++){
                for(int j = 1;j<=n;j++){
                    parent[j][i] = parent[parent[j][i-1]][i-1];
                    val[j][i] = val[j][i-1] ^ vertex[parent[j][i-1]] ^ val[parent[j][i-1]][i-1];
                }
            }
        }

        public int[] getHeight(){
            return this.height;
        }

        public int[][] getParent(){
            return this.parent;
        }

        public int[] lcaAndXor(int p,int q){
            int output = 0;
            output = output ^ vertex[p] ^ vertex[q];
            if(height[p] != height[q]){
                if(this.height[p] < height[q]){
                    int updates[] = bringNodesToSameHeight(p,q,output);
                    p = updates[0];
                    q = updates[1];
                    output = updates[2];
                }    
                else{
                    int updates[] = bringNodesToSameHeight(q,p,output);
                    p = updates[0];
                    q = updates[1];
                    output = updates[2];
                }
            }
            if(p == q){
                return new int[]{p,output^p};
            }
            while(parent[p][0] != parent[q][0]){
                int counter = 1;
                while(parent[p][counter] != parent[q][counter]){
                    counter++;
                }    
                output = output ^ val[p][counter-1] ^ val[q][counter-1];
                p = parent[p][counter-1];
                q = parent[q][counter-1];
                output = output ^ vertex[p] ^ vertex[q];
            }
            output = output ^ vertex[parent[p][0]];
            return new int[]{parent[p][0],output};
        }

        // Here assuming that p is above than q, i.e. height[p] < height[q]
        private int[] bringNodesToSameHeight(int p,int q, int xorOp){
            int diff = height[q] - height[p];
            int counter = 0;
            int value = 1;
            while(value <= diff){
                if((value & diff) != 0){
                    xorOp = xorOp ^ val[q][counter];
                    q = parent[q][counter];
                    xorOp = xorOp ^ vertex[q];
                }
                counter++;
                value = value << 1;
            }
            return new int[]{p,q,xorOp};
        }
    }
}
