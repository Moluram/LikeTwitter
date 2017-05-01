package twitter.service.comment;

import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonObject;
import twitter.beans.Comment;
import twitter.beans.Role;

/**
 * Created by Nikolay on 28.04.2017.
 */
public interface CommentService {

    void addComment(Comment comment);

    void updateComment(Comment comment);

    List<Comment> listComment();

    void removeComment(Long id);

    JsonArray getCommentsByTweetId(Long tweetId);
}
