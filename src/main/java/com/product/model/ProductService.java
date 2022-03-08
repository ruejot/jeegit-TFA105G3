package com.product.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.productImg.model.ProductImgVO;

public class ProductService {
	
	private ProductDAO_interface dao;
	
	public ProductService() {
		dao = new ProductDAO();
	}
	
	public ProductVO addPro(Integer busid, String name, Integer price, Integer stock, Date shelfDate, 
			Integer status, String description, String shippingMethod, String mainCategory, String subCategory, List<ProductImgVO> list) {
		
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
		
		dao.insertWithProductImg(proVO, list);
		
		return proVO;
	}
	
//	public ProductVO updatePro(Integer merid, Integer busid, String name, Integer price, Integer stock, Date shelfDate, 
//			Integer status, String description, String shippingMethod, String mainCategory, String subCategory, ProductImgVO proImgVO) {
//		
//		ProductVO proVO = new ProductVO();
//		
//		proVO.setMerid(merid);
//		proVO.setBusid(busid);
//		proVO.setName(name);
//		proVO.setPrice(price);
//		proVO.setStock(stock);
//		proVO.setShelfDate(shelfDate);
//		proVO.setStatus(status);
//		proVO.setDescription(description);
//		proVO.setShippingMethod(shippingMethod);
//		proVO.setMainCategory(mainCategory);
//		proVO.setSubCategory(subCategory);
//		dao.updateWithProductImg(proVO, proImgVO);
//		
//		return proVO;
//	}
	
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
	
	public void updateMerStockQty(Integer qty, Integer merid) {
		dao.updateMerStockQty(qty, merid);
	}
	
	public ProductVO getOneProduct(Integer merid) {
		return dao.findByPrimaryKey(merid);
	}
	
	public List<ProductVO> getAll(){
		return dao.getAll();
	}
	
	public Set<ProductImgVO> getImgsByMerid(Integer merid) {
		return dao.getImgsByMerid(merid);
	}

	public List<ProductVO> getAllByProdId(Integer merid) {
		if (merid != null) {
			return dao.getAllByProdid(merid);
		} else
			return null;
	}
	public List<ProductVO> getAllByProductId(Integer prodid) {
		if(prodid != null) {
			return dao.getAllByProdid(prodid);
		} else
			return null;
	}

	public List<ProductVO> getAllbyV_MerPro() {
		return dao.getAllbyV_MerPro();
	}

	public List<ProductVO> getAllByProductName(String name) {
		if (name == null || name.trim().length() == 0) {
			return null;
		}
		return dao.getAllByProductName(name);
	}

	public List<ProductVO> getSpecialClassByMainCategory(String maincategory){
		return dao.getAllByMainCategory(maincategory);
	}
	
	public List<ProductVO> getProductsByBusid(Integer busId) {
		return dao.getProductByBusid(busId);
	}
	
}
