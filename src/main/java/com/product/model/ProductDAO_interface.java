package com.product.model;

import java.util.List;
import java.util.Set;

import com.productImg.model.ProductImgVO;

public interface ProductDAO_interface {
	public abstract void insert(ProductVO productVO);
	public abstract void update(ProductVO productVO);
	public abstract void delete(Integer merid);
	public abstract ProductVO findByPrimaryKey(Integer merid);
	public abstract List<ProductVO> getAll();
	public abstract ProductVO queryByImgid (Integer imgid);
	public abstract List<ProductVO> getAllByMerid(Integer merid);
	public abstract List<ProductVO> getAllByProductName(String name);
	//查詢某商品照片(一對多)(回傳 Set)
	public Set<ProductVO> getImgsByImgno(Integer merid);

}
