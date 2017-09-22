package com.example;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public final class MyFirstSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds = new HashSet<String>();

    // Be careful this makes your code available to anyone
    static {
        System.setProperty("com.amazon.speech.speechlet.servlet.disableRequestSignatureCheck", "true");
    }

    public MyFirstSpeechletRequestStreamHandler() {
        super(new MyFirstSpeechlet(), supportedApplicationIds);
    }
}
