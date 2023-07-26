package kr.co.sist.log;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class UiResult extends JDialog {

    public UiResult() {

        ImageIcon icon = new ImageIcon("C:/Users/dltmd/Desktop/backg.jpg");
        JPanel jp = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(icon.getImage(), 0, 0, null);
            }
        };
        jp.setLayout(null);

        JButton jbConfirm = new JButton("확인");
        jbConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 여기에 확인 버튼 클릭 시 동작할 내용을 추가
                
                dispose(); // 다이얼로그 닫기
            }
        });

        jbConfirm.setBackground(new Color(254, 231, 21));
        jbConfirm.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        jp.add(jbConfirm).setBounds(350, 580, 100, 60);

        int labelY = 60; // Y 좌표 기준 값

        JLabel label1 = new JLabel("파일명");
        label1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        label1.setForeground(Color.WHITE);
        label1.setBounds(40, labelY, 100, 50);
        jp.add(label1);

        JLabel label2 = new JLabel("생성된 날짜");
        label2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        label2.setForeground(Color.WHITE);
        label2.setBounds(300, labelY, 200, 30); // Y 좌표 기준 값 사용
        jp.add(label2);

        labelY += 80; // Y 좌표 기준 값 증가

        JLabel label3 = new JLabel("1. 최다 사용 key의 이름과 횟수");
        label3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        label3.setForeground(Color.WHITE);
        label3.setBounds(80, labelY, 400, 50); // Y 좌표 기준 값 사용
        jp.add(label3);

        labelY += 80; // Y 좌표 기준 값 증가

        JLabel label4 = new JLabel("2. 브라우저별 접속횟수, 비율");
        label4.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        label4.setForeground(Color.WHITE);
        label4.setBounds(80, labelY, 400, 50); // Y 좌표 기준 값 사용
        jp.add(label4);

        labelY += 80; // Y 좌표 기준 값 증가

        JLabel label5 = new JLabel("3. 서비스 성공(200) 실패(404) 횟수");
        label5.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        label5.setForeground(Color.WHITE);
        label5.setBounds(80, labelY, 400, 50); // Y 좌표 기준 값 사용
        jp.add(label5);

        labelY += 90; // Y 좌표 기준 값 증가

        JLabel label6 = new JLabel("4. 요청이 가장 많은 시간");
        label6.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        label6.setForeground(Color.WHITE);
        label6.setBounds(80, labelY, 400, 30); // Y 좌표 기준 값 사용
        jp.add(label6);

        labelY += 80; // Y 좌표 기준 값 증가

        JLabel label7 = new JLabel("5. 비정상 요청 횟수와 비율");
        label7.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        label7.setForeground(Color.WHITE);
        label7.setBounds(80, labelY, 400, 30); // Y 좌표 기준 값 사용
        jp.add(label7);

        add(jp);

        setBounds(500, 400, 800, 700);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new UiResult();
    }
}
