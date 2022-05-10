package dao;

public class Loc {
	private int classno;
	private int locno;
	//temp
	private String loc_name;
	private String upper_locname;
	
	
	
	public String getUpper_locname() {
		return upper_locname;
	}
	public void setUpper_locname(String upper_locname) {
		this.upper_locname = upper_locname;
	}
	public String getLoc_name() {
		return loc_name;
	}
	public void setLoc_name(String loc_name) {
		this.loc_name = loc_name;
	}
	public int getClassno() {
		return classno;
	}
	public void setClassno(int classno) {
		this.classno = classno;
	}
	public int getLocno() {
		return locno;
	}
	public void setLocno(int locno) {
		this.locno = locno;
	}

}
