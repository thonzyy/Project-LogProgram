package day0724;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Function2 {
	
	private int code200= 0;
	private int code403= 0;
	private int code404= 0;
	private int code500= 0;
	private int requestNum = 0;
	private String code403Share, code500Share;
	private List<Integer>number;
	
	
	

	
	public void countHttpStatusCode() throws IOException {
//		int serviceCode = Integer.parseInt(temp.substring(temp.indexOf("[") + 1, temp.indexOf("]")));
		number =  readLogFile.readFile(new File("e:/dev/sist_input_1.log"));
		
		for (int i = 0; i < number.size() ; i++) {
			if (number.get(i) == 200) {
				code200++;
			} else if (number.get(i) == 404) {
				code404++;
			} else if (number.get(i) == 403) {
				code403++;
			}
		}
		//while문 돌려서 일일이 확인
		

	}
	public void getRequestNum () {
		requestNum = number.size();
	}
	public void calCode403Share() {

		code403Share = String.format("%4.2f", (code403 / (double) requestNum) * 100);
	}
	
	public void calCode500Share () {
		code500Share =String.format("%4.2f", (code500/ (double)requestNum) *100);
	}

	public static void main(String[] args) {

	}

}
