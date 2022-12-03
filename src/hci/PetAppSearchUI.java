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

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/*	  돌봄이 돌봄 서비스 신청 조회 화면입니다.
 *	 
 *  대문 글자(신청 내역)
 *  
 *  JTable : 신청 내역입니다. 아직 내용은 추가 X
 *  
 *  뒤로가기 버튼 -> PetSitterUI
 *  조회 버튼 -> PetAppDetailUI
 *  으로 구성됩니다
 */

@SuppressWarnings("serial")
public class PetAppSearchUI extends JFrame implements ActionListener, MouseListener{

	protected JTable AppTable;
	protected DefaultTableModel AppModel;
	
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
	
	int SelectedRow;
	
	RoundedButton LookupButton;
	ApplicationList list;
	ArrayList<String> KeyList;
	String ID;
	PetSitter thePetSitter;
	
	public PetAppSearchUI(String ID) {
		super("PetAppSearchUI");
		setSize(600, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		
		getContentPane().setBackground(Color.WHITE);
		
		thePetSitter = PetSitter.getPetSitter(ID);
		
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
		String header[] = {"지역", "신청 기간","가격","신청 ID"};
		String contents[][] = {{"","","",""}};
		
		// 신청 내역
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
		AppTable.addMouseListener(this);
		
		// 셀 글자 가운데 정렬
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
				
		AppTable.getColumn("지역").setCellRenderer(celAlignCenter);
		AppTable.getColumn("신청 기간").setCellRenderer(celAlignCenter);
		AppTable.getColumn("가격").setCellRenderer(celAlignCenter);
		
		// 지역 셀 크기 조정
		AppTable.getColumn("지역").setWidth(130);
		AppTable.getColumn("지역").setMinWidth(130);
		AppTable.getColumn("지역").setMaxWidth(130);
		
		// 신청 기간 셀 크기 조정
		AppTable.getColumn("신청 기간").setWidth(280);
		AppTable.getColumn("신청 기간").setMinWidth(280);
		AppTable.getColumn("신청 기간").setMaxWidth(280);
		
		// 가격 셀 크기 조정
		AppTable.getColumn("가격").setWidth(130);
		AppTable.getColumn("가격").setMinWidth(130);
		AppTable.getColumn("가격").setMaxWidth(130);
		
		// 신청 ID 셀을 숨김 -> 신청 ID 셀은 Key 값이기 때문에 필요하지만, 보일 필요는 없기 때문에 가림
		AppTable.getColumn("신청 ID").setWidth(0);
		AppTable.getColumn("신청 ID").setMinWidth(0);
		AppTable.getColumn("신청 ID").setMaxWidth(0);
		
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

		// 해시 테이블에 있는 값 추가
		list = ApplicationList.getList();
		Application application;
		for(String key: list.getForAcceptTable().keySet()){
			application = list.getForAcceptTable().get(key);
			String[] data = new String[4];
			data[0] = application.getLocation();
			data[1] = application.getPeriodOfService();
			data[2] = application.getPrice();
			data[3] = key;
			AppModel.addRow(data);
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
		
		// 조회 버튼
		LookupButton = new RoundedButton("조회");
		add(LookupButton);
		c = new Color(64,126,219);
		LookupButton.setBackground(c);
		LookupButton.setForeground(Color.WHITE);
		LookupButton.setBounds(450, 680, 100, 50);
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
		else if(ActionCmd.equals("조회")) {
			String applicationID = (String)AppTable.getValueAt(SelectedRow, 3); // Key(신청 ID) 얻어오기
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
		LookupButton.setVisible(true);
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
