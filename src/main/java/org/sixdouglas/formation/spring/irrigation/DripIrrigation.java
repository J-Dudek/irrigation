package org.sixdouglas.formation.spring.irrigation;

import org.sixdouglas.formation.spring.irrigation.producer.GreenHouseProducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Component
public class DripIrrigation {
    private static Logger LOGGER = LoggerFactory.getLogger(DripIrrigation.class);

    public Flux<Drop> followDrops() {
        return  Flux
                .interval(Duration.ofMillis(20))
                .map(d-> Drop.builder().greenHouseId(1)
                        .rowId(1).dropperId(1)
                        .instant(Instant.now())
                        .build());

    }

    public Flux<Drop> followDropper(int greenHouseId, int rowId, int dropperId) {

        return Flux.interval(Duration.ofMillis(20))
                .map(d-> Drop.builder().greenHouseId(greenHouseId)
                        .rowId(rowId)
                        .instant(Instant.now())
                        .dropperId(dropperId).build());
    }

    public Flux<DetailedDrop> followDetailedDropper(int greenHouseId, int rowId, int dropperId) {
        //TODO use the GreenHouseProducer.getDrops() function as producer, but filter the output to fit the given criteria
        //TODO    then map it to a DetailedDrop using the getDetailedDrop() function
    /**
        Drop drop = Drop.builder().dropperId(dropperId).rowId(rowId).greenHouseId(greenHouseId).instant(Instant.now()).build();
        GreenHouseProducer.getDrops().map()
        Flux<DetailedDrop> detailedDropFlux =
                Flux.interval(Duration.ofMillis(20))
                        .map(d-> DetailedDrop.builder().greenHouse(GreenHouse.builder().id(greenHouseId)..build())
                                .instant(Instant.now())
                                .build());
     **/
        return null;
    }

    private Mono<DetailedDrop> getDetailedDrop(Drop drop) {
        //TODO use the GreenHouseProducer.getDropper() function to find the Dropper information wrap in a Greenhouse
        //TODO    then map it to build a DetailedDrop
        return null;
    }
}
