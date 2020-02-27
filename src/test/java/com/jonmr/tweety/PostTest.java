package com.jonmr.tweety;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

public class PostTest {
    @Test
    public void outputsFormattedString() {
        Post post = new Post(new User("someone"), "foo", LocalDateTime.now().minusSeconds(10));

        assertTrue(post.toString().matches("foo \\([0-9]+ seconds ago\\)"));
    }
}