package com.bksoftware.sellingweb.security;

import com.bksoftware.sellingweb.service_impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
//        http.antMatcher("/api/**/admin/**")
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint())
//                .and()
//                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
//                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());

        http.authorizeRequests()
                .antMatchers("/admin/**").authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");

        http.authorizeRequests()
                .and()
                .formLogin()
                .loginPage("/admin/login").permitAll().loginProcessingUrl("/admin/login/pass")
                .failureUrl("/admin/login?error").defaultSuccessUrl("/admin/home",true);
        http.authorizeRequests().and().logout().logoutUrl("/admin/login/logout").logoutSuccessUrl("/admin/login");

//                .formLogin().defaultSuccessUrl("/admin/home");
//                .antMatchers("/resources/**").hasIpAddress("127.0.0.1")
//                .and()
//                .httpBasic().authenticationEntryPoint(restAuthenticationEntryPoint())
//                .and()
//                .addFilter(new APIFilter(authenticationManager()));
        //     http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
