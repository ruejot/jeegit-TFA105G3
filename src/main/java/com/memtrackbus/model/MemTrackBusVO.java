package com.memtrackbus.model;

import java.io.Serializable;

public class MemTrackBusVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer busfollowId;
	private Integer memberId;
	private Integer busId;

	@Override
	public String toString() {
		return "MemTrackBusBean [busfollowId=" + busfollowId + ", memberId=" + memberId + ", busId=" + busId + "]";
	}

	public Integer getBusfollowId() {
		return busfollowId;
	}

	public void setBusfollowId(Integer busfollowId) {
		this.busfollowId = busfollowId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getBusId() {
		return busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

}
