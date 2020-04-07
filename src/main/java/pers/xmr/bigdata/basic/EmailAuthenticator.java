package pers.xmr.bigdata.basic;

import lombok.Getter;
import lombok.Setter;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

@Getter
@Setter
public class EmailAuthenticator extends Authenticator {
    private String userName;
    private String password;

    public EmailAuthenticator() {
    }

    public EmailAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }

}
