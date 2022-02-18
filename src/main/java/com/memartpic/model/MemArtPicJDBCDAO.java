package com.memartpic.model;

import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class MemArtPicJDBCDAO implements MemArtPicDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "5525";

	private static final String INSERT_STMT = "INSERT INTO MEM_ART_PIC (BL_ART_ID,BL_ART_PIC,TIME) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT BL_ARTPIC_ID,BL_ART_ID,BL_ART_PIC,TIME FROM MEM_ART_PIC";
	private static final String GET_ONE_STMT = "SELECT BL_ARTPIC_ID,BL_ART_ID,BL_ART_PIC,TIME FROM MEM_ART_PIC where BL_ARTPIC_ID = ?";
	private static final String GET_Pic_ByArtId_STMT = "SELECT BL_ARTPIC_ID,BL_ART_ID,BL_ART_PIC,TIME FROM MEM_ART_PIC where BL_ARTPIC_ID = ? order by BL_ART_ID";

	private static final String DELETE = "DELETE FROM MEM_ART_PIC where BL_ARTPIC_ID = ?";
	
	//private static final String DELETE_EMPs = "DELETE FROM emp2 where deptno = ?";
	//private static final String DELETE_DEPT = "DELETE FROM dept2 where deptno = ?";	
	
	private static final String UPDATE = "UPDATE MEM_ART_PIC set BL_ART_PIC=? , TIME=? where BL_ART_ID = ?";

	@Override
	public void insert(MemArtPicVO memArtPicVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memArtPicVO.getBlArtId());
			pstmt.setBytes(2, memArtPicVO.getBlArtPic());	
			pstmt.setTimestamp(3, memArtPicVO.getTime());

