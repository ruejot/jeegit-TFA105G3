package com.memsavedart.model;

import java.util.*;

public interface MemSavedArtDAO_interface {
	      public void insert(MemSavedArtVO memSavedArtVO);
          public void update(MemSavedArtVO memSavedArtVO);
          public void delete(Integer savMemberId,Integer savArtId);
          public MemSavedArtVO findByPrimaryKey(Integer savId);
          public MemSavedArtVO ifSaved(Integer savMemberId,Integer savArtId);
	      public List<MemSavedArtVO> getAll();
	      public List<MemSavedArtVO> getArtBySavMem(Integer savMemberId);
	      //查詢某會員收藏的文章(一對多)(回傳 Set)
//	      public Set<MemSavedArtVO> getArtByMemberId(Integer memSavedArtVO);
}
