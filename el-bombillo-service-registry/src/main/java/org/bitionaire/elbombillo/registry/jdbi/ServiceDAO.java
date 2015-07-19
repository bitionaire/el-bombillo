package org.bitionaire.elbombillo.registry.jdbi;

import org.bitionaire.elbombillo.registry.api.model.Service;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(ServiceMapper.class)
public interface ServiceDAO {

    @SqlUpdate("insert into service (name, baseUrl, username, password) values (:name, :baseUrl, :username, :password)")
    void insert(@BindBean Service service);

    @SqlUpdate("update service set name=:name, username=:username, password=:password where baseUrl = :baseUrl")
    void update(@BindBean Service service);

    @SqlQuery("select name, baseUrl, username, password from service where baseUrl = :baseUrl")
    Service findByBaseUrl(@Bind("baseUrl") String baseUrl);

    @SqlQuery("select name, baseUrl, username, password from service where name = :name")
    List<Service> findByName(@Bind("name") String name);
}
