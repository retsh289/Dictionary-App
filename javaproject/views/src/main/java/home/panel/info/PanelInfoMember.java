package home.panel.info;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import org.mindrot.jbcrypt.BCrypt;

import dao.impl.UserDAOImpl;
import entity.User;
import helper.RegexPattern;
import home.panel.PanelVocab;
import service.Authorization;
import service.Register;

import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelInfoMember extends JPanel {
	private JPasswordField textPassword;
	private JPasswordField passwordConfirm;
	private JPasswordField newPassword;

	
	private static PanelInfoMember myInstance;

	public static PanelInfoMember getMyInstance() {
		if (myInstance == null) {
			myInstance = new PanelInfoMember();
		}
		return myInstance;
	}
	
	public PanelInfoMember() {
		initComponent();
	}

	private void initComponent() {
		setLayout( new BorderLayout());
		setBounds(0, 0, 1042, 691);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel);
		panel.setLayout(null);
		
		JLabel lblHeader = new JLabel("THÔNG TIN CÁ NHÂN");
		lblHeader.setForeground(new Color(37, 57, 111));
		lblHeader.setFont(new Font("Arial", Font.BOLD, 24));
		lblHeader.setBounds(50, 39, 339, 50);
		panel.add(lblHeader);
		
		JLabel lblNewLabel = new JLabel("Cập nhật thông tin tài khoản");
		lblNewLabel.setForeground(new Color(37, 57, 111));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(50, 82, 233, 38);
		panel.add(lblNewLabel);
		
		JPanel panelContent = new JPanel();
		panelContent.setBackground(new Color(255, 255, 255));
		panelContent.setBounds(271, 173, 500, 322);
		panelContent.setBorder(BorderFactory.createLineBorder(new Color(37, 57, 111), 2));
		panel.add(panelContent);
		panelContent.setLayout(null);
		
		passwordConfirm = new JPasswordField();
		passwordConfirm.setBounds(180, 201, 300, 40);
		panelContent.add(passwordConfirm);
		
		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.setBounds(187, 271, 126, 40);
		panelContent.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpdateActionPerformed(e);
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 16));
		btnUpdate.setBackground(new Color(67, 98, 190));
		
		newPassword = new JPasswordField();
		newPassword.setBounds(180, 131, 300, 40);
		panelContent.add(newPassword);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(180, 61, 300, 40);
		panelContent.add(textPassword);
		
		JLabel lblPassword = new JLabel("Mật khẩu");
		lblPassword.setBounds(10, 70, 102, 21);
		panelContent.add(lblPassword);
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblNewPassword = new JLabel("Mật khẩu mới");
		lblNewPassword.setBounds(10, 140, 102, 21);
		panelContent.add(lblNewPassword);
		lblNewPassword.setForeground(Color.BLACK);
		lblNewPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblResetPassword = new JLabel("Nhập lại mật khẩu");
		lblResetPassword.setBounds(10, 210, 130, 21);
		panelContent.add(lblResetPassword);
		lblResetPassword.setForeground(Color.BLACK);
		lblResetPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lbliMtKhu = new JLabel("Đổi Mật Khẩu");
		lbliMtKhu.setHorizontalAlignment(SwingConstants.CENTER);
		lbliMtKhu.setForeground(new Color(37, 57, 111));
		lbliMtKhu.setFont(new Font("Arial", Font.BOLD, 18));
		lbliMtKhu.setBounds(0, 11, 500, 39);
		panelContent.add(lbliMtKhu);
		
	}
	
	protected void btnUpdateActionPerformed(ActionEvent e) {
		UserDAOImpl userDAO = new UserDAOImpl();
		Integer id =  userDAO.getIdFromDbByAccount(Authorization.email);
		StringBuilder s = new StringBuilder();
		boolean isPassValid = BCrypt.checkpw(textPassword.getText(), userDAO.getPassFromDbByAccount(Authorization.email));
		boolean checkNewPasswordValid = Register.checkRegexRegister(RegexPattern.PASSWORD, newPassword, s,"password");
		boolean checkPasswordConfirmValid = Register.checkPasswordConfirm(newPassword, passwordConfirm, s);
		
		boolean isAllValid = true;
		
		System.out.println(textPassword.getText() + "\t" + passwordConfirm.getText() + "\t" + newPassword.getText() );
		if(textPassword.getText().equals("") || passwordConfirm.getText().equals("") || newPassword.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Phải nhập vào tất cả dữ liệu!");
			isAllValid = false;
		} else if(!isPassValid) {
			JOptionPane.showMessageDialog(this, "Mật khẩu nhập vào không đúng!");
			isAllValid = false;
		} else if(!checkNewPasswordValid) {
			JOptionPane.showMessageDialog(this, "Mật khẩu mới không đúng định đạng!");
			isAllValid = false;
		} else if(!checkPasswordConfirmValid) {
			JOptionPane.showMessageDialog(this, "Nhập lại mật khẩu không khớp");
			isAllValid = false;
		}
		
		if(isAllValid) {
			User u = new User();
			u.setId(id);
			u.setPassword(newPassword.getText());
			new UserDAOImpl().updatePassword(u);
			JOptionPane.showMessageDialog(this, "Cập nhật mật khẩu thành công");
			reset();
		}
	}
	
	private void reset() {
		textPassword.setText("");
		newPassword.setText("");
		passwordConfirm.setText("");
	}
}
