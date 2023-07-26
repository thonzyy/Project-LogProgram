package report;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LogIO {
	
	private String filePath;
	private List<String> logList;

	//log파일인지 확인 후 파일 경로 저장
	public void openLog() throws FileNotFoundException {
		//filePath = "";
		JFrame jm = new JFrame(); //임시 JFrame
		FileDialog fdOpen = new FileDialog(jm/*JFrame*/, "Log File Open", FileDialog.LOAD);
		fdOpen.setVisible(true);
		
		String fileName = fdOpen.getFile();
		String fileDir = fdOpen.getDirectory();
		
		if(fdOpen!=null) {
			//.log로 끝나는지 확인
			if(fileName.substring(fileName.lastIndexOf(".")).equals(".log")) {
				filePath = fileDir + fileName;
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
	 
	//레포트 생성
	public void writeReport() throws IOException {
		StringBuilder reportName = new StringBuilder("/report_").append(System.currentTimeMillis()).append(".dat");
		File file = new File("e:/dev/report");
		
		//디렉토리 경로가 존재하지 않으면 디렉토리 생성
		if(!file.exists()) {
			file.mkdirs();
		}
		
		//OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		
		//지정한 경로와 이름으로 레포트 생성
		try {
			bw = new BufferedWriter(new FileWriter(file.getAbsolutePath() + reportName));
			bw.write("레포트내용~");
			bw.flush();
			//osw = new OutputStreamWriter(new FileOutputStream(file.getAbsolutePath() + reportName));
			//osw.write("레포트 내용~");
			//osw.flush();`
		}finally {
			//if(osw!=null) osw.close();
			if(bw!=null) bw.close();
		}
	}
	
	//메인은 날리기~
	public static void main(String[] args) {
		try {
			LogIO logio = new LogIO();
			logio.openLog();
			System.out.println(logio.readLog().get(0));
			//List<String> li = logio.readLog();
			//System.out.println(li.get(969));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
