package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.handle.MyaccessDeniedHandler;

//若要自訂登入邏輯則要繼承
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private MyaccessDeniedHandler myaccessDeniedHandler;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//表單提交
		http.formLogin()
		//loginpage.html 表單 action 內容
		    .loginProcessingUrl("/login")
		//自訂義登入介面
		    .loginPage("/loginpage")
		//登入成功後造訪頁面
		    .successForwardUrl("/") //welcome 頁面
		//登入失敗後造訪頁面
		    .failureForwardUrl("/fail");
		
		//授權認證
		http.authorizeHttpRequests()
		   // 不需要被認證的頁面: /loginpage
		   .antMatchers("/loginpage").permitAll()
		   //權限判斷
		   //必須要有admin權限才可以進入
		   .antMatchers("/adminpage").hasAnyAuthority("admin")
		   //必須要有 manager 角色才可訪問
		   .antMatchers("/managerpage").hasRole("manager")
		   //指定任意角色都可訪問
		   .antMatchers("/employeepage").hasAnyRole("manager","employee")
		   //其他都要被認證
		   .anyRequest().authenticated();
		
		// 登出 
		http.logout()
		    .deleteCookies("JSESSIONID")
		    .logoutSuccessUrl("/loginpage")
		    .logoutRequestMatcher(new AntPathRequestMatcher("/logout")); //可以使用任何 http 方法登出
		//異常處理
		http.exceptionHandling()
		    //.accessDeniedPage(null) 異常處理頁面 二擇一
		    .accessDeniedHandler(myaccessDeniedHandler);
		    
		// http.csrf().disable(); 關閉 csrf 防護
		
		// remember me
		http.rememberMe()
		    .userDetailsService(userDetailsService)
		    .tokenValiditySeconds(60); //通常都會大於 session out 時間
	}

	
	


	//(必要)要建立密碼演算的實例	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
