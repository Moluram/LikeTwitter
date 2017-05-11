package twitter.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import twitter.beans.Comment;
import twitter.service.comment.CommentService;
import twitter.web.dto.CommentDto;

import javax.json.JsonValue;

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

  @PostMapping
  public @ResponseBody CommentDto addComment(@RequestBody CommentDto commentDto){
    commentService.addComment(commentDto);
    return commentDto;
  }

  @GetMapping
  public @ResponseBody String loadCommentsForTweet(@RequestParam Long tweetId){
    return commentService.getCommentsByTweetId(tweetId).toString();
  }
}
