package org.softwareenginnering.projecthoneybadger;

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
}
