package com.gft.delivery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/*
	@Autowired
	private AutenticationService autenticationService;
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	 
	@Override
	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(autenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}
	*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			//.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
			//.and()
		
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/auth").permitAll()
			.antMatchers("/produtos/**",
						 "/estoques/**",
						 "/fornecedores/**",
						 "/clientes/**",
						 "/compras/**").permitAll()
			.anyRequest().authenticated();
			//.and()
			//.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);	
	}
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        	.antMatchers("/**.html",
        				 "/v2/api-docs",
        				 "/webjars/**",
        				 "/configuration/**","/swagger-resources/**");
    }
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /*
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
    */
    /*
    @Bean
    @Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
    	return super.authenticationManagerBean();
    }
	*/
}

