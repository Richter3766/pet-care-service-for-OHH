package hci.petsitter;

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

import hci.utility.ConfirmUI;
import hci.utility.RoundedButton;
import pd.systemuser.*;
/*	  돌봄이 화면입니다.
 *	 
 *  대문 글자(안녕하세요! OOO 님)
 *  
 *  돌봄 서비스 신청 조회 버튼 -> PetAppSearchUI
 *  신청 내역 확인 버튼 -> PetAppListUI
 *  회원 정보 수정
 *  -> 이건 더미 버튼입니다
 *  Requirement 에는 있기도 하고 허전해서 넣었습니다
 *  
 *  로그아웃 -> LoginUI
 *  으로 구성됩니다
 */

@SuppressWarnings("serial")
public class PetSitterUI extends JFrame implements ActionListener{

	Color c;
	String name = "박태정";
	
	// 버튼 이미지 & 크기 변환
	ImageIcon logoutImg1 = new ImageIcon("././Image/ExitButton1.png");
	ImageIcon logoutImg2 = new ImageIcon("././Image/ExitButton2.png");
	
	Image img1 = logoutImg1.getImage();
	Image changeImg1 = img1.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	ImageIcon LogOutButtonIcon1 = new ImageIcon(changeImg1);
	
	Image img2 = logoutImg2.getImage();
	Image changeImg2 = img2.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	ImageIcon LogOutButtonIcon2 = new ImageIcon(changeImg2);
	
	PetSitter ThePetSitter;
	String ID;
	
	public PetSitterUI(String ID) {
		super("PetSitterUI");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		getContentPane().setBackground(Color.WHITE);
		
		ThePetSitter = PetSitter.getPetSitter(ID);
		
		// 대문 글자 (안녕하세요!)
		JLabel TitleLabel = new JLabel("안녕하세요!");
		TitleLabel.setHorizontalAlignment(JLabel.LEFT);
		TitleLabel.setForeground(Color.BLACK);
		TitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		add(TitleLabel);
		TitleLabel.setBounds(30, 50, 500, 70);
		
		// 대문 글자의 돌봄이 이름 부분
		JLabel NameLabel = new JLabel(ThePetSitter.getName() + "님");
		NameLabel.setHorizontalAlignment(JLabel.LEFT);
		c = new Color(103,155,245);
		NameLabel.setForeground(Color.BLACK);
		NameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		add(NameLabel);
		NameLabel.setBounds(320, 50, 500, 70);
		
		// 구분선
		JSeparator JSep = new JSeparator();
		add(JSep);
		JSep.setBounds(0, 170, 600, 70);
		
		// 돌봄 서비스 신청 조회 버튼
		RoundedButton ApplicationButton = new RoundedButton("돌봄 서비스 신청 조회");
		add(ApplicationButton);
		c = new Color(64,126,219);
		ApplicationButton.setBackground(c);
		ApplicationButton.setForeground(Color.WHITE);
		ApplicationButton.setBounds(30, 230, 530, 150);
		ApplicationButton.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		ApplicationButton.addActionListener(this);
		
		// 신청 내역 확인 버튼
		RoundedButton AppListButton = new RoundedButton("신청 내역 확인");
		add(AppListButton);
		c = new Color(162,202,255);
		AppListButton.setBackground(c);
		AppListButton.setForeground(Color.WHITE);
		AppListButton.setBounds(30, 430, 530, 50);
		AppListButton.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		AppListButton.addActionListener(this);
		
		// 돌봄이 정보 수정 버튼 (더미)
		RoundedButton ChangePetSitInfoButton = new RoundedButton("돌봄이 정보 수정");
		add(ChangePetSitInfoButton);
		c = new Color(162,202,255);
		ChangePetSitInfoButton.setBackground(c);
		ChangePetSitInfoButton.setForeground(Color.WHITE);
		ChangePetSitInfoButton.setBounds(30, 500, 530, 50);
		ChangePetSitInfoButton.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		
		// 로그아웃 버튼
		JButton LogoutButton = new JButton(LogOutButtonIcon1);
		add(LogoutButton);
		LogoutButton.setBounds(20, 670, 70, 70);
		LogoutButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		LogoutButton.setActionCommand("로그아웃");
		LogoutButton.setRolloverIcon(LogOutButtonIcon2);
		LogoutButton.setBorderPainted(false);
		LogoutButton.setContentAreaFilled(false);
		LogoutButton.setFocusPainted(false);
		LogoutButton.addActionListener(this);
		
		this.ID = ID;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("돌봄 서비스 신청 조회")) {
			PetAppSearchUI PetAppSearchWindow = new PetAppSearchUI(ID);
			PetAppSearchWindow.setVisible(true);
			dispose();
		}
		else if(ActionCmd.equals("신청 내역 확인")) {
			PetAppListUI PetAppListWindow = new PetAppListUI(ID);
			PetAppListWindow.setVisible(true);
			dispose();
		}
		else if(ActionCmd.equals("로그아웃")) {
			int ans = ConfirmUI.showConfirmDialog(this,"정말 로그아웃 하시겠습니까?","확인 메세지",ConfirmUI.YES_NO_OPTION);
			if(ans == 0){ // 로그아웃 수락
				dispose();
			}
		}
		else {
			System.out.println("Unexpected Error");
			System.exit(0);
		}
	}
}
