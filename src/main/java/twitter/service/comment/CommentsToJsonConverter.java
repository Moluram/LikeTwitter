package twitter.service.comment;

import java.util.List;
import java.util.stream.Collectors;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import twitter.beans.Comment;
import twitter.service.constants.CommentJsonFields;

/**
 * Created by Nikolay on 30.04.2017.
 */
class CommentsToJsonConverter {

  private List<Comment> originalComments;

  public JsonArray convert(List<Comment> comments) {
    setOriginalComments(comments);
    List<Comment> firstLayerComments = comments.stream().filter(c -> c.getParentCommentId() == null)
        .collect(Collectors.toList());
    JsonArrayBuilder originalCommentsBuilder = Json.createArrayBuilder();
    addCommentsToTree(originalCommentsBuilder, firstLayerComments);
    return originalCommentsBuilder.build();
  }

  private void addCommentsToTree(JsonArrayBuilder jsonCommentsBuilder, List<Comment> comments) {
    for (Comment currentComment : comments) {
      JsonObjectBuilder jsonCommentBuilder = Json.createObjectBuilder()
          .add(CommentJsonFields.FIELD_ID, currentComment.getId())
          .add(CommentJsonFields.FIELD_PARENT_ID,
              String.valueOf(currentComment.getParentCommentId()))
          .add(CommentJsonFields.FIELD_TEXT, currentComment.getText())
          .add(CommentJsonFields.FIELD_DATE, currentComment.getDate().toString())
          .add(CommentJsonFields.FIELD_PUBLISHER, currentComment.getUser().getUsername())
          .add(CommentJsonFields.FIELD_PUBLISHER_PHOTO,
              currentComment
                  .getUser()
                  .getUserProfile()
                  .getMiniPhoto()
          );
      List<Comment> childComments = originalComments
          .stream()
          .filter(c ->
              currentComment.getId().equals(c.getParentCommentId()))
          .collect(Collectors.toList());
      JsonArrayBuilder childCommentsBuilder = Json.createArrayBuilder();
      if (childComments.size() > 0) {
        addCommentsToTree(childCommentsBuilder, childComments);
      }
      jsonCommentsBuilder.add(jsonCommentBuilder
          .add("child", childCommentsBuilder.build())
          .build()
      );
    }
  }

  public void setOriginalComments(List<Comment> originalComments) {
    this.originalComments = originalComments;
  }
}
