package org.bitionaire.elbombillo.account.persistence.dao;

import org.bitionaire.elbombillo.account.model.Account;
import org.bitionaire.elbombillo.account.persistence.orm.AccountMapper;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(AccountMapper.class)
public interface AccountDAO {

    @SqlQuery("select id, username, firstname, lastname, email from account where id = :id")
    Account findAccount(@Bind("id") final long id);

    @SqlQuery("select id, username, firstname, lastname, email from account where username = :username")
    Account findAccount(@Bind("username") final String username);

    @SqlQuery("select id, username, firstname, lastname, email from account limit :limit offset :offset")
    List<Account> allAccounts(@Bind("limit") final int limit, @Bind("offset") final int offset);

    @GetGeneratedKeys
    @SqlUpdate("insert into account (username, firstname, lastname, email) values (:username, :firstName, :lastName, :email)")
    long insertAccount(@BindBean final Account account);

    @SqlQuery("select count(id) from account")
    long countAccounts();

    @SqlUpdate("update account set username=:username, firstname=:firstname, lastname=:lastname, email=:email whree id=:id")
    void updateAccount(@BindBean final Account account);

    @SqlUpdate("delete from account where id=:id")
    void deleteUser(@Bind("id") final long id);

    @SqlUpdate("delete from account where username = :username")
    void deleteUser(@Bind("username") final String username);
}
