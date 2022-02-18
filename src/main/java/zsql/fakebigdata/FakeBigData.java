package zsql.fakebigdata;

import java.util.HashSet;
import java.util.Set;

public class FakeBigData {
	public static void main(String[] args) {
		
		System.out.println( genTwNameAtMost3Word() );
//		genTwNameIn3Word();
	}

	public static String genTwNameAtMost3Word() {
		final int TW_NAME_LENGTH = 3;
		String fullName = null;
		
		String[] lastName = new String[] 
				{ "李", "林", "黃", "趙", "郭", "盧", "陳", "張", "何", "周", "楊", "陳", "許", "吳", "劉" };
		
		String[] firstName_lib = new String[] 
				{ "詩", "靜", "洪", "婷", "定", "淑", "華", "思", "翔", "嘉"
				, "敏", "子", "敏", "芬", "彤", "玲", "明", "珊", "桂", "柏"
				, "然", "丹", "芯", "宜", "廷", "豪", "華", "璟", "雪", "雅"
				, "杰", "娟", "恩", "蓁", "宇", "宏", "晟", "瑜", "佳", "世"
				, "偉", "燕", "文", "妍", "怡", "秀", "語", "甄", "騫", "美"};
		
		
//		為了名字不重複，用set先挑出2個名字。
		Set<String> firstName = new HashSet<>();
		
		for(int i=0; i<TW_NAME_LENGTH-1; i++) {
			int fn_lib_ptr = (int) (Math.random() * firstName_lib.length);
			firstName.add(firstName_lib[fn_lib_ptr]);
//			有個小驚喜，fn_lib_ptr 如果2次都取到一樣的，就會出現單名。
//			System.out.println("fn_lib_ptr: " + fn_lib_ptr);
		}
		
		
//		sb為了把挑出來的姓氏、名字，用toString()組成一個字串。
		StringBuffer sb = new StringBuffer();
		
//		挑姓氏，拼裝姓氏
		sb.append(lastName[(int) (Math.random() * lastName.length)]);
		
//		拼裝名子
		for(String k : firstName) {
			sb.append(k);
		}
		fullName = sb.toString();

//		System.out.println(fullName);
		
		return fullName;
	}
}
