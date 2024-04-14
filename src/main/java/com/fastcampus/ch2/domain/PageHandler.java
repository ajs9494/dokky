package com.fastcampus.ch2.domain;

public class PageHandler {
	private SearchCondition sc;
	private final Integer NAV_SIZE = 10;
	private Integer totalCnt;
	private Integer totalPage;
	private Integer beginPage;
	private Integer endPage;
	private boolean showNext;
	private boolean showPrev;
	
	public PageHandler() {}
	
	public PageHandler(Integer totalCnt, SearchCondition sc) {
		super();
		this.totalCnt = totalCnt;
		this.sc = sc;
		
		paging(totalCnt, sc);
	}

	private void paging(Integer totalCnt, SearchCondition sc) {
		totalPage = (int) Math.ceil(totalCnt / (double)sc.getPageSize());
		sc.setPage(Math.min(sc.getPage(), totalPage));
		beginPage = (sc.getPage()-1) / NAV_SIZE * NAV_SIZE + 1;
		endPage = Math.min(beginPage + NAV_SIZE - 1, totalPage);
		showNext = endPage != totalPage;
		showPrev = beginPage != 1;
	}

	public SearchCondition getSc() {
		return sc;
	}

	public void setSc(SearchCondition sc) {
		this.sc = sc;
	}

	public Integer getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(Integer totalCnt) {
		this.totalCnt = totalCnt;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(Integer beginPage) {
		this.beginPage = beginPage;
	}

	public Integer getEndPage() {
		return endPage;
	}

	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}

	public boolean isShowNext() {
		return showNext;
	}

	public void setShowNext(boolean showNext) {
		this.showNext = showNext;
	}

	public boolean isShowPrev() {
		return showPrev;
	}

	public void setShowPrev(boolean showPrev) {
		this.showPrev = showPrev;
	}

	public Integer getNAV_SIZE() {
		return NAV_SIZE;
	}

	@Override
	public String toString() {
		return "PageHandler [sc=" + sc + ", NAV_SIZE=" + NAV_SIZE + ", totalCnt=" + totalCnt + ", totalPage="
				+ totalPage + ", beginPage=" + beginPage + ", endPage=" + endPage + ", showNext=" + showNext
				+ ", showPrev=" + showPrev + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((NAV_SIZE == null) ? 0 : NAV_SIZE.hashCode());
		result = prime * result + ((beginPage == null) ? 0 : beginPage.hashCode());
		result = prime * result + ((endPage == null) ? 0 : endPage.hashCode());
		result = prime * result + ((sc == null) ? 0 : sc.hashCode());
		result = prime * result + (showNext ? 1231 : 1237);
		result = prime * result + (showPrev ? 1231 : 1237);
		result = prime * result + ((totalCnt == null) ? 0 : totalCnt.hashCode());
		result = prime * result + ((totalPage == null) ? 0 : totalPage.hashCode());
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
		PageHandler other = (PageHandler) obj;
		if (NAV_SIZE == null) {
			if (other.NAV_SIZE != null)
				return false;
		} else if (!NAV_SIZE.equals(other.NAV_SIZE))
			return false;
		if (beginPage == null) {
			if (other.beginPage != null)
				return false;
		} else if (!beginPage.equals(other.beginPage))
			return false;
		if (endPage == null) {
			if (other.endPage != null)
				return false;
		} else if (!endPage.equals(other.endPage))
			return false;
		if (sc == null) {
			if (other.sc != null)
				return false;
		} else if (!sc.equals(other.sc))
			return false;
		if (showNext != other.showNext)
			return false;
		if (showPrev != other.showPrev)
			return false;
		if (totalCnt == null) {
			if (other.totalCnt != null)
				return false;
		} else if (!totalCnt.equals(other.totalCnt))
			return false;
		if (totalPage == null) {
			if (other.totalPage != null)
				return false;
		} else if (!totalPage.equals(other.totalPage))
			return false;
		return true;
	}
	
	
}
