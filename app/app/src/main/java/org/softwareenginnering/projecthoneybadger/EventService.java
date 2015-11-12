package org.softwareenginnering.projecthoneybadger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by cnesbitt on 11/3/2015.
 */
public class EventService {

    List<Event> Events;
    Event e1;
    Event e2;
    Event e3;

    EventService()
    {
        e1 = new Event();
        e2 = new Event();
        e3 = new Event();
        e1.setId(UUID.randomUUID());
        e1.setName("e1");
        e1.setTime("3:55pm");
        e1.setDate("11-22-15");
        e1.setAddress("123 Seseme St. St.Louis, MO 72701");
        e1.setEmployee("Jessica");

        e2.setId(UUID.randomUUID());
        e2.setName("e2");
        e2.setTime("4:30pm");
        e2.setDate("11-30-15");
        e2.setAddress("123 Seseme St. St.Louis, MO 72701");
        e2.setEmployee("Jerry");

        e3.setId(UUID.randomUUID());
        e3.setName("e3");
        e3.setTime("5:25pm");
        e3.setDate("11-15-15");
        e3.setAddress("123 Seseme St. St.Louis, MO 72701");
        e3.setEmployee("Dingle Biscut");
        Events = new ArrayList<>();
    }

    public List<Event> getEvents()
    {
        Events.add(e1);
        Events.add(e2);
        Events.add(e3);
        return  Events;
    }

    public void setEvent(Event event)
    {
        Events.add(event);
    }
}
