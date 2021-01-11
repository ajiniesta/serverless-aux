package com.iniesta.sless.infra;

import com.iniesta.sless.conf.Configurator;
import com.iniesta.sless.domain.Event;
import com.iniesta.sless.domain.SlessException;
import com.iniesta.sless.svc.EventConverter;
import org.eclipse.paho.client.mqttv3.*;

import java.util.UUID;

public class MqttConnector implements BrokerConnector {

    private final Configurator conf;
    private final EventConverter eventConverter;
    private final IMqttClient client;
    private final String topic;

    public MqttConnector(Configurator conf, EventConverter eventConverter) {
        this.conf = conf;
        this.topic = conf.get("mqtt-topic", "events");
        this.eventConverter = eventConverter;
        String publisherId = "sless-" + UUID.randomUUID().toString();
        try {
            client = new MqttClient(conf.get("mqtt-url"),publisherId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            client.connect(options);
        } catch (MqttException e) {
            throw new SlessException("Error during the generation connection to mqtt broker", e);
        }
    }

    @Override
    public void send(Event event) {
        MqttMessage msg = new MqttMessage();
        msg.setPayload(eventConverter.formatAsPayload(event));
        try {
            client.publish(topic, msg);
        } catch (MqttException e) {
            throw new SlessException(String.format("Error during the send of event %s", event.toString()), e);
        }
    }
}
