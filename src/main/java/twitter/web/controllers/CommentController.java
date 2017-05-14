package twitter.web.controllers;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import twitter.beans.Comment;
import twitter.beans.User;
import twitter.service.comment.CommentService;
import twitter.web.dto.CommentDto;

import javax.json.JsonValue;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Nikolay on 01.05.2017.
 */
@Controller
@RequestMapping("/comments")
public class CommentController {

  private final CommentService commentService;

  @Autowired
  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @Pointcut(
      value = "execution(* twitter.web.controllers.CommentController.addComment(..))" +
          "&& args(request, user, commentDto)", argNames = "request,user,commentDto")
  @PostMapping
  public @ResponseBody CommentDto addComment(HttpServletRequest request,
      @SessionAttribute("user") User user, @RequestBody CommentDto commentDto){
    commentService.addComment(commentDto);
    return commentDto;
  }

  @GetMapping
  public @ResponseBody String loadCommentsForTweet(@RequestParam Long tweetId){
    return commentService.getCommentsByTweetId(tweetId).toString();
  }
}
