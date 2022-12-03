package hci.member;

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

import pd.systemuser.PetSitter;

/*	 회원 신청정보 세부정보 속 돌봄이 정보 화면입니다.
 *	 
 *  대문 글자(돌봄이 상세정보)
 *  
 *
 */
@SuppressWarnings("serial")
public class MemAppDetailForPetSitterUI extends JFrame implements ActionListener{
	
	// 버튼 이미지 & 크기 변환
		ImageIcon CancelImg1 = new ImageIcon("././Image/CancelButton1.png");
		ImageIcon CancelImg2 = new ImageIcon("././Image/CancelButton2.png");
			
		Image img1 = CancelImg1.getImage();
		Image changeImg1 = img1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon CancelButtonIcon1 = new ImageIcon(changeImg1);
			
		Image img2 = CancelImg2.getImage();
		Image changeImg2 = img2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon CancelButtonIcon2 = new ImageIcon(changeImg2);
	
		String ID;
		PetSitter thePetSitter;
		
	public MemAppDetailForPetSitterUI(String ID) {
		super("MemAppDetailForPetSitterUI");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		getContentPane().setBackground(Color.WHITE);
		
		// 제목 항목
		JLabel TitleLabel = new JLabel("돌봄이 상세정보");
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		add(TitleLabel);
		TitleLabel.setBounds(30, 50, 500, 70);
				
		// 구분선
		JSeparator JSepStart = new JSeparator();
		add(JSepStart);
		JSepStart.setBounds(0, 170, 600, 70);
		
		thePetSitter = PetSitter.getPetSitter(ID);
		String PetSitName = thePetSitter.getName();
		Integer PetSitAge = thePetSitter.getAge();
		String PetSitAddress = thePetSitter.getAddress();
		String PetSitCellPhoneContact = thePetSitter.getCellPhoneContact();
		String PetSitEmail = thePetSitter.getEmail();
		String PetSitCertificate = thePetSitter.getCertificate();
		
		// 돌봄이 이름
		JLabel PetSitterNameTitleLabel = new JLabel("이름 ");
		add(PetSitterNameTitleLabel);
		PetSitterNameTitleLabel.setBounds(20,200,200,30);
		PetSitterNameTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel PetSitterNameContentLabel = new JLabel(PetSitName);
		add(PetSitterNameContentLabel);
		PetSitterNameContentLabel.setBounds(200,200,600,30);
		PetSitterNameContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			
		// 돌봄이 나이
		JLabel PetSitterAgeTitleLabel = new JLabel("나이 ");
		add(PetSitterAgeTitleLabel);
		PetSitterAgeTitleLabel.setBounds(20,270,200,30);
		PetSitterAgeTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel PetSitterAgeContentLabel = new JLabel(PetSitAge.toString());
		add(PetSitterAgeContentLabel);
		PetSitterAgeContentLabel.setBounds(200,270,600,30);
		PetSitterAgeContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 돌봄이 주소
		JLabel PetSitterAddressTitleLabel = new JLabel("주소 ");
		add(PetSitterAddressTitleLabel);
		PetSitterAddressTitleLabel.setBounds(20,340,200,30);
		PetSitterAddressTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel PetSitterAddressContentLabel = new JLabel(PetSitAddress);
		add(PetSitterAddressContentLabel);
		PetSitterAddressContentLabel.setBounds(200,340,600,30);
		PetSitterAddressContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 돌봄이 연락처
		JLabel PetSitterCellPhoneContactTitleLabel = new JLabel("연락처 ");
		add(PetSitterCellPhoneContactTitleLabel);
		PetSitterCellPhoneContactTitleLabel.setBounds(20,410,200,30);
		PetSitterCellPhoneContactTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel PetSitterCellPhoneContactContentLabel = new JLabel(PetSitCellPhoneContact);
		add(PetSitterCellPhoneContactContentLabel);
		PetSitterCellPhoneContactContentLabel.setBounds(200,410,600,30);
		PetSitterCellPhoneContactContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 돌봄이 이메일
		JLabel PetSitterEmailTitleLabel = new JLabel("이메일 ");
		add(PetSitterEmailTitleLabel);
		PetSitterEmailTitleLabel.setBounds(20,480,200,30);
		PetSitterEmailTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel PetSitterEmailContentLabel = new JLabel(PetSitEmail); 
		add(PetSitterEmailContentLabel);
		PetSitterEmailContentLabel.setBounds(200,480,600,30);
		PetSitterEmailContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 돌봄이 자격증
		JLabel PetSitterCertificateTitleLabel = new JLabel("자격증 ");
		add(PetSitterCertificateTitleLabel);
		PetSitterCertificateTitleLabel.setBounds(20,550,200,30);
		PetSitterCertificateTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel PetSitterCertificateContentLabel = new JLabel(PetSitCertificate);
		add(PetSitterCertificateContentLabel);
		PetSitterCertificateContentLabel.setBounds(200,550,600,30);
		PetSitterCertificateContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			
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
		
		this.ID = ID;
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
