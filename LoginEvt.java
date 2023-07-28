package log;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class LoginEvt extends WindowAdapter implements ActionListener, KeyListener {
	
	boolean authority;
	private LoginFrame lf;
	private Map<String, String> mapAccount;
	
//	‘admin,1234’, ‘root, 1111’,’administrator,12345’로 로그인 할 수 있습니다.
//	이때 root계정으로 로그인하면 report문서를 생성할 수 없습니다.(report생성을 클릭하면 “문서를 생성할 수 있는 권한이 없음”을 보여줍니다.

	
	public LoginEvt(LoginFrame lf) {
		this.lf = lf;
		setAccount();
	}
	
	private void setAccount() {
		String[] idarr = "admin,root,administrator".split(",");
		String[] pwarr = "1234,1111,12345".split(",");
		
		mapAccount = new HashMap<String, String>();
		for(int i = 0; i < idarr.length; i++) {
			mapAccount.put(idarr[i], pwarr[i]);
		}
	}
	
	public boolean loginProccess() {
		String id = lf.getJtfID().getText();
		String pw = String.valueOf(lf.getJpfPW().getPassword());
		boolean flag = false;
		
		if(mapAccount.containsKey(id) && mapAccount.get(id).equals(pw)) {
			flag = true;
			if(id.equals("root")) {
				authority = false;
			} else {
				authority = true;
			}
		}
		return flag;
	}
	
	public boolean isAuthority() {
		return authority;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if((ae.getSource() == lf.getJpfPW()) || ae.getSource() == lf.getJbtnLogin()) {
			if(loginProccess()) {
				lf.getJlblTitle().setText("로그인 성공");
				lf.getJlblTitle().setForeground(Color.GREEN);
				lf.setVisible(false);
				new Ui(this);
			} else {
				lf.getJlblTitle().setText("로그인 실패");
				lf.getJlblTitle().setForeground(Color.RED);
			}
		System.out.println("눌림");
		}
	}

	@Override
	public void windowClosing(WindowEvent we) {
		lf.dispose();
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
			if ( !lf.getJtfID().getText().isEmpty()) {
				lf.getJpfPW().requestFocus();
			}//end if
		}//end if
	}//keyPressed

	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}

}
