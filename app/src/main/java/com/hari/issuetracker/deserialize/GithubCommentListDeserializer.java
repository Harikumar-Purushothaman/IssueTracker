package com.hari.issuetracker.deserialize;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.hari.issuetracker.model.CommentsListItem;
import com.hari.issuetracker.model.IssueListItem;
import com.hari.issuetracker.model.User;

import org.w3c.dom.Comment;

import java.lang.reflect.Type;

/**
 * Created by sivaavatar on 2/17/2018.
 */

public class GithubCommentListDeserializer implements JsonDeserializer<CommentsListItem> {

    @Override
    public CommentsListItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        CommentsListItem commentsListItem = new CommentsListItem();

        JsonObject repoJsonObject = json.getAsJsonObject();
        commentsListItem.setBody(repoJsonObject.get("body").getAsString());

        User user = new User();

        JsonElement userJsonElement = repoJsonObject.get("user");
        JsonObject userJsonObject = userJsonElement.getAsJsonObject();
        user.setLogin(userJsonObject.get("login").getAsString());
        user.setAvatar_url(userJsonObject.get("avatar_url").getAsString());

        commentsListItem.setUser(user);
        return commentsListItem;
    }
}
