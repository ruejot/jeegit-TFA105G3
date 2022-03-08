package com.memheart.model;

import java.util.*;

public interface MemHeartDAO_interface {
	      public void insert(MemHeartVO MemHeartVO);
          public void update(MemHeartVO MemHeartVO);
          public void delete(Integer artid,Integer heMemberId);
          public MemHeartVO findByPrimaryKey(Integer artid,Integer heMemberId);
	      public List<MemHeartVO> getAll();
	      
	      public MemHeartVO ifLiked(Integer heArtId,Integer heMemberId);
	      //查詢某文章的愛心數(一對多)(回傳 Set)
	      public Set<MemHeartVO> getCountByMember(Integer MemHeartVO);
}
