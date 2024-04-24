package com.fastcampus.ch2.domain;

import java.util.Date;

public class CommentLikeDto {
	private Integer lno;
	private Integer cno;
	private String nickname;
	private boolean isLiked;
	private Date regdate;
	
	public CommentLikeDto() {}
	
	public CommentLikeDto(Integer cno, String nickname, boolean isLiked) {
		super();
		this.cno = cno;
		this.nickname = nickname;
		this.isLiked = isLiked;
	}

	public Integer getLno() {
		return lno;
	}

	public void setLno(Integer lno) {
		this.lno = lno;
	}

	public Integer getCno() {
		return cno;
	}

	public void setCno(Integer cno) {
		this.cno = cno;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean getIsLiked() {
		return isLiked;
	}

	public void setIsLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "CommentLikeDto [lno=" + lno + ", cno=" + cno + ", nickname=" + nickname + ", isLiked=" + isLiked
				+ ", regdate=" + regdate + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cno == null) ? 0 : cno.hashCode());
		result = prime * result + (isLiked ? 1231 : 1237);
		result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
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
		CommentLikeDto other = (CommentLikeDto) obj;
		if (cno == null) {
			if (other.cno != null)
				return false;
		} else if (!cno.equals(other.cno))
			return false;
		if (isLiked != other.isLiked)
			return false;
		if (nickname == null) {
			if (other.nickname != null)
				return false;
		} else if (!nickname.equals(other.nickname))
			return false;
		return true;
	}
}
