package com.memheart.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.memreply.model.MemReplyVO;

public class MemHeartService {

	private MemHeartDAO_interface dao;

	public MemHeartService() {
		dao = new MemHeartJDBCDAO();
	}

	public List<MemHeartVO> getAll() {
		return dao.getAll();
	}

	public MemHeartVO findByPrimaryKey(Integer heArtId, Integer heMemberId) {
		return dao.findByPrimaryKey(heArtId,heMemberId);
	}

	public Set<MemHeartVO> getCountByMember(Integer heMemberId) {
		return dao.getCountByMember(heMemberId);
	}
	
	public MemHeartVO addHeart(Integer heArtId, Integer heMemberId,
			Timestamp time) {
		MemHeartVO memHeartVO = new MemHeartVO();

		memHeartVO.setHeArtId(heArtId);
		memHeartVO.setHeMemberId(heMemberId);
		memHeartVO.setTime(time);
		dao.insert(memHeartVO);

		return memHeartVO;
	}
	
	
	public MemHeartVO updateHeart(Integer heMemberId,Timestamp time,Integer heArtId) {
		MemHeartVO memHeartVO = new MemHeartVO();
		memHeartVO.setHeArtId(heArtId);
		memHeartVO.setHeMemberId(heMemberId);
		memHeartVO.setTime(time);
		dao.insert(memHeartVO);
		return memHeartVO;
	}
	

	public void delete(Integer heArtId,Integer heMemberId) {
		dao.delete(heArtId,heMemberId);
	}
	
}
