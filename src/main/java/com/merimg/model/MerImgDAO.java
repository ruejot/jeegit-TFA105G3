package com.merimg.model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.product.model.ProductJDBCDAO;
import com.product.model.ProductVO;

public class MerImgDAO implements MerImgDAO_interface {

	private static DataSource ds= null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TFA105G3TestDB");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT_STMT = "INSERT INTO pet_g3db_tfa105.MER_IMG(MER_ID, PIC_NAME, MER_PIC) VALUES (?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE pet_g3db_tfa105.MER_IMG SET MER_PIC = ? WHERE PIC_NAME = ? and MER_ID = ?";
	private static final String FIND_BY_PIC_NAME= "SELECT * FROM pet_g3db_tfa105.MER_IMG WHERE PIC_NAME = ? ";
	private static final String FIND_BY_IMG_ID = "SELECT * FROM pet_g3db_tfa105.MER_IMG WHERE IMG_ID = ?";
	private static final String FIND_All= "SELECT * FROM pet_g3db_tfa105.MER_IMG";
	private static final String FIND_AllbyMerid= "SELECT * FROM pet_g3db_tfa105.MER_IMG WHERE MER_ID =?";
	
	@Override
	public void insert(MerImgVO merImgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, merImgVO.getMerid());
			pstmt.setString(2, merImgVO.getPicname());
//			byte[] pic = setPictureByteArray();
//			pstmt.setBytes(3, pic);
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null ) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}
	@Override
	public void update(MerImgVO merImgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setBytes(1, merImgVO.getMerpic());
			pstmt.setString(2, merImgVO.getPicname());
			pstmt.setInt(3, merImgVO.getMerid());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (con != null ) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("null")
	@Override
	public MerImgVO queryByName(String picname) {

		MerImgVO temp = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	try {
		con =ds.getConnection();
		pstmt = con.prepareStatement(FIND_BY_PIC_NAME);
	
		pstmt.setString(1, picname);
		
		rs = pstmt.executeQuery();
		int i = 1;
		while(rs.next()) {
			temp = new MerImgVO();
			temp.setImgid(rs.getInt("IMG_ID"));
			temp.setMerid(rs.getInt("MER_ID"));
			temp.setPicname(picname);
			Blob blob = rs.getBlob("MER_PIC");
			byte[] buf = blob.getBytes(1, (int)blob.length());
			temp.setMerpic(buf);
		}
		
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
		return temp;
	}
	@Override
	public MerImgVO queryByImgid(Integer imgid) {
		
		MerImgVO temp = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
	try {
		con =ds.getConnection();
		pstmt = con.prepareStatement(FIND_BY_IMG_ID);
	
		pstmt.setInt(1,	imgid);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			temp = new MerImgVO();
			temp.setImgid(imgid);
			temp.setPicname(rs.getString("PIC_NAME"));
			temp.setMerid(rs.getInt("MER_ID"));
			temp.setMerpic(rs.getBytes("MER_PIC"));
		}
		
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
		// Clean up JDBC resources
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
		return temp;
	}
	public static byte[] setPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	@Override
	public List<MerImgVO> getAll() {
		List<MerImgVO> list = new ArrayList<MerImgVO>();
		MerImgVO merImgVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_All);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				merImgVO = new MerImgVO();
				merImgVO.setImgid(rs.getInt("img_id"));
				merImgVO.setMerid(rs.getInt("mer_id"));
				merImgVO.setPicname(rs.getString("pic_name"));
				merImgVO.setMerpic(rs.getBytes("mer_pic"));
				list.add(merImgVO); // Store the row in the list
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<MerImgVO> getAllByMerid(Integer merid) {
		List<MerImgVO> list = new ArrayList<MerImgVO>();
		MerImgVO merImgVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_AllbyMerid);
			pstmt.setInt(1, merid);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				merImgVO = new MerImgVO();
				merImgVO.setImgid(rs.getInt("img_id"));
				merImgVO.setMerid(rs.getInt("mer_id"));
				merImgVO.setPicname(rs.getString("pic_name"));
				merImgVO.setMerpic(rs.getBytes("mer_pic"));
				list.add(merImgVO); // Store the row in the list
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	
	public static void main(String[] args)  {
		int i = 2;
	MerImgDAO dao = new MerImgDAO(); 
		MerImgVO m =dao.queryByName("超級球");
		System.out.println(m.getPicname());
		System.out.println(m.getImgid());
		System.out.println(m.getMerid());
		System.out.println(m.getMerpic());
		
//		FileOutputStream outPutStream = new
//		         FileOutputStream("C:\\TFA105_WebApp\\eclipse_WTP_workspace1\\TFA105G3\\src\\main\\webapp\\assets\\imgs\\blob_output"+i+".jpg");
//		         outPutStream.write(m.getMerpic());
//		         outPutStream.flush();
//		         System.out.println("C:\\TFA105_WebApp\\eclipse_WTP_workspace1\\TFA105G3\\src\\main\\webapp\\assets\\imgs\\blob_output"+i+".jpg");
//	
		List<MerImgVO> list = dao.getAllByMerid(8);
		for (MerImgVO bean : list) {
			System.out.print(bean.getImgid() + ",");
			System.out.print(bean.getMerid() + ",");
			System.out.print(bean.getPicname() + ",");
			System.out.println("\n-----------------");
		}
	}
	
	
}
