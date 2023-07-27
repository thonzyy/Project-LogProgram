package Login;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadLogFile {
	
	private Ui ui;
	private static List<String> logList2;
	
	public ReadLogFile(Ui ui) {
		try {
			logList2 = new LogIO(ui).readLog();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    public static List<Integer> readFile() throws IOException {
//    	   logList2 = new LogIO().readLog();
    	   List<Integer> logList = new ArrayList<>();
    	   Iterator<String> itr =logList2.iterator();
    	   
    	    int lineCounter = 0;
    	  
    	        while (itr.hasNext()) {

    	            // [] 안에 있는 숫자 추출하여 logList2에 추가
    	            int startIndex = logList2.get(lineCounter).indexOf("[") + 1;
    	            int endIndex = logList2.get(lineCounter).indexOf("]");
    	            if (startIndex > 0 && endIndex > startIndex) {
    	                String numberStr = logList2.get(lineCounter).substring(startIndex, endIndex);
    	                try {
    	                    int number = Integer.parseInt(numberStr);

    	                    // 1000번째에서 1500번째 라인까지만 숫자를 logList2에 추가
    	                    if (lineCounter >= 1000 && lineCounter <= 1500) {
    	                    	logList.add(number);
    	                    }
    	                } catch (NumberFormatException e) {
    	                    // 숫자로 변환할 수 없는 경우 무시
    	                }
    	            }
    	            lineCounter++;
    	        }
    	    

    	    // logList2 반환
    	    return logList;
    	}
    
    public static List<Integer> readFile(int num1, int num2) throws IOException {
        List<Integer> logList = new ArrayList<>();

        int lineCounter = 0;

            Iterator<String> itr =logList2.iterator();
            while (itr.hasNext()) {

                // [] 안에 있는 숫자 추출하여 logList2에 추가
                int startIndex = logList2.get(lineCounter).indexOf("[") + 1;
                int endIndex = logList2.get(lineCounter).indexOf("]");
                if (startIndex > 0 && endIndex > startIndex) {
                    String numberStr = logList2.get(lineCounter).substring(startIndex, endIndex);
                    try {
                        int number = Integer.parseInt(numberStr);

                        // num1부터 num2까지의 라인 범위 내 숫자를 logList2에 추가
                        if (lineCounter > num1-1 && lineCounter < num2+1) {
                        	logList.add(number);
                        }
                    } catch (NumberFormatException e) {
                        // 숫자로 변환할 수 없는 경우 무시
                    }
                }
                lineCounter++;
            }

        // logList2 반환
        return logList;
        }
    }


