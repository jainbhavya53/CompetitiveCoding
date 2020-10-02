import java.util.Scanner;
import java.util.*;

// https://www.codechef.com/LRNDSA08/problems/ENOC1
// Not exactly solution of Nuttela Path, but a part of it
// Depicts how to find LCA in logN
public class LCA{
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

            DFS obj = new DFS(edges);
            for(int j = 0;j<q;j++){
                System.out.println(obj.lca(in.nextInt(),in.nextInt()));
            }
        }
    }
    
    static class DFS{
        List<Integer>[] edges;
        final int LOGN = 25;
        int parent[][];
        int height[];
        int n;

        public DFS(List<Integer>[] edges){
            this.edges = edges;
            this.n = edges.length-1;
            this.parent = new int[n+1][LOGN];
            this.height = new int[n+1];
            height[0] = -1;
            dfs(1,0,-1);
            fillParent();
        }

        private void dfs(int c,int p,int h){
            parent[c][0] = p;
            height[c] = h + 1; 

            for(int edge:edges[c]){
                dfs(edge,c,height[c]);
            }
        }

        private void fillParent(){
            for(int i = 1;i<LOGN;i++){
                for(int j = 1;j<=n;j++){
                    parent[j][i] = parent[parent[j][i-1]][i-1];
                }
            }
        }

        public int[] getHeight(){
            return this.height;
        }

        public int[][] getParent(){
            return this.parent;
        }

        public int lca(int p,int q){
            if(height[p] != height[q]){
                if(this.height[p] < height[q]){
                    int updates[] = bringNodesToSameHeight(p,q);
                    p = updates[0];
                    q = updates[1];
                }    
                else{
                    int updates[] = bringNodesToSameHeight(q,p);
                    p = updates[0];
                    q = updates[1];
                }
            }
            if(p == q){
                return p;
            }
            while(parent[p][0] != parent[q][0]){
                int counter = 1;
                while(parent[p][counter] != parent[q][counter]){
                    counter++;
                }    
                p = parent[p][counter-1];
                q = parent[q][counter-1];
            }
            return parent[p][0];
        }

        // Here assuming that p is above than q, i.e. height[p] < height[q]
        private int[] bringNodesToSameHeight(int p,int q){
            int diff = height[q] - height[p];
            int counter = 0;
            int value = 1;
            while(value <= diff){
                if((value & diff) != 0){
                    q = parent[q][counter];
                }
                counter++;
                value = value << 1;
            }
            return new int[]{p,q};
        }
    }
}
