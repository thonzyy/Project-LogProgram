package log;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	
	private JPanel jpbackground;
	private JLabel jlblTitle;
	private JLabel jlblID;
	private JLabel jlblPW;
	private JTextField jtfID;
	private JPasswordField jpfPW;
	private JButton jbtnLogin;
	private ImageIcon icon;
	
	public LoginFrame() {
		super("로그인");
		
		icon = new ImageIcon("E:/dev/workspace/TeamProject4/src/log/image/backgLogin.jpg");
		jpbackground = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(icon.getImage(), 0, 0, null);
            }
        };
        
        jpbackground.setLayout(null);

        Color fontColor = Color.white;
        Font font = new Font("Arial Rounded MT", Font.BOLD, 18);
		
		jlblTitle = new JLabel("로그인", JLabel.CENTER);
		jlblTitle.setForeground(Color.WHITE);
		jlblTitle.setVerticalTextPosition(JLabel.BOTTOM);
		jlblTitle.setFont(new Font("HY견고딕", Font.BOLD, 58));
		jlblTitle.setBounds(0, 0, 400, 200);
		
		jlblID = new JLabel("아이디");
		jlblID.setFont(font);
		jlblID.setForeground(fontColor);
		jlblID.setBounds(80, 200, 80, 35);
		
		jlblPW = new JLabel("비밀번호");
		jlblPW.setFont(font);
		jlblPW.setForeground(fontColor);
		jlblPW.setBounds(jlblID.getX(), 240, 80, 35);
		
		jtfID = new JTextField(15);
		jtfID.setBounds(jlblID.getX()+90, jlblID.getY()+6, 140, 25);
		
		jpfPW = new JPasswordField(15);
		jpfPW.setBounds(jlblPW.getX()+90, jlblPW.getY()+6, 140, 25);
		
		jbtnLogin = new JButton("로그인");
		jbtnLogin.setBounds(150, jlblPW.getY()+50, 80, 35);
		
		LoginEvt le = new LoginEvt(this);
		jbtnLogin.addActionListener(le);
		jtfID.addKeyListener(le);
		
		addWindowListener(le);
		
		jpbackground.add(jlblTitle);
		jpbackground.add(jlblID);
		jpbackground.add(jtfID);
		jpbackground.add(jlblPW);
		jpbackground.add(jpfPW);
		jpbackground.add(jbtnLogin);
		
		add(jpbackground);
		
		
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
