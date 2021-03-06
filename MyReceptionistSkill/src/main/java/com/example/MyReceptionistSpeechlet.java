package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

public class MyReceptionistSpeechlet implements Speechlet {
    private static final Logger log = LoggerFactory.getLogger(MyReceptionistSpeechlet.class);

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
        session.getSessionId());
        // any initialization logic goes here
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
            throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return getInitialIntentResponse();
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session)
            throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        
        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;
        log.info("onIntent requestId={}, sessionId={}, intentName={}", request.getRequestId(),
                 session.getSessionId(), intentName);

        if ("InitialContactIntent".equals(intentName)) {
            return getInitialIntentResponse();
        } else if ("GivingMyNameIntent".equals(intentName)) {
            return getNameIntentResponse(intent);
        } else if ("AMAZON.CancelIntent".equals(intentName)) {
            return getCancelOrStopResponse();
        } else if ("AMAZON.StopIntent".equals(intentName)) {
            return getCancelOrStopResponse();
        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session)
            throws SpeechletException {
        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        // any cleanup logic goes here
    }

    private SpeechletResponse getInitialIntentResponse() {
        String repromptText = "Please tell me your name so I can check if you have an appointment.";
        String text = "Welcome at our company. " + repromptText;
        
        // Card used in the Alexa interface
        SimpleCard card = new SimpleCard();
        card.setTitle("Normal response");
        card.setContent(text);

        // The text that will be spoken by Alexa
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(text);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    private SpeechletResponse getNameIntentResponse(Intent intent) {
        String repromptText = "Please tell me your name again";
        String first_name_gb = intent.getSlot("CustomerNameGB").getValue();
        String first_name_us = intent.getSlot("CustomerNameUS").getValue();
        String first_name = (first_name_gb != null) ? first_name_gb : first_name_us;
        
        String responseText = "";

        if (first_name != null) {
            responseText = "Hi " + first_name + ", I've checked the corporate agenda, and it seems you do not have a meeting scheduled. I'm sorry. A colleague has been notified and will contact you shortly.";
        } else {
            responseText = "I didn't get your name. Would you like to try again?";
        }

        // Card used in the Alexa interface
        SimpleCard card = new SimpleCard();
        card.setTitle("Normal response");
        card.setContent(responseText);

        // The text that will be spoken by Alexa
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        // Create reprompt
        Reprompt reprompt = new Reprompt();
        speech.setText(responseText);

        if (first_name != null) {
            return SpeechletResponse.newTellResponse(speech, card);
        } else {
            return SpeechletResponse.newAskResponse(speech, reprompt, card);
        }
    }

    private SpeechletResponse getCancelOrStopResponse() {
        String speechText = "Thank you for using my services.";

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech);
    }

    private SpeechletResponse getHelpResponse() {
        String speechText = "Please contact a real life receptionist for more help.";

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech);
    }
    
}
