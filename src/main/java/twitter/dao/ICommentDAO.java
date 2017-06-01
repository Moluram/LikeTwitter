package twitter.dao;

import java.util.List;
import twitter.entity.Comment;

/**
 * Created by Nikolay on 27.04.2017.
 */
public interface ICommentDAO extends IGenericDAO<Comment> {

  List<Comment> readByTweetId(Long tweetId);
}
