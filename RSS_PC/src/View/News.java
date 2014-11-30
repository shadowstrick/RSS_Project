package View;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import Model.FeedItem;
import Utils.Utils;
import View.IWindow;
import View.Button;
import Controler.Controler;

public class News extends IWindow {
	
	private Controler c;
	
	private Button	back;
	
	private ArrayList _feedItems;
	private JTable		table;

	public News(Controler contr){
		c = contr;
		back = new Button("Retour", this, Controler.mainMenuId);
	}

	public JPanel start(){
		JPanel p = new JPanel();
		Box b = Box.createVerticalBox();
		Box btest = Box.createVerticalBox();
		
		ArrayList feedItems = c.getAllFeedItem();
		
		String[] entetes = {"Titre", "Lien", "Resumé", "Lu"};
		Object[][] tab = new Object[feedItems.size()][entetes.length];
		
		_feedItems = feedItems;
		for(int i = 0; i < feedItems.size(); i++)
		{
			FeedItem item =(FeedItem)feedItems.get(i);
			tab[i][0] = item.title;
			tab[i][1] = item.link;
			tab[i][2] = item.description;
			tab[i][3] = (item.read) ? "Oui" : "Non";
		}
		
		table = new JTable(tab, entetes);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSize(700, 600);
		Button ouvrir= new Button("Ouvrir ", this, 0);
		b.add(ouvrir, BorderLayout.LINE_START);
		b.add(new JLabel(" "));
		btest.add(new JScrollPane(table), BorderLayout.CENTER);
		btest.add(new JLabel("                                                                                                                                                      "));
		p.add(btest);
		b.add(back, BorderLayout.LINE_END);
		p.add(b, BorderLayout.PAGE_END);
		//p.add(b, BorderLayout.PAGE_END);
		
		return (p);
	}
	
	public JPanel start(String title, String description, String link){
		JPanel p = new JPanel();
		Box b = Box.createVerticalBox();
		Box btest = Box.createVerticalBox();
		
		ArrayList feedItems = c.getAllFeedItem();
		ArrayList feedItems2 = new ArrayList();
		for (int i = 0; i < feedItems.size(); i++){
			FeedItem	item = (FeedItem) feedItems.get(i);
			if (item.title.contains(title) && title.compareTo("") != 0)
				feedItems2.add(item);
			else if (item.link.contains(link) && link.compareTo("") != 0)
				feedItems2.add(item);
			else if (item.description.contains(description) && description.compareTo("") != 0)
				feedItems2.add(item);
		}
		String[] entetes = {"Titre", "Lien", "Resumé", "Lu"};
		Object[][] tab = new Object[feedItems2.size()][entetes.length];
		
		_feedItems = feedItems2;
		for(int i = 0; i < _feedItems.size(); i++)
		{
			FeedItem item =(FeedItem)_feedItems.get(i);
			tab[i][0] = item.title;
			tab[i][1] = item.link;
			tab[i][2] = item.description;
			tab[i][3] = (item.read) ? "Oui" : "Non";
		}
		
		table = new JTable(tab, entetes);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSize(700, 600);
		Button ouvrir= new Button("Ouvrir ", this, 0);
		b.add(ouvrir, BorderLayout.LINE_START);
		b.add(new JLabel(" "));
		btest.add(new JScrollPane(table), BorderLayout.CENTER);
		btest.add(new JLabel("                                                                                                                                                      "));
		p.add(btest);
		b.add(back, BorderLayout.LINE_END);
		p.add(b, BorderLayout.PAGE_END);
		//p.add(b, BorderLayout.PAGE_END);
		
		return (p);
	}
	
	
	public void	GoNextView(int id, String buttonName)
	{
		if (buttonName == "Ouvrir ")
		{
			if (table.getSelectedRow() < 0)
				return;
			String s_url = ((FeedItem)(_feedItems.get(table.getSelectedRow()))).link;
			URL url;
			try {
				url = new URL(s_url);
				Utils.openWebpage(url);
				c.setRead(((FeedItem)(_feedItems.get(table.getSelectedRow()))).id_feed,((FeedItem)(_feedItems.get(table.getSelectedRow()))).id);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			id = Controler.newsId;
		}
		
		c.GoNextView(id);
	}
	
}
