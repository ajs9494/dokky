package com.fastcampus.ch2.domain;

import java.util.Date;

public class BoardLikeDto {
	private Integer bno;
	private String nickname;
	private boolean isLiked;
	private Date regdate;
	
	public BoardLikeDto() {}
	
	public BoardLikeDto(Integer bno, String nickname, boolean isLiked) {
		super();
		this.bno = bno;
		this.nickname = nickname;
		this.isLiked = isLiked;
	}

	public Integer getBno() {
		return bno;
	}

	public void setBno(Integer bno) {
		this.bno = bno;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bno == null) ? 0 : bno.hashCode());
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
		BoardLikeDto other = (BoardLikeDto) obj;
		if (bno == null) {
			if (other.bno != null)
				return false;
		} else if (!bno.equals(other.bno))
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

	@Override
	public String toString() {
		return "LikeDto [bno=" + bno + ", nickname=" + nickname + ", isLiked=" + isLiked + ", regdate=" + regdate + "]";
	}
}