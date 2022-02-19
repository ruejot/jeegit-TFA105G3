package com.merimg.servlet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductVO;


/**
 * Servlet implementation class getPic
 */
@WebServlet("/getproductPic")

public class DBGifReader extends HttpServlet {

	Connection con;

	private static final String GET_PIC_STMT = "SELECT * from pet_g3db_tfa105.MER_IMG where IMG_ID = ?";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
//		List<ProductVO> productlist =  
			try {
				pstmt = con.prepareStatement(GET_PIC_STMT);
				pstmt.setInt(1, Integer.parseInt(req.getParameter("aa")));
				rs = pstmt.executeQuery();

				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("MER_PIC"));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				} else {
					res.sendError(HttpServletResponse.SC_NOT_FOUND);
				}
				rs.close();
				pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}
		}

	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei",
					"root", "password");
		} catch (ClassNotFoundException e) {
			throw new UnavailableException("Couldn't load JdbcOdbcDriver");
		} catch (SQLException e) {
			throw new UnavailableException("Couldn't get db connection");
		}
	}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}