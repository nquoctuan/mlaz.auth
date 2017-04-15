package com.mlaz.api.controller;

import com.google.gson.Gson;
import com.mlaz.api.Repositories.MlazUserProfileRepository;
import com.mlaz.api.model.MlazUserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by jimmy on 15/4/17.
 */
@Controller
public class SignUpController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignUpController.class);

    private ProviderSignInUtils providerSignInUtils;

    private MlazUserProfileRepository mlazUserProfileRepository;

    @Autowired
    public SignUpController(MlazUserProfileRepository mlazUserProfileRepository,
                            ConnectionFactoryLocator connectionFactoryLocator,
                            UsersConnectionRepository connectionRepository) {
        this.mlazUserProfileRepository = mlazUserProfileRepository;
        this.providerSignInUtils = new ProviderSignInUtils(connectionFactoryLocator, connectionRepository);;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(WebRequest request, Model model) {
        LOGGER.info("redirect to register");

        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        Facebook facebook = (Facebook) connection.getApi();
        String [] fields = { "id", "email",  "first_name", "last_name" };
        User user = facebook.fetchObject("me", User.class, fields);

        MlazUserProfile userProfile = new MlazUserProfile(user.getId(), user.getFirstName(), user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getName());


        if (mlazUserProfileRepository.save(userProfile) == null) {
            LOGGER.error("CANNOT SAVE USER TO DB");
        }
        model.addAttribute(userProfile);

        LOGGER.debug("USER SESSION: {}",(new Gson()).toJson(model));
        return "home";
    }
}
