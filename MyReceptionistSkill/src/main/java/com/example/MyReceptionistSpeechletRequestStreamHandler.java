package com.example;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public final class MyReceptionistSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds = new HashSet<String>();

    // Be careful this makes your code available to anyone
    static {
        /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
        if (System.getenv("AMAZON_SUPPORTED_APPLICATION_ID") != null && !System.getenv("AMAZON_SUPPORTED_APPLICATION_ID").isEmpty()) {
            supportedApplicationIds.add(System.getenv("AMAZON_SUPPORTED_APPLICATION_ID"));
        }
        
    }

    public MyReceptionistSpeechletRequestStreamHandler() {
        super(new MyReceptionistSpeechlet(), supportedApplicationIds);
    }
}
