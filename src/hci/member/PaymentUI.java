package hci.member;

import hci.utility.ConfirmUI;
import hci.utility.RoundedButton;
import pd.application.Application;
import pd.application.ApplicationList;
import pd.payment.Payment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;

/*	 결제 화면입니다.
 *	 
 *  대문 글자(결제 정보 입력)
 *  
 *
 */

@SuppressWarnings("serial")
public class PaymentUI extends JFrame implements ActionListener{

	final static int AccountY = 380; 					//  계좌 항목 Y축 좌표
	final static int AccountPasswordY = 440; 			//  계좌 비밀번호 항목 Y축 좌표
	final static int ValidY = 500; 						//  계좌 유효기간 항목 Y축 좌표
	final static int BirthY = 560;						//  주민번호 앞자리 항목 Y축 좌표
	
	Color c;
	
	private String account;
    private String accountPassword;
    private String valid;
    private String birth;
	
	// 버튼 이미지 & 크기 변환
	ImageIcon CancelImg1 = new ImageIcon("././Image/CancelButton1.png");
	ImageIcon CancelImg2 = new ImageIcon("././Image/CancelButton2.png");
		
	Image img1 = CancelImg1.getImage();
	Image changeImg1 = img1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonIcon1 = new ImageIcon(changeImg1);
		
	Image img2 = CancelImg2.getImage();
	Image changeImg2 = img2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonIcon2 = new ImageIcon(changeImg2);

	JTextField AccountField;
	JPasswordField AccountPasswordField;
	JTextField ValidField;
	JTextField BirthField;
	
	String Price;
	String ID;
	String applicationID;
	
	ApplicationList list = ApplicationList.getList();
	Application application;
	Payment thePay;
	
	public PaymentUI(String ID, String applicationID) {
		super("PaymentUI");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		getContentPane().setBackground(Color.WHITE);
		
		// 제목 항목
		JLabel TitleLabel = new JLabel("결제 정보 입력");
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		add(TitleLabel);
		TitleLabel.setBounds(30, 50, 500, 70);
				
		// 구분선
		JSeparator JSepStart = new JSeparator();
		add(JSepStart);
		JSepStart.setBounds(0, 170, 600, 70);
	
		application = list.getForPaymentTable().get(applicationID);
		Price = application.getPrice();
		
		JLabel PriceTitleLabel = new JLabel("결제할 가격 : ");
		PriceTitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		add(PriceTitleLabel);
		PriceTitleLabel.setBounds(130, 230, 200, 70);
		
		JLabel PriceLabel = new JLabel(Price);
		PriceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		add(PriceLabel);
		PriceLabel.setBounds(350, 230, 200, 70);
		
		JLabel AccountLabel = new JLabel("계좌번호 ");
		add(AccountLabel);
		AccountLabel.setBounds(30,AccountY,70,30);
		AccountLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		AccountField = new JTextField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        add(AccountField);
		AccountField.setBounds(200, AccountY, 200, 30);
		AccountField.setBackground(Color.LIGHT_GRAY);
		AccountField.setForeground(Color.BLACK);
		AccountField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		JLabel AccountPasswordLabel = new JLabel("계좌 비밀번호 ");
		add(AccountPasswordLabel);
		AccountPasswordLabel.setBounds(30,AccountPasswordY,100,30);
		AccountPasswordLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		AccountPasswordField = new JPasswordField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        add(AccountPasswordField);
		AccountPasswordField.setBounds(200, AccountPasswordY, 200, 30);
		AccountPasswordField.setBackground(Color.LIGHT_GRAY);
		AccountPasswordField.setForeground(Color.BLACK);
		AccountPasswordField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		JLabel ValidLabel = new JLabel("유효기간 ");
		add(ValidLabel);
		ValidLabel.setBounds(30,ValidY,70,30);
		ValidLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		ValidField = new JTextField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        add(ValidField);
		ValidField.setBounds(200, ValidY, 200, 30);
		ValidField.setBackground(Color.LIGHT_GRAY);
		ValidField.setForeground(Color.BLACK);
		ValidField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		JLabel BirthLabel = new JLabel("주민번호 앞자리");
		add(BirthLabel);
		BirthLabel.setBounds(30,BirthY,150,30);
		BirthLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		BirthField = new JTextField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        add(BirthField);
		BirthField.setBounds(200, BirthY, 200, 30);
		BirthField.setBackground(Color.LIGHT_GRAY);
		BirthField.setForeground(Color.BLACK);
		BirthField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		
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
		
		this.ID = ID;
		this.applicationID = applicationID;
	}
	
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("뒤로가기")) {
			MemberUI MemberWindow = new MemberUI(ID);
			MemberWindow.setVisible(true);
			dispose();
		}else if(ActionCmd.equals("제출")) {
			int ans = ConfirmUI.showConfirmDialog(this,"결제하시겠습니까?","확인 메세지",ConfirmUI.YES_NO_OPTION);
			if(ans == 0) { // 제출
				account = AccountField.getText();
				accountPassword = "";
				//AccountPasswordField에서 패스워드를 얻어옴, char[] 배열에 저장
				char[] secret_pw = AccountPasswordField.getPassword(); 

				//secret_pw 배열에 저장된 암호의 자릿수 만큼 for문 돌리면서 cha 에 한 글자씩 저장
				for(char cha : secret_pw){         
					Character.toString(cha);       //cha 에 저장된 값 string으로 변환
					//Password 에 저장하기, Password 에 값이 비어있으면 저장, 값이 있으면 이어서 저장하는 삼항연산자
					accountPassword += (accountPassword.equals("")) ? ""+cha+"" : ""+cha+"";   
					}
				valid = ValidField.getText();
				birth = BirthField.getText();
				if(account.equals("")) 
					ConfirmUI.showMessageDialog(this, "계좌번호를 입력해주세요.", "결제 실패");
				else if(accountPassword.equals("")) 
					ConfirmUI.showMessageDialog(this, "계좌 비밀번호을 입력해주세요.", "결제 실패");
				else if(valid.equals("")) 
					ConfirmUI.showMessageDialog(this, "유효기간을 입력해주세요.", "결제 실패");
				else if(birth.equals("")) 
					ConfirmUI.showMessageDialog(this, "주민번호 앞자리를 입력해주세요.", "결제 실패");
				else {
					thePay = new Payment(account,accountPassword,valid,birth);
					thePay.Pay(applicationID);
					ConfirmUI.showMessageDialog(this,"결제가 완료되었습니다.","확인 메세지");
					MemAppListUI MemAppListWindow = new MemAppListUI(ID);
					MemAppListWindow.setVisible(true);
					dispose();
				}
			}
		}
		else {
			System.out.println("Unexpected Error");
			System.exit(0);
		}
	}
}
