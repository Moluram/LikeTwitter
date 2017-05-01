package twitter.service.comment;

import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import org.springframework.stereotype.Service;
import twitter.beans.Comment;
import twitter.dao.ICommentDAO;

/**
 * Created by Nikolay on 30.04.2017.
 */
@Service
public class CommentServiceImpl implements CommentService {

  private final ICommentDAO commentDAO;

  public CommentServiceImpl(ICommentDAO commentDAO) {
    this.commentDAO = commentDAO;
  }

  @Override
  public void addComment(Comment comment) {
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
    return converter.convert(comments);
  }

}
