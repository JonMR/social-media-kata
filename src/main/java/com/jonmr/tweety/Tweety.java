package com.jonmr.tweety;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Tweety {
    private final Map<String, User> users = new HashMap<>();
    private final List<Post> posts = new ArrayList<>();

    public void addUser(User user) {
        users.putIfAbsent(user.getName(), user);
    }

    public Optional<User> getUser(String name) {
        return Optional.of(users.get(name));
    }

    public void publish(String name, String message) {
        User user = getUser(name).orElseThrow();
        posts.add(new Post(user, message, LocalDateTime.now()));
    }

    public Collection<Post> timeline(String name) {
        return posts
                .stream()
                .filter(post -> post.getAuthor().getName().equals(name))
                .collect(Collectors.toList());
    }
}
