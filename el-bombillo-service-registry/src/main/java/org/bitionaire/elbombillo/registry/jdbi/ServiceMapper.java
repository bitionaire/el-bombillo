package org.bitionaire.elbombillo.registry.jdbi;

import org.bitionaire.elbombillo.registry.api.model.Credentials;
import org.bitionaire.elbombillo.registry.api.model.Service;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServiceMapper implements ResultSetMapper<Service> {

    @Override
    public Service map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        final String username = r.getString("username");
        final String password = r.getString("password");

        final Credentials credentials;
        if (username != null && !username.isEmpty()) {
            credentials = new Credentials(username, password);
        } else {
            credentials = null;
        }

        return new Service(r.getString("name"), r.getString("baseUrl"), credentials);
    }
}
