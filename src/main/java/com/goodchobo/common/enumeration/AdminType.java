package com.goodchobo.common.enumeration;

public enum AdminType {
	ADMINISTRATOR("administrator", 1),
	MANAGER("manager", 2),
	OFFICER("officer", 3),
	STAFF("staff", 4);

	private String name;
	private int level;
	AdminType(String name, int level) {
		this.name = name;
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public static AdminType resolve(String name) {
		for(AdminType current : AdminType.values()) {
	        if(current.name.equals(name)) {
	            return current;
	        }
	    }

	    return null;
	}

	public static AdminType resolve(int level) {
		for(AdminType current : AdminType.values()) {
	        if(current.level == level) {
	            return current;
	        }
	    }

	    return null;
	}

}
