package View;

import javax.swing.Box;
import javax.swing.JPanel;

import View.IWindow;
import View.Button;
import Controler.Controler;

public class ViewRSS extends IWindow{

	private Controler c;
	private Button back;
	
	public ViewRSS(Controler contr){
		c = contr;
		back = new Button("Back", this, Controler.newsId);
	}
	
	public JPanel start(){
		JPanel p = new JPanel();
		Box b = Box.createVerticalBox();
		
		b.add(back);
		p.add(b);
		
		return (p);
	}
	
	public void		GoNextView(int id, String buttonName){
		c.GoNextView(id);
	}
}
