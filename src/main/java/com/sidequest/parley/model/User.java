package com.sidequest.parley.model;

public class User {
	private int id;
	private String name;

	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String[] toArray() {
		String[] fields = {String.valueOf(this.id), this.name};
		return fields;
		
	}

	public String stringify() {
		String id = String.valueOf(this.id);
		String escapedName = this.name.replace("\"", "\"\"");
		String[] fields = {id, escapedName};
		return String.join(",", fields);
		
		/* StringBuilder sb = new StringBuilder();
		    sb.append("\"").append(this.id).append("\",\"");
		    sb.append(this.name.replaceAll("\"", "\"\"")).append("\"\n");
		    return sb.toString();
		*/
		//    return "\"" + this.id + "\",\"" + this.name.replace("\"", "\"\"") + "\"";
		//return this.id + "," + this.name;
	}
}
