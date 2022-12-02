package hci;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;


/*
 * 알림창 Class
 *  사용법
 * - 1. 일반 메세지 창
 * ConfirmUI.showMessageDialog(this,띄울 메세지, 메세지창 제목);
 * 입력한 메세지를 보여주는 메세지창을 띄운다. return 값 X
 * 
 * - 2. 확인 메세지 창
 * ConfirmUI.showConfirmDialog(this,띄울 메세지, 메세지창 제목, 선택창 옵션);
 * 입력한 메세지를 보여주는 메세지창을 띄우며, 선택창 옵션에 따라 선택창을 바꿀 수 있다.
 * return 값은 int 형으로, Yes/OK -> 0, No -> 1, Cancel -> 2, 기본(x로 닫기) -> -1을 반환한다
 * 옵션 : 
 *  2-1. DEFAULT_OPTION 
 *  선택창이 확인 하나뿐이다.
 *  2-2. YES_NO_OPTION
 *  네/아니오로 나뉘는 2개의 선택창을 제공한다.
 *  2-3. YES_NO_CANCEL_OPTION
 *  네/아니오/취소로 나뉘는 3개의 선택창을 제공한다.
 *  2-4. OK_CANCEL_OPTION
 *  확인/취소로 나뉘는 2개의 선택창을 제공한다.
 * 
 *  옵션 입력시에는 ConfirmUI.DEFAULT_OPTION 이런식으로 입력하면 된다.
 * 
 *  이 클래스의 메소드는 static 으로 생성 없이도 사용가능하다
 *  
 *  JOptionPane()을 참고했으며, ShowInputDialog(입력창 제공)가 필요하다면 추가 가능
 */

public class ConfirmUI extends JDialog implements ActionListener {
	
	Color c;
	
	public static final int         DEFAULT_OPTION = -1;
	public static final int         YES_NO_OPTION = 0;
	public static final int         YES_NO_CANCEL_OPTION = 1;
	public static final int         OK_CANCEL_OPTION = 2;
     
	
	public static final int         YES_OPTION = 0;
	public static final int         NO_OPTION = 1;
	public static final int         CANCEL_OPTION = 2;
	public static final int         OK_OPTION = 0;
	public static final int         CLOSED_OPTION = -1;
	
	public static final int 		FUNC_MESSAGE = 0;
	public static final int  		FUNC_CONFIRM = 1;
	public static final int  		FUNC_INPUT= 2;
		
