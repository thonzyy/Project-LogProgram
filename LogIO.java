package log;


import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LogIO {
	
	@SuppressWarnings("unused")
	private Ui ui;
	
	static String filePath;
	
	private String fileMyeong;
	private long madeTime;
	private List<String> logList;
	
	public LogIO(Ui ui) {
		this.ui = ui;
	}

	//log파일인지 확인 후 파일 경로 저장
	public void openLog() throws FileNotFoundException {
		//filePath = "";
		JFrame jm = new JFrame(); //임시 JFrame
		FileDialog fdOpen = new FileDialog(jm/*JFrame*/, "Log File Open", FileDialog.LOAD);
		fdOpen.setVisible(true);
		
		String fileDir = fdOpen.getDirectory();
		String fileName = fdOpen.getFile();
		
		if(fdOpen!=null) {
			//.log로 끝나는지 확인
			if(fileName.substring(fileName.lastIndexOf(".")).equals(".log")) {
				filePath = fileDir + fileName;
				File file = new File(filePath);
				madeTime = file.lastModified();
				fileMyeong = file.getName();
				
			}else {
				JOptionPane.showMessageDialog(null, "Log파일이 아닙니다.","Error Message",JOptionPane.ERROR_MESSAGE);
			}			
		}
	 }
	
	//List에 LogData 저장
	//filepath!=null일 때 호출
	//openLog(); 
	//if(filePath!=null) readLog();
	public List<String> readLog() throws IOException {
		logList = new ArrayList<String>();
		BufferedReader br = null;
		String logData = "";
		
		try {
			br = new BufferedReader(new FileReader(new File(filePath)));
			while((logData = br.readLine()) != null) {
				logList.add(logData);
			}	
		}finally {
			if(br!=null) br.close();
		}

		return logList;
	}
	
	public List<String> readLog(int start, int finish) throws IOException {
		logList = new ArrayList<String>();
		BufferedReader br = null;
		String logData = "";
		
		try {
			br = new BufferedReader(new FileReader(new File(filePath)));
			while((logData = br.readLine()) != null) {
				logList.add(logData);
			}	
			
			if(logList.size() < finish) {
				finish = logList.size();
			}
			
		}finally {
			if(br!=null) br.close();
		}
		return logList.subList(start, finish);
		
	}
	 
	//레포트 생성
	public void writeReport() throws IOException {
		StringBuilder reportName = new StringBuilder("/report_").append(System.currentTimeMillis()).append(".rtf");
//		File file = new File("e:/dev/report");
		File file = new File("/Users/marklim/Downloads");
		
		//디렉토리 경로가 존재하지 않으면 디렉토리 생성
		if(!file.exists()) {
			file.mkdirs();
		}
		
		//OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		LogAnalyze la = new LogAnalyze(ui, this);
		
		
		//지정한 경로와 이름으로 레포트 생성
		try {
			bw = new BufferedWriter(new FileWriter(file.getAbsolutePath() + reportName));
			bw.write(la.totalResult());
			bw.flush();
			//osw = new OutputStreamWriter(new FileOutputStream(file.getAbsolutePath() + reportName));
			//osw.write("레포트 내용~");
			//osw.flush();`
		}finally {
			//if(osw!=null) osw.close();
			if(bw!=null) bw.close();
		}
	}

	public String getFileMyeong() {
		return fileMyeong;
	}

	public long getMadeTime() {
		return madeTime;
	}
	
	public String getMadeTimeByString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(madeTime);
	}
	
}