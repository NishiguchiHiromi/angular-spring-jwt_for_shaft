package com.example.mySource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MySourceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void encryptPW() {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String encodedPW = bcrypt.encode("test");
		System.out.println(encodedPW);

//		"$2a$10$olykWQM8.JAHQQN4H9VW2.DR3TWNO0NwBIBEhHshMB6yxf4c/QwNe"
	}

}
