package hci.login;

import hci.utility.ConfirmUI;
import hci.utility.RoundedButton;
import pd.systemuser.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class RegisterUI extends JFrame implements ActionListener  {

	final static int nameY = 40; 						// 이름 항목의 Y축 좌표
	final static int BirthY = 100; 						// 시작 시간 항목의 Y축 좌표
	final static int AgeY = 160; 						// 종료 시간 항목의 Y축 좌표
	final static int AddressY = 220; 					// 위치 항목의 Y축 좌표
	final static int CellPhoneContactY = 280; 		  	// 서비스 종류 항목의 Y축 좌표 1
	final static int EmailY = 340; 						// 서비스 종류 항목의 Y축 좌표 2
	
	final static int loginIDY = 40;						// ID 항목의 Y축 좌표
	final static int loginIDCheckY = 100;
	final static int PasswordY = 160;					// 비밀번호 항목의 Y축 좌표
	final static int EnterWayY = 220; 					// 출입 방법 항목의 Y축 좌표
	final static int NumOfPetY = 280; 					// 반려동물 수 항목의 Y축 좌표
	
	final static int CertificateY = 220;
	
	Color c;
	
	private String name;
	private String birth;
	private int age;
	private String address;
	private String cellPhoneContact;
	private String email;
	
	private String loginID;
	private String password;
	private String enterWay;
	private int numOfPet;
	
	private String certificate;
	
	boolean IDChecked = false;
	
	Member theMember;
	PetSitter thePetSitter;
	
	// 버튼 이미지 & 크기 변환
	ImageIcon CancelImg1 = new ImageIcon("././Image/CancelButton1.png");
	ImageIcon CancelImg2 = new ImageIcon("././Image/CancelButton2.png");
	
	Image img1 = CancelImg1.getImage();
	Image changeImg1 = img1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonIcon1 = new ImageIcon(changeImg1);
	
	Image img2 = CancelImg2.getImage();
	Image changeImg2 = img2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonIcon2 = new ImageIcon(changeImg2);
	
	JPanel ContentPanel1;
	JPanel ContentPanel2;
	
	protected JTextField NameField;

	protected JComboBox<String> YearCombo;
	protected JComboBox<String> MonthCombo;
	protected JComboBox<String> DayCombo;
	protected JComboBox<Integer> AgeCombo;
	protected JComboBox<Integer> NumOfPetCombo;
	
	protected JTextField AddressField;
	protected JTextField AgeField;
	protected JTextField CellPhoneContactField;
	protected JTextField EmailField;
	
	protected JTextField LoginIDField;
	protected JPasswordField PasswordField;
	protected JTextField EnterWayField;
	
	protected JTextField CertificateField;
	
	protected JLabel LoginIDCheckLabel;
	
	RoundedButton SubmitButton;
	RoundedButton PreviouspageButton;
	RoundedButton NextpageButton;
	RoundedButton LoginIDCheckButton;
	
	String Role;
	public RegisterUI(String Role) {
		super("RegisterUI");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		getContentPane().setBackground(Color.WHITE);
		
		
		// 제목 항목
		JLabel TitleLabel = new JLabel("회원 정보 입력");
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		add(TitleLabel);
		TitleLabel.setBounds(30, 50, 500, 70);
		
		// 구분선
		JSeparator JSepStart = new JSeparator();
		add(JSepStart);
		JSepStart.setBounds(0, 170, 600, 70);
		
		// 항목들을 담은 Panel 1페이지
		ContentPanel1 = new JPanel();
		add(ContentPanel1);
		ContentPanel1.setBackground(Color.WHITE);
		ContentPanel1.setBounds(0, 170, 600, 450);
		ContentPanel1.setLayout(null);
		
		// 항목들을 담은 Panel 2페이지
		ContentPanel2 = new JPanel();
		add(ContentPanel2);
		ContentPanel2.setBackground(Color.WHITE);
		ContentPanel2.setBounds(0, 170, 600, 450);
		ContentPanel2.setLayout(null);
	
		// 이름 항목
		JLabel NameLabel = new JLabel("이름 ");
		ContentPanel1.add(NameLabel);
		NameLabel.setBounds(30,nameY,40,30);
		NameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 이름 텍스트필드 생성 & 테두리 없애기
		NameField = new JTextField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        
		ContentPanel1.add(NameField);
		NameField.setEditable(true);
		NameField.setBounds(140, nameY, 80, 30);
		NameField.setBackground(Color.LIGHT_GRAY);
		NameField.setForeground(Color.BLACK);
		NameField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		
		// 생년월일 항목
		JLabel BirthLabel = new JLabel("생년월일 ");
		ContentPanel1.add(BirthLabel);
		BirthLabel.setBounds(30,BirthY,80,30);
		BirthLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		String[] YearString = new String[100];
		for(int i = 0, j = 1922;i<100;i++,j++){
			YearString[i] = String.format("%04d", j+1);
		}
		
		String[] MonthString = new String[12];
		for(int i = 0;i<12;i++){
			MonthString[i] = String.format("%02d", i+1);
		}

		String[] DayString = new String[31];
		for(int i = 0;i<31;i++){
			DayString[i] = String.format("%02d", i+1);
		}

		String[] HourString = new String[12];
		for(int i = 0;i<12;i++){
			HourString[i] = String.format("%d", i+1);
		}
		
		YearCombo = new JComboBox<>(YearString);
		ContentPanel1.add(YearCombo);
		YearCombo.setBounds(140, BirthY, 70, 30);
		YearCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel YearLabel = new JLabel("년");
		ContentPanel1.add(YearLabel);
		YearLabel.setBounds(210,BirthY,30,30);
		YearLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		MonthCombo = new JComboBox<>(MonthString);
		ContentPanel1.add(MonthCombo);
		MonthCombo.setBounds(240, BirthY, 50, 30);
		MonthCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		JLabel MonthLabel = new JLabel("월");
		ContentPanel1.add(MonthLabel);
		MonthLabel.setBounds(290,BirthY,30,30);
		MonthLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		DayCombo = new JComboBox<>(DayString);
		ContentPanel1.add(DayCombo);
		DayCombo.setBounds(320, BirthY, 50, 30);
		DayCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));


		JLabel DayLabel = new JLabel("일");
		ContentPanel1.add(DayLabel);
		DayLabel.setBounds(370,BirthY,90,30);
		DayLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 나이 항목
		
		Integer[] AgeString = new Integer[100];
		for(int i = 0; i<100;i++){
			AgeString[i] = i+1;
		}
		
		JLabel AgeLabel = new JLabel("나이 ");
		ContentPanel1.add(AgeLabel);
		AgeLabel.setBounds(30,AgeY,40,30);
		AgeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				
		AgeCombo = new JComboBox<>(AgeString);
		ContentPanel1.add(AgeCombo);
		AgeCombo.setBounds(140,AgeY,50,31);
		AgeCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 주소 항목
		JLabel AddressLabel = new JLabel("주소 ");
		ContentPanel1.add(AddressLabel);
		AddressLabel.setBounds(30,AddressY,40,30);
		AddressLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		AddressField = new JTextField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
		ContentPanel1.add(AddressField);
		AddressField.setEditable(true);
		AddressField.setBounds(140,AddressY,350,31);
		AddressField.setBackground(Color.LIGHT_GRAY);
		AddressField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 연락처
		JLabel CellPhoneContactLabel = new JLabel("연락처 ");
		ContentPanel1.add(CellPhoneContactLabel);
		CellPhoneContactLabel.setBounds(30,CellPhoneContactY,50,30);
		CellPhoneContactLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		
		CellPhoneContactField = new JTextField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
		ContentPanel1.add(CellPhoneContactField);
		CellPhoneContactField.setEditable(true);
		CellPhoneContactField.setBounds(140,CellPhoneContactY,150,31);
		CellPhoneContactField.setBackground(Color.LIGHT_GRAY);
		CellPhoneContactField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 이메일
		JLabel EmailLabel = new JLabel("이메일 주소 ");
		ContentPanel1.add(EmailLabel);
		EmailLabel.setBounds(30,EmailY,90,30);
		EmailLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		EmailField = new JTextField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
		ContentPanel1.add(EmailField);
		EmailField.setEditable(true);
		EmailField.setBounds(140,EmailY,350,31);
		EmailField.setBackground(Color.LIGHT_GRAY);
		EmailField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 로그인 ID 항목 (페이지 2)
		JLabel LoginIDLabel = new JLabel("ID ");
		ContentPanel2.add(LoginIDLabel);
		LoginIDLabel.setBounds(30,nameY,40,30);
		LoginIDLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				
		// 로그인ID 텍스트필드 생성 & 테두리 없애기
		LoginIDField = new JTextField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
		        
		ContentPanel2.add(LoginIDField);
		LoginIDField.setEditable(true);
		LoginIDField.setBounds(140, loginIDY, 120, 30);
		LoginIDField.setBackground(Color.LIGHT_GRAY);
		LoginIDField.setForeground(Color.BLACK);
		LoginIDField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		LoginIDCheckButton = new RoundedButton("ID 중복확인");
		c = new Color(64,126,219);
		LoginIDCheckButton.setBackground(c);
		LoginIDCheckButton.setForeground(Color.WHITE);
		LoginIDCheckButton.setBounds(270, loginIDY, 150, 30);
		LoginIDCheckButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		ContentPanel2.add(LoginIDCheckButton);
		LoginIDCheckButton.setVisible(false);
		LoginIDCheckButton.addActionListener(this);
		
		// 회원 비밀번호 항목 (페이지 2)
		JLabel PasswordLabel = new JLabel("비밀번호 ");
		ContentPanel2.add(PasswordLabel);
		PasswordLabel.setBounds(30,PasswordY,80,30);
		PasswordLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
						
		// 비밀번호 텍스트필드 생성 & 테두리 없애기
		PasswordField = new JPasswordField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        
        LoginIDCheckLabel = new JLabel("아이디 중복 체크 미완료");
        ContentPanel2.add(LoginIDCheckLabel);
        c = new Color(240,62,62);
        LoginIDCheckLabel.setBounds(30, loginIDCheckY, 200, 30);
        LoginIDCheckLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        LoginIDCheckLabel.setForeground(c);
        
		ContentPanel2.add(PasswordField);
		PasswordField.setEditable(true);
		PasswordField.setBounds(140, PasswordY, 120, 30);
		PasswordField.setBackground(Color.LIGHT_GRAY);
		PasswordField.setForeground(Color.BLACK);
		PasswordField.setVisible(false);
		PasswordField.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		// 회원 출입방법 항목 (페이지 2)
		JLabel EnterWayLabel = new JLabel("출입 방법 ");
		ContentPanel2.add(EnterWayLabel);
		EnterWayLabel.setBounds(30,EnterWayY,80,30);
		EnterWayLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			
		// 자격증 항목 (페이지 2)
		JLabel CertificateLabel = new JLabel("자격증 ");
		ContentPanel2.add(CertificateLabel);
		CertificateLabel.setBounds(30,CertificateY,80,30);
		CertificateLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 출입방법 텍스트필드 생성 & 테두리 없애기
		EnterWayField = new JTextField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
				        
        ContentPanel2.add(EnterWayField);
		EnterWayField.setEditable(true);
		EnterWayField.setBounds(140, EnterWayY, 200, 30);
		EnterWayField.setBackground(Color.LIGHT_GRAY);
		EnterWayField.setForeground(Color.BLACK);
		EnterWayField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		
		// 회원 반려동물 수 (페이지 2)
		JLabel NumOfPetLabel = new JLabel("반려동물 수 ");
		ContentPanel2.add(NumOfPetLabel);
		NumOfPetLabel.setBounds(30,NumOfPetY,100,30);
		NumOfPetLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				
		Integer[] NumOfPetString = new Integer[5];
		for(int i = 0;i<5;i++){
			NumOfPetString[i] = i+1;
		}
		
		
		NumOfPetCombo = new JComboBox<>(NumOfPetString);
		ContentPanel2.add(NumOfPetCombo);
		NumOfPetCombo.setBounds(140, NumOfPetY, 40, 30);
		NumOfPetCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 자격증 텍스트필드 생성 & 테두리 없애기
		CertificateField = new JTextField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
				        
        ContentPanel2.add(CertificateField);
		CertificateField.setEditable(true);
		CertificateField.setBounds(140, CertificateY, 200, 30);
		CertificateField.setBackground(Color.LIGHT_GRAY);
		CertificateField.setForeground(Color.BLACK);
		CertificateField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
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
		
		// 가입 버튼
		SubmitButton = new RoundedButton("가입");
		add(SubmitButton);
		c = new Color(64,126,219);
		SubmitButton.setBackground(c);
		SubmitButton.setForeground(Color.WHITE);
		SubmitButton.setBounds(450, 680, 100, 50);
		SubmitButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		SubmitButton.setVisible(false);
		SubmitButton.addActionListener(this);
		
		NextpageButton = new RoundedButton("다음 페이지");
		add(NextpageButton);
		c = new Color(64,126,219);
		NextpageButton.setBackground(c);
		NextpageButton.setForeground(Color.WHITE);
		NextpageButton.setBounds(400, 680, 150, 50);
		NextpageButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		NextpageButton.addActionListener(this);
		
		PreviouspageButton = new RoundedButton("이전 페이지");
		add(PreviouspageButton);
		c = new Color(64,126,219);
		PreviouspageButton.setBackground(c);
		PreviouspageButton.setForeground(Color.WHITE);
		PreviouspageButton.setBounds(280, 680, 150, 50);
		PreviouspageButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		PreviouspageButton.setVisible(false);
		PreviouspageButton.addActionListener(this);
		
		if(Role.equals("Member")) {
			TitleLabel.setText("회원 정보 입력");
			EnterWayField.setVisible(true);
			NumOfPetCombo.setVisible(true);
			NumOfPetLabel.setVisible(true);
			EnterWayLabel.setVisible(true);
			CertificateField.setVisible(false);
			CertificateLabel.setVisible(false);
		}else if(Role.equals("PetSitter")) {
			TitleLabel.setText("돌봄이 정보 입력");
			CertificateField.setVisible(true);
			CertificateLabel.setVisible(true);
			EnterWayField.setVisible(false);
			NumOfPetCombo.setVisible(false);
			NumOfPetLabel.setVisible(false);
			EnterWayLabel.setVisible(false);
		}
		
		this.Role = Role;
	}
	
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("뒤로가기")) {
			dispose();
		}else if(ActionCmd.equals("다음 페이지")) {
			ContentPanel1.setVisible(false);
			ContentPanel2.setVisible(true);
			NextpageButton.setVisible(false);
			PreviouspageButton.setVisible(true);
			LoginIDCheckButton.setVisible(true);
			SubmitButton.setVisible(true);
			PasswordField.setVisible(true);
		}else if(ActionCmd.equals("이전 페이지")) {
			ContentPanel2.setVisible(false);
			ContentPanel1.setVisible(true);
			PreviouspageButton.setVisible(false);
			NextpageButton.setVisible(true);
			SubmitButton.setVisible(false);
			LoginIDCheckButton.setVisible(false);
			PasswordField.setVisible(false);
		}else if(ActionCmd.equals("ID 중복확인")) {
			String ID = LoginIDField.getText();
			if(ID == null) {
				ConfirmUI.showMessageDialog(this, "ID를 입력하세요.", "중복 확인");
			}else if(ID.length() < 5) {
				ConfirmUI.showMessageDialog(this, "ID는 5자리 이상이어야합니다", "중복 확인");
			}else if(Member.getMember(ID) == null) {
				ConfirmUI.showMessageDialog(this, ID + "는 사용 가능합니다.", "중복 확인");
				int ans = ConfirmUI.showConfirmDialog(this, ID + "\n 이 ID를  사용하시겠습니까?", "중복 확인",ConfirmUI.YES_NO_OPTION);
				if(ans == 0) {
					IDChecked = true;
					LoginIDCheckButton.setVisible(false);
					LoginIDCheckLabel.setText("아이디 중복 체크 완료");
					LoginIDCheckLabel.setForeground(Color.BLACK);
					LoginIDField.setEditable(false);
				}
			}else {
				ConfirmUI.showMessageDialog(this, "이미 사용중인 ID입니다.", "중복 확인");
			}
		}else if(ActionCmd.equals("가입")) {
			if(Role.equals("Member")) {
				name = NameField.getText();
				birth = String.join(".",YearCombo.getSelectedItem().toString(),
						Objects.requireNonNull(MonthCombo.getSelectedItem()).toString(),
						Objects.requireNonNull(DayCombo.getSelectedItem()).toString());
				
				age = (int)AgeCombo.getSelectedItem();
				address = AddressField.getText();
				cellPhoneContact = CellPhoneContactField.getText();
				email = EmailField.getText();
				loginID = LoginIDField.getText();
				password = "";
				//PasswordField에서 패스워드를 얻어옴, char[] 배열에 저장
				char[] secret_pw = PasswordField.getPassword(); 

				//secret_pw 배열에 저장된 암호의 자릿수 만큼 for문 돌리면서 cha 에 한 글자씩 저장
				for(char cha : secret_pw){         
					Character.toString(cha);       //cha 에 저장된 값 string으로 변환
					//Password 에 저장하기, Password 에 값이 비어있으면 저장, 값이 있으면 이어서 저장하는 삼항연산자
					password += (password.equals("")) ? ""+cha+"" : ""+cha+"";   
					}
				enterWay = EnterWayField.getText();
				numOfPet = (int)NumOfPetCombo.getSelectedItem();
			
				if(name == "") 
					ConfirmUI.showMessageDialog(this, "이름을 입력해주세요.", "가입 실패");
				else if(birth == "") 
					ConfirmUI.showMessageDialog(this, "생년월일을 입력해주세요.", "가입 실패");
				else if(address == "") 
					ConfirmUI.showMessageDialog(this, "주소를 입력해주세요.", "가입 실패");
				else if(cellPhoneContact == "") 
					ConfirmUI.showMessageDialog(this, "연락처를 입력해주세요.", "가입 실패");
				else if(email == "") 
					ConfirmUI.showMessageDialog(this, "이메일을 입력해주세요.", "가입 실패");
				else if(IDChecked == false)
					ConfirmUI.showMessageDialog(this, "ID 중복체크를 해주세요.", "가입 실패");
				else if(password == "") 
					ConfirmUI.showMessageDialog(this, "비밀번호를 입력해주세요.", "가입 실패");
				else if(enterWay == "") 
					ConfirmUI.showMessageDialog(this, "출입 방법을 입력해주세요.", "가입 실패");
				else {
				    theMember = new Member(name,birth,age,address,cellPhoneContact,email,loginID,password,loginID,enterWay,numOfPet);
					ConfirmUI.showMessageDialog(this, "회원가입이 완료되었습니다.", "가입 완료");
					dispose();
				}
			}else if(Role.equals("PetSitter")) {
				name = NameField.getText();
				birth = String.join(".",YearCombo.getSelectedItem().toString(),
						Objects.requireNonNull(MonthCombo.getSelectedItem()).toString(),
						Objects.requireNonNull(DayCombo.getSelectedItem()).toString());
				
				age = (int)AgeCombo.getSelectedItem();
				address = AddressField.getText();
				cellPhoneContact = CellPhoneContactField.getText();
				email = EmailField.getText();
				loginID = LoginIDField.getText();
				password = "";
				//PasswordField에서 패스워드를 얻어옴, char[] 배열에 저장
				char[] secret_pw = PasswordField.getPassword(); 

				//secret_pw 배열에 저장된 암호의 자릿수 만큼 for문 돌리면서 cha 에 한 글자씩 저장
				for(char cha : secret_pw){         
					Character.toString(cha);       //cha 에 저장된 값 string으로 변환
					//Password 에 저장하기, Password 에 값이 비어있으면 저장, 값이 있으면 이어서 저장하는 삼항연산자
					password += (password.equals("")) ? ""+cha+"" : ""+cha+"";   
					}
				certificate = CertificateField.getText();
				
				if(name == "") 
					ConfirmUI.showMessageDialog(this, "이름을 입력해주세요.", "가입 실패");
				else if(birth == "") 
					ConfirmUI.showMessageDialog(this, "생년월일을 입력해주세요.", "가입 실패");
				else if(address == "") 
					ConfirmUI.showMessageDialog(this, "주소를 입력해주세요.", "가입 실패");
				else if(cellPhoneContact == "") 
					ConfirmUI.showMessageDialog(this, "연락처를 입력해주세요.", "가입 실패");
				else if(email == "") 
					ConfirmUI.showMessageDialog(this, "이메일을 입력해주세요.", "가입 실패");
				else if(IDChecked == false)
					ConfirmUI.showMessageDialog(this, "ID 중복체크를 해주세요.", "가입 실패");
				else if(password == "") 
					ConfirmUI.showMessageDialog(this, "비밀번호를 입력해주세요.", "가입 실패");
				else if(certificate == "") 
					ConfirmUI.showMessageDialog(this, "자격증을 입력해주세요.", "가입 실패");
				else {
				    thePetSitter = new PetSitter(name,birth,age,address,cellPhoneContact,email,loginID,password,loginID,certificate);
					ConfirmUI.showMessageDialog(this, "회원가입이 완료되었습니다.", "가입 완료");
					dispose();
			}
		}else {
			System.out.println("Unexpected Error");
			System.exit(0);
		}
	}	
	}	
}
