package org.bitionaire.elbombillo.account.jdbi;

import org.bitionaire.elbombillo.account.api.model.Account;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(AccountMapper.class)
public interface AccountDAO {

    @SqlQuery("select id, username, firstname, lastname, email from bombillo_user where id = :id")
    Account findUser(@Bind("id") long id);

    @SqlQuery("select id, username, firstname, lastname, email from bombillo_user")
    List<Account> allUsers();

    @GetGeneratedKeys
    @SqlUpdate("insert into bombillo_user (username, firstname, lastname, email) values (:username, :firstName, :lastName, :email)")
    long insertUser(@BindBean Account account);
}
