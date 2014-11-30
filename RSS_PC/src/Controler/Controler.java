package Controler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import Network.Network;
import View.IWindow;
import View.ConnectView;
import View.MainWindow;
import View.Search;
import View.ManageRSS;
import View.CreateRSS;
import View.News;
import View.ViewRSS;
import View.Register;
import Utils.Utils;
import Network.NetworkResponse;
import Model.BasicResponse;
import Model.Feed;
import Model.User;
import Network.JsonTraitement;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.JSONObject;

public class Controler {
	
	public static int connexionId = 0;
	public static int mainMenuId = 1;
	public static int searchId = 2;
	public static int manageRSSId = 3;
	public static int createRssId = 4;
	public static int newsId= 5;
	public static int viewId= 6;
	public static int registerId= 7;

	private IWindow		_views[];
	private JFrame		_window;
	
	private Network		_network;
	
	private User     	_user;
	
	public Controler()
	{
		_network = new Network("http://ec2-54-93-205-81.eu-central-1.compute.amazonaws.com:8090");
		
		_window = new JFrame();
		_window.setTitle("RSS");
	    _window.setSize(800, 600);
	    _window.setLocationRelativeTo(null);
		
	    initViews();
	}
	
	private void	initViews()
	{
		_views = new IWindow[8];
		
		_views[connexionId] = new ConnectView(this);
		_views[mainMenuId] = new MainWindow(this);
		_views[searchId] = new Search(this);
		_views[manageRSSId] = new ManageRSS(this);
		_views[createRssId] = new CreateRSS(this);
		_views[newsId] = new News(this);
		_views[viewId] = new ViewRSS(this);
		_views[registerId] = new Register(this);
	}
	
	public void		start()
	{
		GoNextView(connexionId);
	}
	
	public void		GoNextView(int id)
	{
		_window.setVisible(false);
		JPanel p = _views[id].start();
		p.setSize(800, 600);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		p.setLocation(dim.width, dim.height);
		_window.setContentPane(p);
		_window.setVisible(true);
	}
	
	public BasicResponse		connectUser(String user, String pass)
	{
		NetworkResponse response;
		try {
			String password = Utils.getHash(pass);
			
			 response = _network.sendRequestPOST("/users/auth/" + user + "/" + password, "");
		} catch (Exception e) {
			BasicResponse r = new BasicResponse();
			r.success = false;
			r.message = "Connexion avec le serveur impossible.";
			return (r);
		}
		BasicResponse b = JsonTraitement.basicResponse(response.contents);
		if (b.success)
		{
			_user = new User();
			_user.username = user;
			_user.password = Utils.getHash(pass);
		}
		return (b);
	}
	
	public BasicResponse		registerUser(String user, String email, String pass)
	{
		User	u = new User();
		NetworkResponse response;
		
		u.id = -1;
		u.username = user;
		u.email = email;
		u.password = pass;
		JSONObject obj = JsonTraitement.JsonEncodeUser(u);
		
		try {
			 response = _network.sendRequestPOST("/users/add/", obj.toJSONString());
		} catch (Exception e) {
			BasicResponse b = new BasicResponse();
			b.success = false;
			b.message = "Connexion avec le serveur impossible.";
			return (b);
		}
		BasicResponse b = JsonTraitement.basicResponse(response.contents);
		return (b);
	}
	
	public ArrayList		listFlux()
	{
		ArrayList		l;
		NetworkResponse r;
		try {
			r = _network.sendRequestGET("/feed/user/get/" + _user.username + "/" + _user.password);
		} catch (Exception e) {
			return (null);
		}
		l = JsonTraitement.ListFeed(r.contents);
		return (l);
	}
	
	public BasicResponse		createRSS(String title, String feedlink, String link, String description)
	{
		Feed					f = new Feed();
		NetworkResponse			r;
		
		f.title = title;
		f.feedLink = feedlink;
		f.link = link;
		f.description = description;
		
		JSONObject j = JsonTraitement.encodeFeed(f);
		try {
			r = _network.sendRequestPOST("/feed/assign/" + _user.username + "/" + _user.password, j.toJSONString());
		} catch (Exception e) {
			BasicResponse b = new BasicResponse();
			b.success = false;
			b.message = "Connexion avec le serveur impossible.";
			return (b);
		}
		BasicResponse b = new BasicResponse();
		b.success = true;
		b.message = "";
		return (b);
	}
	
	public BasicResponse		deleteRSS(int id)
	{
		NetworkResponse			r;
		
		try {
			r = _network.sendRequestPOST("/feed/" + id + "/unlink/" + _user.username + "/" + _user.password, "");
		} catch (Exception e) {
			BasicResponse b = new BasicResponse();
			b.success = false;
			b.message = "Connexion avec le serveur impossible.";
			return (b);
		}
		BasicResponse b = JsonTraitement.basicResponse(r.contents);
		return (b);
	}
	
	public	ArrayList		getAllFeedItem()
	{
		ArrayList			feeds = this.listFlux();
		ArrayList			feedItems = new ArrayList();
		
		for (int i = 0; i < feeds.size(); i++)
		{
			ArrayList		l;
			NetworkResponse r = null;
			long id;
			try {
				id = (((Feed)(feeds.get(i))).id);		
				if (id != 0)
					r = _network.sendRequestGET("/feed/" + id  + "/get/items");
			} catch (Exception e) {
				return (null);
			}
			if (id != 0)
			{
				l = JsonTraitement.ListFeedArray(r.contents, (int)((Feed)(feeds.get(i))).id);
				feedItems.addAll(l);
			}
		}
		return (feedItems);
	}

	public void			setRead(int feedId, String id)
	{
		try{
			_network.sendRequestPOST("/feed/" + feedId + "/item/user/read/true/" + _user.username + "/" + _user.password, id);
		} catch(Exception e) {
			
		}
	}
	
	public void			searchFeed(String title, String description, String link)
	{
		News	n = new News(this);
		
		if (title != "" || description != "" || link != "")
		{
			JPanel p;
			_window.setVisible(false);
			p = n.start(title, description, link);
			_window.setContentPane(p);
			_window.setVisible(true);
		}
		else
			GoNextView(Controler.newsId);
	}
	
}
