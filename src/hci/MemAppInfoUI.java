package hci;

import pd.application.Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.text.DecimalFormat;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

/*	  회원 신청정보 입력화면입니다.
 *	 
 *  대문 글자(신청 정보 입력)
 *  
 *  이름 : 미리 회원정보에서 받아 입력
 *  시작 시간 : 회원이 직접 입력
 *  종료 시간 : 회원이 직접 입력
 *  위치 : 미리 회원정보에서 받아 입력
 *  신청 서비스 : 회원이 체크박스 체크
 *  가격 : 서비스 & 시간에 따라 계산
 *  뒤로가기 버튼 -> MemberUI
 *  제출 버튼 -> 확인 창 뜬 후 MemberUI
 *  으로 구성됩니다
 */

public class MemAppInfoUI extends JFrame implements ActionListener {

	final static int nameY = 20; 				// 이름 항목의 Y축 좌표
	final static int StartY = 80; 				// 시작 시간 항목의 Y축 좌표
	final static int EndY = 140; 				// 종료 시간 항목의 Y축 좌표
	final static int LocationY = 200; 			// 위치 항목의 Y축 좌표
	final static int ServiceY1 = 260; 			// 서비스 종류 항목의 Y축 좌표 1
	final static int ServiceY2 = 320; 			// 서비스 종류 항목의 Y축 좌표 2
	final static int priceY = 380;				// 가격 항목의 Y축 좌표

	DecimalFormat formatter = new DecimalFormat("###,###");
	Color c;
	String name = "박태정";
	String Location = "경북대학교 IT 대학";
	String price = "15,000원";
	int curPrice = 15000;
	int[] priceSet = new int[3];


	// 버튼 이미지 & 크기 변환
	ImageIcon CancelImg1 = new ImageIcon("././Image/CancelButton1.png");
	ImageIcon CancelImg2 = new ImageIcon("././Image/CancelButton2.png");
	
	Image img1 = CancelImg1.getImage();
	Image changeImg1 = img1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonIcon1 = new ImageIcon(changeImg1);
	
	Image img2 = CancelImg2.getImage();
	Image changeImg2 = img2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonIcon2 = new ImageIcon(changeImg2);
	
	protected JTextField NameField;

	protected JLabel StartYearNumLabel;
	protected JLabel EndYearNumLabel;

	protected JComboBox<String> StartMonthCombo;
	protected JComboBox<String> StartDayCombo;
	protected JComboBox<String> StartHourCombo;

	protected JComboBox<String> EndMonthCombo;
	protected JComboBox<String> EndDayCombo;
	protected JComboBox<String> EndHourCombo;
	
	protected JTextField LocationField;
	
	protected JCheckBox ServiceCheck1;
	protected JCheckBox ServiceCheck2;
	protected JCheckBox ServiceCheck3;
	
	protected JTextField PriceField;
	
	protected JComboBox<String> StartAMPMCombo;
	protected JComboBox<String> EndAMPMCombo;
	
