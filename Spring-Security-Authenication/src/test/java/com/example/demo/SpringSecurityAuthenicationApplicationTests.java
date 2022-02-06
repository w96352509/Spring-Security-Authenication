package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


class SpringSecurityAuthenicationApplicationTests {

	public static void main(String[] args) {
		PasswordEncoder pe = new BCryptPasswordEncoder();
		String ecode = pe.encode("1234");
		System.out.println(ecode);
		boolean matchs = pe.matches("1234", ecode);
		System.out.println(matchs);
		System.out.println();
		
		
		String ecode2 = pe.encode("5678");
		System.out.println(ecode2);
		boolean matchs2 = pe.matches("5678", ecode2);
		System.out.println(matchs2);
	}
}
