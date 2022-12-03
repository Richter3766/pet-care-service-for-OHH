package hci.petsitter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import hci.utility.ConfirmUI;
import hci.utility.RoundedButton;
import pd.application.Application;
import pd.application.ApplicationList;
import pd.systemuser.*;

@SuppressWarnings("serial")
public class PetAppListUI extends JFrame implements ActionListener, MouseListener{

	protected JTable AppTable;
	protected DefaultTableModel AppModel;
	
	Color c;
	
	ImageIcon CancelImg1 = new ImageIcon("././Image/CancelButton1.png");
	ImageIcon CancelImg2 = new ImageIcon("././Image/CancelButton2.png");
	
	Image img1 = CancelImg1.getImage();
	Image changeImg1 = img1.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonIcon1 = new ImageIcon(changeImg1);
	
	Image img2 = CancelImg2.getImage();
	Image changeImg2 = img2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	ImageIcon CancelButtonIcon2 = new ImageIcon(changeImg2);
	
	ApplicationList list;
	
	int SelectedRow;
	
	RoundedButton AppCancelButton;
	RoundedButton LookupButton;
	
	String ID;
	PetSitter thePetSitter;
	public PetAppListUI(String ID) {
		super("PetAppListUI");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		getContentPane().setBackground(Color.WHITE);
		
		
		//제목 항목
		JLabel TitleLabel = new JLabel("신청 내역");
		TitleLabel.setHorizontalAlignment(JLabel.CENTER);
		TitleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		add(TitleLabel);
		TitleLabel.setBounds(30, 50, 500, 70);
		
		// 구분선
		JSeparator JSepStart = new JSeparator();
		add(JSepStart);
		JSepStart.setBounds(0, 170, 600, 70);	
		
		// 신청 목록
		String header[] = {"신청 ID", "신청 기간", "신청 상태"};
		String contents[][] = {{"","",""}};
		
		AppModel = new DefaultTableModel(contents, header) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		AppTable = new JTable(AppModel);
		c = new Color(64,126,219);
		AppTable.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 15));
		AppTable.getTableHeader().setForeground(Color.WHITE);
		AppTable.getTableHeader().setBackground(c);
		AppTable.setFont(new Font("맑은 고딕", Font.PLAIN, 13)); // 테이블 내용 폰트 조정
		
		
		// 셀 글자 가운데 정렬
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
				
		AppTable.getColumn("신청 ID").setCellRenderer(celAlignCenter);
		AppTable.getColumn("신청 기간").setCellRenderer(celAlignCenter);
		AppTable.getColumn("신청 상태").setCellRenderer(celAlignCenter);
		AppTable.addMouseListener(this);
		
		// 신청 ID 셀 크기 조정
		AppTable.getColumn("신청 ID").setWidth(130);
		AppTable.getColumn("신청 ID").setMinWidth(130);
		AppTable.getColumn("신청 ID").setMaxWidth(130);
				
		// 신청 기간 셀 크기 조정
		AppTable.getColumn("신청 기간").setWidth(280);
		AppTable.getColumn("신청 기간").setMinWidth(280);
		AppTable.getColumn("신청 기간").setMaxWidth(280);
				
		// 신청 상태 셀 크기 조정
		AppTable.getColumn("신청 상태").setWidth(130);
		AppTable.getColumn("신청 상태").setMinWidth(130);
		AppTable.getColumn("신청 상태").setMaxWidth(130);
		
		// 셀 위치 변경 불가
		AppTable.getTableHeader().setReorderingAllowed(false);
		
		// 셀 크기 변경 불가
		AppTable.getTableHeader().setResizingAllowed(false);
		
		AppModel.removeRow(0); // 0번째 행 삭제(빈칸)
		list = ApplicationList.getList();
		Application application;
		for(String key: list.getForPaymentTable().keySet()){
			application = list.getForPaymentTable().get(key);
			if(application == null) break;
			if(application.getPetSitterID().equals(ID)){
				String[] data = new String[3];
				data[0] = application.getApplicationID();
				data[1] = application.getPeriodOfService();
				data[2] = application.getState();
				AppModel.addRow(data);
			}
		}
		for(String key: list.getForActiveTable().keySet()){
			application = list.getForActiveTable().get(key);
			if(application == null) break;
			if(application.getPetSitterID().equals(ID)){	
				String[] data = new String[3];
				data[0] = application.getApplicationID();
				data[1] = application.getPeriodOfService();
				data[2] = application.getState();
				AppModel.addRow(data);
			}
		}
		for(String key: list.getForCompleteTable().keySet()){
			application = list.getForCompleteTable().get(key);
			if(application == null) break;
			if(application.getPetSitterID().equals(ID)){
				String[] data = new String[3];
				data[0] = application.getApplicationID();
				data[1] = application.getPeriodOfService();
				data[2] = application.getState();
				AppModel.addRow(data);
			}
		}
		
		// 데이터가 화면 넘어갈 시 정렬
		JScrollPane AppScroll = new JScrollPane(AppTable);
		add(AppScroll);
		AppScroll.setBounds(20, 190, 540, 480);
		AppScroll.getViewport().setBackground(Color.WHITE);
		
		// 뒤로가기 버튼
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
		
		// 신청 취소 버튼
		AppCancelButton = new RoundedButton("신청 취소");
		add(AppCancelButton);
		c = new Color(240,62,62);
		AppCancelButton.setBackground(c);
		AppCancelButton.setForeground(Color.WHITE);
		AppCancelButton.setBounds(450, 680, 100, 50);
		AppCancelButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		AppCancelButton.addActionListener(this);
		AppCancelButton.setVisible(false);
		
		// 조회 버튼
		LookupButton = new RoundedButton("조회");
		add(LookupButton);
		c = new Color(64,126,219);
		LookupButton.setBackground(c);
		LookupButton.setForeground(Color.WHITE);
		LookupButton.setBounds(330, 680, 100, 50);
		LookupButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		LookupButton.setVisible(false);
		LookupButton.addActionListener(this);
		
		this.ID = ID;
		
	}
	public void actionPerformed(ActionEvent e) {
		String ActionCmd = e.getActionCommand();
		if(ActionCmd.equals("뒤로가기")) {
			PetSitterUI PetSitterWindow = new PetSitterUI(ID);
			PetSitterWindow.setVisible(true);
			dispose();
		}
		else if(ActionCmd.equals("신청 취소")) {
			int ans = ConfirmUI.showConfirmDialog(this,"정말 신청을 취소하시겠습니까?","확인 메세지",ConfirmUI.YES_NO_OPTION);
			if(ans == 0){ // 신청 취소하기
				// 신청 정보를 결제 대기에서 수락 대기로 변경
				String applicationID = (String) AppTable.getValueAt(SelectedRow, 0);
				list.moveForPaymentToAccept(applicationID);

				AppModel.removeRow(SelectedRow);
				ConfirmUI.showMessageDialog(this,"신청이 취소되었습니다","신청 취소 완료");
				LookupButton.setVisible(false);
				AppCancelButton.setVisible(false);
			}
		}else if(ActionCmd.equals("조회")) {
			String applicationID = (String)AppTable.getValueAt(SelectedRow, 0); // Key(신청 ID) 얻어오기
			PetAppDetailUI PetAppDetailWindow = new PetAppDetailUI(ID,applicationID); // 상세정보 창 열기
			PetAppDetailWindow.setVisible(true);
			dispose();
		}
		else {
			System.out.println("Unexpected Error");
			System.exit(0);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		SelectedRow = AppTable.getSelectedRow(); // 선택된 Table 의 Row 값 가져오기
		String Status = (String)AppTable.getModel().getValueAt(SelectedRow,2);
		if(Status.equals("완료") || Status.equals("진행중")) {
			LookupButton.setVisible(true);
			AppCancelButton.setVisible(false);
			LookupButton.setBounds(450, 680, 100, 50);
		}else {
			LookupButton.setVisible(true);
			AppCancelButton.setVisible(true);
			AppCancelButton.setBounds(450, 680, 100, 50);
			LookupButton.setBounds(330, 680, 100, 50);
		}
	 }

	 public void mousePressed(MouseEvent e) {
	  // TODO Auto-generated method stub
	  
	 }

	 public void mouseReleased(MouseEvent e) {
	  // TODO Auto-generated method stub
	  
	 }

	 public void mouseEntered(MouseEvent e) {
	  // TODO Auto-generated method stub
	  
	 }

	 public void mouseExited(MouseEvent e) {
	  // TODO Auto-generated method stub
	  
	 }
}
