package com.fastcampus.ch2.domain;

import java.util.Date;

public class CommentDto {
	private Integer cno;
	private Integer pcno;
	private Integer bno;
	private String writer;
	private String contents;
	private Date regdate;
	
	public CommentDto() { this(-1, "", ""); }
	
	public CommentDto(Integer bno, String writer, String contents) {
		super();
		this.bno = bno;
		this.writer = writer;
		this.contents = contents;
	}
	
	public Integer getCno() {
		return cno;
	}
	public void setCno(Integer cno) {
		this.cno = cno;
	}
	public Integer getPcno() {
		return pcno;
	}
	public void setPcno(Integer pcno) {
		this.pcno = pcno;
	}
	public Integer getBno() {
		return bno;
	}
	public void setBno(Integer bno) {
		this.bno = bno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "CommentDto [cno=" + cno + ", pcno=" + pcno + ", bno=" + bno + ", writer=" + writer + ", contents="
				+ contents + ", regdate=" + regdate + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bno == null) ? 0 : bno.hashCode());
		result = prime * result + ((cno == null) ? 0 : cno.hashCode());
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((pcno == null) ? 0 : pcno.hashCode());
		result = prime * result + ((writer == null) ? 0 : writer.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentDto other = (CommentDto) obj;
		if (bno == null) {
			if (other.bno != null)
				return false;
		} else if (!bno.equals(other.bno))
			return false;
		if (cno == null) {
			if (other.cno != null)
				return false;
		} else if (!cno.equals(other.cno))
			return false;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (pcno == null) {
			if (other.pcno != null)
				return false;
		} else if (!pcno.equals(other.pcno))
			return false;
		if (writer == null) {
			if (other.writer != null)
				return false;
		} else if (!writer.equals(other.writer))
			return false;
		return true;
	}
	
	
}
