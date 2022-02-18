package com.memblogart.model;

import java.util.*;

public interface MemBlogArtDAO_interface {
	      public void insert(MemBlogArtVO MemBlogArtVO);
          public void update(MemBlogArtVO MemBlogArtVO);
          public void delete(Integer artid);
          public MemBlogArtVO findByPrimaryKey(Integer artid);
	      public List<MemBlogArtVO> getAll();
	      public List<MemBlogArtVO> getAllByMember(Integer memberId);
	      //查詢某會員的文章(一對多)(回傳 Set)
	      public Set<MemBlogArtVO> getTimeByMemberId(Integer MemBlogArtVO);


}
