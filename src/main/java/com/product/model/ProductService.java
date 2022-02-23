package com.product.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.productImg.model.ProductImgVO;

public class ProductService {
	
	private ProductDAO_interface dao;
	
	public ProductService() {
		dao = new ProductJDBCDAO();
	}
	
	public ProductVO addPro(Integer busid, String name, Integer price, Integer stock, Date shelfDate, 
			Integer status, String description, String shippingMethod, String mainCategory, String subCategory) {
		
		ProductVO proVO = new ProductVO();
		
		proVO.setBusid(busid);
		proVO.setName(name);
		proVO.setPrice(price);
		proVO.setStock(stock);
		proVO.setShelfDate(shelfDate);
		proVO.setStatus(status);
		proVO.setDescription(description);
		proVO.setShippingMethod(shippingMethod);
		proVO.setMainCategory(mainCategory);
		proVO.setSubCategory(subCategory);
		dao.insert(proVO);
		
		return proVO;
	}
	
	public ProductVO updatePro(Integer merid, Integer busid, String name, Integer price, Integer stock, Date shelfDate, 
			Integer status, String description, String shippingMethod, String mainCategory, String subCategory) {
		
		ProductVO proVO = new ProductVO();
		
		proVO.setMerid(merid);
		proVO.setBusid(busid);
		proVO.setName(name);
		proVO.setPrice(price);
		proVO.setStock(stock);
		proVO.setShelfDate(shelfDate);
		proVO.setStatus(status);
		proVO.setDescription(description);
		proVO.setShippingMethod(shippingMethod);
		proVO.setMainCategory(mainCategory);
		proVO.setSubCategory(subCategory);
		dao.update(proVO);
		
		return proVO;
	}
	
	
//	public void addProduct(ProductVO productVO) {
//		dao.insert(productVO);
//	}

//	public void updateProduct(ProductVO productVO) {
//		dao.update(productVO);
//	}
	
	public void deleteProduct(Integer merid) {
		dao.delete(merid);
	}
	
	public ProductVO getOneProduct(Integer merid) {
		return dao.findByPrimaryKey(merid);
	}
	
	public List<ProductVO> getAll(){
		return dao.getAll();
	}
	
	public Set<ProductImgVO> getImgsByImgno(Integer merid) {
		return dao.getImgsByImgno(merid);
	}

}
