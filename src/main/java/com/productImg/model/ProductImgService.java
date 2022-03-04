package com.productImg.model;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class ProductImgService {
	
private ProductImgDAO_interface dao;
	
	public ProductImgService() {
		dao = new ProductImgDAO();
	}
	
//		public void addProductImg(ProductImgVO productImgVO) {
//			dao.insert(productImgVO);
//		}
	
		public ProductImgVO addProductImg(Integer merid, byte[] merpic, Date time, Connection con) {
			ProductImgVO proImgVO = new ProductImgVO();
			proImgVO.setMerid(merid);
			proImgVO.setMerpic(merpic);
			proImgVO.setTime(time);
			dao.insert(proImgVO, con);
			
			return proImgVO;
		}
		
//		public void updateProductImg(ProductImgVO productImgVO) {
//			dao.update(productImgVO);
//		}
		
		public ProductImgVO updateProductImg(Integer imgid, Integer merid, byte[] merpic, Date time, Connection con) {
			ProductImgVO proImgVO = new ProductImgVO();
			proImgVO.setImgid(imgid);
			proImgVO.setMerid(merid);
			proImgVO.setMerpic(merpic);
			proImgVO.setTime(time);
			dao.update(proImgVO, con);
			
			return proImgVO;
		}
		
		public void deleteProductImg(Integer imgid) {	
			dao.delete(imgid);
		}
		
		public ProductImgVO getOneProductImg(Integer merid) {
			return dao.findByPrimaryKey(merid);
		}
		
		public List<ProductImgVO> getAll(){
			return dao.getAll();
		}
		
		
		
		
}
