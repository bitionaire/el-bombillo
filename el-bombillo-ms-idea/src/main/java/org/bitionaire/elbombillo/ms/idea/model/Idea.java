package org.bitionaire.elbombillo.ms.idea.model;

import lombok.Getter;

import java.util.Date;
import java.util.List;

/**
 *
 * @author netdevfighter
 */
public class Idea {

    @Getter
    private final long id;
    @Getter
    private final String title;
    @Getter
    private final String description;
    @Getter
    private final User owner;
    @Getter
    private final Date creationDate;
    @Getter
    private Date modifyDate;
    @Getter
    private final IdeaStatus status;

    public Idea(long id, String title, String description, User owner, Date creationDate, Date modifyDate, IdeaStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
        this.status = status;
    }
}
