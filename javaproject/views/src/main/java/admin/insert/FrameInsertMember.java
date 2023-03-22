package admin.insert;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import admin.panel.PanelAdmin;
import admin.panel.PanelMember;
import helper.ErrorMessage;
import helper.FrameUtils;
import service.UserService;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class FrameInsertMember extends JFrame {
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	
	private JPasswordField passwordFieldPassword;
	private JPasswordField passwordResetPassword;
	private JLabel lblAddMember;
	private JLabel lblEmail;
	private JLabel lblPassword ;
	private JLabel lblResetPassword;
	private JLabel lblLevel;
	private JTextField textEmail;
	private JTextField txtLevel;
	private JButton btnAdd;
	private JButton btnReset;
	
	private UserService userService;
	private Map<String, String> data;
	
	public PanelMember panelParent;
	
	private static FrameInsertMember myInstance;
	
	public static FrameInsertMember getMyInstance() {
		if (myInstance == null) {
			myInstance = new FrameInsertMember();
		} else {
			myInstance.dispose();
			myInstance = new FrameInsertMember();
		}
		return myInstance;
	}
	
	public FrameInsertMember() {
		initComponent();
		FrameUtils.alignFrameScreenCenter(this);
		data = new HashMap<>();	
	}
	
	private void initComponent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 470, 442);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAddMember = new JLabel("Thêm Thành Viên");
		lblAddMember.setForeground(new Color(37, 57, 111));
		lblAddMember.setFont(new Font("Arial", Font.BOLD, 20));
		lblAddMember.setBounds(20, 11, 219, 34);
		contentPane.add(lblAddMember);
		
//		EMAIL
		lblEmail = new JLabel("Email ");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEmail.setBounds(32, 103, 102, 21);
		contentPane.add(lblEmail);
		panel.setBackground(new Color(242, 247, 255));
		panel.setBounds(0, 0, 869, 62);
		contentPane.add(panel);
		
		textEmail = new JTextField();
		textEmail.setFocusTraversalPolicyProvider(true);
		textEmail.setMargin(new Insets(2, 6, 2, 2));
		textEmail.setHorizontalAlignment(SwingConstants.LEFT);
		textEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		textEmail.setColumns(10);
		textEmail.setBackground(Color.WHITE);
		textEmail.setBounds(186, 94, 239, 38);
		contentPane.add(textEmail);
		
//		PASSWORD
		lblPassword = new JLabel("Mật khẩu");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		lblPassword.setBounds(32, 165, 102, 21);
		contentPane.add(lblPassword);
		
		lblResetPassword = new JLabel("Nhập lại mật khẩu");
		lblResetPassword.setForeground(Color.BLACK);
		lblResetPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		lblResetPassword.setBounds(32, 229, 130, 21);
		contentPane.add(lblResetPassword);
		
//		LEVEL
		lblLevel = new JLabel("Chức vụ");
		lblLevel.setForeground(Color.BLACK);
		lblLevel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblLevel.setBounds(32, 292, 102, 21);
		contentPane.add(lblLevel);
		
		
		
		btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddActionPerformed(e);
			}
		});
		btnAdd.setBackground(new Color(67, 98, 190));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setFont(new Font("Arial", Font.BOLD, 16));
		btnAdd.setBounds(50, 348, 150, 44);
		contentPane.add(btnAdd);
		
		btnReset = new JButton("Xóa");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doBtnResetActionPerformed(e);
			}
		});
		btnReset.setBackground(new Color(67, 98, 190));
		btnReset.setForeground(new Color(255, 255, 255));
		btnReset.setFont(new Font("Arial", Font.BOLD, 16));
		btnReset.setBounds(242, 348, 150, 44);
		contentPane.add(btnReset);
		
		txtLevel = new JTextField();
		txtLevel.setEditable(false);
		txtLevel.setText("Thành viên");
		txtLevel.setMargin(new Insets(2, 6, 2, 2));
		txtLevel.setHorizontalAlignment(SwingConstants.LEFT);
		txtLevel.setFont(new Font("Arial", Font.PLAIN, 14));
		txtLevel.setColumns(10);
		txtLevel.setBackground(Color.WHITE);
		txtLevel.setBounds(186, 284, 239, 38);
		contentPane.add(txtLevel);
		
		passwordFieldPassword = new JPasswordField();
		passwordFieldPassword.setBounds(186, 156, 239, 38);
		contentPane.add(passwordFieldPassword);
		
		passwordResetPassword = new JPasswordField();
		passwordResetPassword.setBounds(186, 220, 239, 38);
		contentPane.add(passwordResetPassword);
		
	}
	
	
	protected void doBtnResetActionPerformed(ActionEvent e) {
		passwordFieldPassword.setText(""); 
		passwordResetPassword.setText("");
	}
	
	
	protected void btnAddActionPerformed(ActionEvent e) {
		data.put("email", textEmail.getText());
		data.put("password", new String(passwordFieldPassword.getPassword()));
		data.put("confirmPassword", new String(passwordResetPassword.getPassword()));
		data.put("role", "3");
		
		userService = new UserService();
		boolean insert = userService.add(data);
		if(insert) {
			JOptionPane.showMessageDialog(this, "Thêm thành viên thành công");
			dispose();
			
			this.panelParent.frameParent.callPanel(new PanelMember());
		} else {
			JOptionPane.showMessageDialog(this, ErrorMessage.ERROR_MESSAGES);
		}
		
	}
}
