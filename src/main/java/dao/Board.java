package dao;

import java.sql.Date;

public class Board {
	private int boardno;
	private int tchno;
	private int studno;
	private String content;
	private String subject;
	private Date write_date;
	private int readcount;
	private int ref;
	private int re_step;
	private int re_level;
	// temp 
	private String name; // 선생이름 
	private String name2; // 학생이름
	
	

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName() {
		return name;
	}
	
	public int getTchno() {
		return tchno;
	}

	public void setTchno(int tchno) {
		this.tchno = tchno;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}


	public int getStudno() {
		return studno;
	}
	public void setStudno(int studno) {
		this.studno = studno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	
	

}
