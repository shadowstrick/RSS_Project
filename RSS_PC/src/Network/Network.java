package Network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;

public class Network {

	private String _url;
	
	public Network(String url)
	{
		_url = url;
	}
	
	public NetworkResponse sendRequestPOST(String command, String datas ) throws Exception {
		String s_url = _url + command;
		URL url = new URL(s_url);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		
		con.setRequestMethod("POST");
		con.setRequestProperty("content-type", "application/JSON");
		
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream ());
		wr.writeBytes(datas);
		wr.flush();
		wr.close();
		
		int code = con.getResponseCode();
 
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String buffer;
		StringBuffer s_response = new StringBuffer();
 
		while ((buffer = in.readLine()) != null) {
			s_response.append(buffer);
		}
		in.close();
		
		NetworkResponse response = new NetworkResponse();
		response.code = code;
		response.contents = s_response.toString();
		
		return (response);
	}

	public NetworkResponse sendRequestGET(String command) throws Exception {
		String s_url = _url + command;
		URL url = new URL(s_url);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		
		con.setRequestMethod("GET");
		con.setRequestProperty("content-type", "application/JSON");
		
		con.setDoOutput(true);
		
		int code = con.getResponseCode();
	
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String buffer;
		StringBuffer s_response = new StringBuffer();
	
		while ((buffer = in.readLine()) != null) {
			s_response.append(buffer);
		}
		in.close();
		
		NetworkResponse response = new NetworkResponse();
		response.code = code;
		response.contents = s_response.toString();
		
		return (response);
	}

	
	
	
}
