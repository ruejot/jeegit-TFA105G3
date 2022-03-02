package com.productImg.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductImgDAO implements ProductImgDAO_interface {

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

	private static final String INSERT_STMT = "INSERT INTO MER_IMG (MER_ID, MER_PIC, TIME) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT IMG_ID, MER_ID, MER_PIC, TIME FROM MER_IMG ORDER BY IMG_ID";
	private static final String GET_ONE_STMT = "SELECT IMG_ID, MER_ID, MER_PIC, TIME FROM MER_IMG WHERE MER_ID = ?";
	private static final String DELETE = "DELETE FROM MER_IMG where IMG_ID = ?";
	private static final String UPDATE = "UPDATE MER_IMG set MER_ID=?, MER_PIC=?, TIME=? where IMG_ID = ?";
	private static final String FIND_AllbyProdid = "SELECT * FROM pet_g3db_tfa105.MER_IMG WHERE MER_ID =?";

//	@Override
//	public void insert(ProductImgVO productImgVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, productImgVO.getMerid());
//			pstmt.setBytes(2, productImgVO.getMerpic());
//			pstmt.setDate(3, productImgVO.getTime());
//
//			pstmt.executeUpdate();
//
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
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, imgid);

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
	public ProductImgVO findByPrimaryKey(Integer merid) {

		ProductImgVO productImgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, merid);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				productImgVO = new ProductImgVO();
				productImgVO.setImgid(rs.getInt("IMG_ID"));
				productImgVO.setMerid(rs.getInt("MER_ID"));
				productImgVO.setMerpic(rs.getBytes("MER_PIC"));
				productImgVO.setTime(rs.getDate("TIME"));

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

			con = ds.getConnection();
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
	public List<ProductImgVO> getAllByProdid(Integer prodid) {
		List<ProductImgVO> list = new ArrayList<ProductImgVO>();
		ProductImgVO ProductImgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_AllbyProdid);
			pstmt.setInt(1, prodid);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ProductImgVO = new ProductImgVO();
				ProductImgVO.setImgid(rs.getInt("img_id"));
				ProductImgVO.setMerid(rs.getInt("mer_id"));
				ProductImgVO.setPicname(rs.getString("pic_name"));
				ProductImgVO.setMerpic(rs.getBytes("mer_pic"));
				list.add(ProductImgVO); // Store the row in the list
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
			pstmt.setInt(4, productImgVO.getImgid());

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
//
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, productImgVO.getImgid());
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
