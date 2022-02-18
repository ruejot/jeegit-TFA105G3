package com.memfollow.model;

import java.io.Serializable;

public class MemFollowVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer friendshipId;
	private Integer memberId;
	private Integer followee;
	private String friendship;

	@Override
	public String toString() {
		return "MemFollowBean [friendshipId=" + friendshipId + ", memberId=" + memberId + ", followee=" + followee
				+ ", friendship=" + friendship + "]";
	}

	public Integer getFriendshipId() {
		return friendshipId;
	}

	public void setFriendshipId(Integer friendshipId) {
		this.friendshipId = friendshipId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getFollowee() {
		return followee;
	}

	public void setFollowee(Integer followee) {
		this.followee = followee;
	}

	public String getFriendship() {
		return friendship;
	}

	public void setFriendship(String friendship) {
		this.friendship = friendship;
	}

}
