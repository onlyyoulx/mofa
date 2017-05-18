package com.mofa.entity;

import java.util.List;
import java.util.Map;

public class Result {
	private List li;
	private String v;
	
	public Result(){
		
	}

	public List getLi() {
		return li;
	}

	public void setLi(List li) {
		this.li = li;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public Result(List li, String v) {
		super();
		this.li = li;
		this.v = v;
	}
	
	
	

}
