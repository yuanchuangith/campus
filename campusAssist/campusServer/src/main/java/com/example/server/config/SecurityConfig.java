package com.example.server.config;

import com.example.server.config.hander.AccessDenis;
import com.example.server.config.hander.userAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity    // 添加 security 过滤器
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private  JwtAuthFilter jwtAuthFilter;
    @Autowired
    private  urlFiter urlFiter;

//    @Autowired
//    private AccessDenis accessDenis;
//    @Autowired
//    private userAccessDeniedHandler userAccessDeniedHandler;

//    @Bean
//    public JwtAuthFilter authFilter() throws Exception {
//        return new JwtAuthFilter();
//    }
    //获取AuthenticationManager（认证管理器），登录时认证使用
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public JwtAuthFilter jwtAuthenticationTokenFilter() {
//        return new JwtAuthFilter();
//    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer( )throws Exception {
        //允许不登录验证访问
//        (web) ->  web.ignoring().
        return (web) -> web.ignoring().antMatchers("/Account/login","/Account/index","/Account/*","/work/*","/conpany/*","/aply/*","/obedient/*","/serverCode/*","/upload/*","/content/*","/login/doLogin",
                "/login/doLogout",
                "/css/**",
                "/js/**",
                "/index.html",
                "favicon.ico",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**",
                "/captcha/**",
                "/wsyeb/**");
    }

//    配置过滤
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


//       http.exceptionHandling().authenticationEntryPoint(authEntr).accessDeniedHandler(userAccessDeniedHandler);
//       http.exceptionHandling().accessDeniedHandler(userAccessDeniedHandler);
        http.addFilterBefore(urlFiter,UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(jwtAuthFilter,urlFiter.class);


    return http.csrf().disable()//关闭csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//关闭session
                .and()
                .authorizeRequests(auth ->
                        auth.antMatchers().permitAll()
                                .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService).build();
    }

    //配置加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
