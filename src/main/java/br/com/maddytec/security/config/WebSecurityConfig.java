package br.com.maddytec.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.maddytec.security.JwtAuthenticationEntryPoint;
import br.com.maddytec.security.filters.JwtAuthenticationTokenFilter;


@EnableWebSecurity
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationTokenFilter();
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf()
				.disable()
				.exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers("/swagger-ui.html").permitAll()
				.antMatchers("/swagger/**").permitAll()
				.antMatchers("/swagger.json").permitAll()
				.antMatchers("/", "/csrf", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
						"/configuration/**", "/swagger-ui.html", "/webjars/**").permitAll()
				.antMatchers("/auth/**").permitAll()
				.anyRequest().authenticated();
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		httpSecurity.headers().cacheControl();
	}
}
