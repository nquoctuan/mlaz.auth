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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class MainController {

    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private MlazUserProfileRepository mlazUserProfileRepository;

    @RequestMapping("/")
    public String home(HttpServletRequest request, Principal currentUser, Model model) {
        LOG.info("HOME");
        //util.setModel(request, currentUser, model);
        return "home";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Principal currentUser, Model model) {
        LOG.info("LOGIN");

        Gson gson = new Gson();

        if (currentUser == null)
            return "login" ;

        String userId = currentUser.getName();

        MlazUserProfile mlazUserProfile = mlazUserProfileRepository.findByUserId(userId);
        LOG.info(gson.toJson(mlazUserProfile));

        return "login";
    }
}

//
//    @Autowired
//    private ConnectionRepository connectionRepository;
//
//    @Autowired
//    private DataDao dataDao;
//
//    @Autowired
//    private SocialControllerUtil util;
//
//    @RequestMapping("/")
//    public String home(HttpServletRequest request, Principal currentUser, Model model) {
//        util.setModel(request, currentUser, model);
//        return "home";
//    }
//
//    @RequestMapping("/login")
//    public String login(HttpServletRequest request, Principal currentUser, Model model) {
//        util.setModel(request, currentUser, model);
//        return "login";
//    }
//
//    @RequestMapping(value= "/update", method = POST)
//    public String update(
//        HttpServletRequest request,
//        Principal currentUser,
//        Model model,
//        @RequestParam(value = "data", required = true) String data) {
//
//        LOG.debug("Update data to: {}", data);
//        String userId = currentUser.getName();
//        dataDao.setDate(userId, data);
//
//        util.setModel(request, currentUser, model);
//        return "home";
//    }
//
//    @RequestMapping(value= "/updateStatus", method = POST)
//    public String updateStatus(
//        WebRequest webRequest,
//        HttpServletRequest request,
//        Principal currentUser,
//        Model model,
//        @RequestParam(value = "status", required = true) String status) {
//        MultiValueMap<String, Connection<?>> cmap = connectionRepository.findAllConnections();
//        LOG.error("cs.size = {}", cmap.size());
//        Set<Map.Entry<String, List<Connection<?>>>> entries = cmap.entrySet();
//        for (Map.Entry<String, List<Connection<?>>> entry : entries) {
//            for (Connection<?> c : entry.getValue()) {
//                LOG.debug("Updates {} with the status '{}'", entry.getKey(), status);
//                c.updateStatus(status);
//            }
//        }
//
//        return "home";
//    }
//}
