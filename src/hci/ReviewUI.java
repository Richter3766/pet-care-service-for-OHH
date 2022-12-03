package hci;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;

import pd.application.ApplicationList;

import javax.swing.JTextArea;
import javax.swing.JComboBox;

/*	 회원 리뷰 화면입니다.
 *	 
 *  대문 글자(리뷰 작성)
 *  
 *  별점 주기 (콤보박스)
 *  제목 입력 TextField
 *  내용 입력 TextArea
 *  제출 버튼 -> 제출
 *  으로 구성됩니다
 */

@SuppressWarnings("serial")
public class ReviewUI extends JFrame implements ActionListener{
		
	JTextField ReviewTitleField;
	JTextArea ReviewArea;
	
	JComboBox<String> ReviewCombo;
	
	Color c;
	
	// 버튼 이미지 & 크기 변환
	ImageIcon CancelImg1 = new ImageIcon("././Image/CancelButton1.png");
	ImageIcon CancelImg2 = new ImageIcon("././Image/CancelButton2.png");
	
	Image img1 = CancelImg1.getImage();
	Image changeImg1 = img1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonIcon1 = new ImageIcon(changeImg1);
	
	Image img2 = CancelImg2.getImage();
	Image changeImg2 = img2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonIcon2 = new ImageIcon(changeImg2);
	
	// 리뷰를 위한 ID
	String applicationID;
	
	public ReviewUI(String applicationID) {
		super("ReviewUI");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		getContentPane().setBackground(Color.WHITE);
		
		//제목 항목
		JLabel TitleLabel = new JLabel("리뷰 작성");
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		add(TitleLabel);
		TitleLabel.setBounds(30, 50, 500, 70);
				
		
		// 구분선
		JSeparator JSep = new JSeparator();
		add(JSep);
		JSep.setBounds(0, 170, 600, 70);
		
		JLabel ReviewLabel = new JLabel("별점");
		add(ReviewLabel);
		ReviewLabel.setBounds(120, 190, 100, 40);
		ReviewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		
		// 별점 주기 콤보 박스
		String ReviewPoint[] = {"★★★★★","★★★★☆","★★★☆☆","★★☆☆☆","★☆☆☆☆"};
		ReviewCombo = new JComboBox<String>(ReviewPoint);
		add(ReviewCombo);
		ReviewCombo.setBounds(220, 190, 200, 40);
		ReviewCombo.setFont(new Font("맑은 고딕", Font.BOLD, 30));
	
		// 리뷰 제목 텍스트필드
		ReviewTitleField = new JTextField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
		add(ReviewTitleField);
	
		// 텍스트박스 안에 뜨는 hint 구현용인데, 이게 자바 Swing 기본 기능에 없어서 Class 로 구현했습니다
		@SuppressWarnings("unused")
		TextHint hint = new TextHint(ReviewTitleField, " 제목을 입력하세요");
		
		c = new Color(233,236,239);
		ReviewTitleField.setBounds(30, 250, 520, 50);
		ReviewTitleField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		ReviewTitleField.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		ReviewTitleField.setBackground(c);
		
		JSeparator JRTFSep = new JSeparator();
		add(JRTFSep);
		JRTFSep.setBounds(30, 300, 520, 50);
		
		// 리뷰 내용 입력 텍스트 에어리아
		ReviewArea = new JTextArea();
		add(ReviewArea);
		
		c = new Color(233,236,239);
		ReviewArea.setBounds(30, 350, 520, 200);
		ReviewArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		ReviewArea.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		ReviewArea.setBackground(c);
		
		@SuppressWarnings("unused")
		TextAreaHint AreaHint = new TextAreaHint(ReviewArea, "리뷰 작성하기");
		
		// 뒤로가기 버튼
		JButton CancelButton = new JButton(CancelButtonIcon1);
		add(CancelButton);
		CancelButton.setBounds(0, 660, 100, 100);
		CancelButton.setActionCommand("뒤로가기");
		CancelButton.setRolloverIcon(CancelButtonIcon2);
		CancelButton.setBorderPainted(false);
		CancelButton.setContentAreaFilled(false);
		CancelButton.setFocusPainted(false);
		CancelButton.addActionListener(this);
		
		// 제출 버튼
		RoundedButton SubmitButton = new RoundedButton("제출");
		add(SubmitButton);
		c = new Color(64,126,219);
		SubmitButton.setBackground(c);
		SubmitButton.setForeground(Color.WHITE);
		SubmitButton.setBounds(450, 680, 100, 50);
		SubmitButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		SubmitButton.addActionListener(this);
		
		// ID 초기화
		this.applicationID = applicationID;
		
		// 작성한 리뷰 보기
		//
		if(!ApplicationList.getList().getForCompleteTable().get(this.applicationID).getReview()[0].equals("")) {
			String[] review = ApplicationList.getList().getForCompleteTable().get(this.applicationID).getReview();
			ReviewCombo.setSelectedItem(review[0]);
			ReviewTitleField.setText(review[1]);
			ReviewArea.setText(review[2]);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("뒤로가기")) {
			MemAppListUI MemAppListWindow = new MemAppListUI();
			MemAppListWindow.setVisible(true);
			dispose();
		}
		else if(ActionCmd.equals("제출")) {
			int ans = ConfirmUI.showConfirmDialog(this,"리뷰를 제출하시겠습니까?","확인 메세지",ConfirmUI.YES_NO_OPTION);
			if(ans == 0){ // 리뷰 제출 수락
				String score, title, content;
				score = (String)ReviewCombo.getSelectedItem();
				title = ReviewTitleField.getText();
				content = ReviewArea.getText();
				// 신청완료된 신청을 가져온 다음 setReview
				ApplicationList.getList().getForCompleteTable().get(this.applicationID).setReview(score, title, content);
				ConfirmUI.showMessageDialog(null, "리뷰가 제출되었습니다","제출 완료");
				MemberUI MemberWindow = new MemberUI();
				MemberWindow.setVisible(true);
				dispose();
			}
		}
		else {
			System.out.println("Unexpected Error");
			System.exit(0);
		}
	}
	
}
