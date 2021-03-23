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
        /*
         use the GreenHouseProducer.getDrops() function as producer, but filter the output to fit the given criteria
            then map it to a DetailedDrop using the getDetailedDrop() function
        */
        return GreenHouseProducer.getDrops().flatMap(this::getDetailedDrop);


    }

    private Mono<DetailedDrop> getDetailedDrop(Drop drop) {
        /*
         use the GreenHouseProducer.getDropper() function to find the Dropper information wrap in a Greenhouse
            then map it to build a DetailedDrop
         */
        return GreenHouseProducer.getDropper(drop.getGreenHouseId(), drop.getRowId(), drop.getDropperId())
                .map(greenHouse1 -> DetailedDrop.builder().build());



    }
}
