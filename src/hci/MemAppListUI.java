package hci;

import pd.application.Application;
import pd.application.ApplicationList;
import pd.systemuser.*;

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

/*	  회원 신청 내역 확인화면입니다.
 *	 
 *  대문 글자(신청 정보 입력)
 *  
 *  JTable : 신청목록입니다. 아직 내용은 추가 X
 *  
 *  뒤로가기 버튼 -> MemberUI
 *  리뷰 버튼 -> ReviewUI
 *  결제 버튼 -> ?? (평소엔 보이지 않음) 리뷰버튼 자리에 존재
 *  으로 구성됩니다
 */

@SuppressWarnings("serial")
public class MemAppListUI extends JFrame implements ActionListener, MouseListener{
	
	protected JTable AppTable;
	protected DefaultTableModel AppModel;
	
	RoundedButton ReviewButton;
	RoundedButton PayButton;
	RoundedButton RemoveButton;
	RoundedButton LookupButton;
	
	int SelectedRow;
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
	
	ApplicationList list;
	
	String ID;
	Member theMember;
	public MemAppListUI(String ID) {
		super("MemAppListUI");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		getContentPane().setBackground(Color.WHITE);
		
		theMember = Member.getMember(ID);
		
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
		String[] header = {"신청 ID", "신청 기간", "신청 상태"};
		String[][] contents = {{"","",""}};
		
		AppModel = new DefaultTableModel(contents, header) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		AppTable = new JTable(AppModel);
		c = new Color(64,126,219);
		AppTable.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 15)); // 테이블 헤더 폰트 조정
		AppTable.getTableHeader().setForeground(Color.WHITE);
		AppTable.getTableHeader().setBackground(c);
		AppTable.setFont(new Font("맑은 고딕", Font.PLAIN, 13)); // 테이블 내용 폰트 조정
		AppTable.addMouseListener(this);
		
		// 셀 글자 가운데 정렬
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		
		AppTable.getColumn("신청 ID").setCellRenderer(celAlignCenter);
		AppTable.getColumn("신청 기간").setCellRenderer(celAlignCenter);
		AppTable.getColumn("신청 상태").setCellRenderer(celAlignCenter);
		
		
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
		
		// 데이터가 화면 넘어갈 시 정렬
		JScrollPane AppScroll = new JScrollPane(AppTable);
		add(AppScroll);
		AppScroll.setBounds(20, 190, 540, 480);
		AppScroll.getViewport().setBackground(Color.WHITE);
		
		AppModel.removeRow(0); // 0번째 행 삭제(빈칸)

		// 해시 테이블 값 추가
		list = ApplicationList.getList();
		Application application;
		for(String key: list.getForAcceptTable().keySet()){
			String temp = key.split("-")[0];
				if(temp.compareTo(theMember.getLoginID()) == 0){
					 application = list.getForAcceptTable().get(key);
					 String[] data = new String[3];
					 data[0] = application.getApplicationID();
					 data[1] = application.getPeriodOfService();
					 data[2] = application.getState();
					 AppModel.addRow(data);
				}
		}
		for(String key: list.getForPaymentTable().keySet()){
			String temp = key.split("-")[0];
			if(temp.compareTo(theMember.getLoginID()) == 0){
				application = list.getForPaymentTable().get(key);
				String[] data = new String[3];
				data[0] = application.getApplicationID();
				data[1] = application.getPeriodOfService();
				data[2] = application.getState();
				AppModel.addRow(data);
			}
		}
		for(String key: list.getForActiveTable().keySet()){
			String temp = key.split("-")[0];
			if(temp.compareTo(theMember.getLoginID()) == 0){
				application = list.getForActiveTable().get(key);
				String[] data = new String[3];
				data[0] = application.getApplicationID();
				data[1] = application.getPeriodOfService();
				data[2] = application.getState();
				AppModel.addRow(data);
			}
		}
		for(String key: list.getForCompleteTable().keySet()){
			String temp = key.split("-")[0];
			if(temp.compareTo(theMember.getLoginID()) == 0){
				application = list.getForCompleteTable().get(key);
				String[] data = new String[3];
				data[0] = application.getApplicationID();
				data[1] = application.getPeriodOfService();
				data[2] = application.getState();
				AppModel.addRow(data);
			}
		}

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
		
		// 리뷰 버튼 (완료 상태에만 보임)
		ReviewButton = new RoundedButton("리뷰");
		add(ReviewButton);
		c = new Color(64,126,219);
		ReviewButton.setBackground(c);
		ReviewButton.setForeground(Color.WHITE);
		ReviewButton.setBounds(450, 680, 100, 50);
		ReviewButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		ReviewButton.addActionListener(this);
		ReviewButton.setVisible(false);
		
		// 결제 버튼 (결제 대기시에만 보임)
		PayButton = new RoundedButton("결제");
		add(PayButton);
		c = new Color(64,126,219);
		PayButton.setBackground(c);
		PayButton.setForeground(Color.WHITE);
		PayButton.setBounds(450, 680, 100, 50);
		PayButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		PayButton.addActionListener(this);
		PayButton.setVisible(false);
		
		// 삭제 버튼 (수락 대기시에만 보임)
		RemoveButton = new RoundedButton("삭제");
		add(RemoveButton);
		c = new Color(240,62,62);
		RemoveButton.setBackground(c);
		RemoveButton.setForeground(Color.WHITE);
		RemoveButton.setBounds(450, 680, 100, 50);	
		RemoveButton.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		RemoveButton.addActionListener(this);	
		RemoveButton.setVisible(false);
		
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
			MemberUI MemberWindow = new MemberUI(ID);
			MemberWindow.setVisible(true);
			dispose();
		}
		else if(ActionCmd.equals("리뷰")) {
			int ans = ConfirmUI.showConfirmDialog(this,"리뷰를 작성하시겠습니까?","확인 메세지",ConfirmUI.YES_NO_OPTION);
			if(ans == 0){ // 리뷰 작성 수락
				// 선택된 신청의  ID를 ReviewUI로 넘겨줌
				String applicationID = (String) AppTable.getValueAt( SelectedRow, 0);
				ReviewUI ReviewWindow = new ReviewUI(ID,applicationID);
				ReviewWindow.setVisible(true);
				dispose();
			}
		}else if(ActionCmd.equals("결제")) {
			int ans = ConfirmUI.showConfirmDialog(this,"결제 창으로 이동하시겠습니까?","확인 메세지",ConfirmUI.YES_NO_OPTION);
			if(ans == 0){ // 결제창 이동
				String applicationID = (String) AppTable.getValueAt( SelectedRow, 0);
				PaymentUI PaymentWindow = new PaymentUI(ID,applicationID);
				PaymentWindow.setVisible(true);
				dispose();
			}
		}else if(ActionCmd.equals("삭제")) {
			int ans = ConfirmUI.showConfirmDialog(this,"정말 삭제하시겠습니까?","확인 메세지",ConfirmUI.YES_NO_OPTION);
			if(ans == 0){ // 삭제하기
				// 먼저 데이터베이스에서 삭제
				String applicationID = (String) AppTable.getValueAt(SelectedRow, 0);
				ApplicationList list = ApplicationList.getList();
				list.removeForAccept(applicationID);
				list.removeForPayment(applicationID);
				AppModel.removeRow(SelectedRow);

				ReviewButton.setVisible(false);
				PayButton.setVisible(false);
				RemoveButton.setVisible(false);
				LookupButton.setVisible(false);
				ConfirmUI.showMessageDialog(this,"삭제가 완료되었습니다.","확인 메세지");
			}
		}else if(ActionCmd.equals("조회")) {
			String applicationID = (String)AppTable.getValueAt(SelectedRow, 0); // Key(신청 ID) 얻어오기
			MemAppDetailUI MemAppDetailWindow = new MemAppDetailUI(applicationID); // 상세정보 창 열기
			MemAppDetailWindow.setVisible(true);
		}
		else {
			System.out.println("Unexpected Error");
			System.exit(0);
		}
	}
	
	 public void mouseClicked(MouseEvent e) {
		 SelectedRow = AppTable.getSelectedRow(); // 선택된 Table 의 Row 값 가져오기
		 String Status = (String)AppTable.getModel().getValueAt(SelectedRow,2);
		 if(Status.equals("수락 대기")) {
			 PayButton.setVisible(false);
			 ReviewButton.setVisible(false);
			 RemoveButton.setVisible(true);
			 LookupButton.setVisible(true);
			 RemoveButton.setBounds(450, 680, 100, 50);
			 LookupButton.setBounds(330, 680, 100, 50);
		 }else if(Status.equals("결제 대기")) {
			 ReviewButton.setVisible(false);
			 RemoveButton.setVisible(true);
			 PayButton.setVisible(true);
			 LookupButton.setVisible(true);
			 PayButton.setBounds(210, 680, 100, 50);
			 RemoveButton.setBounds(450, 680, 100, 50);
			 LookupButton.setBounds(330, 680, 100, 50);
		 }else if(Status.equals("진행중")) {
			 ReviewButton.setVisible(false);
			 PayButton.setVisible(false);
			 RemoveButton.setVisible(false);
			 LookupButton.setVisible(true);
			 PayButton.setBounds(450, 680, 100, 50);
			 LookupButton.setBounds(450, 680, 100, 50);
		}else if(Status.equals("완료")) {
			 RemoveButton.setVisible(false);
			 PayButton.setVisible(false);
			 ReviewButton.setVisible(true);
			 LookupButton.setVisible(true);
			 PayButton.setBounds(450, 680, 100, 50);
			 LookupButton.setBounds(330, 680, 100, 50);
		 }else {
			 ReviewButton.setVisible(false);
			 PayButton.setVisible(false);
			 RemoveButton.setVisible(false);
			 LookupButton.setVisible(false);
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
