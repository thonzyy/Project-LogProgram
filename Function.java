package Login;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Function {

	private Ui ui;
	
	private List<String> logList;
	private Map<String, Integer> logMap;
	
	public Function(Ui ui) {
		this.ui = ui;
		
		try {
			function_1();
			function_2();
			function_4();
			System.out.println("-------------");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 1. 최다사용 키의 이름과 횟수를 출력하는 method
	public void function_1() throws IOException {
		// 파일 List를 불러옴
		// 경로 설정
		logList = new LogIO(ui).readLog();

		logMap = new HashMap<>();

		int keyStartIndex = 0;
		int keyEndIndex = 0;
		String key = null;
		for (String log : logList) {
			// "key="의 시작 인덱스를 찾음
			// 'keyStartIndex'를 사용하여 각 로그에서 "key="라는 문자열을 찾는다
			keyStartIndex = log.indexOf("key=");
			// 만약 해당 로그에 "key="가 존재하지 않는다면 다음 로그로 넘어간다
			if (keyStartIndex != -1) {
				// 존재한다면 해당 인덱스에서 4를 더한 값을 끝 인덱스로 설정.
				keyStartIndex += 4; // "key=" 다음 위치로 이동

				// "&" 문자 이전까지의 인덱스를 찾음
				keyEndIndex = log.indexOf("&", keyStartIndex);
				if (keyEndIndex == -1) {
					keyEndIndex = log.length(); // "&"문자가 없으면 끝까지가 키의 끝
				} // end if
				key = log.substring(keyStartIndex, keyEndIndex); // 키 값을 추출
				logMap.put(key, logMap.getOrDefault(key, 0) + 1);
			} // end if
		} // end for

		// 최다 사용을 키를 구하는 일
		String maxVariable = null;
		int maxValue = Integer.MIN_VALUE;
		int value = 0;
		String variable = null;
		for (Map.Entry<String, Integer> entry : logMap.entrySet()) {
			variable = entry.getKey();
			// 만약 현재 엔트리의 값이 maxValue보다 크다면, maxValue 와 maxVarialbe를 해당 엔트리의 값과 키로 갱신
			value = entry.getValue();
			if (value > maxValue) {
				maxVariable = variable;
				maxValue = value;
			} // end if
		} // end for

		System.out.println(maxVariable + "\t" + maxValue + "회");
	}// end function_1

	// 2. 브라우저별 접속 횟수, 비율을 구하는 method
	public void function_2() {
		int chromeCnt = 0;
		int ieCnt = 0;
		int operaCnt = 0;
		int firefoxCnt = 0;
		int safariCnt = 0;
		int allCnt = logList.size();

		for (String log : logList) {
			if (log.indexOf("[Chrome]") != -1) {
				chromeCnt++;
			} else if (log.indexOf("[ie]") != -1) {
				ieCnt++;
			} else if (log.indexOf("[opera]") != -1) {
				operaCnt++;
			} else if (log.indexOf("[firefox]") != -1) {
				firefoxCnt++;
			} else if (log.indexOf("[Safari]") != -1) {
				safariCnt++;
			} // end if

		} // end for
		System.out.printf("Chrome - %d ( %.2f %% )\n", chromeCnt, ((double) chromeCnt / allCnt * 100));
		System.out.printf("IE - %d ( %.2f %% )\n", ieCnt, ((double) ieCnt / allCnt * 100));
		System.out.printf("opera - %d ( %.2f %% )\n", operaCnt, ((double) operaCnt / allCnt * 100));
		System.out.printf("firefox - %d ( %.2f %% )\n", firefoxCnt, ((double) firefoxCnt / allCnt * 100));
		System.out.printf("Safari - %d ( %.2f %% )\n", safariCnt, ((double) safariCnt / allCnt * 100));
	}// function_2

	// 4. 요청이 가장 많은 시간
	public void function_4() {
		logMap = new HashMap<>();

//		logList에 저장된 로그 데이터를 순회하면서 각 로그의 시간대를 추출합니다.
		// 각 로그에서 시간대를 추출하고, 시간대별 사용량을 계산하여 logMap에 저장
		int timeStartIndex = 0;
		int timeEndIndex = 0;
		String key = null;
		for (String log : logList) {
			timeStartIndex = log.lastIndexOf("-") + 4; // '-' 다음 인덱스부터 시간대가 시작됨
			timeEndIndex = timeStartIndex + 2; // 시간대는 두 자리 숫자로 구성되어 있음

			if (timeEndIndex <= log.length()) {
				key = log.substring(timeStartIndex, timeEndIndex);
				logMap.put(key, logMap.getOrDefault(key, 0) + 1);
			} // end if
		} // end for

//		시간대를 logMap에 저장하고, 해당 시간대의 사용 횟수를 계산합니다.
//		logMap에는 시간대와 해당 시간대의 사용 횟수가 저장되어 있습니다.
//		각 시간대의 사용 횟수를 확인하여 최다 사용 시간대와 그 횟수를 찾습니다.
//		최다 사용 시간대를 출력합니다.
		// 최다 사용 시간대를 구하는 일
		String maxTimeSlot = null;
		int usage = 0;
		String timeSlot = null;
		int maxUsage = Integer.MIN_VALUE;
		for (Map.Entry<String, Integer> entry : logMap.entrySet()) {
			timeSlot = entry.getKey();
			usage = entry.getValue();
			if (usage > maxUsage) {
				maxTimeSlot = timeSlot;
				maxUsage = usage;
			} // end if
		} // end for

		System.out.println(maxTimeSlot + "시");

	}// function_4

//	public static void main(String[] args) {
//		Function f1 = new Function();
//		try {
//
//			System.out.println("최다사용 키의 이름과 횟수 : \n");
//			f1.function_1();
//			System.out.println("--------------------------------");
//			System.out.println("\n브라우저별 접속 횟수, 비율 : \n");
//			f1.function_2();
//			System.out.println("--------------------------------");
//			System.out.println("\n요청이 가장 많은 시간 : \n");
//			f1.function_4();
//			System.out.println("--------------------------------");
//		} catch (IOException e) {
//			e.printStackTrace();
//		} // end catch
//	}// main
}// class