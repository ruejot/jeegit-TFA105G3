package com.product.model;

import java.util.List;
import java.util.Set;

import com.productImg.model.ProductImgVO;

public interface ProductDAO_interface {

	public void insert(ProductVO productVO);

	public void update(ProductVO productVO);

	public void delete(Integer merid);

	public ProductVO findByPrimaryKey(Integer merid);

	public List<ProductVO> getAll();
	
	//查詢某商品照片(一對多)(回傳 Set)
	public Set<ProductImgVO> getImgsByImgno(Integer merid);

}
