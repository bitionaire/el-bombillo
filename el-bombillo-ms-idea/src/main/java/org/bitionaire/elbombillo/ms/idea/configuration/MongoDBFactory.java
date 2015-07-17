package org.bitionaire.elbombillo.ms.idea.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bitionaire.elbombillo.ms.idea.hazelcast.MongoIdeasStore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by tlindenmann on 14.06.15.
 */
public class MongoDBFactory {

    @NotNull
    @NotEmpty
    @JsonProperty
    private String hostname = "localhost";

    @Min(1)
    @Max(65535)
    @JsonProperty
    private int port = 27017;


    public MongoIdeasStore createMonogIdeaStore() {
        return null;
    }
}
