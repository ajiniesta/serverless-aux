package com.iniesta.sless.svc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iniesta.sless.domain.Event;
import com.iniesta.sless.domain.SlessException;

import java.text.SimpleDateFormat;

public class EventConverter {

    private final ObjectMapper objectMapper;

    public EventConverter() {
        this.objectMapper = new ObjectMapper();
    }

    public Event parse(String input){
        try {
            Event event = objectMapper.readValue(input, Event.class);
            return event;
        } catch (JsonProcessingException e) {
            throw new SlessException(String.format("Exception during the parse of input %s", input),e);
        }
    }

    public String format(Event input){
        try{
            String output = objectMapper.writer().writeValueAsString(input);
            return output;
        } catch (JsonProcessingException e) {
            throw new SlessException(String.format("Exception during the parse of input %s", input),e);
        }
    }

    public byte[] formatAsPayload(Event event) {
        String payload = format(event);
        return payload.getBytes();
    }
}
