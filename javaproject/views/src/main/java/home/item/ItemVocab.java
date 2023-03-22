package home.item;

import javax.swing.JPanel;

import dao.impl.BookmarkDAOImpl;
import dao.impl.HistoryDAOImpl;
import dao.impl.UserDAOImpl;
import dao.impl.WordTypeDAOImpl;
import entity.Bookmark;
import entity.History;
import entity.Vocabulary;
import helper.StringUtils;
import home.panel.PanelDetailVocab;
import service.Authorization;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ItemVocab extends JPanel {

	private JLabel lblWord;
	private WordTypeDAOImpl typeDao;
	
	private Vocabulary vocab;
	public PanelDetailVocab panelDetailVocab;
	
	public ItemVocab(Vocabulary vocab) {
		this.vocab = vocab;
		
		initComponent();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_this_mouseClicked(e);
			}
		});
	}
	
	private void initComponent() {		
		setBackground(Color.WHITE);
		setLayout(null);
		setBounds(0, 0, 332, 30);
		typeDao = new WordTypeDAOImpl();
		lblWord = new JLabel("");
		lblWord.setForeground(new Color(37, 57, 111));
		lblWord.setFont(new Font("Arial", Font.PLAIN, 14));
		lblWord.setBounds(10, 0, 322, 28);
		StringBuilder str = new StringBuilder();
		str.append(StringUtils.toCapitalize(vocab.getWord()) + " (");
		str.append(StringUtils.toCapitalize(typeDao.get(vocab.getWordTypeId()).toLowerCase()) + ") ");
		lblWord.setText(str.toString());
		add(lblWord);
	}
	
	protected void do_this_mouseClicked(MouseEvent e) {
		Integer userId = new UserDAOImpl().selectIdByUserEmail(Authorization.email); 
		History his = new History(vocab.getId(), userId);
		HistoryDAOImpl hdao= new HistoryDAOImpl();
		
		if(hdao.checkExistHistory(userId, vocab.getId()) == null){
			hdao.insert(his);
		}
		
		panelDetailVocab = PanelDetailVocab.getMyInstance(vocab);
	}
}
