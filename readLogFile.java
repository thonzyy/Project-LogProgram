package day0724;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class readLogFile {

    public static List<Integer> readFile(File file) throws IOException {
    	   List<Integer> logList2 = new ArrayList<>();

    	    BufferedReader br = new BufferedReader(new FileReader(file));
    	    int lineCounter = 0;

    	    try {
    	        String temp;

    	        while ((temp = br.readLine()) != null) {
    	            lineCounter++;

    	            // [] 안에 있는 숫자 추출하여 logList2에 추가
    	            int startIndex = temp.indexOf("[") + 1;
    	            int endIndex = temp.indexOf("]");
    	            if (startIndex > 0 && endIndex > startIndex) {
    	                String numberStr = temp.substring(startIndex, endIndex);
    	                try {
    	                    int number = Integer.parseInt(numberStr);

    	                    // 1000번째에서 1500번째 라인까지만 숫자를 logList2에 추가
    	                    if (lineCounter >= 1000 && lineCounter <= 1500) {
    	                        logList2.add(number);
    	                    }
    	                } catch (NumberFormatException e) {
    	                    // 숫자로 변환할 수 없는 경우 무시
    	                }
    	            }
    	        }
    	    } finally {
    	        if (br != null) {
    	            br.close();
    	        }
    	    }

    	    // logList2 반환
    	    return logList2;
    	}

    public static void main(String[] args) {
        File file = new File("e:/dev/sist_input_1.log");

        try {
            List<Integer> extractedNumbers = readFile(file);
            // logList2 출력
            for (int num : extractedNumbers) {
                System.out.println(num);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
