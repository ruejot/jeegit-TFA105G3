package zsql.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//import util.Util;

public class TestWriteBlob_pet {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/pet_g3db_tfa105?serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PASSWORD = "password";
	
	
	private static final String SQL = "INSERT INTO MER_IMG(MER_ID, MER_PIC) VALUES (?, ?)";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(SQL);

			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 1);
			InputStream is = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0111.png");
			pstmt.setBlob(2, is);
			pstmt.executeUpdate();
			is.close();

			// 2. setBytes
			pstmt.setInt(1, 1);
			byte[] pic = getPictureByteArray("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0112.png");
			pstmt.setBytes(2, pic);
			pstmt.executeUpdate();
//
			// 3. setBinaryStream
			pstmt.setInt(1, 2);
			InputStream is2 = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0211.png");
			pstmt.setBinaryStream(2, is2);
			pstmt.executeUpdate();
			is2.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 3);
			InputStream aa = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0311.png");
			pstmt.setBlob(2, aa);
			pstmt.executeUpdate();
			aa.close();
			
			// 2. setBytes
			pstmt.setInt(1, 4);
			byte[] pic4 = getPictureByteArray("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0411.png");
			pstmt.setBytes(2, pic4);
			pstmt.executeUpdate();
//
			// 3. setBinaryStream
			pstmt.setInt(1, 4);
			InputStream pic41 = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0412.png");
			pstmt.setBinaryStream(2, pic41);
			pstmt.executeUpdate();
			pic41.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 4);
			InputStream pic42 = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0413.png");
			pstmt.setBlob(2, pic42);
			pstmt.executeUpdate();
			pic42.close();
			
			// 2. setBytes
			pstmt.setInt(1, 5);
			byte[] pic5 = getPictureByteArray("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0421.png");
			pstmt.setBytes(2, pic5);
			pstmt.executeUpdate();
//
			// 3. setBinaryStream
			pstmt.setInt(1, 5);
			InputStream is25 = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0422.png");
			pstmt.setBinaryStream(2, is25);
			pstmt.executeUpdate();
			is25.close();

			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 6);
			InputStream is6 = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0431.png");
			pstmt.setBlob(2, is6);
			pstmt.executeUpdate();
			is6.close();
			
			// 2. setBytes
			pstmt.setInt(1, 6);
			byte[] pic61 = getPictureByteArray("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0432.png");
			pstmt.setBytes(2, pic61);
			pstmt.executeUpdate();
