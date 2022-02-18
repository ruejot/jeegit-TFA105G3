package com.memfollow.model;

import java.util.List;

public class MemFollowService {
	private MemFollowDAO_interface dao;

	public MemFollowService() {
		dao = new MemFollowDAO();
	}
	
//Integer friendshipId, Integer memberId, Integer followee, String friendship
	public MemFollowVO insertMemFollow(Integer memberId
			, Integer followee, String friendship) {
		MemFollowVO memFollowBean = new MemFollowVO();
		
		memFollowBean.setMemberId(memberId);
		memFollowBean.setFollowee(followee);
		memFollowBean.setFriendship(friendship);
		
		dao.insert(memFollowBean);
		return memFollowBean;
	}
	
	public MemFollowVO updateMemFollow(Integer friendshipId, Integer memberId
			, Integer followee, String friendship) {
		MemFollowVO memFollowBean = new MemFollowVO();
		
		memFollowBean.setFriendshipId(friendshipId);
		memFollowBean.setMemberId(memberId);
		memFollowBean.setFollowee(followee);
		memFollowBean.setFriendship(friendship);
		
		dao.update(memFollowBean);
		return memFollowBean;
	}
	
	public void deleteMemFollow(Integer friendshipId) {
		dao.delete(friendshipId);
	}

	public MemFollowVO findByPrimaryKey(Integer friendshipId) {
		return dao.findByPrimaryKey(friendshipId);
	}

	public List<MemFollowVO> getAll() {
		return dao.getAll();
	}
	
	public List<MemFollowVO> getAllByMemberId(Integer memberId) {
		return dao.getAllByMemberId(memberId);
	}
}
