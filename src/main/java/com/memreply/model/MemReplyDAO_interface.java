package com.memreply.model;

import java.util.*;

public interface MemReplyDAO_interface {
	      public void insert(MemReplyVO memReplyVO);
          public void update(MemReplyVO memReplyVO);
          public void delete(Integer reId);
          public MemReplyVO findByPrimaryKey(Integer reArtId);
	      public List<MemReplyVO> getAll();
	      public List<MemReplyVO> getAllByArtId(Integer reArtId);
	      //查詢某會員的文章(一對多)(回傳 Set)
	      public Set<MemReplyVO> getReByArtId(Integer memReplyVO);
}
