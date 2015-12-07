package org.softwareenginnering.projecthoneybadger;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientService {
    ArrayList<Client> Clients;
    Client c1;
    Client c2;
    Client c3;
    ClientService()
    {
        c1 = new Client();
        c2 = new Client();
        c3 = new Client();
        c1.setId(UUID.randomUUID());
        c2.setId(UUID.randomUUID());
        c3.setId(UUID.randomUUID());
        c1.setAddress("123 goScrewYourself st.");
        c2.setAddress("123 goScrewYourself st.");
        c3.setAddress("123 goScrewYourself st.");
        c1.setClientName("Billy");
        c2.setClientName("Tod");
        c3.setClientName("Caren");
        c1.setPhoneNumber("255-310-3333");
        c2.setPhoneNumber("255-310-3333");
        c3.setPhoneNumber("255-310-3333");
        c1.setEmail("love333@gmail.com");
        c2.setEmail("Ihate@yahoo.com");
        c3.setEmail("IloveandHate@gmail.com");
        Clients = new ArrayList<>();
    }

    public List<Client> getClients()
    {
        Clients.add(c1);
        Clients.add(c2);
        Clients.add(c3);
        return Clients;
    }

    public void setClients(Client client)
    {
        Clients.add(client);
    }

    public List<Client> getAll() {
        return get("clients");
    }

    public Client getOne(String uuid) {
        List<Client> list = get("clients/" + uuid);
        return list.get(0);
    }

    public void create(Client cli) {
        Gson gson = new Gson();
        String item = gson.toJson(cli);
        try {
            ServerCommunication.post("clients", item);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void modifyNotAddressFields(Client cli, String fieldName, String newValue) {
        try {
            ServerCommunication.put("clients/" + cli.getId() + "/" + fieldName + "/" + newValue);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void delete(Client cli) {
        try {
            ServerCommunication.delete("clients/" + cli.getId());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private List<Client> get(String command) {
        JSONArray responseArray;
        JSONObject responseObject;
        List<Client> tempList = new ArrayList<Client>();

        try {
            String data = ServerCommunication.get(command);
            if (data != null) {
                responseArray = new JSONArray(data);
                for (int i = 0; i < responseArray.length(); i++) {
                    responseObject = responseArray.getJSONObject(i);
                    Client tempClient = new Client();
                    tempClient.setId(UUID.fromString(responseObject.getString("ID")));
                    tempClient.setClientName(responseObject.getString("name"));
                    tempClient.setAddress(responseObject.getString("address"));
                    tempClient.setEmail(responseObject.getString("email"));
                    tempClient.setPhoneNumber(responseObject.getString("phone"));
                    tempList.add(tempClient);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return tempList;
    }
}
