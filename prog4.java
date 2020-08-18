import java.io.*;
import java.util.*;

class prog4 {

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
			String str1 = s.nextLine();
			String str2 = s.nextLine();
			String str = "", st = "";
			int i;
			int []a = new int[26];
			int []a1 = new int[26];
			for (i = 0; i < str1.length(); i++)
				a[(int)str1.charAt(i) - 97]++;
			for (i = 0; i < str2.length(); i++)
				a[(int)str2.charAt(i) - 97]--;
			for (i = 0; i < 26; i++)
				a1[i] = a[i];
			for (i = 0; i <= (int)str2.charAt(0) - 97; i++) {
				while (a[i] != 0) {
					str += (char)(i + 97);
					a[i]--;
				}
			}
			str += str2;
			for (i = (int)str2.charAt(0) - 96; i < 26; i++) {
				while (a[i] != 0) {
					str += (char)(i + 97);
					a[i]--;
				}
			}

			for (i = 0; i < (int)str2.charAt(0) - 97; i++) {
				while (a1[i] != 0) {
					st += (char)(i + 97);
					a1[i]--;
				}
			}
			st += str2;
			for (i = (int)str2.charAt(0) - 97; i < 26; i++) {
				while (a1[i] != 0) {
					st += (char)(i + 97);
					a1[i]--;
				}
			}
			if (str.compareTo(st) > 0)
				System.out.println(st);
			else
				System.out.println(str);
		}
		double endTime   = System.nanoTime() / 1000000000;
		double totalTime = endTime - startTime;
		System.err.format("%.8f\n", totalTime);
		return;
	}
}