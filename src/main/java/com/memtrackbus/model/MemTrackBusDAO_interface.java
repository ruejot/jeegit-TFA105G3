package com.memtrackbus.model;

import java.util.List;

public interface MemTrackBusDAO_interface {
	public abstract void insert(MemTrackBusVO MemTrackBusBean);
	public abstract void update(MemTrackBusVO MemTrackBusBean);
	public abstract void delete(Integer busfollowId);
	public abstract MemTrackBusVO findByPrimaryKey(Integer busfollowId);
	public abstract List<MemTrackBusVO> getAll();
	public abstract List<MemTrackBusVO> getAllByMemberId(Integer memberId);
}
