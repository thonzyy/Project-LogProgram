package Login;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	
	private JPanel background;
	private JLabel jlblTitle;
	private JLabel jlblID;
	private JLabel jlblPW;
	private JTextField jtfID;
	private JPasswordField jpfPW;
	private JButton jbtnLogin;
	
	public LoginFrame() {
		super("로그인");
		
		setLayout(null);
		
		
		jlblTitle = new JLabel();
		jlblTitle.setText("로그인");
		jlblTitle.setVerticalTextPosition(JLabel.BOTTOM);
		jlblTitle.setFont(new Font(null, Font.BOLD, 24));
		jlblTitle.setBounds(0, 0, 400, 200);
		
		Font font = new Font(null, Font.PLAIN, 16);
		
		jlblID = new JLabel("아이디");
		jlblID.setFont(font);
		jlblID.setBounds(80, 200, 80, 35);
		
		jlblPW = new JLabel("비밀번호");
		jlblPW.setFont(font);
		jlblPW.setBounds(jlblID.getX(), 240, 80, 35);
		
		jtfID = new JTextField(15);
		jtfID.setBounds(jlblID.getX()+100, jlblID.getY(), 120, 35);
		
		jpfPW = new JPasswordField(15);
		jpfPW.setBounds(jlblPW.getX()+100, jlblPW.getY(), 120, 35);
		
		jbtnLogin = new JButton("로그인");
		jbtnLogin.setBounds(150, jlblPW.getY()+50, 80, 35);
		
		LoginEvt le = new LoginEvt(this);
		jbtnLogin.addActionListener(le);
		jtfID.addKeyListener(le);
		
		addWindowListener(le);
		
		add(jlblTitle);
		add(jlblID);
		add(jtfID);
		add(jlblPW);
		add(jpfPW);
		add(jbtnLogin);
		
		setResizable(false);
		setSize(400, 450);
		setLocationRelativeTo(null);
		setVisible(true);
	}


	public JLabel getJlblTitle() {
		return jlblTitle;
	}

	public JTextField getJtfID() {
		return jtfID;
	}

	public JPasswordField getJpfPW() {
		return jpfPW;
	}
	
	public JButton getJbtnLogin() {
		return jbtnLogin;
	}

	public static void main(String[] args) {
		new LoginFrame();
	}
}