	@SuppressWarnings("serial")
	public MemAppInfoUI() {
		super("MemAppInfoUI");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		getContentPane().setBackground(Color.WHITE);
		
		
		// 제목 항목
		JLabel TitleLabel = new JLabel("신청 정보 입력");
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		add(TitleLabel);
		TitleLabel.setBounds(30, 50, 500, 70);
		
		// 구분선
		JSeparator JSepStart = new JSeparator();
		add(JSepStart);
		JSepStart.setBounds(0, 170, 600, 70);
		
		// 신청 항목들을 담은 Panel
		JPanel ContentPanel = new JPanel();
		add(ContentPanel);
		ContentPanel.setBackground(Color.WHITE);
		ContentPanel.setBounds(0, 170, 600, 450);
		ContentPanel.setLayout(null);
		
	
		// 이름 항목
		JLabel NameLabel = new JLabel("이름 ");
		ContentPanel.add(NameLabel);
		NameLabel.setBounds(30,nameY,40,30);
		NameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 이름 텍스트필드 생성 & 테두리 없애기
		NameField = new JTextField(name){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        
		ContentPanel.add(NameField);
		NameField.setEditable(false);
		NameField.setBounds(140, nameY, 80, 30);
		NameField.setBackground(Color.WHITE);
		NameField.setForeground(Color.BLACK);
		NameField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		
		// 시작 시간 항목
		JLabel StartTimeLabel = new JLabel("시작 시간 ");
		ContentPanel.add(StartTimeLabel);
		StartTimeLabel.setBounds(30,StartY,80,30);
		StartTimeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		String[] YearString = new String[12];
		for(int i = 0;i<12;i++){
			YearString[i] = String.format("%02d", i+1);
		}

		String[] DayString = new String[31];
		for(int i = 0;i<31;i++){
			DayString[i] = String.format("%02d", i+1);
		}

		String[] HourString = new String[12];
		for(int i = 0;i<12;i++){
			HourString[i] = String.format("%d", i+1);
		}
		StartYearNumLabel = new JLabel("2022");
		ContentPanel.add(StartYearNumLabel);
		StartYearNumLabel.setBounds(140,StartY,50,30);
		StartYearNumLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel StartYearLabel = new JLabel("년");
		ContentPanel.add(StartYearLabel);
		StartYearLabel.setBounds(180,StartY,90,30);
		StartYearLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		StartMonthCombo = new JComboBox<>(YearString);
		ContentPanel.add(StartMonthCombo);
		StartMonthCombo.setBounds(200, StartY, 50, 30);
		StartMonthCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel StartMonthLabel = new JLabel("월");
		ContentPanel.add(StartMonthLabel);
		StartMonthLabel.setBounds(250,StartY,90,30);
		StartMonthLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		StartDayCombo = new JComboBox<>(DayString);
		ContentPanel.add(StartDayCombo);
		StartDayCombo.setBounds(280, StartY, 50, 30);
		StartDayCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));


