package org.bitionaire.elbombillo.account.persistence.orm;

import org.bitionaire.elbombillo.account.model.Account;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements ResultSetMapper<Account> {

    @Override
    public Account map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new Account(r.getLong("id"), r.getString("username"), r.getString("firstname"), r.getString("lastname"), r.getString("email"));
    }
}
