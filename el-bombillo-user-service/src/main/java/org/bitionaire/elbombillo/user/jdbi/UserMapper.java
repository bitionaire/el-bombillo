package org.bitionaire.elbombillo.user.jdbi;

import org.bitionaire.elbombillo.user.api.model.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<User> {

    @Override
    public User map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new User(r.getLong("id"), r.getString("username"), r.getString("firstname"), r.getString("lastname"), r.getString("email"));
    }
}
