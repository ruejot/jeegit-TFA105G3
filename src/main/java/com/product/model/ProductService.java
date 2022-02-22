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

	public void addProduct(ProductVO productVO) {
		dao.insert(productVO);
	}

	public ProductVO addPro(Integer busid, String name, Integer price, Integer stock, Date shelfDate, Integer status,
			String description, String shippingMethod, String mainCategory, String subCategory) {
	
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

	public ProductVO updateProduct(ProductVO productVO) {
		ProductVO product = new ProductVO();
		product.setMerid(productVO.getMerid());
		product.setName(productVO.getName());
		product.setPrice(productVO.getPrice());
		product.setShelfDate(productVO.getShelfDate());
		product.setStock(productVO.getStock());
		product.setStatus(productVO.getStatus());
		product.setMainCategory(productVO.getMainCategory());
		dao.update(productVO);
		return product;
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

	public void deleteProduct(Integer porddid) {
		dao.delete(porddid);
	}

	public ProductVO getOneProduct(Integer pordid) {
		return dao.findByPrimaryKey(pordid);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}

	public Set<ProductImgVO> getImgsByImgno(Integer pordid) {
		return dao.getImgsByImgno(pordid);
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


}
