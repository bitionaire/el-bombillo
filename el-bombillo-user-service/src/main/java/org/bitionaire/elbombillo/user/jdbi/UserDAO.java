package org.bitionaire.elbombillo.user.jdbi;

import org.bitionaire.elbombillo.user.api.model.User;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(UserMapper.class)
public interface UserDAO {

    @SqlQuery("select id, username, firstname, lastname, email from bombillo_user where id = :id")
    User findUser(@Bind("id") long id);

    @SqlQuery("select id, username, firstname, lastname, email from bombillo_user")
    List<User> allUsers();

    @GetGeneratedKeys
    @SqlUpdate("insert into bombillo_user (username, firstname, lastname, email) values (:username, :firstName, :lastName, :email)")
    long insertUser(@BindBean User user);
}
