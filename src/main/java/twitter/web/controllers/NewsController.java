package twitter.web.controllers;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import twitter.beans.Tweet;
import twitter.beans.User;
import twitter.dao.IUserDAO;
import twitter.service.news.NewsService;
import twitter.web.constants.AttributeNamesConstants;
import twitter.web.constants.PageNamesConstants;
import twitter.web.constants.URLConstants;
import twitter.web.constants.WebConstants;
import twitter.web.dto.TweetDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller is used to process login
 *
 * @author moluram
 */
@Controller
@RequestMapping(WebConstants.SLASH + URLConstants.NEWS_PAGE)
public class NewsController {
  private static final Integer NUMBER_OF_NEWS = 10;
  private NewsService newsService;
  private IUserDAO userDAO;

  @Autowired
  public void setUserDAO(IUserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Autowired
  public void setNewsService(NewsService newsService) {
    this.newsService = newsService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String getPage(Model model, @SessionAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME) User user) {
    List<Tweet> news = newsService.getLatest(user);
    if (NUMBER_OF_NEWS < news.size()) {
      news.subList(0, NUMBER_OF_NEWS);
    }
    model.addAttribute(AttributeNamesConstants.NEWS, listOfDto(news));
    return PageNamesConstants.NEWS;
  }

  @RequestMapping(value = WebConstants.SLASH + URLConstants.GET_NEWS, method = RequestMethod.GET)
  public @ResponseBody List<TweetDto> getNewsOnPage(@RequestParam(AttributeNamesConstants.PAGE_NUMBER) int number,
                                      @SessionAttribute(AttributeNamesConstants.USER_ATTRIBUTE_NAME) User user) {
    List<Tweet> news = newsService.getLatest(user);
    if (NUMBER_OF_NEWS * (number + 1) < news.size()) {
      news = news.subList(NUMBER_OF_NEWS * number, NUMBER_OF_NEWS * (number + 1));
    } else if (NUMBER_OF_NEWS * number < news.size()) {
      news = news.subList(NUMBER_OF_NEWS * number, news.size());
    } else {
      return null;
    }
    return listOfDto(news);
  }


  private List<TweetDto> listOfDto(List<Tweet> userTweets) {
    List<TweetDto> tweetDtos = new ArrayList<>();
    for ( Tweet tweet: userTweets) {
      tweetDtos.add(new TweetDto(tweet, userDAO.findByUsername(tweet.getOwnerUsername())));
    }
    return Lists.reverse(tweetDtos);
  }
}
