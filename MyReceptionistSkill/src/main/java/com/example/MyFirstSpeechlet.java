package com.example;

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
import com.amazon.speech.ui.SimpleCard;

public class MyFirstSpeechlet implements Speechlet {

    @Override
    public void onSessionStarted(final SessionStartedRequest request, final Session session)
            throws SpeechletException {
        // Place for initialization logic.
    }

    @Override
    public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
            throws SpeechletException {
        // Place for launch logic, such as a welcome message.
        return null;
    }

    @Override
    public SpeechletResponse onIntent(final IntentRequest request, final Session session)
            throws SpeechletException {
        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("InitialContactIntent".equals(intentName)) {
            return getInitialIntentResponse();
        } else if ("GivingMyNameIntent".equals(intentName)) {
            return getNameIntentResponse(intent);
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }

    @Override
    public void onSessionEnded(final SessionEndedRequest request, final Session session)
            throws SpeechletException {
        // Place for cleanup logic.
    }

    private SpeechletResponse getInitialIntentResponse() {
        String text = "Welcome at our company. Please tell me your name so I can check if you have an appointment.";

        // Card used in the Alexa interface
        SimpleCard card = new SimpleCard();
        card.setTitle("Normal response");
        card.setContent(text);

        // The text that will be spoken by Alexa
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(text);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    private SpeechletResponse getNameIntentResponse(Intent intent) {
        String first_name = intent.getSlot("CustomerNameGB").getValue();
        String text = "";
        if (first_name != null) {
            text = "Hi " + first_name + ", I've checked the corporate agenda, and it seems you do not have a meeting scheduled. I'm sorry. A colleague has been notified and will contact you shortly.";
        } else {
            text = "I didn't get your name. Would you like to try again?";
        }
        // Card used in the Alexa interface
        SimpleCard card = new SimpleCard();
        card.setTitle("Normal response");
        card.setContent(text);

        // The text that will be spoken by Alexa
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(text);

        return SpeechletResponse.newTellResponse(speech, card);
    }
}
