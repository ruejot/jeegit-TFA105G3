package com.memblogart.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.memreply.model.MemReplyVO;

public class MemBlogArtService {

	private MemBlogArtDAO_interface dao;

	public MemBlogArtService() {
		dao = new MemBlogArtJDBCDAO();
	}

	public List<MemBlogArtVO> getAll() {
		return dao.getAll();
	}

	public MemBlogArtVO findByPrimaryKey(Integer artid) {
		return dao.findByPrimaryKey(artid);
	}

	public Set<MemBlogArtVO> getTimeByMemberId(Integer memberId) {
		return dao.getTimeByMemberId(memberId);
	}

	public MemBlogArtVO addBlog(Integer memberId, String title,
			Timestamp posttime,String content) {
		MemBlogArtVO memBlogArtVO = new MemBlogArtVO();

		memBlogArtVO.setMemberId(memberId);
		memBlogArtVO.setTitle(title);
		memBlogArtVO.setPosttime(posttime);
		memBlogArtVO.setContent(content);
		dao.insert(memBlogArtVO);

		return memBlogArtVO;
	}
	
	
	public MemBlogArtVO updateBlog(String title,
			Timestamp posttime,String content,Integer artid) {
		MemBlogArtVO memBlogArtVO = new MemBlogArtVO();
		
		memBlogArtVO.setArtid(artid);
		memBlogArtVO.setTitle(title);
		memBlogArtVO.setPosttime(posttime);
		memBlogArtVO.setContent(content);
		
		dao.update(memBlogArtVO);

		return memBlogArtVO;
	}
	
	
	public void delete(Integer artid) {
		dao.delete(artid);
	}

	public List<MemBlogArtVO> getAllByMember(Integer memberId) {
		return dao.getAllByMember(memberId);
	}
	
	public List<MemBlogArtVO> getAllByArtId(Integer artId) {
		return dao.getAllByMember(artId);
	}
}
