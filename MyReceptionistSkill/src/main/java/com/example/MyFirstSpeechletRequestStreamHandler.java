package com.example;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public final class MyFirstSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds = new HashSet<String>();

    // Be careful this makes your code available to anyone
    static {
        /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
        supportedApplicationIds.add("amzn1.ask.skill.7801858a-eee6-46d4-8f88-d79666f5ca25");
        
    }

    public MyFirstSpeechletRequestStreamHandler() {
        super(new MyFirstSpeechlet(), supportedApplicationIds);
    }
}
