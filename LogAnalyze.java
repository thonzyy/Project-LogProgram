package log;


import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class LogAnalyze {
	
	private Ui ui;
	private LogIO logio;
	
	private List<String> logList;
	private Map<String, Integer> logMap;
	
    private int requestNum;
    private String[] responseResultName;
    private int[] responseResultNum;
    private String[] webSites;
//    private List<Integer> number;
	
	
	public LogAnalyze(Ui ui, LogIO logio) {
		this.ui = ui;
		this.logio = logio;
		setLoglist();
		init();
	}
	
	
	public void setLoglist() {
		String start = ui.getjtStart().getText();
		String end = ui.getjtEnd().getText();

		try {
			if (!start.isEmpty() && !end.isEmpty()) {
				logList = logio.readLog(Integer.parseInt(start)-1, Integer.parseInt(end));
			} else if(start.isEmpty() && !end.isEmpty()) {
				logList = logio.readLog(0, Integer.parseInt(end));
			} else if(start.isEmpty() && end.isEmpty()) {
				logList = logio.readLog();
			} else {
				JOptionPane.showMessageDialog(ui, "잘 못 입력하셨습니다.");
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		responseResultName = "요청 성공(200),비정상적 요청(403),요청 실패(404),요청 에러(500)".split(",");
		webSites = "Chrome,IE,opera,firefox,Safari".split(",");
	}
	
	public String totalResult() {
		StringBuilder sbResultList = new StringBuilder();
		
			try {
				sbResultList.append("파일명 : ").append(logio.getFileMyeong()).append("\n");
				sbResultList.append("log파일 생성일 : ").append(logio.getMadeTimeByString()).append("\n\n");
				sbResultList.append("최다 사용 키의 이름과 횟수 : ").append(analyzeKey_Name()).append("\n");
				sbResultList.append("브라우저별 접속 횟수, 비율 :\n").append(analyzeWebBrowser());
				sbResultList.append("요청이 가장 많은 시간 [ ").append(analyzeTime()).append(" ]\n\n");
				sbResultList.append("브라우저별 요청 처리결과 횟수, 비율 :\n");
				sbResultList.append(printResult());
				sbResultList.append("\n총 파일 개수 : ").append(logList.size());
				
			} catch (IOException e) {
			}
			
		
		return sbResultList.toString();
	}

	// 1. 최다사용 키의 이름과 횟수를 출력하는 method
		public String analyzeKey_Name() throws IOException {
			// 파일 List를 불러옴
			// 경로 설정

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
			

			return maxVariable + "  " + maxValue + "회\n";
		}// end function_1

		// 2. 브라우저별 접속 횟수, 비율을 구하는 method
		public String analyzeWebBrowser() {
			//int 배열에 있는 각가의 웹브라우저의 갯수는 String 배열에 있는 웹브라우저 순서와 같다. 
			int[] countArr = new int[webSites.length]; // 브라우저 갯수만큼만 생
			int allCnt = logList.size();

			for (String log : logList) {
				if (log.indexOf("[Chrome]") != -1) {
					countArr[0]++;
//					chromeCnt++
				} else if (log.indexOf("[ie]") != -1) {
					countArr[1]++;
//					ieCnt++;
				} else if (log.indexOf("[opera]") != -1) {
					countArr[2]++;
//					operaCnt++;
				} else if (log.indexOf("[firefox]") != -1) {
					countArr[3]++;
//					firefoxCnt++;
				} else if (log.indexOf("[Safari]") != -1) {
					countArr[4]++;
//					safariCnt++;
				} // end if

			} // end for
//			
			StringBuilder sb = new StringBuilder();
			DecimalFormat df = new DecimalFormat("0.00"); 
			for(int i = 0; i < webSites.length; i++) {
				sb.append(webSites[i]).append(" - ");
				sb.append(countArr[i]);
				sb.append(" ( ").append(df.format((double) countArr[i] / allCnt * 100)).append(" % )\n");
			}
			
			return sb.toString();
		}// function_2

		// 4. 요청이 가장 많은 시간
		public String analyzeTime() {
			logMap = new HashMap<>();

//			logList에 저장된 로그 데이터를 순회하면서 각 로그의 시간대를 추출합니다.
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

//			시간대를 logMap에 저장하고, 해당 시간대의 사용 횟수를 계산합니다.
//			logMap에는 시간대와 해당 시간대의 사용 횟수가 저장되어 있습니다.
//			각 시간대의 사용 횟수를 확인하여 최다 사용 시간대와 그 횟수를 찾습니다.
//			최다 사용 시간대를 출력합니다.
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

			return maxTimeSlot + "시";

		}// function_4
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		public void countHttpStatusCode() throws IOException {
			responseResultNum = new int[responseResultName.length];
			int flag = -1;
			
	        for (int num : readFile()) {
	            if (num == 200) {
	                flag = 0;
	            } else if (num == 403) {
	            	flag = 1;
	            } else if (num == 404) {
	            	flag = 2;
	            } else if (num == 500) {
	            	flag = 3;
	            }
	            responseResultNum[flag]++;
	        }
	        requestNum = logList.size(); // 전체 요청 개수 설정
	        
	    }
	    
	    public List<Integer> readFile() throws IOException {
			List<Integer> logIntList = new ArrayList<>();
			Iterator<String> itr = logList.iterator();

			int lineCounter = 0;
			int startIndex = 0;
			int endIndex = 0;
			int number = 0;
			String numberStr = "";

			while (itr.hasNext()) {

				if (lineCounter == logList.size()) {
					break;
				}

				try {
					// [] 안에 있는 숫자 추출하여 logList2에 추가
					startIndex = logList.get(lineCounter).indexOf("[") + 1;
					endIndex = logList.get(lineCounter).indexOf("]");
					if (startIndex > 0 && endIndex > startIndex) {
						numberStr = logList.get(lineCounter).substring(startIndex, endIndex);
						number = Integer.parseInt(numberStr);

						logIntList.add(number);
						lineCounter++;
					}
				} catch (NumberFormatException e) {
					// 숫자로 변환할 수 없는 경우 무시
				}
			}

			// logList2 반환
			return logIntList;
		}

		public String calCodeShare() {
			StringBuilder sb = null;
			DecimalFormat df = new DecimalFormat("0.00");
			
			sb = new StringBuilder();

			for (int i = 0; i < responseResultName.length; i++) {
				sb.append(responseResultName[i]).append(" : ");
				sb.append(responseResultNum[i]);
				sb.append(" ( ").append(df.format((responseResultNum[i] / (double) requestNum) * 100)).append("% )\n");
			}

			return sb.toString();
		}

	    public String printResult() {
	    	// 결과 출력
	    	try {
				countHttpStatusCode();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
			return calCodeShare();
	    }
	
}
