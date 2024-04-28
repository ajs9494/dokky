package com.fastcampus.ch2.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchCondition {
	private Integer page;
	private Integer pageSize;
	private String option;
	private String keyword;
	private Boolean isPopular;
	
	public SearchCondition() {
		this(1, 10);
	}
	
	public SearchCondition(Integer page, Integer pageSize) {
		this(page, pageSize, "TC", "", false);
	}
	
	public SearchCondition(Integer page, Integer pageSize, String option, String keyword, Boolean isPopular) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.option = option;
		this.keyword = keyword;
		this.isPopular = isPopular;
	}
	
	public String getQueryString() {
		return getQueryString(page);
	}
	
	public String getQueryString(Integer page) {
		return UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("pageSize", pageSize)
				.queryParam("option", option)
				.queryParam("keyword", keyword)
				.queryParam("isPopular", isPopular)
				.build().toString();
	}
	
	public Integer getOffset() {
		return (page-1)*pageSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public Boolean getIsPopular() {
		return isPopular;
	}
	
	public void setIsPopular(Boolean isPopular) {
		this.isPopular = isPopular;
	}

	@Override
	public String toString() {
		return "SearchCondition [page=" + page + ", pageSize=" + pageSize + ", option=" + option + ", keyword="
				+ keyword + ", isPopular=" + isPopular + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isPopular == null) ? 0 : isPopular.hashCode());
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
		result = prime * result + ((option == null) ? 0 : option.hashCode());
		result = prime * result + ((page == null) ? 0 : page.hashCode());
		result = prime * result + ((pageSize == null) ? 0 : pageSize.hashCode());
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
		SearchCondition other = (SearchCondition) obj;
		if (isPopular == null) {
			if (other.isPopular != null)
				return false;
		} else if (!isPopular.equals(other.isPopular))
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		if (option == null) {
			if (other.option != null)
				return false;
		} else if (!option.equals(other.option))
			return false;
		if (page == null) {
			if (other.page != null)
				return false;
		} else if (!page.equals(other.page))
			return false;
		if (pageSize == null) {
			if (other.pageSize != null)
				return false;
		} else if (!pageSize.equals(other.pageSize))
			return false;
		return true;
	}
	
	
}
