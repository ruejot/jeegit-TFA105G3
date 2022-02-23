package com.memartpic.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.memblogart.model.MemBlogArtVO;

public class MemArtPicService {

	private MemArtPicDAO_interface dao;

	public MemArtPicService() {
		dao = new MemArtPicJDBCDAO();
	}

	public List<MemArtPicVO> getAll() {
		return dao.getAll();
	}

	public MemArtPicVO findByPrimaryKey(Integer blArtPicId) {
		return dao.findByPrimaryKey(blArtPicId);
	}

	public Set<MemArtPicVO> getPicByArtId(Integer blArtId) {
		return dao.getPicByArtId(blArtId);
	}

	public MemArtPicVO addArtPic(Integer blArtId, byte[] blArtPic,
			Timestamp time) {
		MemArtPicVO memArtPicVO = new MemArtPicVO();

		memArtPicVO.setBlArtId(blArtId);
		memArtPicVO.setBlArtPic(blArtPic);
		memArtPicVO.setTime(time);
		dao.insert(memArtPicVO);

		return memArtPicVO;
	}
	
	
	public MemArtPicVO updateArtPic(Integer blArtPicId, Integer blArtId, byte[] blArtPic,
			Timestamp time) {
		MemArtPicVO memArtPicVO = new MemArtPicVO();

		memArtPicVO.setBlArtPicId(blArtPicId);
		memArtPicVO.setBlArtId(blArtId);
		memArtPicVO.setBlArtPic(blArtPic);
		memArtPicVO.setTime(time);
		dao.insert(memArtPicVO);

		return memArtPicVO;
	}
	
	
	public void delete(Integer blArtPicId) {
		dao.delete(blArtPicId);
	}
}
