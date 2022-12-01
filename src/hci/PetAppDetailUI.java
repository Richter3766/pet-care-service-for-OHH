package hci;

import pd.application.Application;
import pd.application.ApplicationList;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;

/*	  돌봄이 신청 내역 확인화면입니다.
 *	 
 *  대문 글자(신청 정보 입력)
 *  
 *  JTable : 신청목록입니다. 아직 내용은 추가 X
 *  
 *  뒤로가기 버튼 -> MemberUI
 *  리뷰 버튼 -> ReviewUI
 *  결제 버튼 -> ?? (평소엔 보이지 않음) 리뷰버튼 자리에 존재
 *  으로 구성됩니다
 */

@SuppressWarnings("serial")
public class PetAppDetailUI extends JFrame implements ActionListener{

	Color c;
	
	ImageIcon Cancelimg1 = new ImageIcon("././Image/CancelButton1.png");
	ImageIcon Cancelimg2 = new ImageIcon("././Image/CancelButton2.png");
	
	Image img1 = Cancelimg1.getImage();
	Image changeImg1 = img1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonicon1 = new ImageIcon(changeImg1);
	
	Image img2 = Cancelimg2.getImage();
	Image changeImg2 = img2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonicon2 = new ImageIcon(changeImg2);
	
	
	
	public PetAppDetailUI(String Key) {
		super("PetAppDetailUI");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		getContentPane().setBackground(Color.WHITE);
		
		//제목 항목
		JLabel TitleLabel = new JLabel("상세 정보");
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		add(TitleLabel);
		TitleLabel.setBounds(30, 50, 500, 70);
		
		// 구분선
		JSeparator JSepStart = new JSeparator();
		add(JSepStart);
		JSepStart.setBounds(0, 170, 600, 70);
		
		// Key값을 통해 선택한 신청의 정보 받아옴
		ApplicationList list = ApplicationList.getList();
		Application application;
		application = list.getForAcceptTable().get(Key);
		
		// 회원 이름
		JLabel MemberNameTitleLabel = new JLabel("회원 이름 ");
		add(MemberNameTitleLabel);
		MemberNameTitleLabel.setBounds(20,180,80,30);
		MemberNameTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel MemberNameContentLabel = new JLabel(""); // <- 회원 이름 넣으면 됩니다
		add(MemberNameContentLabel);
		MemberNameContentLabel.setBounds(200,180,600,30);
		MemberNameContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 이용 시간
		JLabel periodOfServiceTitleLabel = new JLabel("이용 시간 ");
		add(periodOfServiceTitleLabel);
		periodOfServiceTitleLabel.setBounds(20,240,80,30);
		periodOfServiceTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel periodOfServiceContentLabel = new JLabel(application.getPeriodOfService());
		add(periodOfServiceContentLabel);
		periodOfServiceContentLabel.setBounds(200,240,600,30);
		periodOfServiceContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		// 위치
		JLabel locationTitleLabel = new JLabel("위치 ");
		add(locationTitleLabel);
		locationTitleLabel.setBounds(20,300,80,30);
		locationTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel locationContentLabel = new JLabel(application.getLocation());
		add(locationContentLabel);
		locationContentLabel.setBounds(200,300,600,30);
		locationContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 신청 서비스 종류
		JLabel kindOfServicesTitleLabel = new JLabel("신청 서비스 종류 ");
		add(kindOfServicesTitleLabel);
		kindOfServicesTitleLabel.setBounds(20,360,200,30);
		kindOfServicesTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel kindOfServicesContentLabel = new JLabel(application.getKindOfServices());
		if(application.getKindOfServices().equals(""))
			kindOfServicesContentLabel.setText("신청 서비스 없음 ");
		add(kindOfServicesContentLabel);
		kindOfServicesContentLabel.setBounds(200,360,600,30);
		kindOfServicesContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 가격
		JLabel priceTitleLabel = new JLabel("가격 ");
		add(priceTitleLabel);
		priceTitleLabel.setBounds(20,420,80,30);
		priceTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel priceContentLabel = new JLabel(application.getPrice());
		add(priceContentLabel);
		priceContentLabel.setBounds(200,420,600,30);
		priceContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		/*
		// 반려동물 마리수
		JLabel PetnumTitleLabel = new JLabel("반려동물 마리수 ");
		add(PetnumTitleLabel);
		PetnumTitleLabel.setBounds(20,480,200,30);
		PetnumTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel PetnumContentLabel = new JLabel(); // <- 반려동물 마리수 넣으면 됨
		add(PetnumContentLabel);
		PetnumContentLabel.setBounds(200,480,600,30);
		PetnumContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 반려동물 나이
		JLabel PetageTitleLabel = new JLabel("반려동물 마리수 ");
		add(PetageTitleLabel);
		PetageTitleLabel.setBounds(20,540,200,30);
		PetageTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel PetageContentLabel = new JLabel(); // <- 반려동물 나이 넣으면 됨
		add(PetageContentLabel);
		PetageContentLabel.setBounds(200,540,600,30);
		PetageContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		*/
		
		
		JButton CancelButton = new JButton(CancelButtonicon1);
		add(CancelButton);
		CancelButton.setBounds(0, 660, 100, 100);
		CancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		CancelButton.setActionCommand("뒤로가기");
		CancelButton.setRolloverIcon(CancelButtonicon2);
		CancelButton.setBorderPainted(false);
		CancelButton.setContentAreaFilled(false);
		CancelButton.setFocusPainted(false);
		CancelButton.addActionListener(this);
		
		RoundedButton AcceptButton = new RoundedButton("수락");
		add(AcceptButton);
		c = new Color(64,126,219);
		AcceptButton.setBackground(c);
		AcceptButton.setForeground(Color.WHITE);
		AcceptButton.setBounds(450, 680, 100, 50);
		AcceptButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		AcceptButton.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("뒤로가기")) {
			dispose();
		}
		else if(ActionCmd.equals("수락")) {
			int ans = ConfirmUI.showConfirmDialog(this,"신청을 수락하시겠습니까?","확인 메세지",ConfirmUI.YES_NO_OPTION);
			if(ans == 0){ // 신청 수락
				ConfirmUI.showMessageDialog(this,"신청 수락이 완료되었습니다","신청 수락 완료");
			}
		}
		else {
			System.out.println("Unexpected Error");
			System.exit(0);
		}
	}
}
