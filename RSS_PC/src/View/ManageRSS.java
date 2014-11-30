package View;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import Model.Feed;
import View.IWindow;
import View.Button;
import Controler.Controler;

public class ManageRSS extends IWindow{

	Controler c;
	
	private Button  back;
	private Button	add;
	private Button	open;
	private JTable	table;
	
	
	public ManageRSS(Controler contr){
		c = contr;
	}
	
	public JPanel start(){
		JPanel p = new JPanel();
		Box btest = Box.createVerticalBox();
		Box b = Box.createVerticalBox();
		Box bButton = Box.createVerticalBox();

		ArrayList l = c.listFlux();
		
		String[] entetes = {"Titre", "Lien", "Description"};
		Object[][]	tab;
		
		tab = new Object[l.size()][entetes.length];
		for (int i = 0; i < l.size(); i++)
		{
			Feed	f = (Feed) l.get(i);
			
				tab[i][0] = f.title;
				tab[i][1] = f.link;
				tab[i][2] = f.description;	
		}
		
		table = new JTable(tab, entetes);
		table.setSize(700, 600);
		
		add = new Button( "Ajouter      ", this, Controler.createRssId);
		open = new Button("Supprimer", this, 0);
		back = new Button("Retour       ", this, Controler.mainMenuId);
		
		//bButton.add(new JLabel("                                                                                    "));
		bButton.add(add);
		bButton.add(new JLabel(" "));
		bButton.add(open);
		bButton.add(new JLabel(" "));
		bButton.add(back);
		//bButton.add(new JLabel("                                                                                    "));
		bButton.setSize(100, 600);
		btest.add(table, BorderLayout.CENTER);
		btest.add(new JLabel("                                                                                                                                                      "));
		p.add(btest);
		b.add(bButton, BorderLayout.PAGE_END);
		p.add(b, BorderLayout.PAGE_END);
		p.setSize(800, 600);
		return (p);
	}
	
	public void GoNextView(int id, String buttonName){
		if (buttonName == "Supprimer")
		{
			if (table.getSelectedRow() < 0)
				return;
			
			c.deleteRSS(table.getSelectedRow());
			id = Controler.manageRSSId;
		}
		
		
		c.GoNextView(id);
	}
}
