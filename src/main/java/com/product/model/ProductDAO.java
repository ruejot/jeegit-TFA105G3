package com.product.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.order.model.OrderVO;
import com.productImg.model.ProductImgDAO;
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

	private static final String INSERT_STMT = "INSERT INTO pet_g3db_tfa105.MER (BUS_ID, NAME, PRICE, STOCK, SHELF_DATE, STATUS, DESCRIPTION, SHIPPING_METHOD, MAIN_CATEGORY, SUB_CATEGORY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT  MER_ID, BUS_ID, NAME, PRICE, STOCK, SHELF_DATE, STATUS, DESCRIPTION, SHIPPING_METHOD, MAIN_CATEGORY, SUB_CATEGORY FROM pet_g3db_tfa105.MER ORDER BY MER_ID";
	private static final String GET_ONE_STMT = "SELECT MER_ID, BUS_ID, NAME, PRICE, STOCK, SHELF_DATE, STATUS, DESCRIPTION, SHIPPING_METHOD, MAIN_CATEGORY, SUB_CATEGORY FROM pet_g3db_tfa105.MER WHERE MER_ID = ?";
	private static final String DELETE_IMGs = "DELETE FROM pet_g3db_tfa105.MER_IMG WHERE MER_ID = ?";
	private static final String DELETE_MER = "DELETE FROM pet_g3db_tfa105.MER where MER_ID = ?";
	private static final String UPDATE = "UPDATE pet_g3db_tfa105.MER SET BUS_ID=?, NAME=?, PRICE=?, STOCK=?, SHELF_DATE=?, STATUS=?, DESCRIPTION=?, SHIPPING_METHOD=?, MAIN_CATEGORY=?, SUB_CATEGORY=? WHERE MER_ID = ?";
	private static final String GET_Imgs_ByMerid_STMT = "SELECT IMG_ID, MER_PIC, TIME, MER_ID FROM pet_g3db_tfa105.MER_IMG WHERE MER_ID = ?";
	private static final String GET_ALL_By_vMerPro = "SELECT * FROM pet_g3db_tfa105.v_merimg_mer";
	private static final String FIND_AllbyMerid = "SELECT * FROM pet_g3db_tfa105.v_MERIMG_MER WHERE MER_ID= ?";
	private static final String FIND_AllbyMerName = "SELECT * FROM pet_g3db_tfa105.MER WHERE MER_NAME like ? ";
	private static final String FIND_AllbyMainCategory = "SELECT * FROM pet_g3db_tfa105.MER WHERE Main_Category like ? ";
	private static final String FIND_AllbySubCategory = "SELECT * FROM pet_g3db_tfa105.v_MERIMG_MER WHERE Sub_Category = ? ";
	private static final String GET_PRODUCTS_BY_BUSID = "SELECT * FROM pet_g3db_tfa105.MER WHERE BUS_ID = ? ORDER BY MER_ID DESC";
	
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
	public Set<ProductImgVO> getImgsByMerid(Integer merid) {
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
				productVO.setMerid(rs.getInt("mer_id"));
				productVO.setBusid(rs.getInt("bus_id"));
				productVO.setName(rs.getString("name"));
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
				productVO.setMerid(rs.getInt("mer_id"));
				productVO.setBusid(rs.getInt("bus_id"));
				productVO.setName(rs.getString("name"));
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

	@Override
	public void insertWithProductImg(ProductVO productVO, List<ProductImgVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();

			// 設定於pstmt.executeUpdate()之前
			con.setAutoCommit(false);

			// 先新增商品主檔
			String cols[] = { "MER_ID" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);

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

			// 獲取對應的新增主鍵值
			String next_merId = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_merId = rs.getString(1);
				System.out.println("自增主鍵值= " + next_merId + "(剛新增成功的商品編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();

			// 再同時新增照片
			ProductImgDAO dao = new ProductImgDAO();
			System.out.println("list.size()-A=" + list.size());
			for (ProductImgVO addImg : list) {
				addImg.setMerid(Integer.parseInt(next_merId));
				dao.insert(addImg, con);
			}
			// 設定於pstmt.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B=" + list.size());
			System.out.println("新增商品編號" + next_merId + "時,共有" + list.size() + "筆圖片同時被新增");

		} catch (SQLException se) {
			if (con != null) {
				try {
					// 設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-order");
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
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}
	
	


	@Override
	public List<ProductVO> getProductByBusid(Integer busid) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PRODUCTS_BY_BUSID);
			
			pstmt.setInt(1, busid);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				productVO = new ProductVO();
				productVO.setMerid(rs.getInt("MER_ID"));
				productVO.setBusid(rs.getInt("BUS_ID"));
				productVO.setName(rs.getString("NAME"));
				productVO.setPrice(rs.getInt("PRICE"));
				productVO.setStock(rs.getInt("STOCK"));
				productVO.setShelfDate(rs.getDate("SHELF_DATE"));
				productVO.setStatus(rs.getInt("STATUS"));
				productVO.setDescription(rs.getString("DESCRIPTION"));
				productVO.setShippingMethod(rs.getString("SHIPPING_METHOD"));
				productVO.setMainCategory(rs.getString("MAIN_CATEGORY"));
				productVO.setSubCategory(rs.getString("SUB_CATEGORY"));
				list.add(productVO); // Store the row in the list
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
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
		return list;
	}

	@Override
	public void updateWithProductImg(ProductVO productVO, List<ProductImgVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();

			// 設定於pstmt.executeUpdate()之前
			con.setAutoCommit(false);

			// 先更新商品主檔
			String cols[] = { "MER_ID" };
			pstmt = con.prepareStatement(UPDATE, cols);

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

			// 獲取對應的新增主鍵值
			String next_merId = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_merId = rs.getString(1);
				System.out.println("自增主鍵值= " + next_merId + "(剛更新成功的商品編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();

			// 再同時更新照片
			ProductImgDAO dao = new ProductImgDAO();
			System.out.println("list.size()-A=" + list.size());
			for (ProductImgVO addImg : list) {
				addImg.setMerid(Integer.parseInt(next_merId));
				dao.insert(addImg, con);
			}
			// 設定於pstmt.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B=" + list.size());
			System.out.println("更新商品編號" + next_merId + "時,共有" + list.size() + "筆圖片同時被更新");

		} catch (SQLException se) {
			if (con != null) {
				try {
					// 設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-order");
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
					se.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
		
	}
	
}


