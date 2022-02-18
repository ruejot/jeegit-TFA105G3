package com.memsavedart.model;

import java.util.*;

public interface MemSavedArtDAO_interface {
	      public void insert(MemSavedArtVO memSavedArtVO);
          public void update(MemSavedArtVO memSavedArtVO);
          public void delete(Integer savId);
          public MemSavedArtVO findByPrimaryKey(Integer savId);
	      public List<MemSavedArtVO> getAll();
	      //查詢某會員收藏的文章(一對多)(回傳 Set)
	      public Set<MemSavedArtVO> getArtByMemberId(Integer memSavedArtVO);
}
