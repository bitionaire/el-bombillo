package org.bitionaire.elbombillo.ui.gateway.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by jzinnau on 12.06.2015.
 */
public class Idea {

    private long id;

    @Length(max = 150)
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private User owner;

    @NotNull
    private Date creationDate;

    @NotNull
    private Date modifyDate;

    @NotNull
    private IdeaStatus status;

    private User[] supporters;
    private User[] members;
    private Tag[] tags;

    public Idea(
            long id,
            String title,
            String description,
            User owner,
            Date creationDate,
            Date modifyDate,
            IdeaStatus status,
            User[] supporters,
            User[] members,
            Tag[] tags
    ){
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.creationDate = creationDate;
        this.modifyDate = modifyDate;
        this.status = status;
        this.supporters = supporters;
        this.members = members;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @JsonProperty
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @JsonProperty
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @JsonProperty
    public IdeaStatus getStatus() {
        return status;
    }

    public void setStatus(IdeaStatus status) {
        this.status = status;
    }

    @JsonProperty
    public User[] getSupporters() {
        return supporters;
    }

    public void setSupporters(User[] supporters) {
        this.supporters = supporters;
    }

    @JsonProperty
    public User[] getMembers() {
        return members;
    }

    public void setMembers(User[] members) {
        this.members = members;
    }

    @JsonProperty
    public Tag[] getTags() {
        return tags;
    }

    public void setTags(Tag[] tags) {
        this.tags = tags;
    }
}
