package org.bitionaire.elbombillo.account.jdbi;

import org.bitionaire.elbombillo.account.api.model.Account;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(AccountMapper.class)
public interface AccountDAO {

    @SqlQuery("select id, username, firstname, lastname, email from account where id = :id")
    Account findAccount(@Bind("id") long id);

    @SqlQuery("select id, username, firstname, lastname, email from account")
    List<Account> allAccounts();

    @GetGeneratedKeys
    @SqlUpdate("insert into account (username, firstname, lastname, email) values (:username, :firstName, :lastName, :email)")
    long insertAccount(@BindBean Account account);
}
