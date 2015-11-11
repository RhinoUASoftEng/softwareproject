package org.softwareenginnering.projecthoneybadger;

import java.util.ArrayList;
import java.util.List;

public class ClientService {
    ArrayList<Client> Clients;
    ClientService()
    {
        Clients = new ArrayList<>();
    }

    public List<Client> getClients()
    {
        return Clients;
    }
}
