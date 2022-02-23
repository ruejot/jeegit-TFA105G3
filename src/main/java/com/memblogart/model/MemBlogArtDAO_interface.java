package com.memblogart.model;

import java.util.*;

public interface MemBlogArtDAO_interface {
	      public int insert(MemBlogArtVO MemBlogArtVO);
          public void update(MemBlogArtVO MemBlogArtVO);
          public void delete(Integer artid);
          public MemBlogArtVO findByPrimaryKey(Integer artid);
	      public List<MemBlogArtVO> getAll();
	      public List<MemBlogArtVO> getAllByMember(Integer memberId);
	      public List<MemBlogArtVO> getAllByArtId(Integer artId);
	      //查詢某會員的文章(一對多)(回傳 Set)
	      public Set<MemBlogArtVO> getTimeByMemberId(Integer MemBlogArtVO);



}
