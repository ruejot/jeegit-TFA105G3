package com.productImg.model;

import java.util.List;

public interface ProductImgDAO_interface {

	//public void insert(ProductImgVO productImgVO);
	public void update(ProductImgVO productImgVO);
	public void delete(Integer imgid);
	public ProductImgVO findByPrimaryKey(Integer imgid);
	public List<ProductImgVO> getAll();
	public List<ProductImgVO> getAllByProdid(Integer prodid);
	public void insert(ProductImgVO productImgVO, java.sql.Connection con);

}
