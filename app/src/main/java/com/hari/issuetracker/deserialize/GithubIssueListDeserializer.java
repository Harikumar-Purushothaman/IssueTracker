package com.hari.issuetracker.deserialize;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.hari.issuetracker.model.IssueListItem;
import com.hari.issuetracker.model.User;

import java.lang.reflect.Type;

/**
 * Created by sivaavatar on 2/17/2018.
 */

public class GithubIssueListDeserializer implements JsonDeserializer<IssueListItem> {

    @Override
    public IssueListItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        IssueListItem issueListItem = new IssueListItem();

        JsonObject repoJsonObject = json.getAsJsonObject();
        issueListItem.setTitle(repoJsonObject.get("title").getAsString());
        issueListItem.setComments_url(repoJsonObject.get("comments_url").getAsString());
        issueListItem.setUpdated_at(repoJsonObject.get("updated_at").getAsString());
        issueListItem.setBody(repoJsonObject.get("body").getAsString());

        User user = new User();

        JsonElement userJsonElement = repoJsonObject.get("user");
        JsonObject userJsonObject = userJsonElement.getAsJsonObject();
        user.setLogin(userJsonObject.get("login").getAsString());
        user.setAvatar_url(userJsonObject.get("avatar_url").getAsString());

        issueListItem.setUser(user);
        return issueListItem;
    }
}
