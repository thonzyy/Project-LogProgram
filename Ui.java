package log;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Ui extends JFrame {
	
	private LoginEvt lfe;
	
	private JButton jbView;
	private JButton jbReport;
	private JTextField jtStart;
	private JTextField jtEnd;
	private JLabel jlStart;
	private JLabel jlEnd;

	public Ui(LoginEvt lfe) {
		super("Log Info");
		
		this.lfe = lfe;

		ImageIcon icon = new ImageIcon("E:/dev/workspace/TeamProject4/src/log/image/backg.jpg");
		JPanel background = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
			}
		};

		jbView = new JButton("View");
		jbReport = new JButton("Report");
		jtStart = new JTextField(15);
		jtEnd = new JTextField(15);
		jlStart = new JLabel("Start");
		jlEnd = new JLabel("End");

		jbView.setBackground(new Color(254, 231, 21));
		jbReport.setBackground(new Color(254, 231, 21));
		jbView.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		jbReport.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		jlStart.setForeground(Color.WHITE);
		jlEnd.setForeground(Color.WHITE);
		jlStart.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		jlEnd.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));

		background.setLayout(null);

		jbView.setBounds(100, 60, 300, 150);
		jbReport.setBounds(480, 60, 300, 150);
		jtStart.setBounds(183, 235, 600, 40);
		jtEnd.setBounds(183, 300, 600, 40);
		jlStart.setBounds(120, 227, 100, 50);
		jlEnd.setBounds(130, 295, 100, 50);

		background.add(jbView);
		background.add(jbReport);
		background.add(jtStart);
		background.add(jtEnd);
		background.add(jlStart);
		background.add(jlEnd);

		setContentPane(background); // 메인 패널을 프레임의 컨텐트 팬으로 설정

		// 이벤트 생성
		UiEvt ue = new UiEvt(this);
		jbView.addActionListener(ue);
		jbReport.addActionListener(ue);
		jtStart.addKeyListener(ue);

		addWindowListener(ue);

		setSize(900, 430);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}// Ui

	public JButton getjbView() {
		return jbView;
	}

	public JButton getjbReport() {
		return jbReport;
	}

	public JTextField getjtStart() {
		return jtStart;
	}

	public JTextField getjtEnd() {
		return jtEnd;
	}
	
	public LoginEvt getLfe() {
		return lfe;
	}

//	public static void main(String[] args) {
//		new Ui();
//
//	}// main

}// class