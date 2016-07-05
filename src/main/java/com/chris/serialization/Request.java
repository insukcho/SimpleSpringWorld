package com.chris.serialization;

import com.chris.entity.User;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Request {
    public final User user;

    @JsonCreator
    public Request(@JsonProperty("regNo") Long regNo, @JsonProperty("id") String id, @JsonProperty("pwd") String pwd, @JsonProperty("name") String name, 
    		@JsonProperty("birthday") String birthday, @JsonProperty("email") String email, @JsonProperty("description") String description, @JsonProperty("userType")  String userType) {
        this.user = new User(regNo, id, pwd, name, birthday, email, description, userType);
    }
}
