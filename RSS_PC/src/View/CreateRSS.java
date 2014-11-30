package View;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import View.IWindow;
import View.Button;
import Controler.Controler;

public class CreateRSS extends IWindow {

	Controler c;
	
	private Button	add;
	private Button  cancel;
	
	private JLabel		title_l = new JLabel(      "Titre               ");
	private JTextField 	title = new JTextField();
	private Box			bTitle;
	
	private JLabel		feedlink_l = new JLabel(   "Lien du Flux  ");
	private JTextField 	feedlink = new JTextField();
	private Box			bFeedLink;
	
	private JLabel		link_l = new JLabel(       "Lien                ");
	private JTextField 	link = new JTextField();
	private Box			bLink;
	
	private JLabel		description_l = new JLabel("Description  ");
	private JTextField 	description = new JTextField();
	private Box			bDescription;
	
	
	public CreateRSS(Controler contr){
		c = contr;
	}
	
	public JPanel start(){
		JPanel p = new JPanel();
		add = new Button("Ajouter", this, Controler.manageRSSId);
		cancel = new Button("Annuler", this, Controler.manageRSSId);
		
		Box b = Box.createVerticalBox();
		Box buttonBox = Box.createHorizontalBox();
		
		bTitle = Box.createHorizontalBox();
		bTitle.add(title_l);
		bTitle.add(title);
		
		bFeedLink = Box.createHorizontalBox();
		bFeedLink.add(feedlink_l);
		bFeedLink.add(feedlink);
		
		bLink = Box.createHorizontalBox();
		bLink.add(link_l);
		bLink.add(link);
		
		bDescription = Box.createHorizontalBox();
		bDescription.add(description_l);
		bDescription.add(description);
		
		b.add(bTitle);
		b.add(bFeedLink);
		b.add(bLink);
		b.add(bDescription);
		
		buttonBox.add(new JLabel("                         "));
		buttonBox.add(add);
		buttonBox.add(new JLabel("  "));
		buttonBox.add(cancel);
		buttonBox.add(new JLabel("                         "));
		
		b.add(buttonBox);
		p.add(b);
		
		return (p);
	}
	
	public void	GoNextView(int id, String buttonName){
		if (buttonName == "Add")
			c.createRSS(title.getText(), feedlink.getText(), link.getText(), description.getText());
		c.GoNextView(id);
	}
	
}
