package com.product.model;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.productImg.model.ProductImgDAO;
import com.productImg.model.ProductImgVO;

public class ProductJDBCDAO implements ProductDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String INSERT_STMT = "INSERT INTO pet_g3db_tfa105.MER (BUS_ID, NAME, PRICE, STOCK, SHELF_DATE, STATUS, DESCRIPTION, SHIPPING_METHOD, MAIN_CATEGORY, SUB_CATEGORY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT  MER_ID, BUS_ID, NAME, PRICE, STOCK, SHELF_DATE, STATUS, DESCRIPTION, SHIPPING_METHOD, MAIN_CATEGORY, SUB_CATEGORY FROM pet_g3db_tfa105.MER ORDER BY MER_ID";
	private static final String GET_ONE_STMT = "SELECT MER_ID, BUS_ID, name, price, stock, SHELF_Date, status, description, SHIPPING_METHOD, MAIN_CATEGORY, SUB_CATEGORY FROM pet_g3db_tfa105.MER WHERE MER_ID = ?";
	private static final String DELETE_IMGs = "DELETE FROM MER_IMG where MER_ID = ?";
	private static final String DELETE_MER = "DELETE FROM mer where MER_ID = ?";
	private static final String UPDATE = "UPDATE pet_g3db_tfa105.MER SET BUS_ID=?, NAME=?, PRICE=?, STOCK=?, SHELF_DATE=?, STATUS=?, DESCRIPTION=?, SHIPPING_METHOD=?, MAIN_CATEGORY=?, SUB_CATEGORY=? WHERE MER_ID = ?";
	private static final String GET_Imgs_ByMerid_STMT = "SELECT IMG_ID, MER_PIC, TIME, MER_ID FROM MER_IMG where MER_ID = ? ORDER BY IMG_ID";
	private static final String FIND_AllbyMerid = "SELECT * FROM pet_g3db_tfa105.v_MERIMG_MER WHERE MER_ID =?";
	private static final String FIND_AllbyMerName = "SELECT * FROM pet_g3db_tfa105.v_MERIMG_MER WHERE MER_NAME like ? ";

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
				productVO.setMerid(rs.getInt("MER_ID"));
				productVO.setBusid(rs.getInt("BUS_ID"));
				productVO.setName(rs.getString("name"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setStock(rs.getInt("stock"));
				productVO.setShelfDate(rs.getDate("SHELF_Date"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setDescription(rs.getString("description"));
				productVO.setShippingMethod(rs.getString("SHIPPING_METHOD"));
				productVO.setMainCategory(rs.getString("MAIN_CATEGORY"));
				productVO.setSubCategory(rs.getString("SUB_CATEGORY"));
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
				productVO.setMerid(rs.getInt("MER_ID"));
				productVO.setBusid(rs.getInt("BUS_ID"));
				productVO.setName(rs.getString("name"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setStock(rs.getInt("stock"));
				productVO.setShelfDate(rs.getDate("SHELF_Date"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setDescription(rs.getString("description"));
				productVO.setShippingMethod(rs.getString("SHIPPING_METHOD"));
				productVO.setMainCategory(rs.getString("MAIN_CATEGORY"));
				productVO.setSubCategory(rs.getString("SUB_CATEGORY"));
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
	public Set<ProductImgVO> getImgsByMerid(Integer merid) {
		Set<ProductImgVO> set = new LinkedHashSet<ProductImgVO>();
		ProductImgVO productImgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	@Override
	public List<ProductVO> getAllByProductName(String name) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_AllbyMerName);
			pstmt.setString(1, "%" + name + "%");
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
	public List<ProductVO> getAllbyV_MerPro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductVO queryByImgid(Integer imgid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductVO> getAllByProdid(Integer merid) {
		// TODO Auto-generated method stub
		return null;
	}

public static void main(String[] args) throws Exception{
	
	//新增商品同時新增照片
	ProductJDBCDAO dao = new ProductJDBCDAO();
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
	
	String path = "C:/Tibame-Web Project";
	File input1 = new File(path + "/1.jpg");
	int length1 = (int) input1.length();
	byte[] photo1 = new byte[length1];
	FileInputStream fis1 = new FileInputStream(input1);
	fis1.read(photo1);
	fis1.close();
	
	String path1 = "C:/Tibame-Web Project";
	File input2 = new File(path1 + "/2.png");
	int length2 = (int) input2.length();
	byte[] photo2 = new byte[length2];
	FileInputStream fis2 = new FileInputStream(input2);
	fis2.read(photo2);
	fis2.close();
	
	
	List<ProductImgVO> list = new ArrayList<ProductImgVO>();

	ProductImgVO productImgVO1 = new ProductImgVO();
	productImgVO1.setMerpic(photo1);
	productImgVO1.setTime(java.sql.Date.valueOf("2022-02-03"));
	
	ProductImgVO productImgVO2 = new ProductImgVO();
	productImgVO2.setMerpic(photo2);
	productImgVO2.setTime(java.sql.Date.valueOf("2022-02-03"));
	
	list.add(productImgVO1);
	list.add(productImgVO2);
	
	dao.insertWithProductImg(productVO1, list);

	
//	
//	ProductVO productVO2 = new ProductVO();
//	productVO2.setMerid(2);
//	productVO2.setBusid(2);
//	productVO2.setName("HERO-MAMA");
//	productVO2.setPrice(450);
//	productVO2.setStock(30);
//	productVO2.setShelfDate(java.sql.Date.valueOf("2022-01-20"));
//	productVO2.setStatus(3);
//	productVO2.setDescription("HERO-MAMA");
//	productVO2.setShippingMethod("111");
//	productVO2.setMainCategory("FOOD");
//	productVO2.setSubCategory("FOOD");
//	dao.update(productVO2);
//	
//
//	dao.delete(3);
//
//	
//	ProductVO productVO3 = dao.findByPrimaryKey(2);
//	System.out.print(productVO3.getMerid() + ",");
//	System.out.print(productVO3.getBusid() + ",");
//	System.out.print(productVO3.getName() + ",");
//	System.out.print(productVO3.getPrice() + ",");
//	System.out.print(productVO3.getStock() + ",");
//	System.out.print(productVO3.getShelfDate() + ",");
//	System.out.println(productVO3.getStatus() + ",");
//	System.out.println(productVO3.getDescription() + ",");
//	System.out.println(productVO3.getShippingMethod() + ",");
//	System.out.println(productVO3.getMainCategory() + ",");
//	System.out.println(productVO3.getSubCategory() +",");
//	System.out.println("---------------------");
	
//	List<ProductVO> list = dao.getAll();
//	int count = 0;
//	for (int i = 0; i < list.size(); i++) {
//		if (list.get(i).getSubCategory().equals("貓犬飼料")) {
//			count++;
//			System.out.print(list.get(i).getSubCategory() + ",");
//			System.out.println(i);
//		}
//	}
//	System.out.println("count="+ count);
//}
//	
//		System.out.print(aPro.getName() + ",");
//		System.out.print(aPro.getPrice() + ",");
//		System.out.print(aPro.getStock() + ",");
//		System.out.print(aPro.getShelfDate() + ",");
//		System.out.println(aPro.getStatus() + ",");
//		System.out.println(aPro.getDescription() + ",");
//		System.out.println(aPro.getShippingMethod() + ",");
//		System.out.println(aPro.getMainCategory() + ",");
//		System.out.println(aPro.getSubCategory() +",");
	
//	Set<ProductImgVO> set = dao.getImgsByImgno(1);
//	for (ProductImgVO aImg : set) {
//		System.out.print(aImg.getImgid() + ",");
//		System.out.print(aImg.getMerpic() + ",");
//		System.out.print(aImg.getTime() + ",");
//		System.out.print(aImg.getMerid() + ",");
//		System.out.println();
	}

@Override
public List<ProductVO> getAllByMainCategory(String maincategory) {
	// TODO Auto-generated method stub
	return null;
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
		
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd);
		
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
		
		//先

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
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
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
	// TODO Auto-generated method stub
	return null;
}


//@Override
//public void updateWithProductImg(ProductVO productVO, List<ProductImgVO> list) {
//	Connection con = null;
//	PreparedStatement pstmt = null;
//
//	try {
//		
//		Class.forName(driver);
//		con = DriverManager.getConnection(url, userid, passwd);
//		
//		// 設定於pstmt.executeUpdate()之前
//		con.setAutoCommit(false);
//
//		// 先更改商品主檔
//		String cols[] = { "MER_ID" };
//		pstmt = con.prepareStatement(UPDATE, cols);
//
//		pstmt.setInt(1, productVO.getBusid());
//		pstmt.setString(2, productVO.getName());
//		pstmt.setInt(3, productVO.getPrice());
//		pstmt.setInt(4, productVO.getStock());
//		pstmt.setDate(5, productVO.getShelfDate());
//		pstmt.setInt(6, productVO.getStatus());
//		pstmt.setString(7, productVO.getDescription());
//		pstmt.setString(8, productVO.getShippingMethod());
//		pstmt.setString(9, productVO.getMainCategory());
//		pstmt.setString(10, productVO.getSubCategory());
//		pstmt.setInt(11, productVO.getMerid());
//		pstmt.executeUpdate();
//
//		// 獲取對應的修改主鍵值
//		String next_merId = null;
//		ResultSet rs = pstmt.getGeneratedKeys();
//		if (rs.next()) {
//			next_merId = rs.getString(1);
//			System.out.println("自增主鍵值= " + next_merId + "(剛修改成功的商品編號)");
//		} else {
//			System.out.println("未取得自增主鍵值");
//		}
//		rs.close();
//		
//		// 再同時更改照片
//		ProductImgDAO dao1 = new ProductImgDAO();
//		System.out.println("list.size()-for update1=" + list.size());
//		for (ProductImgVO addImg : list) {
//			addImg.setMerid(Integer.parseInt(next_merId));
//			dao1.insert(addImg, con);
//		}
//		// 設定於pstmt.executeUpdate()之後
//		con.commit();
//		con.setAutoCommit(true);
//		System.out.println("list.size()-for update2=" + list.size());
//		System.out.println("更新商品編號" + next_merId + "時,共有" + list.size() + "筆圖片同時被新增");
//
//	} catch (SQLException se) {
//		if (con != null) {
//			try {
//				// 設定於當有exception發生時之catch區塊內
//				System.err.print("Transaction is being ");
//				System.err.println("rolled back-由-product");
//				con.rollback();
//			} catch (SQLException excep) {
//				throw new RuntimeException("rollback error occured. " + excep.getMessage());
//			}
//		}
//		throw new RuntimeException("A database error occured. " + se.getMessage());
//	} catch (ClassNotFoundException e) {
//		e.printStackTrace();
//	} finally {
//		if (pstmt != null) {
//			try {
//				pstmt.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			}
//		}
//		if (con != null) {
//			try {
//				con.close();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			}
//		}
//	}
//}
	
}

