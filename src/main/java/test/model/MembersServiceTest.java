package test.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.members.model.MembersVO;
import com.members.model.MembersJDBCDAO;

// 還沒完成測試!!
public class MembersServiceTest {
	public static void main(String[] args) throws Exception {
//		File file = new File("");
//		System.out.println(file.getAbsolutePath());
//		這一個class檔絕對路徑 C:\TFA105_WebApp\Eclipse_WTP_workspace1\TFA105G3_wei_0211
		MembersJDBCDAO dao = null;

//		insertTest(dao);

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

	private static void insertTest(MembersJDBCDAO dao) throws Exception {
		dao = new MembersJDBCDAO();

//這裡的根目錄是從專案名稱開始算
//		servlet(get, post)網路傳輸相關的，根目錄才是從webapp開始算
//		String path = "src/main/webapp/image/photo_test_pika.jpg";

//目標圖檔完整路徑
//		C:\TFA105_WebApp\Eclipse_WTP_workspace1\TFA105G3_wei_0211\src\main\webapp\images\photo_test_pika.jpg
//目標圖檔相對路徑 如下面path
		String path = "src\\main\\webapp\\images\\photo_test_pika.jpg";
//		InputStream is = getPictureStream(path);
		byte[] photo1 = getPictureByteArray(path);

/*
 * File input1 = new File(path); int length1 = (int) (input1).length();
 * //sql是blob，blob有檔案上限，圖檔太大jdbc寫不進去，但我忘了blob大小上限是多少 4k?，記得的再跟我說。 byte[] photo1
 * = new byte[length1]; // BufferedInputStream in = new
 * BufferedInputStream(input1); FileInputStream fis1 = new
 * FileInputStream(input1); fis1.read(photo1); fis1.close();
 */

		MembersVO membersBean_in = new MembersVO();
		membersBean_in.setName("吳大明");
		membersBean_in.setMobile("0910123123");
		membersBean_in.setPhone("0228117777");
		membersBean_in.setAddress("台北市中山區民權東路二段109號");
		membersBean_in.setDate(java.sql.Date.valueOf("2022-02-10"));
		membersBean_in.setEmail("tibaMembyy@gmail.com");
		membersBean_in.setPassword("a123456");
		membersBean_in.setNickname("吳先生");
		membersBean_in.setIntro("沒有話要說~");
		membersBean_in.setPhoto(photo1);
//		membersBean_in.setPhoto(is);

//		is.close();
		dao.insert(membersBean_in);
		System.out.println("==log.executed.Members_insertTest");
	}
}
