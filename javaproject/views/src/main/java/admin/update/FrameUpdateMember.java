package admin.update;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import admin.item.ItemUser;
import admin.panel.PanelAdmin;
import admin.panel.PanelMember;
import dao.impl.UserDAOImpl;
import entity.User;
import helper.ErrorMessage;
import helper.FrameUtils;
import service.UserService;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class FrameUpdateMember extends JFrame {
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JLabel lblAddMember;
	private JLabel lblEmail;
	private JLabel lblLevel;
	private JTextField textEmail;
	private JTextField txtLevel;
	private JButton btnUpdate;
	private JComboBox<String> cbbRole;
	private UserService userService;
	
	private static FrameUpdateMember myInstance;
	private User user;
	private Map<String, String> data;
	
	public ItemUser itemUser;
	
	public static FrameUpdateMember getMyInstance(User user) {
		if (myInstance == null) {
			myInstance = new FrameUpdateMember(user);
		} else {
			myInstance.dispose();
			myInstance = new FrameUpdateMember(user);

		}
		return myInstance;
	}
	
	public FrameUpdateMember(User user) {
		this.user = user;
		
		initComponent();
		FrameUtils.alignFrameScreenCenter(this);
		userService = new UserService();
	}
	
	private void initComponent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblAddMember = new JLabel("Sửa thông tin thành viên");
		lblAddMember.setForeground(new Color(37, 57, 111));
		lblAddMember.setFont(new Font("Arial", Font.BOLD, 20));
		lblAddMember.setBounds(20, 11, 253, 34);
		contentPane.add(lblAddMember);
		
//		EMAIL
		lblEmail = new JLabel("Email ");
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		lblEmail.setBounds(30, 100, 100, 21);
		contentPane.add(lblEmail);
		panel.setBackground(new Color(242, 247, 255));
		panel.setBounds(0, 0, 384, 62);
		contentPane.add(panel);
		
		textEmail = new JTextField();
		textEmail.setFocusTraversalPolicyProvider(true);
		textEmail.setMargin(new Insets(2, 6, 2, 2));
		textEmail.setHorizontalAlignment(SwingConstants.LEFT);
		textEmail.setFont(new Font("Arial", Font.PLAIN, 14));
		textEmail.setColumns(10);
		textEmail.setBackground(Color.WHITE);
		textEmail.setBounds(115, 95, 240, 38);
		textEmail.setEditable(false);;
		textEmail.setText(user.getEmail());
		contentPane.add(textEmail);
		
//		LEVEL
		lblLevel = new JLabel("Chức vụ");
		lblLevel.setForeground(Color.BLACK);
		lblLevel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblLevel.setBounds(29, 170, 100, 21);
		contentPane.add(lblLevel);
		
		
		
		btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUpdateActionPerformed(e);
			}
		});
		btnUpdate.setBackground(new Color(67, 98, 190));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 16));
		btnUpdate.setBounds(110, 273, 150, 44);
		contentPane.add(btnUpdate);
		
		
		cbbRole = new JComboBox<>();
		cbbRole.addItem("Thành viên");
		cbbRole.addItem("Quản trị viên");
		int cbbRoleIndex = user.getRoleId() == 3 ? 0 : 1;
		cbbRole.setSelectedIndex(cbbRoleIndex);
		cbbRole.setBounds(115, 162, 240, 38);
		cbbRole.setBackground(new Color(255, 255, 255));
		cbbRole.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(cbbRole);
		
		
		FrameUtils.alignFrameScreenCenter(this);
	}
	protected void btnUpdateActionPerformed(ActionEvent e) {
		data = new HashMap<>();
		data.put("id", Integer.toString(user.getId()));
		Integer roleId = cbbRole.getSelectedItem().equals("Thành viên") ? 3 : 2;
		data.put("role", roleId.toString());
		
		if(userService.update(data)) {
			JOptionPane.showMessageDialog(this, "Cập nhật thành viên thành công");
			dispose();
			
			JPanel panel = ((PanelMember) this.itemUser.panelParent).getPanel();
			panel.repaint();
			panel.revalidate();
			((PanelMember) this.itemUser.panelParent).loadData();
		} else {
			JOptionPane.showMessageDialog(this, ErrorMessage.ERROR_MESSAGES);
		}
		
	}
	
	
}
