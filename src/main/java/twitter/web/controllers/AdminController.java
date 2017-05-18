package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import twitter.beans.User;
import twitter.service.comment.CommentService;
import twitter.service.tweet.TweetService;
import twitter.service.user.UserService;
import twitter.web.constants.AttributeNamesConstants;
import twitter.web.constants.PageNamesConstants;
import twitter.web.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nborsuk on 16.05.2017.
 */
@Controller
@RequestMapping("/admin")
public class  AdminController {

    private static final Long LIMIT_USERS_ON_PAGE = 2L;

    private UserService userService;
    private CommentService commentServie;
    private TweetService tweetService;

    @Autowired
    public AdminController(UserService userService, CommentService commentServie, TweetService tweetService) {
        this.userService = userService;
        this.commentServie = commentServie;
        this.tweetService = tweetService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getUserList(@RequestParam(value = AttributeNamesConstants.PAGE_NUMBER, defaultValue = "1") int number,
                                    @SessionAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME) User user, ModelAndView model) {
        Long userCount = userService.count();
        Long pageCount = userCount / LIMIT_USERS_ON_PAGE;
        if (pageCount * LIMIT_USERS_ON_PAGE < userCount) {
            pageCount++;
        }
        model.addObject("pageCount", pageCount);
        model.setViewName(PageNamesConstants.USERS);
        if (number < 1) {
            return model;
        }
        Long offset = (number - 1) * LIMIT_USERS_ON_PAGE;
        List<UserDto> dtos = userService.listUser(LIMIT_USERS_ON_PAGE, offset)
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
        model.addObject(AttributeNamesConstants.USERS, dtos);
        model.addObject("currentPage", number);

        return model;
    }

    @RequestMapping(value="/ban",method = RequestMethod.POST)
    public @ResponseBody Boolean banUser(@RequestParam("userId") Long userId){
        userService.updateUserBan(userId,true);
        return true;
    }

    @RequestMapping(value="/unban",method = RequestMethod.POST)
    public @ResponseBody Boolean unBanUser(@RequestParam("userId") Long userId){
        userService.updateUserBan(userId,false);
        return true;
    }
}
