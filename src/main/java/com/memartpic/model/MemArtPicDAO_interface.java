package com.memartpic.model;

import java.util.*;

public interface MemArtPicDAO_interface {
	      public void insert(MemArtPicVO memArtPicVO);
          public void update(MemArtPicVO memArtPicVO);
          public void delete(Integer blArtPicId);
          public MemArtPicVO findByPrimaryKey(Integer blArtPicId);
	      public List<MemArtPicVO> getAll();
	      //查詢某會員收藏的文章(一對多)(回傳 Set)
	      public Set<MemArtPicVO> getPicByArtId(Integer memArtPicVO);
}
