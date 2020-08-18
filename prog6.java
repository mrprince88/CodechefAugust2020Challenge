import java.io.*;
import java.util.*;

class prog6 {

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

	static long []fact = new long[500005];
	static long m = 1000000007;

	static long recpow(long n, long p , long m) {
		long ans = 1;
		n = n % m;
		if (n == 0)
			return 0;

		while (p > 0) {
			if ((p & 1) == 1)
				ans = (ans * n) % m;
			n = (n * n) % m;
			p >>= 1;
		}

		return ans % m;

	}

	static long mod_inv(long n, long m) {
		return recpow(n, m - 2, m);
	}

	static long nCr(int n, int r, long m) {
		if (r == 0)
			return 1;
		return (((fact[n] * mod_inv(fact[n - r], m)) % m) * mod_inv(fact[r], m)) % m;
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
		fact[0] = fact[1] = 1;
		for (int i = 2; i < 500005; i++) {
			fact[i] = (long)(fact[i - 1] * (long)i) % m;
		}
		int t = s.nextInt();
		while (t-- > 0) {
			int i;
			int n = s.nextInt();
			int []a = new int[n];
			Hashtable<Integer, Integer> h = new Hashtable<>();
			for (i = 0; i < n; i++) {
				a[i] = s.nextInt();
				int c = h.containsKey(a[i]) ? h.get(a[i]) : 0;
				if (h.containsKey(a[i])) {
					if (c == 0)
						h.put(a[i], 1);
					else
						h.put(a[i], c + 1);
				}
				h.put(a[i], c + 1);
			}
			// System.out.println(h);
			long []cm = new long[n + 3];
			long []rem = new long[n + 1];
			for (i = 0; i < n + 1; i++) {
				cm[i] = rem[i] = 1;
			}
			h.forEach((k, v)-> {
				long sum = 1;
				for (int j = 1; j <= v; j++) {
					sum += nCr(v, j, m);
					sum %= m;
					cm[j] *= sum;
					cm[j] %= m;
				}
				rem[v + 1] *= sum;
				rem[v + 1] %= m;
			});
			int j;
			long mul = 1;
			for (i = 1; i <= n; i++) {
				mul *= rem[i];
				mul %= m;
				cm[i] *= mul;
				cm[i] %= m;
			}

			for (i = 1; i <= n; i++) {
				if (!h.containsKey(i)) {
					System.out.print("0 ");
					continue;
				}
				long res = 0, ans = 0, sum = 1;
				for (j = 1; j <= h.get(i); j++) {
					long tmp;
					ans = nCr(h.get(i), j, m);
					tmp = ans;
					sum += ans;
					sum %= m;
					cm[j] = (cm[j] * mod_inv(sum, m)) % m;
					ans *= cm[j];
					ans %= m;
					cm[j] = (cm[j] * (sum - tmp + m) % m) % m;
					res += ans;
					res %= m;
				}
				System.out.print(res % m + " ");
			}
			System.out.println();
		}
		double endTime   = System.nanoTime() / 1000000000;
		double totalTime = endTime - startTime;
		System.err.format("%.8f\n", totalTime);
		return;
	}
}