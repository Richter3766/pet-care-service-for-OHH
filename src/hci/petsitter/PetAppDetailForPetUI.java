package hci.petsitter;

import pd.systemuser.Member;
import pd.systemuser.Pet;

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


/*	 돌봄이 신청정보 세부정보 속 반려동물 정보 화면입니다.
 *	 
 *  대문 글자(회원의 반려동물 정보)
 *  
 *
 */

@SuppressWarnings("serial")
public class PetAppDetailForPetUI extends JFrame implements ActionListener{
	// 버튼 이미지 & 크기 변환
	ImageIcon CancelImg1 = new ImageIcon("././Image/CancelButton1.png");
	ImageIcon CancelImg2 = new ImageIcon("././Image/CancelButton2.png");
		
	Image img1 = CancelImg1.getImage();
	Image changeImg1 = img1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonIcon1 = new ImageIcon(changeImg1);
		
	Image img2 = CancelImg2.getImage();
	Image changeImg2 = img2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonIcon2 = new ImageIcon(changeImg2);
	
	
	public PetAppDetailForPetUI(Member theMember) {
		super("PetAppDetailForPetUI");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		getContentPane().setBackground(Color.WHITE);
		

		// 제목 항목
		JLabel TitleLabel = new JLabel("회원의 반려동물 정보");
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		add(TitleLabel);
		TitleLabel.setBounds(30, 50, 500, 70);
				
		// 구분선
		JSeparator JSepStart = new JSeparator();
		add(JSepStart);
		JSepStart.setBounds(0, 170, 600, 70);
		
		Pet thePet = theMember.getPets().get(0);
		String PetName = thePet.getPetName();
		Integer PetAge = thePet.getPetAge();
		String PetKind = thePet.getPetKind();
		String ChronicDisease = thePet.getChronicDisease();
		String KindOfFeed = thePet.getKindOfFeed();
		
		// 반려동물 이름
		JLabel NameTitleLabel = new JLabel("이름 ");
		add(NameTitleLabel);
		NameTitleLabel.setBounds(20,200,80,30);
		NameTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		JLabel NameContentLabel = new JLabel(PetName); // <- 회원 이름 넣으면 됩니다
		add(NameContentLabel);
		NameContentLabel.setBounds(200,200,600,30);
		NameContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 반려동물 나이
		JLabel AgeTitleLabel = new JLabel("나이 ");
		add(AgeTitleLabel);
		AgeTitleLabel.setBounds(20,280,80,30);
		AgeTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel AgeContentLabel = new JLabel(PetAge.toString());
		add(AgeContentLabel);
		AgeContentLabel.setBounds(200,280,600,30);
		AgeContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		// 반려동물 정보
		JLabel KindTitleLabel = new JLabel("종류 ");
		add(KindTitleLabel);
		KindTitleLabel.setBounds(20,360,80,30);
		KindTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel KindContentLabel = new JLabel(PetKind);
		add(KindContentLabel);
		KindContentLabel.setBounds(200,360,600,30);
		KindContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
						
		// 반려동물 지병
		JLabel ChronicDiseaseTitleLabel = new JLabel("지병 ");
		add(ChronicDiseaseTitleLabel);
		ChronicDiseaseTitleLabel.setBounds(20,440,200,30);
		ChronicDiseaseTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel ChronicDiseaseContentLabel = new JLabel(ChronicDisease);
		add(ChronicDiseaseContentLabel);
		ChronicDiseaseContentLabel.setBounds(200,440,600,30);
		ChronicDiseaseContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
						
		// 반려동물 가격
		JLabel KindOfFeedTitleLabel = new JLabel("가격 ");
		add(KindOfFeedTitleLabel);
		KindOfFeedTitleLabel.setBounds(20,520,80,30);
		KindOfFeedTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel KindOfFeedContentLabel = new JLabel(KindOfFeed);
		add(KindOfFeedContentLabel);
		KindOfFeedContentLabel.setBounds(200,520,600,30);
		KindOfFeedContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));		
		
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

		
	}
	
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("뒤로가기")) {
			dispose();
		}
		else {
			System.out.println("Unexpected Error");
			System.exit(0);
		}
	}
}
