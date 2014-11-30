package View;

import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

import Controler.Controler;
import Model.BasicResponse;

public class ConnectView extends IWindow {
	
	private Controler c;
	
	private JTextField name = new JTextField();
	private JLabel nameLabel = new JLabel("Nom d'utilisateur  ");
	private Button bouton;
	private Button boutonRegister;
	
	private JPasswordField password = new JPasswordField();
	private JLabel passLabel = new JLabel("Mot de passe         ");
	
	private JLabel message = new JLabel("");
	
		public ConnectView(Controler contr){
			c = contr;
		}
		
		public JPanel start(){
			bouton = new Button("Connexion    ", this, Controler.mainMenuId);
			boutonRegister = new Button("S'enregistrer", this, Controler.registerId);
			
			JPanel p = new JPanel();
			Box b = Box.createVerticalBox();
			b.setSize(800, 600);
			Box bName = Box.createHorizontalBox();
			Box bPassword = Box.createHorizontalBox();
			Box bBouton = Box.createHorizontalBox();
			
			bName.add(nameLabel);
			bName.add(name);
			
			bPassword.add(passLabel);
			bPassword.add(password);
			
			bBouton.add(new JLabel("                                       "));
			bBouton.add(bouton);
			bBouton.add(new JLabel("       "));
			bBouton.add(boutonRegister);
			bBouton.add(new JLabel("                                       "));
			
			b.add(bName);
			b.add(bPassword);
			b.add(message);
			b.add(bBouton);
			
			p.add(b);

			return (p);
		}
		
		
		public void	GoNextView(int id, String buttonName)
		{
			if (id == Controler.mainMenuId)
			{
				BasicResponse b = c.connectUser(name.getText(), password.getText());
				if (b.success)
					c.GoNextView(id);
				else
					message.setText(b.message);
			}
			else
				c.GoNextView(id);
		}
		
}
