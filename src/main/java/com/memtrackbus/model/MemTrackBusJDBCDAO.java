package com.memtrackbus.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MemTrackBusJDBCDAO implements MemTrackBusDAO_interface{
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	private static final String userid = "root";
	private static final String passwd = "password";
	
	//BUSFOLLOW_ID, MEMBER_ID, BUS_ID
	private static final String INSERT_STMT = "INSERT INTO MEM_TRACK_BUS (MEMBER_ID, BUS_ID)"
			+ " VALUES (?, ?)";
	private static final String UPDATE = "UPDATE MEM_TRACK_BUS  SET MEMBER_ID=?, BUS_ID=?"
			+ " WHERE BUSFOLLOW_ID=?";
	private static final String DELETE = "DELETE FROM MEM_TRACK_BUS WHERE BUSFOLLOW_ID=?";
	private static final String GET_ONE_STMT = "SELECT BUSFOLLOW_ID, MEMBER_ID, BUS_ID FROM MEM_TRACK_BUS WHERE BUSFOLLOW_ID = ?";
	private static final String GET_ALL_STMT = "SELECT BUSFOLLOW_ID, MEMBER_ID, BUS_ID FROM MEM_TRACK_BUS ORDER BY BUSFOLLOW_ID";
	private static final String GET_ALL_BY_MEMBER_ID_STMT = "SELECT BUSFOLLOW_ID, MEMBER_ID, BUS_ID FROM MEM_TRACK_BUS WHERE MEMBER_ID = ?";
	
	@Override
	public void insert(MemTrackBusVO memFollowBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, memFollowBean.getMemberId());
			pstmt.setInt(2, memFollowBean.getBusId());
			
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
	public void update(MemTrackBusVO memFollowBean) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, memFollowBean.getMemberId());
			pstmt.setInt(2, memFollowBean.getBusId());
			pstmt.setInt(3, memFollowBean.getBusfollowId());
			
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
	public void delete(Integer busfollowId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, busfollowId);

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
	public MemTrackBusVO findByPrimaryKey(Integer busfollowId) {
		MemTrackBusVO memFollowBean = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, busfollowId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memFollowBean = new MemTrackBusVO();
				//BUSFOLLOW_ID, MEMBER_ID, BUS_ID, FRIENDSHIP
				memFollowBean.setBusfollowId(rs.getInt("BUSFOLLOW_ID"));
				memFollowBean.setMemberId(rs.getInt("MEMBER_ID"));
				memFollowBean.setBusId(rs.getInt("BUS_ID"));
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
		
		return memFollowBean;
	}

	@Override
	public List<MemTrackBusVO> getAll() {
		List<MemTrackBusVO> list = new ArrayList<MemTrackBusVO>();
		MemTrackBusVO memFollowBean = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memFollowBean = new MemTrackBusVO();
				
				memFollowBean.setBusfollowId(rs.getInt("BUSFOLLOW_ID"));
				memFollowBean.setMemberId(rs.getInt("MEMBER_ID"));
				memFollowBean.setBusId(rs.getInt("BUS_ID"));
				// 讀取完一筆Bean就存到list，若rs.next()還有再讀取下一個
				list.add(memFollowBean);
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
		
		return list;
	}

	@Override
	public List<MemTrackBusVO> getAllByMemberId(Integer memberId) {
		List<MemTrackBusVO> list = new ArrayList<MemTrackBusVO>();
		MemTrackBusVO memFollowBean = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(GET_ALL_BY_MEMBER_ID_STMT);
			pstmt.setInt(1, memberId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memFollowBean = new MemTrackBusVO();
				
				memFollowBean.setBusfollowId(rs.getInt("BUSFOLLOW_ID"));
				memFollowBean.setMemberId(rs.getInt("MEMBER_ID"));
				memFollowBean.setBusId(rs.getInt("BUS_ID"));
				// 讀取完一筆Bean就存到list，若rs.next()還有再讀取下一個
				list.add(memFollowBean);
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
		
		return list;
	}
}
