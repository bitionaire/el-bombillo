package org.bitionaire.elbombillo.ms.idea.service;


import io.advantageous.qbit.annotation.PathVariable;
import io.advantageous.qbit.annotation.RequestMapping;

import io.advantageous.qbit.annotation.RequestMethod;
import io.advantageous.qbit.annotation.RequestParam;
import org.bitionaire.elbombillo.ms.idea.model.Idea;

import java.util.LinkedList;
import java.util.List;

/**
 * @author netdevfighter
 */
@RequestMapping("/idea-service")
public class IdeaService {

    @RequestMapping(value = "add", method = RequestMethod.PUT)
    public void add(Idea item) {

    }

    @RequestMapping("/idea/{0}")
    public Idea getIdea(@PathVariable long id) {
        return null;
    }

    @RequestMapping("/ideas/")
    private List<Idea> getIdeas(@RequestParam(value = "ids", required = true) List<Long> ids) {
        return new LinkedList<>();
    }
}
