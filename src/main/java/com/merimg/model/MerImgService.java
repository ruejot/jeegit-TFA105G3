package com.merimg.model;

import java.util.List;

import com.product.model.ProductVO;

public class MerImgService {
	private MerImgDAO_interface dao;
	
	public MerImgService() {
		dao =new MerImgJDBCDAO();
	}

	public MerImgVO insertPicture(Integer merid, String picname, byte[] picture) {
		MerImgVO temp = new MerImgVO();
		temp.setMerid(merid);
		temp.setPicname(picname);
		temp.setMerpic(picture);
		dao.insert(temp);
		return temp;
	}
	
	public MerImgVO queryPicByPicname(String picname) {
		return dao.queryByName(picname);
	}
	
	public MerImgVO queryPicByImgid(Integer imgid) {
		return dao.queryByImgid(imgid);
	}
	
	public List<MerImgVO> getImgIdByMerId(Integer merid){
		return dao.getAllByMerid(merid);
	}
}
