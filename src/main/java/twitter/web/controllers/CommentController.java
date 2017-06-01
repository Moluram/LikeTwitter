package twitter.web.controllers;

import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import twitter.entity.Comment;
import twitter.entity.User;
import twitter.service.comment.CommentService;
import twitter.web.dto.CommentDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Serve for working with comments
 *
 * @author Nikolay
 */
@Controller
@RequestMapping("/comments")
public class CommentController {

  private final CommentService commentService;

  @Autowired
  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @Pointcut(value = "execution(* twitter.web.controllers.CommentController.addComment(..))" +
          "&& args(request, commentDto)", argNames = "request,commentDto")
  @PostMapping
  public @ResponseBody CommentDto addComment(HttpServletRequest request,
                                             @RequestBody CommentDto commentDto){
    commentService.addComment(commentDto);
    return commentDto;
  }

  @GetMapping
  public @ResponseBody String loadCommentsForTweet(@RequestParam Long tweetId){
    return commentService.getCommentsByTweetId(tweetId).toString();
  }

  @PostMapping("/{commentId}/delete")
  public @ResponseBody Boolean deleteComment(@PathVariable Long commentId, @SessionAttribute("user") User sessionUser){
    Comment comment=commentService.getById(commentId);
    if(comment.getUser().equals(sessionUser)){
      commentService.removeComment(commentId);
      return true;
    }
    return false;
  }
}