		JLabel StartDayLabel = new JLabel("일");
		ContentPanel.add(StartDayLabel);
		StartDayLabel.setBounds(330,StartY,90,30);
		StartDayLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 오전 오후를 고르는 ComboBox
		String[] AMPMString = {"오전", "오후"};
		StartAMPMCombo = new JComboBox<>(AMPMString);
		ContentPanel.add(StartAMPMCombo);
		StartAMPMCombo.setBounds(360,StartY,70,30);
		StartAMPMCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		StartHourCombo = new JComboBox<>(HourString);
		ContentPanel.add(StartHourCombo);
		StartHourCombo.setBounds(430, StartY, 50, 30);
		StartHourCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel StartHourLabel = new JLabel("시");
		ContentPanel.add(StartHourLabel);
		StartHourLabel.setBounds(480,StartY,80,30);
		StartHourLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));


		// 종료 시간 항목
		JLabel EndTimeLabel = new JLabel("종료 시간 ");
		ContentPanel.add(EndTimeLabel);
		EndTimeLabel.setBounds(30,EndY,80,30);
		EndTimeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		EndYearNumLabel = new JLabel("2022");
		ContentPanel.add(EndYearNumLabel);
		EndYearNumLabel.setBounds(140,EndY,40,30);
		EndYearNumLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel EndYearLabel = new JLabel("년");
		ContentPanel.add(EndYearLabel);
		EndYearLabel.setBounds(180,EndY,40,30);
		EndYearLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		EndMonthCombo = new JComboBox<>(YearString);
		ContentPanel.add(EndMonthCombo);
		EndMonthCombo.setBounds(200, EndY, 50, 30);
		EndMonthCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel EndMonthLabel = new JLabel("월");
		ContentPanel.add(EndMonthLabel);
		EndMonthLabel.setBounds(250,EndY,80,30);
		EndMonthLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		EndDayCombo = new JComboBox<>(DayString);
		ContentPanel.add(EndDayCombo);
		EndDayCombo.setBounds(280, EndY, 50, 30);
		EndDayCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel EndDayLabel = new JLabel("일");
		ContentPanel.add(EndDayLabel);
		EndDayLabel.setBounds(330,EndY,80,30);
		EndDayLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		EndAMPMCombo = new JComboBox<>(AMPMString);
		ContentPanel.add(EndAMPMCombo);
		EndAMPMCombo.setBounds(360,EndY,70,30);
		EndAMPMCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		EndHourCombo = new JComboBox<>(HourString);
		ContentPanel.add(EndHourCombo);
		EndHourCombo.setBounds(430, EndY, 50, 30);
		EndHourCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel EndHourLabel = new JLabel("시");
		ContentPanel.add(EndHourLabel);
		EndHourLabel.setBounds(480,EndY,80,30);
		EndHourLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		
		// 위치 항목
		JLabel LocationLabel = new JLabel("위치 ");
		ContentPanel.add(LocationLabel);
		LocationLabel.setBounds(30,LocationY,40,30);
		LocationLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		LocationField = new JTextField(Location){
            @Override
            public void setBorder(Border border) {
                
            }
        };
		ContentPanel.add(LocationField);
		LocationField.setEditable(false);
		LocationField.setBounds(140,LocationY,350,31);
		LocationField.setBackground(Color.WHITE);
		LocationField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 신청 서비스 종류 항목
		JLabel ServiceTypeLabel = new JLabel("신청 서비스");
		ContentPanel.add(ServiceTypeLabel);
		ServiceTypeLabel.setBounds(30,ServiceY1,130,30);
		ServiceTypeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		// 가격 정보 초기화
		priceSet[0] = 5000;
		priceSet[1] = 20000;
		priceSet[2] = 50000;

		ServiceCheck1 = new JCheckBox("산책 - 5,000 원");
		ServiceCheck1.addActionListener(e -> {
			JCheckBox jCheckBox = (JCheckBox) e.getSource();
			if(jCheckBox.isSelected()){
				curPrice = curPrice + priceSet[0];
			}
			else{
				curPrice = curPrice - priceSet[0];
			}
			String result = formatter.format(curPrice) + "원";
			PriceField.setText(result);
		});
		ContentPanel.add(ServiceCheck1);
		ServiceCheck1.setBounds(140,ServiceY1,180,30);
		ServiceCheck1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		ServiceCheck1.setBackground(Color.WHITE);


		ServiceCheck2 = new JCheckBox("목욕 - 20,000 원");
		ServiceCheck2.addActionListener(e -> {
				JCheckBox jCheckBox = (JCheckBox) e.getSource();
				if(jCheckBox.isSelected()){
					curPrice = curPrice + priceSet[1];
				}
				else{
					curPrice = curPrice - priceSet[1];
				}
				String result = formatter.format(curPrice) + "원";
				PriceField.setText(result);
	});
		ContentPanel.add(ServiceCheck2);
		ServiceCheck2.setBounds(350,ServiceY1,180,30);
		ServiceCheck2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		ServiceCheck2.setBackground(Color.WHITE);
		
		ServiceCheck3 = new JCheckBox("미용 - 50,000 원");
		ServiceCheck3.addActionListener(e -> {
			JCheckBox jCheckBox = (JCheckBox) e.getSource();
			if(jCheckBox.isSelected()){
				curPrice = curPrice + priceSet[2];
			}
			else{
				curPrice = curPrice - priceSet[2];
			}
			String result = formatter.format(curPrice) + "원";
			PriceField.setText(result);
		});
		ContentPanel.add(ServiceCheck3);
		ServiceCheck3.setBounds(140,ServiceY2,180,30);
		ServiceCheck3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		ServiceCheck3.setBackground(Color.WHITE);
		
