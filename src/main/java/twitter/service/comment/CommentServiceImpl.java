package twitter.service.comment;

import java.util.Calendar;
import java.util.List;
import javax.json.JsonArray;
import org.springframework.stereotype.Service;
import twitter.beans.Comment;
import twitter.beans.User;
import twitter.dao.ICommentDAO;
import twitter.service.user.UserService;
import twitter.web.dto.CommentDto;

/**
 * Created by Nikolay on 30.04.2017.
 */
@Service
public class CommentServiceImpl implements CommentService {

  private final ICommentDAO commentDAO;
  private final UserService userService;

  public CommentServiceImpl(ICommentDAO commentDAO, UserService userService) {
    this.commentDAO = commentDAO;
    this.userService = userService;
  }

  @Override
  public void addComment(CommentDto commentDto) {
    Comment comment=new Comment();
    comment.setText(commentDto.getText());
    comment.setParentCommentId(commentDto.getParentId());
    comment.setTweetId(commentDto.getTweetId());
    User user=userService.getUserByUsername(commentDto.getAuthor());
    comment.setUser(user);
    comment.setDate(Calendar.getInstance().getTime());
    commentDAO.create(comment);
  }

  @Override
  public void updateComment(Comment comment) {
    commentDAO.update(comment);
  }

  @Override
  public List<Comment> listComment() {
    return commentDAO.getAll();
  }

  @Override
  public void removeComment(Long id) {
    commentDAO.delete(id);
  }

  @Override
  public JsonArray getCommentsByTweetId(Long tweetId) {
    List<Comment> comments = commentDAO.readByTweetId(tweetId);
    CommentsToJsonConverter converter = new CommentsToJsonConverter();
    JsonArray jsonValues = converter.convert(comments);
    return jsonValues;
  }

}
