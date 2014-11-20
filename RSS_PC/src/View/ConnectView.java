package View;

import javax.swing.JPanel;
import javax.swing.JButton;

public class ConnectView extends IWindow {

	private JButton bouton = new JButton("Connect");
	
		public ConnectView(){

		}
		
		public JPanel start(){
			JPanel p = new JPanel();
			
			p.add(bouton);
			
			return (p);
		}
		
		
}
