package com.product.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.productImg.model.ProductImgVO;

public class ProductDAO implements ProductDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TFA105G3TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO MER (BUS_ID, name, price, stock, SHELF_Date, status, description, SHIPPING_METHOD, MAIN_CATEGORY, SUB_CATEGORY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT  MER_ID, BUS_ID, name, price, stock, SHELF_Date, status, description, SHIPPING_METHOD, MAIN_CATEGORY, SUB_CATEGORY FROM MER order by MER_ID";
	private static final String GET_ONE_STMT = "SELECT MER_ID, BUS_ID, name, price, stock, SHELF_Date, status, description, SHIPPING_METHOD, MAIN_CATEGORY, SUB_CATEGORY FROM MER where MER_ID = ?";
	private static final String DELETE_IMGs = "DELETE FROM MER_IMG where MER_ID = ?";
	private static final String DELETE_MER = "DELETE FROM mer where MER_ID = ?";
	private static final String UPDATE = "UPDATE mer set BUS_ID=?, name=?, price=?, stock=?, SHELF_Date=?, status=?, description=?, SHIPPING_METHOD=?, MAIN_CATEGORY=?, SUB_CATEGORY=? where MER_ID = ?";
	private static final String GET_Imgs_ByMerid_STMT = "SELECT IMG_ID, MER_PIC, time, MER_ID FROM MER_IMG where MER_ID = ?";
	private static final String GET_ALL_By_vMerPro = "SELECT * FROM pet_g3db_tfa105.v_merimg_mer";
	private static final String FIND_AllbyMerid = "SELECT * FROM pet_g3db_tfa105.v_MERIMG_MER WHERE MER_ID= ?";
	private static final String FIND_AllbyMerName = "SELECT * FROM pet_g3db_tfa105.v_MERIMG_MER WHERE MER_NAME like ? ";
	private static final String FIND_AllbyMainCategory = "SELECT * FROM pet_g3db_tfa105.v_MERIMG_MER WHERE Main_Category like ? ";
	private static final String FIND_AllbySubCategory = "SELECT * FROM pet_g3db_tfa105.v_MERIMG_MER WHERE Sub_Category = ? ";

	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productVO.getBusid());
			pstmt.setString(2, productVO.getName());
			pstmt.setInt(3, productVO.getPrice());
			pstmt.setInt(4, productVO.getStock());
			pstmt.setDate(5, productVO.getShelfDate());
			pstmt.setInt(6, productVO.getStatus());
			pstmt.setString(7, productVO.getDescription());
			pstmt.setString(8, productVO.getShippingMethod());
			pstmt.setString(9, productVO.getMainCategory());
			pstmt.setString(10, productVO.getSubCategory());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	}

	@Override
	public void update(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, productVO.getBusid());
			pstmt.setString(2, productVO.getName());
			pstmt.setInt(3, productVO.getPrice());
			pstmt.setInt(4, productVO.getStock());
			pstmt.setDate(5, productVO.getShelfDate());
			pstmt.setInt(6, productVO.getStatus());
			pstmt.setString(7, productVO.getDescription());
			pstmt.setString(8, productVO.getShippingMethod());
			pstmt.setString(9, productVO.getMainCategory());
			pstmt.setString(10, productVO.getSubCategory());
			pstmt.setInt(11, productVO.getMerid());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	}

	@Override
	public void delete(Integer merid) {
		int updateCount_IMGs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();

			// 設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);

			// 先刪除照片
			pstmt = con.prepareStatement(DELETE_IMGs);
			pstmt.setInt(1, merid);
			updateCount_IMGs = pstmt.executeUpdate();
			// 再刪除商品
			pstmt = con.prepareStatement(DELETE_MER);
			pstmt.setInt(1, merid);
			pstmt.executeUpdate();

			// 設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	}

	@Override
	public ProductVO findByPrimaryKey(Integer merid) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, merid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setMerid(rs.getInt("mer_id"));
				productVO.setBusid(rs.getInt("bus_id"));
				productVO.setName(rs.getString("name"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setStock(rs.getInt("stock"));
				productVO.setShelfDate(rs.getDate("shelf_Date"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setDescription(rs.getString("description"));
				productVO.setShippingMethod(rs.getString("shipping_Method"));
				productVO.setMainCategory(rs.getString("main_Category"));
				productVO.setSubCategory(rs.getString("sub_Category"));
			}

			// Handle any driver errors
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
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {

		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setMerid(rs.getInt("mer_id"));
				productVO.setBusid(rs.getInt("bus_id"));
				productVO.setName(rs.getString("name"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setStock(rs.getInt("stock"));
				productVO.setShelfDate(rs.getDate("shelf_Date"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setDescription(rs.getString("description"));
				productVO.setShippingMethod(rs.getString("shipping_Method"));
				productVO.setMainCategory(rs.getString("main_Category"));
				productVO.setSubCategory(rs.getString("sub_Category"));
				list.add(productVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public Set<ProductImgVO> getImgsByImgno(Integer merid) {
		Set<ProductImgVO> set = new LinkedHashSet<ProductImgVO>();
		ProductImgVO productImgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Imgs_ByMerid_STMT);
			pstmt.setInt(1, merid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productImgVO = new ProductImgVO();
				productImgVO.setImgid(rs.getInt("IMG_ID"));
				productImgVO.setMerpic(rs.getBytes("MER_PIC"));
				productImgVO.setTime(rs.getDate("TIME"));
				productImgVO.setMerid(rs.getInt("MER_ID"));
				set.add(productImgVO); // Store the row in the vector
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return set;
	}

	@Override
	public ProductVO queryByImgid(Integer imgid) {
		return null;
	}

	@Override
	public List<ProductVO> getAllByProdid(Integer prodid) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_AllbyMerid);
			pstmt.setInt(1, prodid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setImgid(rs.getInt("img_id"));
				productVO.setMerid(rs.getInt("mer_id"));
				productVO.setBusid(rs.getInt("bus_id"));
				productVO.setName(rs.getString("mer_name"));
				productVO.setPicname(rs.getString("pic_name"));
				productVO.setMerpic(rs.getBytes("mer_pic"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setStock(rs.getInt("stock"));
				productVO.setMainCategory(rs.getString("main_category"));
				productVO.setSubCategory(rs.getString("sub_category"));
				list.add(productVO);
			}
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
	public List<ProductVO> getAllByProductName(String name) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_AllbyMerName);
			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setImgid(rs.getInt("img_id"));
				productVO.setMerid(rs.getInt("mer_id"));
				productVO.setBusid(rs.getInt("bus_id"));
				productVO.setName(rs.getString("mer_name"));
				productVO.setPicname(rs.getString("pic_name"));
				productVO.setMerpic(rs.getBytes("mer_pic"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setStock(rs.getInt("stock"));
				productVO.setMainCategory(rs.getString("main_category"));
				productVO.setSubCategory(rs.getString("sub_category"));
				list.add(productVO); // Store the row in the list
			}
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
	public List<ProductVO> getAllbyV_MerPro() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_By_vMerPro);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setImgid(rs.getInt("img_id"));
				productVO.setMerid(rs.getInt("mer_id"));
				productVO.setBusid(rs.getInt("bus_id"));
				productVO.setName(rs.getString("mer_name"));
				productVO.setPicname(rs.getString("pic_name"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setStock(rs.getInt("stock"));
				productVO.setMainCategory(rs.getString("main_Category"));
				productVO.setSubCategory(rs.getString("sub_Category"));
				productVO.setMerpic(rs.getBytes("mer_pic"));
				list.add(productVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public List<ProductVO> getAllByMainCategory(String maincategory) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_AllbyMainCategory);
			pstmt.setString(1, "%" + maincategory + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setImgid(rs.getInt("img_id"));
				productVO.setMerid(rs.getInt("mer_id"));
				productVO.setBusid(rs.getInt("bus_id"));
				productVO.setName(rs.getString("mer_name"));
				productVO.setPicname(rs.getString("pic_name"));
				productVO.setMerpic(rs.getBytes("mer_pic"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setStock(rs.getInt("stock"));
				productVO.setMainCategory(rs.getString("main_category"));
				productVO.setSubCategory(rs.getString("sub_category"));
				list.add(productVO); // Store the row in the list
			}
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
	public List<ProductVO> getAllBySubCategory(String subcategory) {
		// TODO Auto-generated method stub
		return null;
	}

	
}