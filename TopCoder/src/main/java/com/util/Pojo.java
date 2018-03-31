package com.util;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by sshil on 2/8/2016.
 */
public class Pojo {
	private String name;
	private Map<String, String> map1;
	private Map<String, String> map2;

	private Set<String> set;
	private Date date;
	private UUID uuid;
    public Pojo pojo;

	public static Pojo get(){
		Pojo pojo = new Pojo();
		pojo.name = "Suman";
		Map<String, String> map = new HashMap<>();
		map.put("prop1","prop1");
		map.put("prop2","prop2");
		pojo.map1 = map;
		map = new HashMap<>();
		map.put("prop1","prop1");
		map.put("prop2","prop2");
		pojo.map2 = map;
		Set<String> set = new HashSet<>();
		set.add("Hello");
		set.add("World");
		pojo.set = set;
		Date date = new Date();
		pojo.date = date;
		pojo.uuid = UUID.randomUUID();
		return pojo;
	}


}
