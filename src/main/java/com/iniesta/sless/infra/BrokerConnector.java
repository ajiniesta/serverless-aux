package com.iniesta.sless.infra;

import com.iniesta.sless.domain.Event;

public interface BrokerConnector {

    void send(Event event);

}