//
			// 3. setBinaryStream
			pstmt.setInt(1, 7);
			InputStream is27 = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0441.png");
			pstmt.setBinaryStream(2, is27);
			pstmt.executeUpdate();
			is27.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 7);
			InputStream is17 = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0442.png");
			pstmt.setBlob(2, is17);
			pstmt.executeUpdate();
			is17.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1,8 );
			InputStream is18 = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0451.png");
			pstmt.setBlob(2, is18);
			pstmt.executeUpdate();
			is18.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1,8 );
			InputStream is19 = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0452.png");
			pstmt.setBlob(2, is19);
			pstmt.executeUpdate();
			is19.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1,8);
			InputStream is1a = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0453.png");
			pstmt.setBlob(2, is1a);
			pstmt.executeUpdate();
			is1a.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1,10 );
			InputStream is1b = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0611.png");
			pstmt.setBlob(2, is1b);
			pstmt.executeUpdate();
			is1b.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1,10 );
			InputStream ss = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0612.png");
			pstmt.setBlob(2, ss);
			pstmt.executeUpdate();
			ss.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 11);
			InputStream ss1 = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0621.png");
			pstmt.setBlob(2, ss1);
			pstmt.executeUpdate();
			ss1.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 12);
			InputStream ss3 = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0631.png");
			pstmt.setBlob(2, ss3);
			pstmt.executeUpdate();
			ss3.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 12);
			InputStream ss4 = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0632.png");
			pstmt.setBlob(2, ss4);
			pstmt.executeUpdate();
			ss4.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 12);
			InputStream qq1 = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0633.png");
			pstmt.setBlob(2, qq1);
			pstmt.executeUpdate();
			qq1.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 13);
			InputStream qq = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0641.png");
			pstmt.setBlob(2, qq);
			pstmt.executeUpdate();
			qq.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 14);
			InputStream ww = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0651.png");
			pstmt.setBlob(2, ww);
			pstmt.executeUpdate();
			ww.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 15);
			InputStream hh = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0711.png");
			pstmt.setBlob(2, hh);
			pstmt.executeUpdate();
			hh.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 16);
			InputStream ghj = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0721.png");
			pstmt.setBlob(2, ghj);
			pstmt.executeUpdate();
			ghj.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 17);
			InputStream vb = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0731.png");
			pstmt.setBlob(2, vb);
			pstmt.executeUpdate();
			vb.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 18);
			InputStream nbn = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0511.png");
			pstmt.setBlob(2, nbn);
			pstmt.executeUpdate();
			nbn.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 19);
			InputStream bnn = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0321.png");
			pstmt.setBlob(2, bnn);
			pstmt.executeUpdate();
			bnn.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 19);
			InputStream bn = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0322.png");
			pstmt.setBlob(2, bn);
			pstmt.executeUpdate();
			bn.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 19);
			InputStream mmn = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0323.png");
			pstmt.setBlob(2, mmn);
			pstmt.executeUpdate();
			mmn.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 20);
			InputStream nnm = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0331.png");
			pstmt.setBlob(2, nnm);
			pstmt.executeUpdate();
			nnm.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 20);
			InputStream nm = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0332.png");
			pstmt.setBlob(2, nm);
			pstmt.executeUpdate();
			nm.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 21);
			InputStream yuu = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0121.png");
			pstmt.setBlob(2, yuu);
			pstmt.executeUpdate();
			yuu.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 21);
			InputStream kk = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0122.png");
			pstmt.setBlob(2, kk);
			pstmt.executeUpdate();
			kk.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 22);
			InputStream iuu = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0131.png");
			pstmt.setBlob(2, iuu);
			pstmt.executeUpdate();
			iuu.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 22);
			InputStream ui = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0132.png");
			pstmt.setBlob(2, ui);
			pstmt.executeUpdate();
			ui.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 22);
			InputStream ioo = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0133.png");
			pstmt.setBlob(2, ioo);
			pstmt.executeUpdate();
			ioo.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 23);
			InputStream op = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0911.png");
			pstmt.setBlob(2, op);
			pstmt.executeUpdate();
			op.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 24);
			InputStream po = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0921.png");
			pstmt.setBlob(2, po);
			pstmt.executeUpdate();
			po.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1,25 );
			InputStream trr = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0931.png");
			pstmt.setBlob(2, trr);
			pstmt.executeUpdate();
			trr.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 26);
			InputStream rff = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0941.png");
			pstmt.setBlob(2, rff);
			pstmt.executeUpdate();
			rff.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 27);
			InputStream fff = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0951.png");
			pstmt.setBlob(2, fff);
			pstmt.executeUpdate();
			fff.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 28);
			InputStream fv = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0961.png");
			pstmt.setBlob(2, fv);
			pstmt.executeUpdate();
			fv.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 28);
			InputStream vf = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0962.png");
			pstmt.setBlob(2, vf);
			pstmt.executeUpdate();
			vf.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 29);
			InputStream vvv = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0971.png");
			pstmt.setBlob(2, vvv);
			pstmt.executeUpdate();
			vvv.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 30);
			InputStream ewq = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0981.png");
			pstmt.setBlob(2, ewq);
			pstmt.executeUpdate();
			ewq.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 31);
			InputStream qwe = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0991.png");
			pstmt.setBlob(2, qwe);
			pstmt.executeUpdate();
			qwe.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 32);
			InputStream qwd = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/09101.png");
			pstmt.setBlob(2, qwd);
			pstmt.executeUpdate();
			qwd.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 32);
			InputStream qd = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/09102.png");
			pstmt.setBlob(2, qd);
			pstmt.executeUpdate();
			qd.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 33);
			InputStream tt = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/09111.png");
			pstmt.setBlob(2, tt);
			pstmt.executeUpdate();
			tt.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 34);
			InputStream qe = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0811.png");
			pstmt.setBlob(2, qe);
			pstmt.executeUpdate();
			qe.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 35);
			InputStream j4r = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0341.png");
			pstmt.setBlob(2, j4r);
			pstmt.executeUpdate();
			j4r.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 36);
			InputStream df = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0351.png");
			pstmt.setBlob(2, df);
			pstmt.executeUpdate();
			df.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 37);
			InputStream fdd = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0221.png");
			pstmt.setBlob(2, fdd);
			pstmt.executeUpdate();
			fdd.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 37);
			InputStream fd = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0222.png");
			pstmt.setBlob(2, fd);
			pstmt.executeUpdate();
			fd.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 37);
			InputStream ff = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0223.png");
			pstmt.setBlob(2, ff);
			pstmt.executeUpdate();
			ff.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 38);
			InputStream eew = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0231.png");
			pstmt.setBlob(2, eew);
			pstmt.executeUpdate();
			eew.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 39);
			InputStream ew = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0661.png");
			pstmt.setBlob(2, ew);
			pstmt.executeUpdate();
			ew.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 40);
			InputStream ee = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0671.png");
			pstmt.setBlob(2, ee);
			pstmt.executeUpdate();
			ee.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 41);
			InputStream cz = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0671.png");
			pstmt.setBlob(2, cz);
			pstmt.executeUpdate();
			cz.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 42);
			InputStream caaa = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0461.png");
			pstmt.setBlob(2, caaa);
			pstmt.executeUpdate();
			caaa.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 42);
			InputStream ca = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0462.png");
			pstmt.setBlob(2, ca);
			pstmt.executeUpdate();
			ca.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 43);
			InputStream sx = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0471.png");
			pstmt.setBlob(2, sx);
			pstmt.executeUpdate();
			sx.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 43);
			InputStream sd = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0472.png");
			pstmt.setBlob(2, sd);
			pstmt.executeUpdate();
			sd.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 44);
			InputStream ssc = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0471.png");
			pstmt.setBlob(2, ssc);
			pstmt.executeUpdate();
			ssc.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 45);
			InputStream shs = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0481.png");
			pstmt.setBlob(2, shs);
			pstmt.executeUpdate();
			shs.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 46);
			InputStream aac = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0491.png");
			pstmt.setBlob(2, aac);
			pstmt.executeUpdate();
			aac.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 46);
			InputStream aax = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0492.png");
			pstmt.setBlob(2, aax);
			pstmt.executeUpdate();
			aax.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 47);
			InputStream aaa = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/04101.png");
			pstmt.setBlob(2, aaa);
			pstmt.executeUpdate();
			aaa.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 48);
			InputStream xax = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0821.png");
			pstmt.setBlob(2, xax);
			pstmt.executeUpdate();
			xax.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 49);
			InputStream xaa = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0831.png");
			pstmt.setBlob(2, xaa);
			pstmt.executeUpdate();
			xaa.close();
			
			// 1. setBlob (JDBC 4.0 / JDK 6)
			pstmt.setInt(1, 49);
			InputStream xa = getPictureStream("C:/JavaFramework/eclipse-workspace/jeeweb-TFA105G3/src/main/webapp/image/+++/0832.png");
			pstmt.setBlob(2, xa);
			pstmt.executeUpdate();
			xa.close();
			
			
			
			
			
			System.out.println("Update ok");

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}

	public static InputStream getPictureStream(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		return fis;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
