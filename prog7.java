import java.io.*;
import java.util.*;

class prog7 {

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

	static long comb(long n) {
		if (n == 3)
			return 1;
		if (n == 5)
			return 10;
		if (n == 4)
			return 4;
		int i;
		long freq = 1;
		long []sub = new long[4];
		sub[0] = 1;
		for (i = 1; i <= 3; i++) {
			freq = (freq * (n - i + 1)) / i;
			sub[i] = freq;
		}
		return sub[3];
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
		int t = s.nextInt();
		while (t-- > 0) {
			int n = s.nextInt();
			int c = s.nextInt();
			int k = s.nextInt();
			int i, a = 0, b, c1;
			long ans = 0;
			int[] col = new int[c + 1];
			col[0] = Integer.MIN_VALUE;
			for (i = 0; i < n; i++) {
				a = s.nextInt();
				b = s.nextInt();
				c1 = s.nextInt();
				col[c1]++;
			}
			int []v = new int[c + 1];
			for (i = 1; i <= c; i++)
				v[i] = s.nextInt();
			while (k != 0) {
				Arrays.sort(col);
				if (col[c] >= 3) {
					col[c]--;
					k -= v[c];
				} else
					break;
			}
			Arrays.sort(col);
			for (i = c; i >= 0; i--) {
				if (col[i] >= 3) {
					ans += comb(col[i]);
				} else
					break;
			}
			System.out.println(ans);

		}


		double endTime   = System.nanoTime() / 1000000000;
		double totalTime = endTime - startTime;
		System.err.format("%.8f\n", totalTime);
		return;
	}
}