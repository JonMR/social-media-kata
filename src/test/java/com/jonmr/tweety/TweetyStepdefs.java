package com.jonmr.tweety;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringStartsWith.startsWith;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

import io.cucumber.java8.En;

public class TweetyStepdefs implements En {

    public TweetyStepdefs() {
        var tweety = new Tweety();
        var alice = new User("Alice");
        tweety.addUser(alice);

        // cucumber-java is new to me and I wanted a way to pass something between steps.
        // They recommend picocontainer, but DI seems overkill for this use case.
        AtomicReference<Collection<Post>> timeline = new AtomicReference<>();

        Given("^(.*) has published \"([^\"]*)\"$", tweety::publish);
        When("^(.*) views her timeline$", (String name) -> {
            timeline.set(tweety.timeline(name));
        });
        Then("^(.*) sees: \"([^\"]*) \\([0-9]+ \\w+ ago\\)\"$", (String name, String message) -> {
            String publishedMessage = timeline.get().stream().findFirst().orElseThrow().toString();
            assertThat(publishedMessage, startsWith(message));
        });
    }
}
