package io.khusanjon.onlineclassroom.config.sms;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Akbarov Samandar
 * @created 13/07/2021 - 9:30 PM
 */
@Configuration
@ConfigurationProperties("playmobile")
public class PlayMobileConfiguration {
    private String login;
    private String password;
    private String url;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
