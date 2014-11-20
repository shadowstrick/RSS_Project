package Controler;

import View.IWindow;
import View.ConnectView;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Controler {
	
	int connexionId = 0;
	private IWindow		_views[];
	private JFrame	_window;
	
	public Controler()
	{
		_window = new JFrame();
		_window.setTitle("Ma première fenêtre Java");
	    _window.setSize(400, 100);
	    _window.setLocationRelativeTo(null);
		
	    initViews();
		

		
	}
	
	private void	initViews()
	{
		_views = new IWindow[1];
		
		_views[connexionId] = new ConnectView();
		
	}
	
	public void		start()
	{
	    JPanel p = _views[connexionId].start();
	    _window.setContentPane(p);
	}
}
