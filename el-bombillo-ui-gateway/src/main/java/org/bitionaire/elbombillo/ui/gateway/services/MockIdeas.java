package org.bitionaire.elbombillo.ui.gateway.services;

import org.bitionaire.elbombillo.ui.gateway.core.Idea;
import org.bitionaire.elbombillo.ui.gateway.core.IdeaStatus;
import org.bitionaire.elbombillo.ui.gateway.core.Tag;
import org.bitionaire.elbombillo.ui.gateway.core.User;

import java.util.Date;

/**
 * Created by jzinnau on 12.06.2015.
 */
public class MockIdeas {

    private Idea[] ideas;
    private User[] users;

    public MockIdeas() {
        users = new User[]{
                new User(
                        1,
                        "JohnDoe",
                        "John",
                        "Doe",
                        "john.doe@bombillo.el"
                ),
                new User(
                        2,
                        "Mamu",
                        "Max",
                        "Mustermann",
                        "max.mustermann@bombillo.el"
                )
        };
        ideas = new Idea[]{
                new Idea(
                        1,
                        "Idea 1",
                        "This is just some example idea, so no need for an awesome description.",
                        users[0],
                        new Date(),
                        new Date(),
                        IdeaStatus.NEW,
                        new User[]{users[0]},
                        new User[]{users[0], users[1]},
                        new Tag[0]
                ),
                new Idea(
                        2,
                        "Idea 2",
                        "This is just some example idea, so no need for an awesome description.",
                        users[1],
                        new Date(),
                        new Date(),
                        IdeaStatus.NEW,
                        new User[]{users[0]},
                        new User[]{users[0]},
                        new Tag[0]
                )
        };
    }

    public Idea[] getAllIdeas() {
        return this.ideas;
    }

    public User[] getAllUsers() {
        return this.users;
    }

    public Idea getIdeaById(long id) {
        Idea[] ideas = getAllIdeas();
        for (Idea idea: ideas) {
            if (idea.getId() == id) {
                return idea;
            }
        }
        return null;
    }

    public User getUserById(long id) {
        User[] users = getAllUsers();
        for (User user: users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

}
