package log;


import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

public class UiEvt extends WindowAdapter implements ActionListener, KeyListener {
	private Ui ui;
	private LogIO logio;
	
	private FileDialog fd;
	private String fName;
//	private String filePath;

	public UiEvt(Ui ui) {
		this.ui = ui;
		this.logio = new LogIO(ui);
	}// UiEvt
	
	//파일 열기 메소드
	public void openFiledialog() throws FileNotFoundException, NullPointerException{
			logio.openLog();
	}
	
	// View 버튼
	public void viewOpen() throws NullPointerException, IOException {
		openFiledialog();
		new UiResult(this);
		
	}
	//Report 버튼
	public void reportOpen() throws NullPointerException, IOException {
		if(ui.getLfe().isAuthority()) {
			openFiledialog();
			logio.writeReport();
		} else {
			JOptionPane.showMessageDialog(ui, "접근권한이 없는 아이디 입니다.\n자세한 사항은 해당 부서에 문의하세요.");
		}
		
	}

	// log 파일 선택 메소드
	public void selectLog() {
		fName = fd.getFile();
		System.out.println(fName);
	}
	
	public LogIO getLogio() {
		return logio;
	}
	
	public Ui getUi() {
		return ui;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// viewOpen
		if (ae.getSource() == ui.getjbView()) {
			try {
				viewOpen();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // end if
		if( ae.getSource() == ui.getjbReport()) {
			try {
				reportOpen();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
			}
		}//end if
	}// actionPerformed
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if ( !ui.getjtStart().getText().isEmpty()) {
				ui.getjtEnd().requestFocus();
			}//end if
		}//end if
	}//keyPressed
	
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	
}//class