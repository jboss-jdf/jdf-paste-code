package org.jboss.weld.examples.pastecode.session;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

/**
 * Tracks the post made by the current user
 *
 * @author Pete Muir
 */
@SessionScoped
@Stateful // Adds passivation capabilities....
public class PostTracker implements Serializable {

    private static final long serialVersionUID = -2120112588011284764L;

    private LinkedList<Date> posts;

    public PostTracker() {
        this.posts = new LinkedList<Date>();
    }

    public void addPost() {
        this.posts.offerFirst(new Date());
    }

    public boolean isNewPostAllowed() {
        if (posts.size() > 2) {
            long diff = new Date().getTime() - posts.get(2).getTime();
            return diff > 20 * 1000;
        } else {
            return true;
        }
    }

}
