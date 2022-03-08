package com.product.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.productImg.model.ProductImgDAO_interface;

public class HomePageService {

	private ProductDAO_interface dao;

	public HomePageService() {
		dao = new ProductDAO();
	}

	public Integer getCountsBySubCategory(String sub) {
		List<ProductVO> list = dao.getAll();
		int subcounts = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSubCategory().equals(sub)) {
				subcounts++;
//				System.out.print(list.get(i).getName() + ",");
//				System.out.println(list.get(i).getSubCategory());
			}
		}
//		System.out.println("count="+ sublist);
		return subcounts;
	}

	public Integer getCountsByMainCategory(String main) {
		List<ProductVO> list = dao.getAll();
		int maincounts = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMainCategory().equals(main)) {
				maincounts++;
//				System.out.print(list.get(i).getName() + ",");
//				System.out.println(list.get(i).getSubCategory());
			}
		}
//		System.out.println("count="+ sublist);
		return maincounts;
	}

	public Integer getCountsBySearchBox(String text) {
		List<ProductVO> list = dao.getAllByProductName(text);
		int counts = 0;
		for (int i = 0; i < list.size(); i++) {
			counts++;
		}
		return counts;
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}

	public List<ProductVO> getAllByProductId(Integer prodid) {
		if (prodid != null) {
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
	

	public List<ProductVO> getSpecialClassByMainCategory(String maincategory) {
		return dao.getAllByMainCategory(maincategory);
	}

	public List<ProductVO> getSubCategoryName(String subcategory) {
		List<ProductVO> list = dao.getAll();
		List<ProductVO> sublist = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSubCategory().equals(subcategory)) {
				productVO = new ProductVO();
				productVO.setMerid(list.get(i).getMerid());
				productVO.setBusid(list.get(i).getBusid());
				productVO.setName(list.get(i).getName());
				productVO.setPrice(list.get(i).getPrice());
				productVO.setStock(list.get(i).getStock());
				productVO.setMainCategory(list.get(i).getMainCategory());
				productVO.setSubCategory(list.get(i).getSubCategory());
				sublist.add(productVO);
			}
		}
		return sublist;
	}
}
