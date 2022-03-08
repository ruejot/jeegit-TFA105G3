package com.memfollow.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemFollowDAO implements MemFollowDAO_interface {

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
	
	//FRIENDSHIP_ID, MEMBER_ID, FOLLOWEE, FRIENDSHIP
	private static final String INSERT_STMT = "INSERT INTO MEM_FOLLOW (MEMBER_ID, FOLLOWEE, FRIENDSHIP)"
			+ " VALUES (?, ?, ?)";
	private static final String UPDATE = "UPDATE MEM_FOLLOW  SET MEMBER_ID=?, FOLLOWEE=?, FRIENDSHIP=?"
			+ " WHERE FRIENDSHIP_ID=?";
	private static final String DELETE = "DELETE FROM MEM_FOLLOW WHERE MEMBER_ID = ? and FOLLOWEE = ?;";
	private static final String GET_ONE_STMT = "SELECT FRIENDSHIP_ID, MEMBER_ID, FOLLOWEE, FRIENDSHIP FROM MEM_FOLLOW WHERE FRIENDSHIP_ID = ?";
	private static final String GET_ALL_STMT = "SELECT FRIENDSHIP_ID, MEMBER_ID, FOLLOWEE, FRIENDSHIP FROM MEM_FOLLOW ORDER BY FRIENDSHIP_ID";
	private static final String GET_ALL_BY_MEMBER_ID_STMT = "SELECT FRIENDSHIP_ID, MEMBER_ID, FOLLOWEE, FRIENDSHIP FROM MEM_FOLLOW WHERE MEMBER_ID = ?";

	private static final String GET_ALL_BY_FOLLOWEE = "SELECT * FROM MEM_FOLLOW WHERE FOLLOWEE = ?";
	private static final String IF_FRIEND = "SELECT * FROM MEM_FOLLOW where MEMBER_ID = ? and FOLLOWEE = ?";

	
	
	
	@Override
	public void insert(MemFollowVO memFollowBean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, memFollowBean.getMemberId());
			pstmt.setInt(2, memFollowBean.getFollowee());
			pstmt.setString(3, memFollowBean.getFriendship());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, memFollowBean.getMemberId());
			pstmt.setInt(2, memFollowBean.getFollowee());
			pstmt.setString(3, memFollowBean.getFriendship());
			pstmt.setInt(4, memFollowBean.getFriendshipId());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
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
	public void delete(Integer memberId,Integer followee) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memberId);
			pstmt.setInt(2, followee);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public MemFollowVO ifFriend(Integer memberId,Integer followee) {
		MemFollowVO memFollowBean = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(IF_FRIEND);

			pstmt.setInt(1, memberId);
			pstmt.setInt(2, followee);

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
	public MemFollowVO findByPrimaryKey(Integer friendshipId) {
		MemFollowVO memFollowBean = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
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
			con = ds.getConnection();
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
	public List<MemFollowVO> getAllByFollowee(Integer followee) {
		List<MemFollowVO> list = new ArrayList<MemFollowVO>();
		MemFollowVO memFollowBean = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ALL_BY_FOLLOWEE);
			pstmt.setInt(1, followee);
			
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
