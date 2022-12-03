package hci.login;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;

import hci.utility.ConfirmUI;
import hci.utility.RoundedButton;
import pd.systemuser.Pet;

/*
 * 회원가입할 대상을 고르는 창
 * 회원 / 돌봄이 선택
 */
@SuppressWarnings("serial")
public class RegisterAddPetInfoUI extends JDialog implements ActionListener{
	
	final static int nameY = 40; 						// 이름 항목의 Y축 좌표
	final static int AgeY = 120; 						// 나이 항목의 Y축 좌표
	final static int KindY = 200; 						// 종류 항목의 Y축 좌표
	final static int ChronicDiseaseY = 280; 			// 지병 항목의 Y축 좌표
	final static int KindOfFeedY = 360; 		  		// 사료 종류 항목의 Y축 좌표 
	
	Color c;
	
	// 버튼 이미지 & 크기 변환
	ImageIcon CancelImg1 = new ImageIcon("././Image/CancelButton1.png");
	ImageIcon CancelImg2 = new ImageIcon("././Image/CancelButton2.png");
	
	Image img1 = CancelImg1.getImage();
	Image changeImg1 = img1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonIcon1 = new ImageIcon(changeImg1);
	
	Image img2 = CancelImg2.getImage();
	Image changeImg2 = img2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonIcon2 = new ImageIcon(changeImg2);
	
	JPanel ContentPanel;
	
	JComboBox<Integer> AgeCombo;
	
	JTextField NameField;
	JTextField KindField;
	JTextField ChronicDiseaseField;
	JTextField KindOfFeedField;
	
	Pet thePet = null;
	
	private String petName;                 // 이름
    private int petAge;                     // 나이
    private String petKind;                 // 종류
    private String chronicDisease;          // 지병 여부
    private String kindOfFeed; 				// 사료의 종류
	
	
	RoundedButton SubmitButton;
	
	public RegisterAddPetInfoUI(JFrame parentFrame) {
		super(parentFrame,"반려동물 정보 추가",true);
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);

		getContentPane().setBackground(Color.WHITE);

		
		// 제목 항목
		JLabel TitleLabel = new JLabel("반려동물 정보 입력");
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		add(TitleLabel);
		TitleLabel.setBounds(30, 50, 500, 70);
		
		// 구분선
		JSeparator JSepStart = new JSeparator();
		add(JSepStart);
		JSepStart.setBounds(0, 170, 600, 70);
		
		// 항목들을 담은 Panel
		ContentPanel = new JPanel();
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
		NameField = new JTextField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
        
		ContentPanel.add(NameField);
		NameField.setEditable(true);
		NameField.setBounds(140, nameY, 80, 30);
		NameField.setBackground(Color.LIGHT_GRAY);
		NameField.setForeground(Color.BLACK);
		NameField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 나이 항목
		
		Integer[] AgeString = new Integer[20];
		for(int i = 0; i<20;i++){
			AgeString[i] = i+1;
		}
		
		JLabel AgeLabel = new JLabel("나이 ");
		ContentPanel.add(AgeLabel);
		AgeLabel.setBounds(30,AgeY,40,30);
		AgeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				
		AgeCombo = new JComboBox<>(AgeString);
		ContentPanel.add(AgeCombo);
		AgeCombo.setBounds(140,AgeY,50,31);
		AgeCombo.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 반려동물 종류 항목
		JLabel KindLabel = new JLabel("반려동물 종류 ");
		ContentPanel.add(KindLabel);
		KindLabel.setBounds(30,KindY,100,30);
		KindLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		KindField = new JTextField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
		ContentPanel.add(KindField);
		KindField.setEditable(true);
		KindField.setBounds(140,KindY,200,31);
		KindField.setBackground(Color.LIGHT_GRAY);
		KindField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 반려동물 지병
		JLabel ChronicDiseaseLabel = new JLabel("반려동물 지병 ");
		ContentPanel.add(ChronicDiseaseLabel);
		ChronicDiseaseLabel.setBounds(30,ChronicDiseaseY,100,30);
		ChronicDiseaseLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		
		ChronicDiseaseField = new JTextField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
		ContentPanel.add(ChronicDiseaseField);
		ChronicDiseaseField.setEditable(true);
		ChronicDiseaseField.setBounds(140,ChronicDiseaseY,200,31);
		ChronicDiseaseField.setBackground(Color.LIGHT_GRAY);
		ChronicDiseaseField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		// 사료 종류
		JLabel KindOfFeedLabel = new JLabel("사료 종류 ");
		ContentPanel.add(KindOfFeedLabel);
		KindOfFeedLabel.setBounds(30,KindOfFeedY,90,30);
		KindOfFeedLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
		KindOfFeedField = new JTextField(){
            @Override
            public void setBorder(Border border) {
                
            }
        };
		ContentPanel.add(KindOfFeedField);
		KindOfFeedField.setEditable(true);
		KindOfFeedField.setBounds(140,KindOfFeedY,200,31);
		KindOfFeedField.setBackground(Color.LIGHT_GRAY);
		KindOfFeedField.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		
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
		SubmitButton = new RoundedButton("제출");
		add(SubmitButton);
		c = new Color(64,126,219);
		SubmitButton.setBackground(c);
		SubmitButton.setForeground(Color.WHITE);
		SubmitButton.setBounds(450, 680, 100, 50);
		SubmitButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		SubmitButton.setVisible(true);
		SubmitButton.addActionListener(this);
	}
	
	public static Pet showDialog(JFrame parentFrame)
	{
		RegisterAddPetInfoUI RegisterAddPetInfoWindow = new RegisterAddPetInfoUI(parentFrame);
		RegisterAddPetInfoWindow.setVisible(true);
		
		return RegisterAddPetInfoWindow.thePet;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("제출")) {
			petName = NameField.getText();
			petAge = (int)AgeCombo.getSelectedItem();
			petKind = KindField.getText();
			chronicDisease = ChronicDiseaseField.getText();
			kindOfFeed = KindOfFeedField.getText();
			
			if(petName.equals(""))
				ConfirmUI.showMessageDialog(this, "이름을 입력해주세요", "가입 실패");
			else if(petKind.equals("")) 
				ConfirmUI.showMessageDialog(this, "종류를 입력해주세요.", "가입 실패");
			else if(kindOfFeed.equals("")) 
				ConfirmUI.showMessageDialog(this, "사료 종류를 입력해주세요.", "가입 실패");
			else {
				if(kindOfFeed.equals(""))
					chronicDisease = "없음";
				thePet = new Pet(petName,petAge,petKind,chronicDisease,kindOfFeed);
				ConfirmUI.showMessageDialog(this, "제출되었습니다.", "제출 완료");
				dispose();
			}
		}else if(ActionCmd.equals("뒤로가기")) {
			dispose();
		}
	}
	
}
