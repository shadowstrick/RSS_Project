package Utils;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Test;

public class UtilsTest {
	
	@Test
	public void getHash() {
	 	assertEquals(Utils.getHash("Password"), "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8");
	}

}
