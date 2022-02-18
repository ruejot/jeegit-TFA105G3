package com.productImg.model;

import java.util.List;

public class ProductImgService {
	
private ProductImgDAO_interface dao;
	
	public ProductImgService() {
		dao = new ProductImgDAO();
	}
	
		public void addProductImg(ProductImgVO productImgVO) {
			dao.insert(productImgVO);
		}
		
		public void updateProductImg(ProductImgVO productImgVO) {
			dao.update(productImgVO);
		}
		
		public void deleteProductImg(Integer imgid) {
			dao.delete(imgid);
		}
		
		public ProductImgVO getOneProductImg(Integer imgid) {
			return dao.findByPrimaryKey(imgid);
		}
		
		public List<ProductImgVO> getAll(){
			return dao.getAll();
		}
		
}
