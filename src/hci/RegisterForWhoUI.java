package hci;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class RegisterForWhoUI extends JDialog implements ActionListener{
	
	Color c;
	
	public RegisterForWhoUI(JFrame parentFrame) {
		super(parentFrame,"회원가입 유형 선택",true);
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);

		RoundedButton MemberButton = new RoundedButton("회원");
		add(MemberButton);
		c = new Color(64,126,219);
		MemberButton.setBackground(c);
		MemberButton.setForeground(Color.WHITE);
		MemberButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		MemberButton.setBounds(40, 40, 80, 40);
		MemberButton.addActionListener(this);
		
		RoundedButton PetSitterButton = new RoundedButton("돌봄이");
		add(PetSitterButton);
		c = new Color(64,126,219);
		PetSitterButton.setBackground(c);
		PetSitterButton.setForeground(Color.WHITE);
		PetSitterButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		PetSitterButton.setBounds(160, 40, 80, 40);
		PetSitterButton.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("회원")) {
			dispose();
			RegisterUI RegisterWindow = new RegisterUI("Member");
			RegisterWindow.setVisible(true);
		}else if(ActionCmd.equals("돌봄이")){
			dispose();
			RegisterUI RegisterWindow = new RegisterUI("PetSitter");
			RegisterWindow.setVisible(true);
		}
	}
	
}
