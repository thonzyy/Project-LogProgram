package Login;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class UiResult extends JDialog {
	
	private Ui ui;
	private JTextArea jtaResult;
	private JPanel jp;
	private JButton jbConfirm;
	private ImageIcon icon;
	
    public UiResult(Ui ui) {
    	this.ui = ui;
    	
        icon = new ImageIcon("E:/dev/workspace/TeamProject4/src/Login/image/backgr.jpg");
        jp = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(icon.getImage(), 0, 0, null);
            }
        };
        
        jp.setLayout(null);
        
        jtaResult = new JTextArea();
        jtaResult.setBounds(10, 10, 350, 420);
        jtaResult.setEditable(false);
        
        jbConfirm = new JButton("확인");
        jbConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 여기에 확인 버튼 클릭 시 동작할 내용을 추가
            	
                dispose(); // 다이얼로그 닫기
                
            }
        });

        jbConfirm.setBackground(new Color(254, 231, 21));
        jbConfirm.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        jbConfirm.setBounds(125, 440, 120, 40);
        
        jp.add(jtaResult);
        jp.add(jbConfirm);

//        int labelY = 60; // Y 좌표 기준 값
//
//        JLabel label1 = new JLabel("파일명 : ");
//        label1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
//        label1.setForeground(Color.WHITE);
//        label1.setBounds(40, labelY, 100, 50);
//        jp.add(label1);
//
//        JLabel label2 = new JLabel("생성된 날짜 : ");
//        label2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
//        label2.setForeground(Color.WHITE);
//        label2.setBounds(300, labelY+10, 200, 30); // Y 좌표 기준 값 사용
//        jp.add(label2);
//
//        labelY += 80; // Y 좌표 기준 값 증가
//        
//        JLabel[] labels = new JLabel[6];
////        		"1. 최다 사용 key의 이름과 횟수 : ", "2. 브라우저별 접속횟수, 비율 : " , "3. 서비스 성공(200) 실패(404) 횟수 : ",
////        	    "4. 요청이 가장 많은 시간 : ", "5. 비정상 요청(403) 횟수와 비율 : ", "6. 요청에 대한 에러(500) 발생 횟수와 비율 : "
//        
//        
//        for (int i=0; i < labels.length; i++) {
//        	
//        	labels[i]=new JLabel();
//        	labels[i].setText("1");
//        	
//            labels[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
//            labels[i].setForeground(Color.WHITE);
//            labels[i].setBounds(80, labelY+(80*i), 400, 50);
//            jp.add(labels[i]);
//        }     
        
        add(jp);
        
        setBounds(500, 200, 387, 530);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

}