pstmt.executeUpdate("set auto_increment_offset=1;");
pstmt.executeUpdate("set auto_increment_increment=1;");
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

	}

	@Override
	public void update(MemArtPicVO memArtPicVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

//			pstmt.setInt(1,memArtPicVO.getBlArtPicId());	
			pstmt.setBytes(1, memArtPicVO.getBlArtPic());
			pstmt.setTimestamp(2, memArtPicVO.getTime());
			pstmt.setInt(3, memArtPicVO.getBlArtId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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

	}

	@Override
	public void delete(Integer blArtPicId) {
		int updateCount_EMPs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, blArtPicId);
			
			updateCount_EMPs = pstmt.executeUpdate();

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
//			System.out.println("刪除部門編號" + deptno + "時,共有員工" + updateCount_EMPs
//					+ "人同時被刪除");
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public MemArtPicVO findByPrimaryKey(Integer blArtPicId) {

		MemArtPicVO memArtPicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, blArtPicId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				memArtPicVO = new MemArtPicVO();
				memArtPicVO.setBlArtPicId(rs.getInt("BL_ARTPIC_ID"));
				memArtPicVO.setBlArtId(rs.getInt("BL_ART_ID"));
				memArtPicVO.setBlArtPic(rs.getBytes("BL_ART_PIC"));
				memArtPicVO.setTime(rs.getTimestamp("TIME"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return memArtPicVO;
	}

	@Override
	public List<MemArtPicVO> getAll() {
		List<MemArtPicVO> list = new ArrayList<MemArtPicVO>();
		MemArtPicVO memArtPicVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memArtPicVO = new MemArtPicVO();
				memArtPicVO.setBlArtPicId(rs.getInt("BL_ARTPIC_ID"));
				memArtPicVO.setBlArtId(rs.getInt("BL_ART_ID"));
				memArtPicVO.setBlArtPic(rs.getBytes("BL_ART_PIC"));
				memArtPicVO.setTime(rs.getTimestamp("TIME"));
				
				list.add(memArtPicVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Set<MemArtPicVO> getPicByArtId(Integer blArtId) {
		Set<MemArtPicVO> set = new LinkedHashSet<MemArtPicVO>();
		MemArtPicVO memArtPicVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Pic_ByArtId_STMT);
			pstmt.setInt(1, blArtId);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				memArtPicVO = new MemArtPicVO();
				memArtPicVO.setBlArtPicId(rs.getInt("BL_ARTPIC_ID"));
				memArtPicVO.setBlArtId(rs.getInt("BL_ART_ID"));
				memArtPicVO.setBlArtPic(rs.getBytes("BL_ART_PIC"));
				memArtPicVO.setTime(rs.getTimestamp("TIME"));
				set.add(memArtPicVO); // Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	
	
	
	public static void main(String[] args) throws IOException {

		MemArtPicService service = new MemArtPicService();
		
		byte[] pic = getPictureByteArray("C:\\TFA105_WebApp\\eclipse_WTP_workspace1\\petting_table_kame01\\src\\main\\webapp\\image\\06.jpg");
		service.addArtPic(3, pic, Timestamp.valueOf(LocalDateTime.now()));
		
		byte[] pic2 = getPictureByteArray("C:\\TFA105_WebApp\\eclipse_WTP_workspace1\\petting_table_kame01\\src\\main\\webapp\\image\\03.jpg");
		service.updateArtPic(1, 7, pic2, Timestamp.valueOf(LocalDateTime.now()));
		
		
		service.delete(3);
		
		MemArtPicVO memArtPicVO3 = service.findByPrimaryKey(4);
		System.out.print(memArtPicVO3.getBlArtPicId() + ",");
		System.out.print(memArtPicVO3.getBlArtId() + ",");
		System.out.print(memArtPicVO3.getBlArtPic() + ",");
		System.out.println(memArtPicVO3.getTime());
		System.out.println("---------------------");
		
		List<MemArtPicVO> list = service.getAll();
		for (MemArtPicVO memArtPicVO4 : list) {
			System.out.print(memArtPicVO4.getBlArtPicId() + ",");
			System.out.print(memArtPicVO4.getBlArtId() + ",");
			System.out.print(memArtPicVO4.getBlArtPic() + ",");
			System.out.println(memArtPicVO4.getTime());
			System.out.println();
		}
		
		// 新增
		
	    //memArtPicVO1.setBlArtPicId(1);
//		memArtPicVO1.setBlArtId(7);
//  	byte[] pic = getPictureByteArray("C:\\TFA105_WebApp\\eclipse_WTP_workspace1\\petting_table_kame01\\src\\main\\webapp\\image\\01.jpg");
//		memArtPicVO1.setBlArtPic(pic);
//		memArtPicVO1.setTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));
//		dao.insert(memArtPicVO1);
//		
//		// 修改
//		MemArtPicVO memArtPicVO2 = new MemArtPicVO();
//		memArtPicVO2.setBlArtPicId(1);
//		memArtPicVO2.setBlArtId(7);
//		byte[] pic2 = getPictureByteArray("C:\\TFA105_WebApp\\eclipse_WTP_workspace1\\petting_table_kame01\\src\\main\\webapp\\image\\03.jpg");
//		memArtPicVO2.setBlArtPic(pic2);
//		memArtPicVO2.setTime(new Timestamp(System.currentTimeMillis()));
//		dao.update(memArtPicVO2);
//
//		// 刪除
//		dao.delete(3);
//
		// 查詢
//		MemArtPicVO memArtPicVO3 = dao.findByPrimaryKey(4);
//		System.out.print(memArtPicVO3.getBlArtPicId() + ",");
//		System.out.print(memArtPicVO3.getBlArtId() + ",");
//		System.out.print(memArtPicVO3.getBlArtPic() + ",");
//		
//		System.out.println(memArtPicVO3.getTime());
//		System.out.println("---------------------");
		
	
		
//
////		// 查詢部門
//		List<MemArtPicVO> list = dao.getAll();
//		for (MemArtPicVO memArtPicVO4 : list) {
//			System.out.print(memArtPicVO4.getBlArtPicId() + ",");
//			System.out.print(memArtPicVO4.getBlArtId() + ",");
//			System.out.print(memArtPicVO4.getBlArtPic() + ",");
//			System.out.println(memArtPicVO4.getTime());
//			System.out.println();
//		}
}
}

