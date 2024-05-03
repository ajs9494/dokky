package com.fastcampus.ch2.domain;

import java.util.Date;

public class BoardDto {
	private Integer bno;
	private String title;
	private String writer;
	private String contents;
	private Integer viewcnt;
	private Integer ccnt;
	private Integer likecnt;
	private String category;
	private Date regdate;
	
	public BoardDto() {}
	
	public BoardDto(String title, String writer, String contents, String category) {
		super();
		this.title = title;
		this.writer = writer;
		this.contents = contents;
		this.category = category;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getLikecnt() {
		return likecnt;
	}

	public void setLikecnt(Integer likecnt) {
		this.likecnt = likecnt;
	}

	public Integer getBno() {
		return bno;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(Integer viewcnt) {
		this.viewcnt = viewcnt;
	}

	public Integer getCcnt() {
		return ccnt;
	}

	public void setCcnt(Integer ccnt) {
		this.ccnt = ccnt;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "BoardDto [bno=" + bno + ", title=" + title + ", writer=" + writer + ", contents=" + contents
				+ ", viewcnt=" + viewcnt + ", ccnt=" + ccnt + ", likecnt=" + likecnt + ", category=" + category
				+ ", regdate=" + regdate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bno == null) ? 0 : bno.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((ccnt == null) ? 0 : ccnt.hashCode());
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((likecnt == null) ? 0 : likecnt.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((viewcnt == null) ? 0 : viewcnt.hashCode());
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
		BoardDto other = (BoardDto) obj;
		if (bno == null) {
			if (other.bno != null)
				return false;
		} else if (!bno.equals(other.bno))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (ccnt == null) {
			if (other.ccnt != null)
				return false;
		} else if (!ccnt.equals(other.ccnt))
			return false;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (likecnt == null) {
			if (other.likecnt != null)
				return false;
		} else if (!likecnt.equals(other.likecnt))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (viewcnt == null) {
			if (other.viewcnt != null)
				return false;
		} else if (!viewcnt.equals(other.viewcnt))
			return false;
		if (writer == null) {
			if (other.writer != null)
				return false;
		} else if (!writer.equals(other.writer))
			return false;
		return true;
	}
	
	
	
	
}
