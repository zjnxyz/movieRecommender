package zjn.model;

import java.util.List;

import zjn.util.StringUtil;

public class User {
	
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String SEX = "sex";
	public static final String HOBBY = "hobby";
	
	private int id;
	private String name;
	private String sex;
	private List<String> hobbyList;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public List<String> getHobbyList() {
		return hobbyList;
	}
	public void setHobbyList(List<String> hobbyList) {
		this.hobbyList = hobbyList;
	}
	public String toJSON(){
		StringBuilder sb = new StringBuilder();
		sb.append("{\"" + ID + "\":\"" + id + "\", ");
		sb.append("\"" + NAME + "\":\"" + name + "\", ");
		sb.append("\"" + SEX + "\":\"" + sex + "\",");
		String str = StringUtil.connectString(hobbyList, ",");
		sb.append("\"hobby\":\""+str+"\"");
		sb.append("}");
		return sb.toString();
	}

}
