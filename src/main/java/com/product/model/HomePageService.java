package com.product.model;

import java.util.List;

public class HomePageService {

	private ProductDAO_interface dao;

	public HomePageService() {
		dao = new ProductDAO();
	}

	public Integer getCountsBySubCategory(String sub) {
		List<ProductVO> list = dao.getAll();
		int sublist = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSubCategory().equals(sub)) {
				sublist++;
//				System.out.print(list.get(i).getName() + ",");
//				System.out.println(list.get(i).getSubCategory());
			}
		}
//		System.out.println("count="+ sublist);
		return sublist;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dao == null) ? 0 : dao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HomePageService other = (HomePageService) obj;
		if (dao == null) {
			if (other.dao != null)
				return false;
		} else if (!dao.equals(other.dao))
			return false;
		return true;
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
}
