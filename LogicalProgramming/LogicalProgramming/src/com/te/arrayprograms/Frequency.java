package com.te.arrayprograms;

import java.util.Arrays;

public class Frequency {
	public static void main(String[] args) {
		int[] input = { 1, 1, 1, 2, 6, 1, 15, 50, 2, 6 };
		boolean[] output = new boolean[input.length];
		Arrays.fill(output, false);
		for (int i = 0; i < input.length; i++) {
			if (output[i] == true)
				continue;

			int count = 1;
			for (int j = i + 1; j < input.length; j++) {
				if (input[i] == input[j]) {
					output[j] = true;
					count++;
				}

			}

			System.out.println(input[i] + " " + count);

		}
	}
	/* Second way */
	/*
	 * public static void main(String[] args) { int[] output = new int[100]; int[] x
	 * = { 1, 2, 4, 6, 10, 20, 30, 1, 20, 30 }; for (int i = 0; i < x.length; i++) {
	 * int index = x[i]; output[index]++; } for (int i = 0; i < output.length; i++)
	 * { if (output[i] != 0) { System.out.println(i + " " + output[i]); } }
	 * 
	 * }
	 */

}
