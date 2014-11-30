package Network;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import Model.BasicResponse;
import Model.Feed;
import Model.FeedItem;
import Model.User;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonTraitement {

	public static JSONObject JsonEncode(String[] name, String[] value, int nb)
	{
		JSONObject  obj = new JSONObject();
		
		for (int i= 0; i < nb; i++)
		{
			obj.put(name[i], value[i]);
		}
		return (obj);
	}
	
	public static JSONObject JsonEncodeUser(User u)
	{
		JSONObject   obj = new JSONObject();
		
		obj.put("id", u.id);
		obj.put("username", u.username);
		obj.put("password", u.password);
		obj.put("email", u.email);
		
		return (obj);
	}
	
	public static User  JsonUncodeUser(String s)
	{
		User		u = new User();
		JSONParser 	parser = new JSONParser();

		Object obj;
		try {
			obj = parser.parse(s);

		JSONObject obj2 = (JSONObject)obj;
		u.id = (int)((long)obj2.get("id"));
		u.username = (String)obj2.get("username");
		u.password = (String)obj2.get("password");
		u.email = (String)obj2.get("email");
		return (u);
		} catch (ParseException e) {
			return (null);
		}
	}
	
	public static BasicResponse basicResponse(String s)
	{
		BasicResponse		u = new BasicResponse();
		JSONParser 			parser = new JSONParser();

		try{
		Object obj =  parser.parse(s);
		JSONObject obj2 = (JSONObject)obj;
		u.success = (Boolean)obj2.get("success");
		u.message = (String)obj2.get("message");
		}catch(ParseException pe){
			u.success = false;
			u.message = "Erreur JSON";
		}
		return (u);
	}

	public static ArrayList	ListFeed(String s)
	{
		ArrayList    		l = new ArrayList();

		JSONParser parser=new JSONParser();
	    try{
	         Object obj = parser.parse(s);
	         JSONObject obj3 = (JSONObject)obj;
	         JSONArray array = (JSONArray)obj3.get("feeds");
	         for(int i = 0; i < array.size(); i++)
	         {
	        	 Feed     f = new Feed();
	        	 
	        	 JSONObject obj2 = (JSONObject)array.get(i);
	        	 
	        	 f.id = (long)obj2.get("id");
	        	 f.feedLink = (String)obj2.get("feedLink");
	        	 f.title = (String)obj2.get("title");
	        	 f.link = (String)obj2.get("link");
	        	 f.link = (String)obj2.get("description");
	        	 
	        	 l.add(f);
	         }
	      	}catch(ParseException pe){
	         System.out.println("position: " + pe.getPosition());
	         System.out.println(pe);
	      }
	    return (l);
	}
	
	public static ArrayList		ListFeedArray(String s, int id)
	{
		ArrayList    		l = new ArrayList();

		JSONParser parser=new JSONParser();
	    try{
	         Object obj = parser.parse(s);
	         JSONObject obj3 = (JSONObject)obj;
	         JSONArray array = (JSONArray)obj3.get("items");
	         for(int i = 0; i < array.size(); i++)
	         {
	        	 FeedItem     f = new FeedItem();
	        	 
	        	 JSONObject obj2 = (JSONObject)array.get(i);
	        	 
	        	 f.id = (String)obj2.get("id");
	        	 f.title = (String)obj2.get("title");
	        	 f.link = (String)obj2.get("link");
	        	 f.description = (String)obj2.get("description");
	        	 f.pubDate = (Date)obj2.get("pubDate");
	        	 f.category = (String)obj2.get("category");
	        	 f.read = (Boolean)obj2.get("read");
	        	 f.id_feed = id;
	        	 l.add(f);
	         }
	      	}catch(ParseException pe){
	         System.out.println("position: " + pe.getPosition());
	         System.out.println(pe);
	      }
	    return (l);
	}
	
	public static JSONObject	encodeFeed(Feed f){
		JSONObject		obj = new JSONObject();
		
		obj.put("id", -1);
		obj.put("feedLink", f.feedLink);
		obj.put("title", f.title);
		obj.put("link", f.link);
		obj.put("description", f.description);
		return (obj);
	}
	
}
