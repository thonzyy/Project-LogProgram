package log;



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
	
	private UiEvt uie;
	
	private JTextArea jtaResult;
	private JPanel jp;
	private JButton jbConfirm;
	private ImageIcon icon;
	
	private String viewInfo;
	
    public UiResult(UiEvt uie) {
    	this.uie = uie;
    	
        icon = new ImageIcon("E:/dev/workspace/TeamProject4/src/log/image/backgr.jpg");
        jp = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(icon.getImage(), 0, 0, null);
            }
        };
        
        jp.setLayout(null);
        
        //JTextArea 표시하기 위한 정보 불러오기
        setViewInfo();
        
        jtaResult = new JTextArea();
        jtaResult.setBounds(10, 10, 350, 420);
        jtaResult.setText(viewInfo);
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
        
        add(jp);
        
        setBounds(500, 200, 387, 530);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

	public void setViewInfo() {
		viewInfo = new LogAnalyze(uie.getUi(), uie.getLogio()).totalResult();
	}
    
}