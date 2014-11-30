package View;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JButton;

import Controler.Controler;

public class MainWindow extends IWindow {

	private Controler	c;
	
	private Button news;
	private Button myRSS;
	private Button search;
	
	
	public MainWindow(Controler contr){
		c = contr;
		news = new Button(  "Mes Flux      ", this, Controler.newsId);
		myRSS = new Button( "Gérer RSS   ", this, Controler.manageRSSId);
		search = new Button("Rechercher", this, Controler.searchId);
	}
	
	
	public JPanel start()
	{
		JPanel p = new JPanel();
		Box    buttonBox = Box.createVerticalBox();
		
		buttonBox.add(news);
		buttonBox.add(new JLabel(" "));
		buttonBox.add(myRSS);
		buttonBox.add(new JLabel(" "));
		buttonBox.add(search);
		
		p.add(buttonBox);
		
		return (p);
	}
	
	public void	GoNextView(int id, String buttonName){
		c.GoNextView(id);
	}
}
