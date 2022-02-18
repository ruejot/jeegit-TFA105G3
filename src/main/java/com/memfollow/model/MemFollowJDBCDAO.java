package com.memfollow.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemFollowJDBCDAO implements MemFollowDAO_interface {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	private static final String userid = "root";
	private static final String passwd = "password";
	
	//FRIENDSHIP_ID, MEMBER_ID, FOLLOWEE, FRIENDSHIP
	private static final String INSERT_STMT = "INSERT INTO MEM_FOLLOW (MEMBER_ID, FOLLOWEE, FRIENDSHIP)"
			+ " VALUES (?, ?, ?)";
	private static final String UPDATE = "UPDATE MEM_FOLLOW  SET MEMBER_ID=?, FOLLOWEE=?, FRIENDSHIP=?"
			+ " WHERE FRIENDSHIP_ID=?";
	private static final String DELETE = "DELETE FROM MEM_FOLLOW WHERE FRIENDSHIP_ID=?";
	private static final String GET_ONE_STMT = "SELECT FRIENDSHIP_ID, MEMBER_ID, FOLLOWEE, FRIENDSHIP FROM MEM_FOLLOW WHERE FRIENDSHIP_ID = ?";
	private static final String GET_ALL_STMT = "SELECT FRIENDSHIP_ID, MEMBER_ID, FOLLOWEE, FRIENDSHIP FROM MEM_FOLLOW ORDER BY FRIENDSHIP_ID";
	private static final String GET_ALL_BY_MEMBER_ID_STMT = "SELECT FRIENDSHIP_ID, MEMBER_ID, FOLLOWEE, FRIENDSHIP FROM MEM_FOLLOW WHERE MEMBER_ID = ?";
	
	@Override
	public void insert(MemFollowVO memFollowBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, memFollowBean.getMemberId());
			pstmt.setInt(2, memFollowBean.getFollowee());
			pstmt.setString(3, memFollowBean.getFriendship());
			
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
	public void update(MemFollowVO memFollowBean) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, memFollowBean.getMemberId());
			pstmt.setInt(2, memFollowBean.getFollowee());
			pstmt.setString(3, memFollowBean.getFriendship());
			pstmt.setInt(4, memFollowBean.getFriendshipId());
			
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
	public void delete(Integer friendshipId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, friendshipId);

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
	public MemFollowVO findByPrimaryKey(Integer friendshipId) {
		MemFollowVO memFollowBean = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, friendshipId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memFollowBean = new MemFollowVO();
				//FRIENDSHIP_ID, MEMBER_ID, FOLLOWEE, FRIENDSHIP
				memFollowBean.setFriendshipId(rs.getInt("FRIENDSHIP_ID"));
				memFollowBean.setMemberId(rs.getInt("MEMBER_ID"));
				memFollowBean.setFollowee(rs.getInt("FOLLOWEE"));
				memFollowBean.setFriendship(rs.getString("FRIENDSHIP"));
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
	public List<MemFollowVO> getAll() {
		List<MemFollowVO> list = new ArrayList<MemFollowVO>();
		MemFollowVO memFollowBean = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memFollowBean = new MemFollowVO();
				
				memFollowBean.setFriendshipId(rs.getInt("FRIENDSHIP_ID"));
				memFollowBean.setMemberId(rs.getInt("MEMBER_ID"));
				memFollowBean.setFollowee(rs.getInt("FOLLOWEE"));
				memFollowBean.setFriendship(rs.getString("FRIENDSHIP"));
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
	public List<MemFollowVO> getAllByMemberId(Integer memberId) {
		List<MemFollowVO> list = new ArrayList<MemFollowVO>();
		MemFollowVO memFollowBean = null;
		
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
				memFollowBean = new MemFollowVO();
				
				memFollowBean.setFriendshipId(rs.getInt("FRIENDSHIP_ID"));
				memFollowBean.setMemberId(rs.getInt("MEMBER_ID"));
				memFollowBean.setFollowee(rs.getInt("FOLLOWEE"));
				memFollowBean.setFriendship(rs.getString("FRIENDSHIP"));
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
