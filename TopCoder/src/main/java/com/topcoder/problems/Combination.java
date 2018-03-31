package com.topcoder.problems;

import java.util.ArrayList;
import java.util.List;

public class Combination {

	
	public static List<String> combination(List<List<String>> variants){
		List<String> src = new ArrayList<>();
		List<String> var = variants.get(0);
		for(String str : var){
			src.add(str);
		}
		List<String> destination = new ArrayList<String>();
		for(int i = 1 ; i < variants.size() ; i++){
			var = variants.get(i);
			for(String str : var){
				for(String str1 : src){
					destination.add(str+"#"+str1);
				}
			}
			src = destination;
			destination = new ArrayList<String>();
		}
		
		return src;
	}

	public static void comibationRecursive(List<List<String>> variants){
		List<String> result = recursiveUtil( 0, variants);
		result.stream().forEach(System.out::println);
	}

	private static List<String> recursiveUtil( int index, List<List<String>> variants) {
		if (index == variants.size()-1){
			List<String> list = variants.get(index);
			//list.stream().forEach(e -> System.out.println(str+"#"+e));
			return list;
		}
		List<String> result = new ArrayList<>();
		List<String> list = variants.get(index);
		list.stream().forEach(e -> {
//			String prefix;
//			if (str.length() == 0){
//				prefix = e;
//			} else {
//				prefix = str+"#"+e;
//			}
			List<String> list1 = recursiveUtil(index+1, variants);
			list1.stream().forEach(elem -> result.add(e+"#"+elem));
		});
		return result;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<List<String>> variants = new ArrayList<>();
		List<String> sizes = new ArrayList<String>();
		sizes.add("Small");
		sizes.add("Medium");
		sizes.add("Large");
		variants.add(sizes);

		List<String> colors = new ArrayList<String>();
		colors.add("Black");
		colors.add("Green");
		colors.add("Blue");
		variants.add(colors);

		List<String> patterns = new ArrayList<String>();
		patterns.add("Checked");
		patterns.add("Unchecked");
		variants.add(patterns);

		/*List<String> results =combination(variants);
		for(String result : results){
			System.out.println(result);
		}
		*/
		comibationRecursive(variants);
	}

}
