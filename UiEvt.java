package kr.co.sist.log;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;

import javax.swing.JOptionPane;

public class UiEvt extends WindowAdapter implements ActionListener, KeyListener {
	private Ui ui;
	private FileDialog fd;
	private boolean flag;
	private String fName;
	private String filePath;

	public UiEvt(Ui ui) {
		this.ui = ui;
		flag = true;
	}// UiEvt
	
	//파일 열기 메소드
	public void openFiledialog() {
		fd = new FileDialog(ui, "Log 파일 선택", FileDialog.LOAD);
		fd.setVisible(true);
	}
	
	// View 버튼
	public void viewOpen() {
		openFiledialog();
		
	}
	//Report 버튼
	public void reportOpen() {
		openFiledialog();
		
	}

	// log 파일 선택 메소드
	public void selectLog() {
		String dirPath = fd.getDirectory();
		fName = fd.getFile();
		System.out.println(fName);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// viewOpen
		if (ae.getSource() == ui.getjbView()) {
			viewOpen();
		} // end if
		if( ae.getSource() == ui.getjbReport()) {
			reportOpen();
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
