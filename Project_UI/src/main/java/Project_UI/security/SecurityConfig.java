package Project_UI.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
                http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/joinForm", "/read", "/login", "/result", "/login-fail", "/logout", "/result").permitAll()
                    .antMatchers("/css/**","/lib/**", "/img/**", "/js/**", "/favicon.ico").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .usernameParameter("member_id")
                    .passwordParameter("member_pw")
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/login-success")
                    .failureUrl("/login-fail")
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true);
        
        	return http.build();
		
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
