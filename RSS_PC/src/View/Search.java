package View;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import Controler.Controler;
import View.IWindow;
import View.Button;


public class Search extends IWindow {

	Controler	c;
	
	Button		retour;
	Button		search;
	JLabel     	titleLabel = new JLabel("Titre         ");
	JLabel		urlLabel = new JLabel(  "Lien          ");
	JLabel		descLabel = new JLabel( "Description ");
	JTextField	title = new JTextField();
	JTextField	url = new JTextField();
	JTextField	descr = new JTextField();
	
	
	public Search(Controler contr){
		c = contr;
		retour = new Button("Retour     ", this, Controler.mainMenuId);
		search = new Button("Rechercher ", this, Controler.newsId);
	}
	
	public JPanel start(){
		JPanel p= new JPanel();
		Box btitle = Box.createHorizontalBox();
		Box bUrl = Box.createHorizontalBox();
		Box bdescr = Box.createHorizontalBox();
		Box bButton = Box.createHorizontalBox();
		Box b = Box.createVerticalBox();
		
		
		btitle.add(titleLabel);
		btitle.add(title);
		
		bUrl.add(urlLabel);
		bUrl.add(url);
		
		bdescr.add(descLabel);
		bdescr.add(descr);
		
		bButton.add(search);
		bButton.add(new JLabel("  "));
		bButton.add(retour);

		
		b.add(btitle);
		b.add(bUrl);
		b.add(bdescr);
		
		b.add(bButton);
		
		p.add(b);
		
		return (p);
	}
	
	public void	GoNextView(int id, String buttonName)
	{
		if (id == Controler.mainMenuId)
			c.GoNextView(id);
		else
			c.searchFeed(title.getText(), descr.getText(), url.getText());		
	}
	
}
