package com.memreply.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.memsavedart.model.MemSavedArtVO;

public class MemReplyService {

	private MemReplyDAO_interface dao;

	public MemReplyService() {
		dao = new MemReplyDAO();
	}

	public List<MemReplyVO> getAll() {
		return dao.getAll();
	}

	public MemReplyVO findByPrimaryKey(Integer reId) {
		return dao.findByPrimaryKey(reId);
	}

	public Set<MemReplyVO> getReByArtId(Integer reArtId) {
		return dao.getReByArtId(reArtId);
	}
	
	public MemReplyVO addRe(Integer reArtId, Integer reMemberId,
			String re,Timestamp time) {
		MemReplyVO memReplyVO = new MemReplyVO();

		memReplyVO.setReArtId(reArtId);
		memReplyVO.setReMemberId(reMemberId);
		memReplyVO.setRe(re);
		memReplyVO.setTime(time);
		dao.insert(memReplyVO);

		return memReplyVO;
	}
	
	
	public MemReplyVO updateRe(String re,Timestamp time,Integer reArtId) {
		MemReplyVO memReplyVO = new MemReplyVO();
		memReplyVO.setRe(re);
		memReplyVO.setTime(time);
		memReplyVO.setReId(reArtId);
		dao.update(memReplyVO);

		return memReplyVO;
	}
	
	

	public void delete(Integer reId) {
		dao.delete(reId);
	}
}
