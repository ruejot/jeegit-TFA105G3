package com.memsavedart.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;


public class MemSavedArtService {

	private MemSavedArtDAO_interface dao;

	public MemSavedArtService() {
		dao = new MemSavedArtDAO();
	}
	
	public List<MemSavedArtVO> getAll() {		
		return dao.getAll();
	}

	public MemSavedArtVO findByPrimaryKey(Integer savId) {
		return dao.findByPrimaryKey(savId);
	}

	public Set<MemSavedArtVO> getArtByMemberId(Integer savMemberId) {
		return dao.getArtByMemberId(savMemberId);
	}

	public MemSavedArtVO addSaved(Integer savMemberId, Integer savArtId,
			Timestamp time) {
		MemSavedArtVO memSavedArtVO = new MemSavedArtVO();

		memSavedArtVO.setSavMemberId(savMemberId);
		memSavedArtVO.setSavArtId(savArtId);
		memSavedArtVO.setTime(time);
		dao.insert(memSavedArtVO);

		return memSavedArtVO;
	}
	
	
	public MemSavedArtVO updateSaved(Integer savMemberId, Integer savArtId,
			Timestamp time,Integer savId) {
		MemSavedArtVO memSavedArtVO = new MemSavedArtVO();

		memSavedArtVO.setSavId(savId);
		memSavedArtVO.setSavMemberId(savMemberId);
		memSavedArtVO.setSavArtId(savArtId);
		memSavedArtVO.setTime(time);
		dao.update(memSavedArtVO);

		return memSavedArtVO;
	}
	
	public void delete(Integer savId) {
		dao.delete(savId);
	}
}
