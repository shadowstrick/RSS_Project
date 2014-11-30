package View;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Model.BasicResponse;
import View.IWindow;
import View.Button;
import Controler.Controler;

public class Register extends IWindow {

	private Controler c;
	
	private Button	back;
	private Button  register;
	
	private JTextField name = new JTextField();
	private JLabel nameLabel = new JLabel( "Nom d'utilisateur  ");
	
	private JTextField email = new JTextField();
	private JLabel emailLabel = new JLabel("Adresse email      ");
	
	private JPasswordField pass = new JPasswordField();
	private JLabel passLabel = new JLabel("Mot de passe        ");
	
	private JLabel message = new JLabel("");
	
	public Register(Controler contr){
		c = contr;
		back = new Button(    "Retour       ", this, Controler.connexionId);
		register = new Button("S'enregistrer", this, Controler.connexionId);
	}

	public JPanel start(){
		JPanel p = new JPanel();
		Box b = Box.createVerticalBox();
		Box bLogin = Box.createHorizontalBox();
		Box bemail = Box.createHorizontalBox();
		Box bpass = Box.createHorizontalBox();
		Box bButton = Box.createHorizontalBox();
		
		bLogin.add(nameLabel);
		bLogin.add(name);
		
		bemail.add(emailLabel);
		bemail.add(email);
		
		bpass.add(passLabel);
		bpass.add(pass);
		
		b.add(bLogin);
		b.add(bemail);
		b.add(bpass);
		b.add(message);
		
		bButton.add(register);
		bButton.add(back);
		
		b.add(bButton);
		
		p.add(b);
		
		return (p);
	}
	
	public void	GoNextView(int id, String buttonName)
	{
		if (buttonName == "Retour       ")
			c.GoNextView(id);
		else
		{
			BasicResponse b = c.registerUser(name.getText(), email.getText(), pass.getText());
			if (b.success)
				c.GoNextView(id);
			else
				message.setText(b.message);
		}
	}
}