package hci;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import pd.systemuser.*;
/*
 * 로그인을 하는 화면
 * 아이디와 비밀번호를 입력받는다
 */
public class DoLoginUI extends JDialog implements ActionListener {

	Color c;
	
	public final static int MEMBER = 0;
	public final static int PETSITTER = 1;
	
	JTextField IDField;
	JPasswordField PasswordField;
	
	private String ID;
	private String Password; 
	
	JCheckBox AutoLoginCheckBox;
	
	boolean LoginSuccessed = false;
	boolean AutoLogin = false;
	
	int Role;
	public DoLoginUI(JFrame parentFrame, String title,int Role) {
		super(parentFrame,title,true);
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		getContentPane().setBackground(Color.WHITE);
		
		JLabel IDLabel = new JLabel("ID");
	    add(IDLabel);
	    IDLabel.setHorizontalAlignment(JLabel.CENTER);
	    IDLabel.setBackground(Color.WHITE);
	    IDLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	    IDLabel.setBounds(20, 12, 70, 40);
	    
	    IDField = new JTextField(12);
	    add(IDField);
	    IDField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	    IDField.setBounds(100, 20, 150, 30);
	    
	    JLabel PasswordLabel = new JLabel("Password");
	    add(PasswordLabel);
	    PasswordLabel.setHorizontalAlignment(JLabel.CENTER);
	    PasswordLabel.setBackground(Color.WHITE);
	    PasswordLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	    PasswordLabel.setBounds(20, 52, 70, 40);
	    
	    PasswordField = new JPasswordField(12);
	    add(PasswordField);
	    PasswordField.setBounds(100, 60, 150, 30);
	    PasswordField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	    
		RoundedButton LoginButton = new RoundedButton("로그인");
		add(LoginButton);
		c = new Color(64,126,219);
		LoginButton.setBackground(c);
		LoginButton.setBounds(110, 120, 80, 30);
		LoginButton.setForeground(Color.WHITE);
		LoginButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		LoginButton.addActionListener(this);
		
		AutoLoginCheckBox = new JCheckBox("자동 로그인");
		add(AutoLoginCheckBox);
		AutoLoginCheckBox.setBounds(20,95,90,30);
		AutoLoginCheckBox.setBackground(Color.WHITE);
		AutoLoginCheckBox.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		
		this.Role = Role;
		}
	
	public static String[] showLoginDialog(JFrame parentFrame, String title, int Role)
	{
		DoLoginUI DoLoginWindow = new DoLoginUI(parentFrame,title,Role);
		DoLoginWindow.setVisible(true);
		
		String[] returnString = new String[2]; // String[2]를 반환. [0] -> ID / [1] -> 자동 로그인 여부
		if(DoLoginWindow.LoginSuccessed) { // 비밀번호 맞으면
			returnString[0] = DoLoginWindow.getID();
			returnString[1] = "";
			if(DoLoginWindow.AutoLogin) 
				returnString[1] = "AutoLogin";
			return returnString;
		}else {
			return null;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("로그인")) {
			if(Role == MEMBER) {
				ID = IDField.getText();
				Password = "";
				  
				//PasswordField에서 패스워드를 얻어옴, char[] 배열에 저장
				char[] secret_pw = PasswordField.getPassword(); 

				//secret_pw 배열에 저장된 암호의 자릿수 만큼 for문 돌리면서 cha 에 한 글자씩 저장
				for(char cha : secret_pw){         
					Character.toString(cha);       //cha 에 저장된 값 string으로 변환
					//Password 에 저장하기, Password 에 값이 비어있으면 저장, 값이 있으면 이어서 저장하는 삼항연산자
					Password += (Password.equals("")) ? ""+cha+"" : ""+cha+"";   
					}
				
				Member TheMember = Member.getMember(ID);
				if(TheMember == null) { // ID가 존재하지 않음
					ConfirmUI.showMessageDialog(this, "존재하지 않는 ID입니다.", "로그인 실패");
				}else { // ID 존재
					if(TheMember.getPassword().equals(Password)) // 패스워드가 일치하면 로그인 성공
					{
						LoginSuccessed = true;
						if(AutoLoginCheckBox.isSelected())
							AutoLogin = true;
						dispose();
					}else { // 패스워드 불일치 - 로그인 실패
						ConfirmUI.showMessageDialog(this, "비밀번호가 일치하지 않습니다.", "로그인 실패");
					}
				}
				
			}else if(Role == PETSITTER) {
				ID = IDField.getText();
				Password = "";
				  
				//PasswordField에서 패스워드를 얻어옴, char[] 배열에 저장
				char[] secret_pw = PasswordField.getPassword(); 

				//secret_pw 배열에 저장된 암호의 자릿수 만큼 for문 돌리면서 cha 에 한 글자씩 저장
				for(char cha : secret_pw){         
					Character.toString(cha);       //cha 에 저장된 값 string으로 변환
					//Password 에 저장하기, Password 에 값이 비어있으면 저장, 값이 있으면 이어서 저장하는 삼항연산자
					Password += (Password.equals("")) ? ""+cha+"" : ""+cha+"";   
					}
				
				PetSitter ThePetSitter = PetSitter.getPetSitter(ID);
				if(ThePetSitter == null) { // ID가 존재하지 않음
					ConfirmUI.showMessageDialog(this, "존재하지 않는 ID입니다.", "로그인 실패");
				}else { // ID 존재
					if(ThePetSitter.getPassword().equals(Password)) // 패스워드가 일치하면 로그인 성공
					{
						LoginSuccessed = true;
						if(AutoLoginCheckBox.isSelected())
							AutoLogin = true;
						dispose();
					}else { // 패스워드 불일치 - 로그인 실패
						ConfirmUI.showMessageDialog(this, "비밀번호가 일치하지 않습니다.", "로그인 실패");
					}
				}
			}
		}
			
		}
	
	private String getID() {
		return ID;
	}
}
