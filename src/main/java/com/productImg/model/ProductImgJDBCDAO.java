package com.productImg.model;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductImgJDBCDAO implements ProductImgDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO MER_IMG (MER_ID, MER_PIC, TIME) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT IMG_ID, MER_ID, MER_PIC, TIME FROM MER_IMG ORDER BY IMG_ID";
	private static final String GET_ONE_STMT = "SELECT IMG_ID, MER_ID, MER_PIC, TIME FROM MER_IMG WHERE IMG_ID = ?";
	private static final String DELETE = "DELETE FROM MER_IMG where MER_ID = ?";
	private static final String UPDATE = "UPDATE MER_IMG set MER_ID=?, MER_PIC=?, TIME=? where IMG_ID = ?";

//	@Override
//	public void insert(ProductImgVO productImgVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, productImgVO.getMerid());
//			pstmt.setBytes(2, productImgVO.getMerpic());
//			pstmt.setDate(3, productImgVO.getTime());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

//	@Override
//	public void update(ProductImgVO productImgVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, productImgVO.getMerid());
//			pstmt.setBytes(2, productImgVO.getMerpic());
//			pstmt.setDate(3, productImgVO.getTime());
//			pstmt.setInt(4, productImgVO.getImgid());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}

	@Override
	public void delete(Integer imgid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, imgid);

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
	public ProductImgVO findByPrimaryKey(Integer imgid) {

		ProductImgVO productImgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, imgid);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				productImgVO = new ProductImgVO();
				productImgVO.setImgid(rs.getInt("IMG_ID"));
				productImgVO.setMerid(rs.getInt("MER_ID"));
				productImgVO.setMerpic(rs.getBytes("MER_PIC"));
				productImgVO.setTime(rs.getDate("TIME"));
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
		return productImgVO;
	}

	@Override
	public List<ProductImgVO> getAll() {

		List<ProductImgVO> list = new ArrayList<ProductImgVO>();
		ProductImgVO productImgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productImgVO = new ProductImgVO();
				productImgVO.setImgid(rs.getInt("IMG_ID"));
				productImgVO.setMerid(rs.getInt("MER_ID"));
				productImgVO.setMerpic(rs.getBytes("MER_PIC"));
				productImgVO.setTime(rs.getDate("TIME"));
				list.add(productImgVO); // Store the row in the list
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

	public static void main(String[] args) throws Exception {

		ProductImgJDBCDAO dao = new ProductImgJDBCDAO();

		// 新增
//		String path = "C:/Tibame-Web Project";
//		File input1 = new File(path + "/1.jpg");
//		int length1 = (int) input1.length();
//		byte[] photo1 = new byte[length1];
//		FileInputStream fis1 = new FileInputStream(input1);
//		fis1.read(photo1);
//		fis1.close();
//
//		ProductImgVO productImgVO1 = new ProductImgVO();
//		productImgVO1.setMerid(2);
//		productImgVO1.setMerpic(photo1);
//		productImgVO1.setTime(java.sql.Date.valueOf("2022-02-03"));
//		dao.insert(productImgVO1);
//
//		// 修改
//		ProductImgVO productImgVO2 = new ProductImgVO();
//		productImgVO2.setMerid(2);
//		productImgVO2.setMerpic(photo1);
//		productImgVO2.setTime(java.sql.Date.valueOf("2022-02-10"));
//		productImgVO2.setImgid(1);
//		dao.update(productImgVO2);

		// 刪除
//		dao.delete(2);

		// 查詢
		ProductImgVO productImgVO3 = dao.findByPrimaryKey(3);
		System.out.print(productImgVO3.getImgid() + ",");
		System.out.print(productImgVO3.getMerid() + ",");
		System.out.print(productImgVO3.getMerpic() + ",");
		System.out.print(productImgVO3.getTime() + ",");
		System.out.println("---------------------");

		List<ProductImgVO> list = dao.getAll();
		for (ProductImgVO aImg : list) {
			System.out.print(aImg.getImgid() + ",");
			System.out.print(aImg.getMerid() + ",");
			System.out.print(aImg.getMerpic() + ",");
			System.out.print(aImg.getTime() + ",");
			System.out.println();
		}
	}

	@Override
	public List<ProductImgVO> getAllByProdid(Integer prodid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(ProductImgVO productImgVO, Connection con) {

		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, productImgVO.getMerid());
			pstmt.setBytes(2, productImgVO.getMerpic());
			pstmt.setDate(3, productImgVO.getTime());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			if (con != null) {
				try {
					// 設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-ProductImg");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(ProductImgVO productImgVO, Connection con) {
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, productImgVO.getMerid());
			pstmt.setBytes(2, productImgVO.getMerpic());
			pstmt.setDate(3, productImgVO.getTime());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			if (con != null) {
				try {
					// 設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-ProductImg");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

//	@Override
//	public void delete(ProductImgVO productImgVO, Connection con) {
//
//		PreparedStatement pstmt = null;
//
//		try {
//			pstmt = con.prepareStatement(DELETE);
//			pstmt.setInt(1, productImgVO.getMerid());
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			if (con != null) {
//				try {
//					// 設定於當有exception發生時之catch區塊內
//					System.err.print("Transaction is being ");
//					System.err.println("rolled back-由-ProductImg");
//					con.rollback();
//				} catch (SQLException excep) {
//					throw new RuntimeException("rollback error occured. " + excep.getMessage());
//				}
//			}
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace();
//				}
//			}
//		}
//	}

}
