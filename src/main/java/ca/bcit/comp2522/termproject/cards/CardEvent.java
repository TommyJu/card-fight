package ca.bcit.comp2522.termproject.cards;

import javafx.event.Event;
import javafx.event.EventType;

public class CardEvent extends Event {
    public static final EventType<CardEvent> ANY = new EventType<>(Event.ANY, "ANY");

    public CardEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }

//    @Override
//    public void handle(CardEvent event) {
//        System.out.println("Tile pressed ");
//        event.consume();
//    }
}
