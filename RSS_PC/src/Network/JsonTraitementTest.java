package Network;

import static org.junit.Assert.*;

import org.json.simple.JSONObject;
import org.junit.Test;

import Model.BasicResponse;
import Model.Feed;
import Model.User;

public class JsonTraitementTest {

	@Test
	public void getHash() {
		BasicResponse b = new BasicResponse();
		
		b.message = "testMessage";
		b.success = false;
	 	assertEquals(JsonTraitement.basicResponse("{message:\"testMessage\" success=false}"), b);
	}
	
	@Test
	public void	encodeFeed()
	{
		JSONObject	result = new JSONObject();
		Feed f = new Feed();
		
		result.put("description", "descr");
		f.description = "descr";
		result.put("feedLink", "http://...");
		f.feedLink = "http://...";
		result.put("id", 5);
		f.id = 5;
		result.put("title", "test");
		f.title="test";
		assertEquals(JsonTraitement.encodeFeed(f), result);
	}
	
	@Test
	public void EncodeUncodeUser()
	{
		User u = new User();
		
		u.email = "toto@gmail.com";
		u.id = 5;
		u.password = "12345";
		u.username = "toto";
		
		User v = JsonTraitement.JsonUncodeUser(JsonTraitement.JsonEncodeUser(u).toJSONString());
		if (u.email.compareTo(v.email) != 0 || u.id != v.id || u.password.compareTo(v.password) != 0 || u.username.compareTo(v.username) != 0)
			fail();
	}
	
}
