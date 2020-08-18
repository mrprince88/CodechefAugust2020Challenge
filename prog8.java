import java.io.*;
import java.util.*;

class prog8 {

	static class MyReader {
		BufferedReader br;
		StringTokenizer st;

		public MyReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}


	static class Graph {
		int v;
		int [][]adj;
		Graph(int v) {
			this.v = v;
			adj = new int[v + 1][v + 1];
		}
		void addEdge( int src, int dest) {
			adj[src][dest] = 1;
			adj[dest][src] = 1;
		}
		void DFSUtil(int a, boolean visited[], ArrayList<Integer> ar) {
			visited[a] = true;
			ar.add(a);
			for (int i = 1; i <= v; i++) {
				if (i == a)
					continue;
				if (adj[a][i] == 1) {
					if (visited[i])
						continue;
					DFSUtil(i, visited, ar);
				}
			}
		}

		ArrayList<Integer> DFS(int a) {
			boolean visited[] = new boolean[v + 1];
			ArrayList<Integer> ar = new ArrayList<>();
			DFSUtil(a, visited, ar);
			return ar;
		}

		void remove(int n) {
			for (int i = 1; i <= v; i++) {
				adj[i][n] = 0;
				adj[n][i] = 0;
			}
		}
	}


	static private final String INPUT = "./input.txt";
	static private final String OUTPUT = "./output.txt";

	public static void main(String[] args) {

		long startTime = System.nanoTime() / 1000000000;
		FileInputStream instream = null;
		PrintStream outstream = null;

		try {
			instream = new FileInputStream(INPUT);
			outstream = new PrintStream(new FileOutputStream(OUTPUT));
			System.setIn(instream);
			System.setOut(outstream);
		} catch (Exception e) {
			System.err.println("Error");
		}

		MyReader s = new MyReader();
		StringBuffer sb = new StringBuffer();
		int t = s.nextInt();
		while (t-- > 0) {
			int n = s.nextInt();
			Graph g = new Graph(n);
			int i, j;
			for (i = 1; i < n; i++) {
				int n1 = s.nextInt();
				int n2 = s.nextInt();
				g.addEdge(n1, n2);
			}
			// System.out.println(g.DFS(1));
			long []a = new long[n + 1];
			int []p = new int[n + 1];
			long []b = new long[n + 1];
			for (i = 1; i <= n; i++)
				p[i] = s.nextInt();
			for (i = 1; i <= n; i++)
				a[i] = s.nextLong();
			for (i = 1; i <= n; i++)
				b[i] = s.nextLong();

			Hashtable<Integer, Integer> add = new Hashtable<>();
			for (i = 1; i <= n; i++) {
				ArrayList<Integer> ar = g.DFS(p[i]);
				ListIterator<Integer> m = ar.listIterator();
				// System.out.println(ar);
				while (m.hasNext()) {
					j = m.next().intValue();
					if (b[j] != 0) {
						b[j] -= Math.min(b[j], a[p[i]]);
						if (b[j] == 0)
							add.put(j, i);
					}
				}
				g.remove(p[i]);
			}
			for (i = 1; i <= n; i++) {
				if (add.containsKey(i))
					sb.append(add.get(i) + " ");
				else
					sb.append("-1 ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

		double endTime   = System.nanoTime() / 1000000000;
		double totalTime = endTime - startTime;
		System.err.format("%.8f\n", totalTime);
		return;
	}
}