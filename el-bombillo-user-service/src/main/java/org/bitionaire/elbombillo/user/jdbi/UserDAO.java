package org.bitionaire.elbombillo.user.jdbi;

import org.bitionaire.elbombillo.user.api.model.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(UserMapper.class)
public interface UserDAO {

    @SqlQuery("select id, username, firstname, lastname, email from user where id = :id")
    User findUser(@Bind("id") long id);

    @SqlQuery("select id, username, firstname, lastname, email from user")
    List<User> allUsers();
}
