package home.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import admin.gui.FrameDashboard;
import dao.impl.UserDAOImpl;
import entity.User;
import helper.FrameUtils;
import helper.IconImage;
import helper.RegexPattern;
import service.Authorization;
import service.Login;
import service.ValidateLogin;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JPasswordField;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;
import java.awt.event.FocusAdapter;

public class FrameSignIn extends JFrame {
	private FrameSignIn jfSignIn;
	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JPasswordField textFieldPassword;
	private JPanel panelEmail;
	private JLabel lblIconEmail;
	private JPanel panelPassword;
	private JLabel lblIconPassword;
	private JPanel panel;
	private JPanel panelMain;
	private IconImage icon;
	private JButton btnSignIn;
	private JButton btnSignUp;
	private JLabel lblBg;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameSignIn frame = new FrameSignIn();
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
	private void login() {
		String account = textFieldEmail.getText();
		String password = textFieldPassword.getText();
		StringBuilder s = new StringBuilder();
		try {
			if (ValidateLogin.checkAll(textFieldEmail, textFieldPassword, s)) {
				User user = new User(account, password, 1);
				if (UserDAOImpl.loginDb(user)) {
					Authorization authInfoUser = new Authorization(account, password, user.getRoleId());
					dispose();
					if (Authorization.loggedrole == 2) {
						FrameDashboard frame = new FrameDashboard();
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
								dim.height / 2 - frame.getSize().height / 2);
						frame.setVisible(true);

					} else if (Authorization.loggedrole == 3) {
						FrameHome frame = FrameHome.getMyInstance();
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
								dim.height / 2 - frame.getSize().height / 2);
						frame.setVisible(true);
					} else {
						FrameDashboard frame = new FrameDashboard();
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
								dim.height / 2 - frame.getSize().height / 2);
						frame.setVisible(true);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null,
						s.length() <= 0 ? "Vui Lòng Kiểm Tra Lại Dữ Liệu" : s.toString());
			}
		} catch (Exception e2) {
			e2.printStackTrace();

		}
	}

	public FrameSignIn() {
		initComponent();
		textFieldEmail.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				textFieldEmailKeyReleased(e);
			}
		});

		textFieldPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				StringBuilder s = new StringBuilder();
				Login.checkColorText(RegexPattern.PASSWORD, textFieldPassword, s, "password");
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					login();
				}
			}
		});

		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
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

		JLabel lblSignIn = new JLabel("Đăng nhập");
		lblSignIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignIn.setFont(new Font("Arial", Font.BOLD, 22));
		lblSignIn.setBounds(134, 79, 131, 32);
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
				if (textFieldEmail.getText().equals("Email")) {
					textFieldEmail.setForeground(new Color(37, 57, 111));
					textFieldEmail.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldEmail.getText().equals("")) {
					textFieldEmail.setForeground(new Color(153, 153, 153));
					textFieldEmail.setText("Email");

				}
			}
		});
		textFieldEmail.setForeground(new Color(128, 128, 128));
		textFieldEmail.setText("Email");
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
		panelPassword.setBounds(50, 271, 314, 45);
		panelMain.add(panelPassword);
		panelPassword.setLayout(null);

		lblIconPassword = new JLabel("");
		lblIconPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconPassword.setBounds(270, 0, 44, 45);
		panelPassword.add(lblIconPassword);
		lblIconPassword.setIcon(new ImageIcon(icon.getPasswordImg()));

		textFieldPassword = new JPasswordField();
		textFieldPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textFieldPassword.getText().equals("Password")) {
					textFieldPassword.setForeground(new Color(37, 57, 111));
					textFieldPassword.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldPassword.getText().equals("")) {
					textFieldPassword.setForeground(new Color(153, 153, 153));
					textFieldPassword.setText("Password");
				}
			}
		});
		textFieldPassword.setText("Password");
		textFieldPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		textFieldPassword.setBorder(null);
		textFieldPassword.setBounds(1, 1, 267, 43);
		panelPassword.add(textFieldPassword);
		textFieldPassword.setMargin(new Insets(2, 6, 2, 2));

		// btn Sign In
		btnSignIn = new JButton("Đăng nhập");
		btnSignIn.setBounds(50, 361, 314, 45);
		panelMain.add(btnSignIn);
		btnSignIn.setForeground(new Color(255, 255, 255));
		btnSignIn.setBackground(new Color(37, 57, 111));
		btnSignIn.setFont(new Font("Arial", Font.BOLD, 14));

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

		// Mess
		JLabel textMess1 = new JLabel("");
		textMess1.setBounds(50, 235, 57, 32);
		panelMain.add(textMess1);
		textMess1.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel textmess2 = new JLabel("");
		textmess2.setBounds(50, 320, 57, 38);
		panelMain.add(textmess2);
		textmess2.setAlignmentX(0.5f);
		
		JButton btnNewButton = new JButton("Quên Mật Khẩu");
		btnNewButton.setBorder(null);
		btnNewButton.setForeground(new Color(37, 57, 111));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FrameResetPass x = new FrameResetPass();
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				x.setLocation(dim.width / 2 - x.getSize().width / 2,
						dim.height / 2 - x.getSize().height / 2);
				x.setVisible(true);
			}
		});
		btnNewButton.setBounds(242, 28, 154, 38);
		panelMain.add(btnNewButton);

		FrameUtils.alignFrameScreenCenter(this);
	}

	protected void do_btnSignUp_actionPerformed(ActionEvent e) {
		// desktop = new JDesktopPane();
		dispose();
		FrameSignUp signUp = new FrameSignUp();
		signUp.setVisible(true);
	}

	protected void textFieldEmailKeyReleased(KeyEvent e) {
		StringBuilder s = new StringBuilder();
		Login.checkColorText(RegexPattern.EMAIL, textFieldEmail, s, "email");
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			login();
		}
	}

	public FrameSignIn getJfSignIn() {
		return jfSignIn;
	}

	public void setJfSignIn(FrameSignIn jfSignIn) {
		this.jfSignIn = jfSignIn;
	}
}
