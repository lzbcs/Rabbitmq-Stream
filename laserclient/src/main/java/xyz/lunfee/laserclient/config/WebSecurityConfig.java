package xyz.lunfee.laserclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String USER = "USER";
//https://stackoverflow.com/questions/35218354/difference-between-registerglobal-configure-configureglobal-configureglo
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //认证
        auth.inMemoryAuthentication()

                .withUser("user1")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()
                .withUser("user2")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("liuyunfei")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("yangfan")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("xuzhiqiang")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("hongyue")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("cuizhiwei")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("fenghaobo")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("jixu")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("zhengyuxing")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("lili")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("liqinru")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("liqingyun")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("nilian")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("zuizhaobini")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("tengzhilin")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("liucong")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("zhuminxin")
                .password(passwordEncoder().encode("123"))
                .roles(USER)


                .and()

                .withUser("xiaofeng")
                .password(passwordEncoder().encode("123"))
                .roles(USER)
                .and()

                .withUser("pengsha")
                .password(passwordEncoder().encode("123"))
                .roles(USER)

                .and()

                .withUser("xujinan")
                .password(passwordEncoder().encode("123"))
                .roles(USER);



    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