	protected int ReturnVal = -1;
	
	
	public ConfirmUI(JFrame parentFrame,String message, String title, int selectOption, int funcOption) {
	super(parentFrame,title,true);
	setSize(300, 150);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setLocationRelativeTo(null);
	setLayout(null);
	setResizable(false);
	
	getContentPane().setBackground(Color.WHITE);
	
	
	JLabel MessageLabel = new JLabel(message);
    add(MessageLabel);
    MessageLabel.setHorizontalAlignment(JLabel.CENTER);
    MessageLabel.setBackground(Color.WHITE);
    MessageLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
    MessageLabel.setBounds(0, 12, 285, 40);
    
    
	RoundedButton YesButton = new RoundedButton("네");
	add(YesButton);
	c = new Color(64,126,219);
	YesButton.setBackground(c);
	YesButton.setBounds(60, 70, 60, 30);
	YesButton.setForeground(Color.WHITE);
	YesButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	YesButton.addActionListener(this);
	
	RoundedButton NoButton = new RoundedButton("아니요");
	add(NoButton);
	c = new Color(64,126,219);
	NoButton.setBackground(c);
	NoButton.setForeground(Color.WHITE);
	NoButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	NoButton.addActionListener(this);
	
	RoundedButton OKButton = new RoundedButton("확인");
	add(OKButton);
	c = new Color(64,126,219);
	OKButton.setBackground(c);
	OKButton.setForeground(Color.WHITE);
	OKButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	OKButton.addActionListener(this);
	
	RoundedButton CancelButton = new RoundedButton("취소");
	add(CancelButton);
	c = new Color(64,126,219);
	CancelButton.setBackground(c);
	CancelButton.setForeground(Color.WHITE);
	CancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	CancelButton.addActionListener(this);


	if(funcOption == FUNC_INPUT)
	{
		YesButton.setBounds(60, 70, 60, 30);
		NoButton.setBounds(160, 70, 60, 30);
		OKButton.setBounds(60, 70, 60, 30);
		CancelButton.setBounds(160, 70, 60, 30);
		
		YesButton.setVisible(false);
		NoButton.setVisible(false);
		OKButton.setVisible(false);
		CancelButton.setVisible(false);
	}
	if(funcOption == FUNC_CONFIRM) 
	{
		if(selectOption == DEFAULT_OPTION) {
			OKButton.setBounds(110, 70, 60, 30);
			
			YesButton.setVisible(false);
			NoButton.setVisible(false);
			OKButton.setVisible(true);
			CancelButton.setVisible(false);
		}else if(selectOption == OK_CANCEL_OPTION) {
			OKButton.setBounds(60, 70, 60, 30);
			CancelButton.setBounds(160, 70, 60, 30);
			
			YesButton.setVisible(false);
			NoButton.setVisible(false);
			OKButton.setVisible(true);
			CancelButton.setVisible(true);
		}else if(selectOption == YES_NO_OPTION) {
			YesButton.setBounds(60, 70, 60, 30);
			NoButton.setBounds(160, 70, 60, 30);
			
			YesButton.setVisible(true);
			NoButton.setVisible(true);
			OKButton.setVisible(false);
			CancelButton.setVisible(false);
		}else if(selectOption == YES_NO_CANCEL_OPTION) {
			YesButton.setBounds(40, 70, 60, 30);
			NoButton.setBounds(110, 70, 60, 30);
			CancelButton.setBounds(180, 70, 60, 30);
			
			YesButton.setVisible(true);
			NoButton.setVisible(true);
			OKButton.setVisible(false);
			CancelButton.setVisible(true);
		}
	}
	
	
	}
	
	public ConfirmUI(JFrame parentFrame,String message, String title) {
		super(parentFrame,title,true);
		setSize(300, 150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		getContentPane().setBackground(Color.WHITE);
		
		
		JLabel MessageLabel = new JLabel(message);
	    add(MessageLabel);
	    MessageLabel.setHorizontalAlignment(JLabel.CENTER);
	    MessageLabel.setBackground(Color.WHITE);
	    MessageLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
	    MessageLabel.setBounds(0, 12, 285, 40);
	    
		RoundedButton OKButton = new RoundedButton("확인");
		add(OKButton);
		c = new Color(64,126,219);
		OKButton.setBackground(c);
		OKButton.setBounds(110, 70, 60, 30);
		OKButton.setForeground(Color.WHITE);
		OKButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		OKButton.addActionListener(this);

		
		}
	
	public static int showConfirmDialog(JFrame parentFrame,String message, String title, int selectOption)
	{
		ConfirmUI ConfirmWindow = new ConfirmUI(parentFrame,message,title,selectOption,FUNC_CONFIRM);
		ConfirmWindow.setVisible(true);
		
		return ConfirmWindow.getVal();
	}
	
	public static void showMessageDialog(JFrame parentFrame,String message, String title)
	{
		ConfirmUI ConfirmWindow = new ConfirmUI(parentFrame,message,title);
		ConfirmWindow.setVisible(true);
		
	}
	
	public int getVal() {
		return ReturnVal;
	}
	
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("네")) {
			this.ReturnVal = YES_OPTION;
			dispose();
		}else if(ActionCmd.equals("아니요")) {
			this.ReturnVal = NO_OPTION;
			dispose();
		}else if(ActionCmd.equals("확인")) {
			this.ReturnVal = OK_OPTION;
			dispose();
		}else if(ActionCmd.equals("취소")) {
			this.ReturnVal = CANCEL_OPTION;
			dispose();
		}else {
			this.ReturnVal = DEFAULT_OPTION;
			dispose();
		}
	}
	
	
}
