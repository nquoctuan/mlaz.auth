//package com.mlaz.api.config;
//
//import com.mlaz.api.dao.UsersDao;
//import com.mlaz.api.services.AccountConnectionSignUpService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.encrypt.Encryptors;
//import org.springframework.social.UserIdSource;
//import org.springframework.social.config.annotation.EnableSocial;
//import org.springframework.social.config.annotation.SocialConfigurerAdapter;
//import org.springframework.social.connect.ConnectionFactoryLocator;
//import org.springframework.social.connect.UsersConnectionRepository;
//import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
//import org.springframework.social.security.AuthenticationNameUserIdSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableOAuth2Sso
//public class SocialConfig extends SocialConfigurerAdapter {
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private UsersDao usersDao;
//
//    @Override
//    public UserIdSource getUserIdSource() {
//        return new AuthenticationNameUserIdSource();
//    }
//
//    @Override
//    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
//        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
//        repository.setConnectionSignUp(new AccountConnectionSignUpService(usersDao));
//        return repository;
//    }
//}
