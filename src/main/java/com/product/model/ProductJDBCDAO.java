package com.product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.productImg.model.ProductImgVO;

public class ProductJDBCDAO implements ProductDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO MER (BUS_ID, name, price, stock, SHELF_Date, status, description, SHIPPING_METHOD, MAIN_CATEGORY, SUB_CATEGORY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT  MER_ID, BUS_ID, name, price, stock, SHELF_Date, status, description, SHIPPING_METHOD, MAIN_CATEGORY, SUB_CATEGORY FROM MER order by MER_ID";
	private static final String GET_ONE_STMT = "SELECT MER_ID, BUS_ID, name, price, stock, SHELF_Date, status, description, SHIPPING_METHOD, MAIN_CATEGORY, SUB_CATEGORY FROM MER where MER_ID = ?";
	private static final String DELETE_IMGs = "DELETE FROM MER_IMG where MER_ID = ?";
	private static final String DELETE_MER = "DELETE FROM mer where MER_ID = ?";
	private static final String UPDATE = "UPDATE mer set BUS_ID=?, name=?, price=?, stock=?, SHELF_Date=?, status=?, description=?, SHIPPING_METHOD=?, MAIN_CATEGORY=?, SUB_CATEGORY=? where MER_ID = ?";
	private static final String GET_Imgs_ByMerid_STMT = "SELECT IMG_ID, MER_PIC, time, MER_ID FROM MER_IMG where MER_ID = ? order by IMG_ID";
	private static final String FIND_AllbyMerid = "SELECT * FROM pet_g3db_tfa105.v_MERIMG_MER WHERE MER_ID =?";
	private static final String FIND_AllbyMerName = "SELECT * FROM pet_g3db_tfa105.v_MERIMG_MER WHERE MER_NAME= ? ";

	@Override
	public void insert(ProductVO productVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void delete(Integer merid) {
		int updateCount_IMGs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_IMGs);
			pstmt.setInt(1, merid);
			updateCount_IMGs = pstmt.executeUpdate();		
		
			pstmt = con.prepareStatement(DELETE_MER);
			pstmt.setInt(1, merid);
			pstmt.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			System.out.println("刪除商品時" + merid + "時,共有圖片" + updateCount_IMGs + "同時被刪除!");

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, merid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setMerid(rs.getInt("merid"));
				productVO.setBusid(rs.getInt("busid"));
				productVO.setName(rs.getString("name"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setStock(rs.getInt("stock"));
				productVO.setShelfDate(rs.getDate("shelfDate"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setDescription(rs.getString("description"));
				productVO.setShippingMethod(rs.getString("shippingMethod"));
				productVO.setMainCategory(rs.getString("mainCategory"));
				productVO.setSubCategory(rs.getString("subCategory"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setMerid(rs.getInt("merid"));
				productVO.setBusid(rs.getInt("busid"));
				productVO.setName(rs.getString("name"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setStock(rs.getInt("stock"));
				productVO.setShelfDate(rs.getDate("shelfDate"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setDescription(rs.getString("description"));
				productVO.setShippingMethod(rs.getString("shippingMethod"));
				productVO.setMainCategory(rs.getString("mainCategory"));
				productVO.setSubCategory(rs.getString("subCategory"));
				list.add(productVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public Set<ProductVO> getImgsByImgno(Integer merid) {
		Set<ProductVO> set = new LinkedHashSet<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_AllbyMerid);
			pstmt.setInt(1, merid);
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
				set.add(productVO); // Store the row in the vector
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public static void main(String[] args) {

		ProductJDBCDAO dao = new ProductJDBCDAO();
		//新增
		ProductVO productVO1 = new ProductVO();
		productVO1.setBusid(1);
		productVO1.setName("HAPPYPUPPY");
		productVO1.setPrice(350);
		productVO1.setStock(15);
		productVO1.setShelfDate(java.sql.Date.valueOf("2022-02-03"));
		productVO1.setStatus(1);
		productVO1.setDescription("HAPPYPUPPY");
		productVO1.setShippingMethod("100");
		productVO1.setMainCategory("FOOD");
		productVO1.setSubCategory("FOOD");
		dao.insert(productVO1);
		
		//修改
//		ProductVO productVO2 = new ProductVO();
//		productVO2.setMerid(2);
//		productVO2.setBusid(2);
//		productVO2.setName("HERO-MAMA");
//		productVO2.setPrice(450);
//		productVO2.setStock(30);
//		productVO2.setShelfDate(java.sql.Date.valueOf("2022-01-20"));
//		productVO2.setStatus(3);
//		productVO2.setDescription("HERO-MAMA");
//		productVO2.setShippingMethod("111");
//		productVO2.setMainCategory("FOOD");
//		productVO2.setSubCategory("FOOD");
//		dao.update(productVO2);
//		
//		//刪除
//		dao.delete(3);
//
//		//查詢
//		ProductVO productVO3 = dao.findByPrimaryKey(1);
//		System.out.print(productVO3.getMerid() + ",");
//		System.out.print(productVO3.getBusid() + ",");
//		System.out.print(productVO3.getName() + ",");
//		System.out.print(productVO3.getPrice() + ",");
//		System.out.print(productVO3.getStock() + ",");
//		System.out.print(productVO3.getShelfDate() + ",");
//		System.out.println(productVO3.getStatus() + ",");
//		System.out.println(productVO3.getDescription() + ",");
//		System.out.println(productVO3.getShippingMethod() + ",");
//		System.out.println(productVO3.getMainCategory() + ",");
//		System.out.println(productVO3.getSubCategory() +",");
//		System.out.println("---------------------");
//
//		List<ProductVO> list = dao.getAll();
//		for (ProductVO aPro : list) {
//			System.out.print(aPro.getMerid() + ",");
//			System.out.print(aPro.getBusid() + ",");
//			System.out.print(aPro.getName() + ",");
//			System.out.print(aPro.getPrice() + ",");
//			System.out.print(aPro.getStock() + ",");
//			System.out.print(aPro.getShelfDate() + ",");
//			System.out.println(aPro.getStatus() + ",");
//			System.out.println(aPro.getDescription() + ",");
//			System.out.println(aPro.getShippingMethod() + ",");
//			System.out.println(aPro.getMainCategory() + ",");
//			System.out.println(aPro.getSubCategory() +",");
//			
//			System.out.println();
//		}
//
//		Set<ProductImgVO> set = dao.getImgsByImgno(1);
//		for (ProductImgVO aImg : set) {
//			System.out.print(aImg.getImgid() + ",");
//			System.out.print(aImg.getMerpic() + ",");
//			System.out.print(aImg.getTime() + ",");
//			System.out.print(aImg.getMerid() + ",");
//			System.out.println();
//		}
	}

	@Override
	public ProductVO queryByImgid(Integer imgid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVO> getAllByMerid(Integer merid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVO> getAllByProductName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVO> getAllbyV_MerPro() {
		// TODO Auto-generated method stub
		return null;
	}

}
