package com.bamboo.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bamboo.board.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	private UserService userService;

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	} // BCrypt라는 해시 함수를 이용하여 패스워드를 암호화

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		// static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
//		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				// 페이지 권한 설정
//				.antMatchers("/board/**").hasRole("USER")
////				.antMatchers("/user/myinfo").hasRole("MEMBER")
////				.antMatchers("/**").permitAll() //모두사용가능
//				.and() // 로그인 설정
//				.formLogin()
//				.loginPage("/user/login")
//				.defaultSuccessUrl("/user/login/result")
//				.permitAll()
//				.and() // 로그아웃
//																												// 설정
//				.logout()
//				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
//				.logoutSuccessUrl("/user/logout/result")
//				.invalidateHttpSession(true)
//				.and()
//				// 403 예외처리 핸들링
//				.exceptionHandling().accessDeniedPage("/user/denied");
//	}
//
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable().csrf().disable().formLogin().disable().headers().frameOptions().disable();
	}
}