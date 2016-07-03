package com.chris.serialization;

import com.chris.entity.User;
import com.chris.model.UserType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings({"WeakerAccess", "OptionalUsedAsFieldOrParameterType"})
public class Request {
    public final User user;

    @JsonCreator
    public Request(@JsonProperty("reg_no") Long regNo, @JsonProperty("id") String id, @JsonProperty("pwd") String pwd, @JsonProperty("name") String name, 
    		@JsonProperty("birthday") String birthday, @JsonProperty("email") String email, @JsonProperty("description") String description, @JsonProperty("type")  UserType type) {
        this.user = new User(regNo, id, pwd, name, birthday, email, description, type);
    }
}
