import java.io.*;
import java.util.*;

/*
https://www.hackerearth.com/practice/data-structures/disjoint-data-strutures/basics-of-disjoint-data-structures/practice-problems/algorithm/merge-the-boxes-7fb988ac/
*/

public class MergeTheGroups {

  static class Reader {
    private final int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public Reader() {
      din = new DataInputStream(System.in);
      buffer = new byte[BUFFER_SIZE];
      bufferPointer = bytesRead = 0;
    }

    public Reader(String file_name) throws IOException {
      din = new DataInputStream(new FileInputStream(file_name));
      buffer = new byte[BUFFER_SIZE];
      bufferPointer = bytesRead = 0;
    }

    public String readLine() throws IOException {
      byte[] buf = new byte[64]; // line length
      int cnt = 0, c;
      while ((c = read()) != -1) {
        if (c == '\n') break;
        buf[cnt++] = (byte) c;
      }
      return new String(buf, 0, cnt);
    }

    public int nextInt() throws IOException {
      int ret = 0;
      byte c = read();
      while (c <= ' ') c = read();
      boolean neg = (c == '-');
      if (neg) c = read();
      do {
        ret = ret * 10 + c - '0';
      } while ((c = read()) >= '0' && c <= '9');

      if (neg) return -ret;
      return ret;
    }

    public long nextLong() throws IOException {
      long ret = 0;
      byte c = read();
      while (c <= ' ') c = read();
      boolean neg = (c == '-');
      if (neg) c = read();
      do {
        ret = ret * 10 + c - '0';
      } while ((c = read()) >= '0' && c <= '9');
      if (neg) return -ret;
      return ret;
    }

    public double nextDouble() throws IOException {
      double ret = 0, div = 1;
      byte c = read();
      while (c <= ' ') c = read();
      boolean neg = (c == '-');
      if (neg) c = read();

      do {
        ret = ret * 10 + c - '0';
      } while ((c = read()) >= '0' && c <= '9');

      if (c == '.') {
        while ((c = read()) >= '0' && c <= '9') {
          ret += (c - '0') / (div *= 10);
        }
      }

      if (neg) return -ret;
      return ret;
    }

    private void fillBuffer() throws IOException {
      bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
      if (bytesRead == -1) buffer[0] = -1;
    }

    private byte read() throws IOException {
      if (bufferPointer == bytesRead) fillBuffer();
      return buffer[bufferPointer++];
    }

    public void close() throws IOException {
      if (din == null) return;
      din.close();
    }
  }

  public static PrintWriter out;

  public static void main(String args[]) throws Exception {
    Reader in = new Reader();
    out = new PrintWriter(new BufferedOutputStream(System.out), true);
    if (args.length >= 2
        && args[0].trim().startsWith("--inputPath=")
        && args[1].trim().startsWith("--outputPath=")) {
      String inputPath = args[0].trim().split("--inputPath=")[1];
      String outputPath = args[1].trim().split("--outputPath=")[1];
      in = new Reader(inputPath);
      out = new PrintWriter(new BufferedWriter(new FileWriter(outputPath)), true);
    }
    int n = in.nextInt();
    int[] arr = new int[n + 1];
    arr[0] = -1;
    for (int i = 1; i <= n; i++) {
      arr[i] = in.nextInt();
    }
    Groups g = new Groups(arr);
    int q = in.nextInt();
    for (int i = 0; i < q; i++) {
      int type = in.nextInt();
      if (type == 1) {
        int first = in.nextInt();
        int second = in.nextInt();
        g.updateTree(first, second);
      } else {
        int input = in.nextInt();
        if (input > n) {
          out.println(0);
        } else {
          out.println(g.getAbsDiff(input));
        }
      }
    }
  }

  public static class Groups {
    private int n;
    private int[] arr;
    private int[] treeArr;
    private int[] rankArr;
    private int[] minArr;
    private int[] maxArr;

    public Groups(int[] arr) {
      this.arr = arr;
      this.n = arr.length - 1;
      this.treeArr = new int[n + 1];
      this.rankArr = new int[n + 1];
      this.minArr = new int[n + 1];
      this.maxArr = new int[n + 1];
      this.arr[0] = -1;
      this.treeArr[0] = -1;
      this.rankArr[0] = -1;
      this.minArr[0] = -1;
      this.maxArr[0] = -1;
      for (int i = 1; i <= n; i++) {
        treeArr[i] = i;
        rankArr[i] = 1;
        minArr[i] = arr[i];
        maxArr[i] = arr[i];
      }
    }

    public void updateTree(int first, int second) {
      int fRoot = getRoot(first);
      int sRoot = getRoot(second);
      if (rankArr[fRoot] > rankArr[sRoot]) {
        treeArr[sRoot] = fRoot;
        rankArr[fRoot] = rankArr[fRoot] + rankArr[sRoot];
        minArr[fRoot] = Math.min(minArr[fRoot], minArr[sRoot]);
        maxArr[fRoot] = Math.max(maxArr[fRoot], maxArr[sRoot]);
      } else {
        treeArr[fRoot] = sRoot;
        rankArr[sRoot] = rankArr[sRoot] + rankArr[fRoot];
        minArr[sRoot] = Math.min(minArr[fRoot], minArr[sRoot]);
        maxArr[sRoot] = Math.max(maxArr[fRoot], maxArr[sRoot]);
      }
    }

    public int getRoot(int input) {
      int root = input;
      while (root != treeArr[root]) {
        root = treeArr[root];
      }
      return root;
    }

    public int getAbsDiff(int input) {
      int root = getRoot(input);
      return Math.abs(minArr[root] - maxArr[root]);
    }
  }
}
