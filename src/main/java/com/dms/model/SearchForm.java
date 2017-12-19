package com.dms.model;

import java.util.List;

public class SearchForm {
	
	private Long folderid;
	
	private Long repositoryid;
	
	private String searchtype;
	
	private String searchtext;
	
	private List<SearchCriteria> searchlist;

	public List<SearchCriteria> getSearchlist() {
		return searchlist;
	}

	public void setSearchlist(List<SearchCriteria> searchlist) {
		this.searchlist = searchlist;
	}

	public Long getFolderid() {
		return folderid;
	}

	public void setFolderid(Long folderid) {
		this.folderid = folderid;
	}

	public Long getRepositoryid() {
		return repositoryid;
	}

	public void setRepositoryid(Long repositoryid) {
		this.repositoryid = repositoryid;
	}

	public String getSearchtype() {
		return searchtype;
	}

	public void setSearchtype(String searchtype) {
		this.searchtype = searchtype;
	}

	public String getSearchtext() {
		return searchtext;
	}

	public void setSearchtext(String searchtext) {
		this.searchtext = searchtext;
	}
	
	
	
}
