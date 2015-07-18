package org.bitionaire.elbombillo.registry.jdbi;

import org.bitionaire.elbombillo.registry.api.model.Service;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(ServiceMapper.class)
public interface ServiceDAO {

    @SqlUpdate("insert into service (name, baseUrl) values (:name, :baseUrl)")
    void insert(@Bind("name") String name, @Bind("baseUrl") String baseUrl);

    @SqlUpdate("insert into service (name, baseUrl, username, password) values (:name, :baseUrl, :user, :password)")
    void insert(@Bind("name") String name, @Bind("baseUrl") String baseUrl, @Bind("user") String user, @Bind("password") String password);

    @SqlQuery("select name, baseUrl, username, password from service where name = :name")
    List<Service> findByName(@Bind("name") String name);
}
