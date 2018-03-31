package com.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sshil on 2/8/2016.
 */
public class Conversion {
	List<String> fieldNames = new ArrayList<>();
    public List<String> convert(Pojo pojo) throws IllegalAccessException {
		if (fieldNames.size() == 0) {
			getNames(pojo);
		}
		//fieldNames.stream().forEach(s-> System.out.print(s));
		//System.out.println();
		List<String> list = handleValues(pojo);
		//list.stream().forEach(s -> System.out.print(s));
		return list;
	}

	private List<String> handleValues(Pojo object) throws IllegalAccessException {

		List<String> list = new ArrayList<>();
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true); // You might want to set modifier to public first.
			if (isSingleValuedField(field)) {
				Object value = field.get(object);
				if (value != null) {
					list.add(value.toString());
				} else {
					list.add(" ");
				}
				list.add(",");
			} else if(field.getType().getName().equals("java.util.Map")) {
				list.add("[");
				Map map = (Map)field.get(object);
				handleMapValue(map, list);
				list.add("]");
				list.add(",");
			}else if(field.getType().getName().equals("java.util.Set")) {
				list.add("[");
				Set<String> set = (Set)field.get(object);
				for (String str : set) {
					list.add(str);
					list.add(",");
				}
				list.remove(list.size()-1);
				list.add("]");
				list.add(",");
			}else if(field.getType().isAssignableFrom(Pojo.class)) {
				Pojo pojo = (Pojo)field.get(object);
				if (pojo != null) {
					list.add("[");
					List<String> list1 = handleValues(pojo);
					list.addAll(list1);
					list.remove(list.size() - 1);
					list.add("]");
					list.add(",");
				}
			}
		}
		return list;
	}

	private boolean isSingleValuedField(Field field) {
		return field.getType().isPrimitive()
			|| field.getType().getName().equals("java.lang.String")
			|| field.getType().getName().equals("java.util.Date")
			|| field.getType().getName().equals("java.util.UUID");
	}

	private void handleMapValue(Map<String, String> map, List<String> list) {
		for (Map.Entry<String, String> entry : map.entrySet()){
			list.add(entry.getValue());
			list.add(",");
		}
		list.remove(list.size()-1);

	}

	private void getNames(Object object) throws IllegalAccessException {
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true); // You might want to set modifier to public first.
			if (isSingleValuedField(field)) {
				String name = field.getName();
				fieldNames.add(name);
				fieldNames.add(",");
			} else if(field.getType().getName().equals("java.util.Map")) {
				fieldNames.add("[");
				Map map = (Map)field.get(object);
				handleMap(map);
				fieldNames.add("]");
				fieldNames.add(",");
			}else if(field.getType().getName().equals("java.util.Set")) {
				fieldNames.add("[");
				String name = field.getName();
				fieldNames.add(name);
				fieldNames.add("]");
				fieldNames.add(",");
			}else if(field.getType().isAssignableFrom(Pojo.class)) {
				fieldNames.add("[");
				String name = field.getName();
				fieldNames.add(name);
				fieldNames.add("]");
				fieldNames.add(",");
			}
		}
	}

	private void handleMap(Map<String, String> map) {
		for (Map.Entry<String, String> entry : map.entrySet()){
			fieldNames.add(entry.getKey());
			fieldNames.add(",");
		}
		fieldNames.remove(fieldNames.size()-1);

	}

	public List<String> getHeaders(){
		return fieldNames;
	}
	public static void main(String[] args) throws IllegalAccessException {
		Pojo pojo = Pojo.get();
		Pojo pojo1 = Pojo.get();
		pojo.pojo = pojo1;
		Conversion conversion = new Conversion();
		List<String> list = conversion.convert(pojo);
		// now the headers are ready print it..
		conversion.getHeaders().stream().forEach(s -> System.out.print(s));
		System.out.println();
		// now print the values.
		list.stream().forEach(s-> System.out.print(s));
		System.out.println();
		list = conversion.convert(pojo1);
		list.stream().forEach(s-> System.out.print(s));
		System.out.println();

	}
}
