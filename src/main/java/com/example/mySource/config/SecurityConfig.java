package com.example.mySource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.mySource.auth.JWTAuthenticationFilter;
import com.example.mySource.auth.JWTAuthorizationFilter;
import com.example.mySource.auth.UserDetailsServiceImpl;
import com.example.mySource.constant.CommonConstant;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


	@Value("${client.origin}")
	private String clientOrigin;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(this.corsConfigurationSource()).and().authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/main/**").permitAll()
				.antMatchers("/outsideLogin/**").permitAll()
				.antMatchers("/error").permitAll()
				.antMatchers("/errorInfo").permitAll()
				.anyRequest().authenticated()
				.and().logout()
				.and().csrf().disable()
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), bCryptPasswordEncoder()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager())).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private CorsConfigurationSource corsConfigurationSource() {
		System.out.println(clientOrigin);
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
		corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
		corsConfiguration.addExposedHeader(CommonConstant.AUTHORIZATION_HEADER_NAME);
		corsConfiguration.addAllowedOrigin(clientOrigin);
//        corsConfiguration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
		corsSource.registerCorsConfiguration("/**", corsConfiguration);

		return corsSource;
	}
}
