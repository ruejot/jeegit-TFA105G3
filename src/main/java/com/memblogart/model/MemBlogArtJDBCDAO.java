package com.memblogart.model;

import java.util.*;
import java.sql.*;


public class MemBlogArtJDBCDAO implements MemBlogArtDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "5525";

	private static final String INSERT_STMT = "INSERT INTO MEM_BLOG_ART (MEMBER_ID,TITLE,POSTTIME,CONTENT) VALUES (?, ?, ?, ?)";

	private static final String GET_ALL_STMT = "SELECT ART_ID,MEMBER_ID,TITLE,POSTTIME,HEART,CONTENT FROM MEM_BLOG_ART";
	private static final String GET_ALL_STMT_ByMemberId = "SELECT ART_ID,MEMBER_ID,TITLE,POSTTIME,HEART,CONTENT FROM MEM_BLOG_ART where MEMBER_ID = ?";

	private static final String GET_ONE_STMT = "SELECT ART_ID,MEMBER_ID,TITLE,POSTTIME,HEART,CONTENT FROM MEM_BLOG_ART where ART_ID = ?";
	private static final String GET_time_ByMemberId_STMT = "SELECT ART_ID,MEMBER_ID,TITLE,POSTTIME,HEART,CONTENT FROM MEM_BLOG_ART where MEMBER_ID = ? order by POSTTIME";

	private static final String DELETE = "DELETE FROM MEM_BLOG_ART where ART_ID = ?";
	
	private static final String DELETE_MEM_SAVED_ART = "DELETE FROM pet_g3db_tfa105.MEM_SAVED_ART where SAV_ART_ID = ?";
	private static final String DELETE_MEM_ART_PIC = "DELETE FROM pet_g3db_tfa105.MEM_SAVED_ART where BL_ART_ID = ?";	
	private static final String DELETE_MEM_HEART = "DELETE FROM pet_g3db_tfa105.MEM_MEM_HEART where HE_ART_ID = ?";	
	private static final String DELETE_MEM_REPLY = "DELETE FROM pet_g3db_tfa105.MEM_REPLY where RE_ART_ID = ?";	
	
	private static final String FIND_LAST_INSERTID= "SELECT LAST_INSERT_ID()";
	private static final String UPDATE = "UPDATE MEM_BLOG_ART set TITLE=? , POSTTIME=? , CONTENT=? where ART_ID = ?";

	@Override
	public void insert(MemBlogArtVO memblogartVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet generatedKeys = null;

		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, memblogartVO.getArtid());
			pstmt.setInt(1, memblogartVO.getMemberId());
			pstmt.setString(2,memblogartVO.getTitle());	
			pstmt.setTimestamp(3, memblogartVO.getPosttime());
			pstmt.setString(4,memblogartVO.getContent());
			
		
			
            pstmt.executeUpdate("set auto_increment_offset=1;");
            pstmt.executeUpdate("set auto_increment_increment=1;");
			pstmt.executeUpdate();
			
			ResultSet rs= pstmt.executeQuery(FIND_LAST_INSERTID);
			int i=0 ;
			while (rs.next()) {
				i = rs.getInt(1);
			}
			System.out.println(i);
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
	public void update(MemBlogArtVO memblogartVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1,memblogartVO.getTitle());	
			pstmt.setTimestamp(2, memblogartVO.getPosttime());
			pstmt.setString(3,memblogartVO.getContent());
			pstmt.setInt(4, memblogartVO.getArtid());

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
	public void delete(Integer artid) {
		int updateCount_EMPs = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
	
			
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, artid);
			
			pstmt.executeUpdate();

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
	public MemBlogArtVO findByPrimaryKey(Integer artid) {

		MemBlogArtVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, artid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				artVO = new MemBlogArtVO();
				artVO.setArtid(rs.getInt("ART_ID"));
				artVO.setMemberId(rs.getInt("MEMBER_ID"));
				artVO.setTitle(rs.getString("TITLE"));
				artVO.setPosttime(rs.getTimestamp("POSTTIME"));
				artVO.setHeart(rs.getInt("HEART"));
				artVO.setContent(rs.getString("CONTENT"));
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
		return artVO;
	}

	@Override
	public List<MemBlogArtVO> getAll() {
		List<MemBlogArtVO> list = new ArrayList<MemBlogArtVO>();
		MemBlogArtVO artVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				artVO = new MemBlogArtVO();
				artVO.setArtid(rs.getInt("ART_ID"));
				artVO.setMemberId(rs.getInt("MEMBER_ID"));
				artVO.setTitle(rs.getString("TITLE"));
				artVO.setPosttime(rs.getTimestamp("POSTTIME"));
				artVO.setHeart(rs.getInt("HEART"));
				artVO.setContent(rs.getString("CONTENT"));
				list.add(artVO); // Store the row in the list
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
	public List<MemBlogArtVO> getAllByMember(Integer memberId) {
		List<MemBlogArtVO> list = new ArrayList<MemBlogArtVO>();
		MemBlogArtVO artVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);		
			pstmt = con.prepareStatement(GET_ALL_STMT_ByMemberId);

			pstmt.setInt(1, memberId);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				artVO = new MemBlogArtVO();
				artVO.setArtid(rs.getInt("ART_ID"));
				artVO.setMemberId(rs.getInt("MEMBER_ID"));
				artVO.setTitle(rs.getString("TITLE"));
				artVO.setPosttime(rs.getTimestamp("POSTTIME"));
				artVO.setHeart(rs.getInt("HEART"));
				artVO.setContent(rs.getString("CONTENT"));
				list.add(artVO); // Store the row in the list
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
	public Set<MemBlogArtVO> getTimeByMemberId(Integer memberId) {
		Set<MemBlogArtVO> set = new LinkedHashSet<MemBlogArtVO>();
		MemBlogArtVO memberVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_time_ByMemberId_STMT);
			pstmt.setInt(1, memberId);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				memberVO = new MemBlogArtVO();
				memberVO.setArtid(rs.getInt("artid"));
				memberVO.setMemberId(rs.getInt("memberId"));
				memberVO.setTitle(rs.getString("title"));
				memberVO.setPosttime(rs.getTimestamp("posttime"));
				memberVO.setHeart(rs.getInt("heart"));
				memberVO.setContent(rs.getString("content"));
				set.add(memberVO); // Store the row in the vector
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

	public static void main(String[] args) {


		MemBlogArtService service = new MemBlogArtService();
		service.addBlog(5, "該餵本汪吃罐罐囉", new Timestamp(System.currentTimeMillis()),"拉不拉多");
//		System.out.println(a);
//		service.updateBlog("天竺鼠車車PUIPUI", new Timestamp(System.currentTimeMillis()), "警察車車",3);
//		service.delete(27);
		
//		MemBlogArtVO memberblogartVO3 = service.findByPrimaryKey(10);
//		System.out.print(memberblogartVO3.getArtid() + ",");
//		System.out.print(memberblogartVO3.getMemberId() + ",");
//		System.out.print(memberblogartVO3.getTitle() + ",");
//		System.out.print(memberblogartVO3.getPosttime() + ",");
//		System.out.print(memberblogartVO3.getHeart() + ",");
//		System.out.println(memberblogartVO3.getContent());
//		System.out.println("---------------------");
//		
//		
//		List<MemBlogArtVO> list = service.getAll();
//		for (MemBlogArtVO memberblogartVO4 : list) {
//			System.out.print(memberblogartVO4.getArtid() + ",");
//			System.out.print(memberblogartVO4.getMemberId() + ",");
//			System.out.print(memberblogartVO4.getTitle() + ",");
//			System.out.print(memberblogartVO4.getPosttime() + ",");
//			System.out.print(memberblogartVO4.getHeart() + ",");
//			System.out.println(memberblogartVO4.getContent());
//			System.out.println();
//		}
//		
//		List<MemBlogArtVO> list = service.getAllByMember(1);
//		for (MemBlogArtVO memberblogartVO5 : list) {
//			System.out.print(memberblogartVO5.getArtid() + ",");
//			System.out.print(memberblogartVO5.getMemberId() + ",");
//			System.out.print(memberblogartVO5.getTitle() + ",");
//			System.out.print(memberblogartVO5.getPosttime() + ",");
//			System.out.print(memberblogartVO5.getHeart() + ",");
//			System.out.println(memberblogartVO5.getContent());
//			System.out.println();
//		}
		
		// 新增
//		MemBlogArtVO memberblogartVO1 = new MemBlogArtVO();
//		
//		memberblogartVO1.setArtid(1);
//		memberblogartVO1.setMemberId(1);
//		memberblogartVO1.setTitle("該餵本汪吃罐罐囉");
//		memberblogartVO1.setPosttime(new Timestamp(System.currentTimeMillis()));
//		memberblogartVO1.setHeart(5);
//		memberblogartVO1.setContent("這隻汪也太可愛<3");
//		dao.insert(memberblogartVO1);

		// 修改
//		MemBlogArtVO memberblogartVO2 = new MemBlogArtVO();
//		//memberblogartVO2.setArtid(1);
//		memberblogartVO2.setMemberId(2);
//		memberblogartVO2.setTitle("我的狗會後空翻");
//		memberblogartVO2.setPosttime(new Timestamp(System.currentTimeMillis()));
//		memberblogartVO2.setHeart(6);
//		memberblogartVO2.setContent("水喔");
//		dao.update(memberblogartVO2);

//		 刪除
//		dao.delete(27);

		// 查詢
//		MemBlogArtVO memberblogartVO3 = dao.findByPrimaryKey(10);
//		System.out.print(memberblogartVO3.getArtid() + ",");
//		System.out.print(memberblogartVO3.getMemberId() + ",");
//		System.out.print(memberblogartVO3.getTitle() + ",");
//		System.out.print(memberblogartVO3.getPosttime() + ",");
//		System.out.print(memberblogartVO3.getHeart() + ",");
//		System.out.println(memberblogartVO3.getContent());
//		System.out.println("---------------------");

		// 查詢部門
//		List<MemBlogArtVO> list = dao.getAll();
//		for (MemBlogArtVO memberblogartVO4 : list) {
//			System.out.print(memberblogartVO4.getArtid() + ",");
//			System.out.print(memberblogartVO4.getMemberId() + ",");
//			System.out.print(memberblogartVO4.getTitle() + ",");
//			System.out.print(memberblogartVO4.getPosttime() + ",");
//			System.out.print(memberblogartVO4.getHeart() + ",");
//			System.out.println(memberblogartVO4.getContent());
//			System.out.println();
//		}
}
}