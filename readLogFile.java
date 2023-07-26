package day0724;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class readLogFile {
	
	public static List<Integer> readFile(File file) throws IOException {
        List<String> logList = new ArrayList<>();
        List<Integer> logList2 = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));
        try {
            String temp;

            while ((temp = br.readLine()) != null) {
                logList.add(temp + "\n");

                // [] 안에 있는 숫자 추출하여 logList2에 추가
                int startIndex = temp.indexOf("[") + 1;
                int endIndex = temp.indexOf("]");
                if (startIndex > 0 && endIndex > startIndex) {
                    String numberStr = temp.substring(startIndex, endIndex);
                    try {
                        int number = Integer.parseInt(numberStr);
                        logList2.add(number);
                    } catch (NumberFormatException e) {
                       
                    }
                }
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }

        // logList2 출력
        for (int num : logList2) {
            System.out.println(num);
        }

        return logList2;
    }


	public static void main(String[] args) {
		File file = new File("e:/dev/sist_input_1.log"); 
		readLogFile rlf =new readLogFile();
		
		

	}

}
