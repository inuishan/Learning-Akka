package com.actor;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.message.SetRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ishanjain
 * @since 10/05/18
 */
public class AkkademyDB extends AbstractActor {

    private final LoggingAdapter logger = Logging.getLogger(context().system(), this);

    private final Map<String, Object> map = new HashMap<>();

    private AkkademyDB() {
        receive(ReceiveBuilder.match(SetRequest.class, message -> {
            logger.info("Received set request {}", message.toString());
            map.put(message.getKey(), message.getValue());
        }).matchAny(o -> {
            logger.info("Received unknown request {}", o);
        }).build());
    }
}