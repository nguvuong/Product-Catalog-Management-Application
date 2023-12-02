package ca.sheridan.nguvuong.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity
										http) throws Exception {
		http
		.authorizeHttpRequests((requests) -> requests
		.requestMatchers("/index").permitAll()
		.requestMatchers("/admin/**").hasAuthority("ADMIN")
		.requestMatchers("/sales/**").hasAuthority("SALES")
		.anyRequest().authenticated()
		)
		.formLogin().loginPage("/login").permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll();
		return http.build();
	}

	@Bean
	public UserDetailsService users() {
		// The builder will ensure the passwords are encoded before saving in memory
		UserBuilder users = User.withDefaultPasswordEncoder();
		UserDetails admin = users
		.username("userA")
		.password("aaaa")
		.authorities("ADMIN")
		.build();
		UserDetails sales = users
		.username("userB")
		.password("bbbb")
		.authorities( "SALES")
		.build();
		UserDetails boss = users
				.username("UserC")
				.password("cccc")
				.authorities( "SALES", "ADMIN")
				.build();
	
		return new InMemoryUserDetailsManager(admin, sales, boss);
	}

}