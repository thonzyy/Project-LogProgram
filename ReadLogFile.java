package Login;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadLogFile {
	
	private Ui ui;
	private static List<String> logList2;
	
	public ReadLogFile(Ui ui) {
		this.ui = ui;
		
		try {
			logList2 = new LogIO(ui).readLog();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Integer> readFile() throws IOException {
		List<Integer> logList = new ArrayList<>();
		Iterator<String> itr = logList2.iterator();

		int lineCounter = 0;
		int startIndex = 0;
		int endIndex = 0;
		int number = 0;
		String numberStr = "";

		while (itr.hasNext()) {

			if (lineCounter == logList2.size()) {
				break;
			}

			try {
				// [] 안에 있는 숫자 추출하여 logList2에 추가
				startIndex = logList2.get(lineCounter).indexOf("[") + 1;
				endIndex = logList2.get(lineCounter).indexOf("]");
				if (startIndex > 0 && endIndex > startIndex) {
					numberStr = logList2.get(lineCounter).substring(startIndex, endIndex);
					number = Integer.parseInt(numberStr);

					logList.add(number);
					lineCounter++;
				}
			} catch (NumberFormatException e) {
				// 숫자로 변환할 수 없는 경우 무시
			}
		}

		// logList2 반환
		return logList;
	}
    
	public List<Integer> readFile(int num1, int num2) throws IOException {
		List<Integer> logList = new ArrayList<>();

		int lineCounter = 0;
		int startIndex = 0;
		int endIndex = 0;
		int number = 0;
		String numberStr = "";

		Iterator<String> itr = logList2.iterator();
		while (itr.hasNext()) {

			try {
				// [] 안에 있는 숫자 추출하여 logList2에 추가
				startIndex = logList2.get(lineCounter).indexOf("[") + 1;
				endIndex = logList2.get(lineCounter).indexOf("]");
				if (startIndex > 0 && endIndex > startIndex) {
					numberStr = logList2.get(lineCounter).substring(startIndex, endIndex);
					number = Integer.parseInt(numberStr);

					// num1부터 num2까지의 라인 범위 내 숫자를 logList2에 추가
					if (lineCounter > num1 - 1 && lineCounter < num2 + 1) {
						logList.add(number);
					}
					lineCounter++;
				}
			} catch (NumberFormatException e) {
				// 숫자로 변환할 수 없는 경우 무시
			}
		}

		// logList2 반환
		return logList;
	}
    }


