package Network;

import static org.junit.Assert.*;

import org.json.simple.JSONObject;
import org.junit.Test;

public class NetworkTest {
		
		private String url = "http://ec2-54-93-205-81.eu-central-1.compute.amazonaws.com:8090";
	
		@Test
		public void sendGoodSimplePostRequest() {
			Network request = new Network(url);
			try
			{
			assertNotNull(request.sendRequestPOST("/users/auth/Sebastien/727056edb013b13836abc1761ecb9d841b5b2ff7689b219303e67c17f128bd7b", ""));
			} catch(Exception e){
				
			}
		}
		
		@Test
		public void sendMissingSimplePostRequest() {
			Network request = new Network(url);
			NetworkResponse r = new NetworkResponse();
			
			try
			{
			assertEquals(r, request.sendRequestPOST("/users/auth/Sebastien", ""));
			} catch (Exception e)
			{
				
			}
		}
		
		@Test
		public void getGoodSimpleGetRequest() {
			Network request = new Network(url);
			
			try
			{
				assertNotNull(request.sendRequestGET("feed/user/get/Sebastien/727056edb013b13836abc1761ecb9d841b5b2ff7689b219303e67c17f128bd7b"));
			} catch (Exception e)
			{
				
			}
		}
	
}
