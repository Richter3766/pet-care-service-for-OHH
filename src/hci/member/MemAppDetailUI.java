package hci.member;

import hci.utility.ConfirmUI;
import hci.utility.RoundedButton;
import pd.application.Application;
import pd.application.ApplicationList;
import pd.systemuser.PetSitter;

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

@SuppressWarnings("serial")
public class MemAppDetailUI extends JFrame implements ActionListener{

	
	Color c;
	
	private final int ACCEPT_TABLE = 0; // 수락 대기 Table
	private final int PAYMENT_TABLE = 1; // 결제 대기 Table
	private final int ACTIVE_TABLE = 2; // 진행중 Table
	private final int COMPLETE_TABLE = 3; // 완료 Table
	
	ImageIcon CancelImg1 = new ImageIcon("././Image/CancelButton1.png");
	ImageIcon CancelImg2 = new ImageIcon("././Image/CancelButton2.png");
	
	Image img1 = CancelImg1.getImage();
	Image changeImg1 = img1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonIcon1 = new ImageIcon(changeImg1);
	
	Image img2 = CancelImg2.getImage();
	Image changeImg2 = img2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonIcon2 = new ImageIcon(changeImg2);
	
	ApplicationList list = ApplicationList.getList();
	Application application;
	
	String applicationID;
	String PetSitID;
	PetSitter thePetSitter;
	int TableCheck = -1; // 테이블 확인용 변수
	
	public MemAppDetailUI(String applicationID) {
		super("MemAppDetailUI");
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
		
		this.applicationID = applicationID;
		// Key 값을 통해 선택한 신청의 정보 받아옴
		application = list.getForAcceptTable().get(applicationID); // 수락 대기 Table 에서 탐색
		TableCheck = ACCEPT_TABLE;
		if(application == null) {
			application = list.getForPaymentTable().get(applicationID); // 결제 대기 Table 에서 탐색
			TableCheck = PAYMENT_TABLE;
		}if(application == null) {
			application = list.getForCompleteTable().get(applicationID); // 완료 Table 에서 탐색
			TableCheck = ACTIVE_TABLE;
		}if(application == null) {
			application = list.getForActiveTable().get(applicationID); // 진행 Table 에서 탐색
			TableCheck = COMPLETE_TABLE;
		}if(application == null) {
			ConfirmUI.showMessageDialog(this,"신청 ID에 대한 신청 정보를 찾지 못했습니다.","오류 메세지"); // 모두 찾지 못했을 시 오류 메세지 출력
			dispose();
		}
		
		String PetSitName;
		PetSitID = application.getPetSitterID();
		if(PetSitID == null) {
			PetSitName = "";
		}else {
			thePetSitter = PetSitter.getPetSitter(PetSitID);
			PetSitName = thePetSitter.getName();
		}
		
		// 돌봄이 이름
		JLabel PetSitterNameTitleLabel = new JLabel("돌봄이 이름 ");
		add(PetSitterNameTitleLabel);
		PetSitterNameTitleLabel.setBounds(20,200,200,30);
		PetSitterNameTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel PetSitterNameContentLabel = new JLabel(PetSitName); // <- 돌봄이 이름 넣으면 됩니다
		add(PetSitterNameContentLabel);
		PetSitterNameContentLabel.setBounds(200,200,600,30);
		PetSitterNameContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 이용 시간
		JLabel periodOfServiceTitleLabel = new JLabel("이용 시간 ");
		add(periodOfServiceTitleLabel);
		periodOfServiceTitleLabel.setBounds(20,280,80,30);
		periodOfServiceTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel periodOfServiceContentLabel = new JLabel(application.getPeriodOfService());
		add(periodOfServiceContentLabel);
		periodOfServiceContentLabel.setBounds(200,280,600,30);
		periodOfServiceContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		// 위치
		JLabel locationTitleLabel = new JLabel("위치 ");
		add(locationTitleLabel);
		locationTitleLabel.setBounds(20,360,80,30);
		locationTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel locationContentLabel = new JLabel(application.getLocation());
		add(locationContentLabel);
		locationContentLabel.setBounds(200,360,600,30);
		locationContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				
		// 신청 서비스 종류
		JLabel kindOfServicesTitleLabel = new JLabel("신청 서비스 종류 ");
		add(kindOfServicesTitleLabel);
		kindOfServicesTitleLabel.setBounds(20,440,200,30);
		kindOfServicesTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel kindOfServicesContentLabel = new JLabel(application.getKindOfServices());
		if(application.getKindOfServices().equals(""))
		kindOfServicesContentLabel.setText("신청 서비스 없음 ");
		add(kindOfServicesContentLabel);
		kindOfServicesContentLabel.setBounds(200,440,600,30);
		kindOfServicesContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				
		// 가격
		JLabel priceTitleLabel = new JLabel("가격 ");
		add(priceTitleLabel);
		priceTitleLabel.setBounds(20,520,80,30);
		priceTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel priceContentLabel = new JLabel(application.getPrice());
		add(priceContentLabel);
		priceContentLabel.setBounds(200,520,600,30);
		priceContentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 돌봄이 세부 정보를 볼 수 있는 버튼 -> 결제 대기부터만 볼 수 있음
		RoundedButton PetSitterInfoButton = new RoundedButton("돌봄이 정보 보기");
		add(PetSitterInfoButton);
		c = new Color(64,126,219);
		PetSitterInfoButton.setBackground(c); 
		PetSitterInfoButton.setForeground(Color.WHITE); 
		PetSitterInfoButton.setBounds(30, 580, 530, 50); 
		PetSitterInfoButton.setFont(new Font("맑은 고딕", Font.BOLD, 20)); 
		PetSitterInfoButton.addActionListener(this);
		
		JButton CancelButton = new JButton(CancelButtonIcon1);
		add(CancelButton);
		CancelButton.setBounds(0, 660, 100, 100);
		CancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		CancelButton.setActionCommand("뒤로가기");
		CancelButton.setRolloverIcon(CancelButtonIcon2);
		CancelButton.setBorderPainted(false);
		CancelButton.setContentAreaFilled(false);
		CancelButton.setFocusPainted(false);
		CancelButton.addActionListener(this);
		
		PetSitterInfoButton.setVisible(false);
		if(TableCheck >= PAYMENT_TABLE) // 돌봄이 정보 보기 버튼은 결제 대기부터 활성화된다.
			PetSitterInfoButton.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("뒤로가기")) {
			dispose();
		}else if(ActionCmd.equals("돌봄이 정보 보기")) {
			MemAppDetailForPetSitterUI MemAppDetailForPetSitterWindow =  new MemAppDetailForPetSitterUI(PetSitID);
			MemAppDetailForPetSitterWindow.setVisible(true);
		}
		else {
			System.out.println("Unexpected Error");
			System.exit(0);
		}
	}
}
