package com.merimg.model;

import java.util.List;

import com.product.model.ProductVO;

public interface MerImgDAO_interface {
	public abstract void insert (MerImgVO merImgVO);
	public abstract void update (MerImgVO merImgVO);
	public abstract MerImgVO queryByName (String mername);
	public abstract MerImgVO queryByImgid (Integer imgid);
	public abstract List<MerImgVO> getAll();
	public abstract List<MerImgVO> getAllByMerid(Integer merid);
}
