package com.memblogart.model;

import java.sql.Timestamp;

public class MemBlogArtVO implements java.io.Serializable{
	private Integer artid;
	private Integer memberId;
	private String title;
	private Timestamp posttime;
	private Integer heart;
	private String content;
	
	
	public Integer getArtid() {
		return artid;
	}
	public void setArtid(Integer artid) {
		this.artid = artid;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getPosttime() {
		return posttime;
	}
	public void setPosttime(Timestamp posttime) {
		this.posttime = posttime;
	}
	public Integer getHeart() {
		return heart;
	}
	public void setHeart(Integer heart) {
		this.heart = heart;
	}
	public String getContent() {
		content.replaceAll("\n", "<br>");
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}

