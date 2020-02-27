package com.jonmr.tweety;

import java.time.LocalDateTime;

public class Post {
    private final User author;
    private final String text;
    private final LocalDateTime timestamp;

    public Post(final User author, final String text, final LocalDateTime timestamp) {
        this.author = author;
        this.text = text;
        this.timestamp = timestamp;
    }

    public User getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return String.format("%s (%s ago)",
                text,
                new RelativeTimestampFormatter(timestamp, LocalDateTime.now()).format()
        );
    }
}
