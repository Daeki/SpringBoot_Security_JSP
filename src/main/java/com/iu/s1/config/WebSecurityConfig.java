package com.iu.s1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.iu.s1.member.KakaoService;
import com.iu.s1.security.LoginFail;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private KakaoService kakaoService;
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        .antMatchers("/resources/**")
        .antMatchers("/css/**")
        .antMatchers("/vendor/**")
        .antMatchers("/js/**")
        .antMatchers("/favicon*/**")
        .antMatchers("/img/**")
        ;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.exceptionHandling()
//				.accessDeniedPage("/error/error.html")
//				.accessDeniedHandler(new SecurityException())
				.and()
//			csrf 사용 안하려 함 index
			.cors().and()
			.csrf().disable()
			.authorizeRequests()
				//.antMatchers("/", "/home", "/css/**", "/js/**", "/images/**").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/notice/list", "/notice/detail").permitAll()
				.antMatchers("/notice/**").hasRole("ADMIN")
//				.antMatchers("/member/memberLogin?error", "/error").permitAll()
				.antMatchers("/member/memberJoin").permitAll()
				.antMatchers("/member/**").hasAnyRole("ADMIN", "MEMBER")
				.antMatchers("/oauth2/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/member/memberLogin")
//                .loginProcessingUrl("/doLogin")				- 로그인 폼의 경로를 따로 줄 수도 있음
//                .usernameParameter("id")						- username 파라미터의 이름을 다른 것으로 줄 경우
//                .passwordParameter("pw")						- password 파라미터의 이름을 다른 것으로 줄 경우
//                .successHandler(new MyLoginSuccessHandler())	- 로그인 성공 후 어떤 로직을 실행 하고 싶은 경우 사용
				.defaultSuccessUrl("/member/memberLoginResult")
//				.failureUrl("/member/memberLogin?error")
//				.failureHandler(new LoginFail())
				.permitAll()									//- 이곳에서 하지 않으면 위에서 허용 해줘야 함
				.and()
			.rememberMe()
				.tokenValiditySeconds(2592000)
				.key("rememberKey")
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/memberLogout"))
//	            .logoutUrl("/member/memberLogout")
	            .logoutSuccessUrl("/")
	            .invalidateHttpSession(true)
	            .deleteCookies("JSESSIONID")
				.permitAll()
				.and()
			.oauth2Login()
	         	.userInfoEndpoint()
	         		.userService(kakaoService)//- 이곳에서 하지 않으면 위에서 허용 해줘야 함
			;
			
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
      LoginSuccessHandler 생성
      
      public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {
		    @Override
		    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		        HttpSession session = request.getSession();
		​
		        session.setAttribute("greeting", authentication.getName() + "님 반갑습니다.");
		​
		        response.sendRedirect("/");
		    }
		}
      
     * */

}
