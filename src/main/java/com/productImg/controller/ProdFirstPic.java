package com.productImg.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.product.model.ProductService;


/**
 * Servlet implementation class getPic
 */
@WebServlet("/ProdFirstPic")

public class ProdFirstPic extends HttpServlet {
	private ProductService SERVICE;
	Connection con;

	public void init() throws ServletException {
		try {
				SERVICE = new ProductService();
				Context ctx =new InitialContext();
				DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TFA105G3TestDB");
				con = ds.getConnection();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	}
	private static final String GET_1stPIC = "SELECT  MER_NAME,MER_PIC FROM pet_g3db_tfa105.v_merimg_mer where MER_ID = ? limit 1 ";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
			try {
				
				pstmt = con.prepareStatement(GET_1stPIC);
				pstmt.setInt(1, Integer.parseInt(req.getParameter("aa")));
				rs = pstmt.executeQuery();

				if (rs.next()) {
					BufferedInputStream bufin = new BufferedInputStream(rs.getBinaryStream("MER_PIC"));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = bufin.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
//					System.out.println(rs.getString("MER_NAME")); 
					out.flush();
					bufin.close();
				} else {
					InputStream in = getServletContext().getResourceAsStream("nest-frontend/assets/imgs/noPic.jpg");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					out.flush();
					in.close();
				}
				pstmt.close();
				rs.close();
			}catch (SQLException e) {
					throw new UnavailableException("Couldn't get db connection");
			} catch (IOException e) {
				InputStream in = getServletContext().getResourceAsStream("nest-frontend/assets/imgs/noPic.jpg");
				byte[] b = new byte[in.available()];
//				System.out.println(e+" look PFP 89");
				in.read(b);
				out.write(b);
				in.close();
			}catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e + " look PFP104");
		}
	}

}