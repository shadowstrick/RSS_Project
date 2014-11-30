package View;

import javax.swing.JPanel;

import Controler.Controler;

public abstract class IWindow {
	
	public abstract JPanel start();
	public abstract void	GoNextView(int id, String buttonName);
	
}
