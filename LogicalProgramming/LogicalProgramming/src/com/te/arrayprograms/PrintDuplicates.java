package com.te.arrayprograms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrintDuplicates {

	public static void main(String[] args) {

		int[] input = new int[] { 2, 1, 2, 3, 6, 10, 10, 1, 4, 6 };
		/*
		 * for (int i = 0; i < input.length; i++) { for (int j = i + 1; j <
		 * input.length; j++) { if (input[i] == input[j]) {
		 * System.out.println(input[j]); } } }
		 */

		Set<Integer> unique = new HashSet<Integer>();
		Set<Integer> duplicate = new HashSet<Integer>();

		for (Integer integer : input) {
			if (!unique.add(integer)) {
				duplicate.add(integer);
			}

		}
		System.out.println(unique);
		
		
		System.out.println(duplicate);

	}

}
