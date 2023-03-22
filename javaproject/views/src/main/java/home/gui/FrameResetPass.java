package home.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import admin.gui.FrameDashboard;
import dao.impl.UserDAOImpl;
import entity.User;
import helper.FrameUtils;
import helper.IconImage;
import helper.RegexPattern;
import service.Authorization;
import service.Login;
import service.ValidateLogin;
import service.sendHtmlMail;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameResetPass extends JFrame {
	private FrameSignIn jfSignIn;
	private JPanel contentPane;	
	private JTextField textFieldEmail;
	private JPanel panelEmail;
	private JLabel lblIconEmail;
	private JPanel panelPassword;
	private JLabel lblIconKeyCode;
	private JPanel panel;
	private JPanel panelMain;
	private IconImage icon;
	private JButton btnReset;
	private JButton btnSignUp;
	private JLabel lblBg;
	private JTextField textFieldKeyCode;
	private JPasswordField textPassNew;
	private JButton btnSignIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameResetPass frame = new FrameResetPass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private void resetPass() {
		try {
			if(Authorization.verification.equals(Integer.parseInt(textFieldKeyCode.getText()))) {
				Integer id = new UserDAOImpl().getIdFromDbByAccount(textFieldEmail.getText());
				Integer x =  new UserDAOImpl().updatePassword(new User(id, textFieldEmail.getText(), textPassNew.getText(), null));
				if(x!=0) {
					JOptionPane.showMessageDialog(null,"Mật Khẩu Của Bạn ĐÃ Được Thay Đổi");
					Authorization.verification = null;
					dispose();
					FrameSignIn y = new FrameSignIn();
					y.setVisible(true);
				}
			}else {
				JOptionPane.showMessageDialog(null, "Vui Lòng Nhập Dữ Liệu Chính Xác!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Vui Lòng Nhập Dữ Liệu Chính Xác!");
		}
		
	}

	public  FrameResetPass() {
		// TODO Auto-generated constructor stub
	
		initComponent();
		textFieldEmail.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				textFieldEmailKeyReleased(e);
			}
		});

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetPass();
			}
		});

		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnSignUp_actionPerformed(e);
			}
		});
	}

	private void initComponent() {
		setResizable(false);
		setForeground(new Color(62, 115, 255));
		setTitle("Đăng nhập");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 856, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 247, 255));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		icon = new IconImage();

		// Panel
		panel = new JPanel();
		panel.setBackground(new Color(37, 57, 111));
		panel.setBounds(0, 0, 428, 532);
		contentPane.add(panel);
		panel.setLayout(null);

		lblBg = new JLabel("");
		lblBg.setBounds(0, 0, 428, 532);
		panel.add(lblBg);
		lblBg.setIcon(new ImageIcon(icon.getBgImg()));

		// Panel Main
		panelMain = new JPanel();
		panelMain.setBackground(new Color(255, 255, 255));
		panelMain.setBounds(427, 0, 415, 532);
		contentPane.add(panelMain);
		panelMain.setLayout(null);

		JLabel lblSignIn = new JLabel("Quên Mật Khẩu");
		lblSignIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignIn.setFont(new Font("Arial", Font.BOLD, 22));
		lblSignIn.setBounds(117, 78, 178, 32);
		panelMain.add(lblSignIn);

		// Email
		panelEmail = new JPanel();
		panelEmail.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelEmail.setBackground(new Color(242, 247, 255));
		panelEmail.setBounds(50, 182, 314, 45);
		panelEmail.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (textFieldEmail.getText().equals("Email")) {
					textFieldEmail.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (textFieldEmail.getText().isEmpty()) {
					textFieldEmail.setText("");
				}

			}
		});
		panelMain.add(panelEmail);
		panelEmail.setLayout(null);

		lblIconEmail = new JLabel("");
		lblIconEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconEmail.setBounds(269, 0, 45, 45);
		panelEmail.add(lblIconEmail);
		lblIconEmail.setIcon(new ImageIcon(icon.getEmailImg()));

		textFieldEmail = new JTextField();
		textFieldEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			
			}

			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		textFieldEmail.setForeground(new Color(128, 128, 128));
		textFieldEmail.setBorder(null);
		textFieldEmail.setBounds(1, 1, 267, 43);
		panelEmail.add(textFieldEmail);

		// textFieldEmail.setBorder(new LineBorder(Color.red, 2));
		textFieldEmail.setCaretColor(new Color(0, 0, 0));
		textFieldEmail.setMargin(new Insets(2, 12, 2, 2));
		textFieldEmail.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldEmail.setBackground(UIManager.getColor("Button.highlight"));
		textFieldEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		textFieldEmail.setColumns(10);

		// Password
		panelPassword = new JPanel();
		panelPassword.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPassword.setBackground(new Color(242, 247, 255));
		panelPassword.setBounds(50, 351, 314, 45);
		panelMain.add(panelPassword);
		panelPassword.setLayout(null);

		lblIconKeyCode = new JLabel("");
		lblIconKeyCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconKeyCode.setBounds(270, 0, 44, 45);
		panelPassword.add(lblIconKeyCode);
		lblIconKeyCode.setIcon(new ImageIcon(icon.getPasswordImg()));
		
		textPassNew = new JPasswordField();
		textPassNew.setBounds(0, 0, 270, 45);
		panelPassword.add(textPassNew);

		// btn Sign In
		btnReset = new JButton("Đổi Mật Khẩu");
		btnReset.setBounds(50, 430, 314, 45);
		panelMain.add(btnReset);
		btnReset.setForeground(new Color(255, 255, 255));
		btnReset.setBackground(new Color(37, 57, 111));
		btnReset.setFont(new Font("Arial", Font.BOLD, 14));

		// btn Sign Up
		btnSignUp = new JButton("Đăng ký");
		btnSignUp.setBounds(242, 499, 70, 23);
		panelMain.add(btnSignUp);
		btnSignUp.setBorder(null);
		btnSignUp.setForeground(new Color(37, 57, 111));
		btnSignUp.setFont(new Font("Arial", Font.BOLD, 14));
		btnSignUp.setBackground(new Color(255, 255, 255));

		JLabel lblNewLabel = new JLabel("Chưa có tài khoản?");
		lblNewLabel.setBounds(107, 500, 131, 21);
		panelMain.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));

		
		//btn sign in
		btnSignIn = new JButton("Đăng nhập");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FrameSignIn siginIn = new FrameSignIn();
				
				siginIn.setVisible(true);
			}
		});
		btnSignIn.setBounds(323, 499, 92, 23);
		panelMain.add(btnSignIn);
		btnSignIn.setForeground(new Color(37, 57, 111));
		btnSignIn.setFont(new Font("Arial", Font.BOLD, 14));
		btnSignIn.setBorder(null);
		btnSignIn.setBackground(Color.WHITE);
		// Mess
		JLabel textMess1 = new JLabel("");
		textMess1.setBounds(50, 235, 57, 32);
		panelMain.add(textMess1);
		textMess1.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel textmess2 = new JLabel("");
		textmess2.setBounds(50, 320, 57, 38);
		panelMain.add(textmess2);
		textmess2.setAlignmentX(0.5f);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(50, 238, 314, 45);
		panelMain.add(panel_1);
		panel_1.setLayout(null);
		
		textFieldKeyCode = new JTextField();
		textFieldKeyCode.setText("");
		textFieldKeyCode.setBounds(0, 0, 207, 45);
		panel_1.add(textFieldKeyCode);
		textFieldKeyCode.setColumns(10);
		
		JButton btnSendCode = new JButton("Gửi Mã");
		btnSendCode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!textFieldEmail.getText().equals("")) {
					double randomDouble = Math.random();
					randomDouble = randomDouble * 1000+ 1;
					int randomInt = (int) randomDouble;
					Authorization.verification = randomInt;
					sendHtmlMail.sendMail(textFieldEmail.getText(),"Mã Xác Nhận Đổi Mật Khẩu","<h1>Your Verification is :"+randomInt+"</h1> ");
					JOptionPane.showMessageDialog(null, "Vui Lòng Kiểm Tra Trong Email Của bạn!");
					
				}else {
					JOptionPane.showMessageDialog(null, "Vui Lòng Nhập Email Của Bạn !!");
				}
			}
		});
		btnSendCode.setBounds(206, 0, 108, 45);
		panel_1.add(btnSendCode);
		
	
		btnSendCode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});

		FrameUtils.alignFrameScreenCenter(this);
	}

	protected void do_btnSignUp_actionPerformed(ActionEvent e) {
		// desktop = new JDesktopPane();
		dispose();
		FrameSignUp signUp = new FrameSignUp();
		signUp.setVisible(true);
	}

	protected void textFieldEmailKeyReleased(KeyEvent e) {
	}

	public FrameSignIn getJfSignIn() {
		return jfSignIn;
	}

	public void setJfSignIn(FrameSignIn jfSignIn) {
		this.jfSignIn = jfSignIn;
	}
}
