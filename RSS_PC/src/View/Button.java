package View;

import javax.swing.JButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements MouseListener {

	int id;
	IWindow view;
	String buttonName;
	
	public Button(String str, IWindow viewTraitement, int idNextPage){
		super(str);
		id = idNextPage;
		view = viewTraitement;
		buttonName = str;
		
		this.addMouseListener(this);
	}
	
	  public void mouseClicked(MouseEvent event) { 
		  view.GoNextView(id,  buttonName);
	  }
	  
	  public void mouseEntered(MouseEvent event) { }
	  public void mouseExited(MouseEvent event) { }
	  public void mousePressed(MouseEvent event) { }
	  public void mouseReleased(MouseEvent event) { }  
	
}
