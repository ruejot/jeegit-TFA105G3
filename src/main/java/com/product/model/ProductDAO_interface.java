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
	public abstract List<ProductVO> getAllByProdid(Integer merid);
	public abstract List<ProductVO> getAllByProductName(String name);
	public abstract List<ProductVO> getAllByMainCategory(String maincategory);
	//查詢某商品照片(一對多)(回傳 Set)
	public Set<ProductImgVO> getImgsByImgno(Integer merid);
	public abstract List<ProductVO> getAllbyV_MerPro();

}
