package org.softwareenginnering.projecthoneybadger;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
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

    public List<Event> getAll() {
        return get("events");
    }

    public Event getOne(String uuid) {
        List<Event> list = get("events/" + uuid);
        return list.get(0);
    }

    public void create(Event eve) {
        String [] oldDate = eve.getDate().split("-");
        String [] oldTime = eve.getTime().split(":");
        if (oldDate[0].length() == 1)
            oldDate[0] = "0" + oldDate[0];
        if (oldDate[1].length() == 1)
            oldDate[1] = "0" + oldDate[1];
        if (oldTime[0].length() == 1)
            oldTime[0] = "0" + oldTime[0];
        if (oldTime[1].length() == 1)
            oldTime[1] = "0" + oldTime[1];
        String newDate = oldDate[2] + "-" + oldDate[1] + "-" + oldDate[0];
        newDate += "T" + oldTime[0] + ":" + oldTime[1] + ":00Z";
        eve.setDate(newDate);
        Gson gson = new Gson();
        String item = gson.toJson(eve);
        try {
            ServerCommunication.post("events/", item);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void modifyNotAddressFields(Event eve, String fieldName, String newValue) {
        try {
            ServerCommunication.put("events/" + eve.getId() + "/" + fieldName + "/" + newValue);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void delete(Event eve) {
        try {
            ServerCommunication.delete("events/" + eve.getId());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private List<Event> get(String command) {
        JSONArray responseArray;
        JSONObject responseObject;
        List<Event> tempList = new ArrayList<Event>();

        try {
            String data = ServerCommunication.get(command);
            if (data != null) {
                responseArray = new JSONArray(data);
                for (int i = 0; i < responseArray.length(); i++) {
                    responseObject = responseArray.getJSONObject(i);
                    Event tempEvent = new Event();
                    tempEvent.setId(UUID.fromString(responseObject.getString("ID")));
                    tempEvent.setName(responseObject.getString("client_ID"));
                    String addy = responseObject.getString("address") + " ";
                    addy += responseObject.getString("city") + " ";
                    addy += responseObject.getString("state") + " ";
                    addy += responseObject.getString(("zip"));
                    tempEvent.setAddress(addy);
                    tempEvent.setDate(responseObject.getString("event_date").substring(0, 10));
                    tempEvent.setTime(responseObject.getString("event_date").substring(11, 19));
                    tempList.add(tempEvent);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return tempList;
    }
}
