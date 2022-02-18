package com.memtrackbus.model;

import java.util.List;

public class MemTrackBusService {
	private MemTrackBusDAO_interface dao;

	public MemTrackBusService() {
		dao = new MemTrackBusDAO();
	}
	
	//Integer busfollowId, Integer memberId, Integer busId
	public MemTrackBusVO insertMemTrackBus(Integer memberId, Integer busId) {
		MemTrackBusVO memTrackBusBean = new MemTrackBusVO();
		
		memTrackBusBean.setMemberId(memberId);
		memTrackBusBean.setBusId(busId);
		
		dao.insert(memTrackBusBean);
		return memTrackBusBean;
	}
	
	public MemTrackBusVO updateMemTrackBus(Integer busfollowId, Integer memberId, Integer busId) {
		MemTrackBusVO memTrackBusBean = new MemTrackBusVO();
		
		memTrackBusBean.setBusfollowId(busfollowId);
		memTrackBusBean.setMemberId(memberId);
		memTrackBusBean.setBusId(busId);
		
		dao.update(memTrackBusBean);
		return memTrackBusBean;
	}
	
	public void deleteMemTrackBus(Integer busfollowId) {
		dao.delete(busfollowId);
	}

	public MemTrackBusVO findByPrimaryKey(Integer busfollowId) {
		return dao.findByPrimaryKey(busfollowId);
	}

	public List<MemTrackBusVO> getAll() {
		return dao.getAll();
	}
	
	public List<MemTrackBusVO> getAllByMemberId(Integer memberId) {
		return dao.getAllByMemberId(memberId);
	}
}