//		ServiceCheck4 = new JCheckBox("서비스 4");
//		ContentPanel.add(ServiceCheck4);
//		ServiceCheck4.setBounds(350,ServiceY2,150,30);
//		ServiceCheck4.setFont(new Font("맑은 고딕", Font.BOLD, 20));
//		ServiceCheck4.setBackground(Color.WHITE);
//
		// 가격 항목
		JLabel PriceLabel = new JLabel("가격 ");
		ContentPanel.add(PriceLabel);
		PriceLabel.setBounds(30,priceY,40,30);
		PriceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		PriceField = new JTextField(price){
            @Override
            public void setBorder(Border border) {
                
            }
        };
		ContentPanel.add(PriceField);
		PriceField.setEditable(false);
		PriceField.setBounds(140,priceY,80,30);
		PriceField.setBackground(Color.WHITE);
		PriceField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 구분선
		JSeparator JSepEnd = new JSeparator();
		add(JSepEnd);
		JSepEnd.setBounds(0, 620, 600, 70);
				
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
		
	}

	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("뒤로가기")) {
			MemberUI MemberWindow = new MemberUI();
			MemberWindow.setVisible(true);
			dispose();
		}
		else if(ActionCmd.equals("제출")) {
			int ans = ConfirmUI.showConfirmDialog(this,"제출하시겠습니까?","확인 메세지",ConfirmUI.YES_NO_OPTION);
			if(ans == 0){ // 제출 수락
				Application application = new Application();
				String isRedundant;
				String services = "";

				String start = String.join(".", StartYearNumLabel.getText(),
						Objects.requireNonNull(StartMonthCombo.getSelectedItem()).toString(),
						Objects.requireNonNull(StartDayCombo.getSelectedItem()).toString(),
						Objects.requireNonNull(StartAMPMCombo.getSelectedItem()).toString(),
						Objects.requireNonNull(StartHourCombo.getSelectedItem()).toString() + "시");
				String end = String.join(".", EndYearNumLabel.getText(),
						Objects.requireNonNull(EndMonthCombo.getSelectedItem()).toString(),
						Objects.requireNonNull(EndDayCombo.getSelectedItem()).toString(),
						Objects.requireNonNull(EndAMPMCombo.getSelectedItem()).toString(),
						Objects.requireNonNull(EndHourCombo.getSelectedItem()).toString() + "시");
				application.setPeriodOfService(String.join(" ~ ", start, end));
				application.setLocation(LocationField.getText());
				System.out.println(application.getPeriodOfService());
				if(ServiceCheck1.isSelected()){
					services = services + ServiceCheck1.getText().split(" ")[0];
				}
				if(ServiceCheck2.isSelected()){
					if(ServiceCheck1.isSelected()){
						services = services + ", ";
					}
					services = services + ServiceCheck2.getText().split(" ")[0];
				}
				if(ServiceCheck3.isSelected()){
					if(ServiceCheck1.isSelected() || ServiceCheck2.isSelected()){
						services = services + ", ";
					}
					services = services + ServiceCheck3.getText().split(" ")[0];
				}
				application.setKindOfServices(services);
				application.setPrice(PriceField.getText());
				// 회원 아이디를 얻는 법 필요
				application.setApplicationID("임시 ID");

				if((isRedundant = application.requestApplication()) != null){
					int doYouReplace = ConfirmUI.showConfirmDialog(this,
							"신청이 존재합니다.\n대체하시겠습니까?","확인 메세지",
							ConfirmUI.YES_NO_OPTION);
					if(doYouReplace == 0){
						application.requestReplaceApplication(isRedundant);
						application.requestApplication();
					}
					else{
						ConfirmUI.showMessageDialog(this,"신청이 취소되었습니다","신청 취소");
						dispose();
					}
				}
				ConfirmUI.showMessageDialog(this,"신청이 완료되었습니다","신청 완료");
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
