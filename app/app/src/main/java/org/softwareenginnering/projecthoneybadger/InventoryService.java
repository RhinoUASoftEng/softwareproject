package org.softwareenginnering.projecthoneybadger;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.HttpGet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.softwareenginnering.projecthoneybadger.inventory;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

public class InventoryService {
    inventory i1;
    inventory i2;
    inventory i3;
    inventory i4;
    inventory i5;
    List<inventory> inventories;
    private String uuid;

    InventoryService()
    {
        i1 = new inventory();
        i2 = new inventory();
        i3 = new inventory();
        i4 = new inventory();
        i5 = new inventory();
        i1.setId(UUID.randomUUID());
        i2.setId(UUID.randomUUID());
        i3.setId(UUID.randomUUID());
        i4.setId(UUID.randomUUID());
        i5.setId(UUID.randomUUID());
        i1.setProductItem("i1");
        i2.setProductItem("i2");
        i3.setProductItem("i3");
        i4.setProductItem("i4");
        i5.setProductItem("i5");
        i1.setVendor("Walmart");
        i2.setVendor("Target");
        i3.setVendor("Sams Club");
        i4.setVendor("Lowes");
        i5.setVendor("Walmart");
        i1.setCost(250.00);
        i2.setCost(150.00);
        i3.setCost(35.20);
        i4.setCost(25.00);
        i5.setCost(40.00);
        i1.setQuantity(20);
        i2.setQuantity(30);
        i3.setQuantity(200);
        i4.setQuantity(2);
        i5.setQuantity(5);
        i1.setReorderLimit(2);
        i2.setReorderLimit(2);
        i3.setReorderLimit(2);
        i4.setReorderLimit(2);
        i5.setReorderLimit(10);
        inventories = new ArrayList<>();
    }

    public List<inventory> getInventories()
    {
        inventories.add(i1);
        inventories.add(i2);
        inventories.add(i3);
        inventories.add(i4);
        inventories.add(i5);
        return inventories;
    }

    public List<inventory> getAll() {
        return get("products");
    }

    public inventory getOne(String uuid) {
        List<inventory> list = get("products/" + uuid);
        return list.get(0);
    }

    public void create(inventory inv) {
        Gson gson = new Gson();
        String item = gson.toJson(inv);
        try {
            ServerCommunication.post("products", item);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void modifyNotAddressFields(inventory inv, String fieldName, String newValue) {
        try {
            ServerCommunication.put("products/" + inv.getId() + "/" + fieldName + "/" + newValue);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void delete(inventory inv) {
        try {
            ServerCommunication.delete("products/" + inv.getId());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private List<inventory> get(String command) {
        JSONArray responseArray;
        JSONObject responseObject;
        List<inventory> tempList = new ArrayList<inventory>();

        try {
            String data = ServerCommunication.get(command);
            if (data != null) {
                responseArray = new JSONArray(data);
                for (int i = 0; i < responseArray.length(); i++) {
                    responseObject = responseArray.getJSONObject(i);
                    inventory tempInventory = new inventory();
                    tempInventory.setId(UUID.fromString(responseObject.getString("ID")));
                    tempInventory.setProductItem(responseObject.getString("name"));
                    tempInventory.setCost(responseObject.getDouble("cost"));
                    tempInventory.setQuantity(responseObject.getInt("count"));
                    tempInventory.setReorderLimit(responseObject.getInt("reorder"));
                    tempList.add(tempInventory);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return tempList;
    }

    /*public List<inventory> getRemoteInventories() {
        String path = "http://54.187.159.168:8080/hb_server/api0/products";
        JSONArray responseArray;
        JSONObject responseObject;
        List<inventory> tempList = new ArrayList<inventory>();

        HttpGet httpGet = new HttpGet(path);
        HttpClient httpClient = new DefaultHttpClient();

        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine stat = response.getStatusLine();
            int status = stat.getStatusCode();

            if (status == 200) {
                System.out.println("Response OK...");
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity);

                responseArray = new JSONArray(data);

                for (int i = 0; i < responseArray.length(); i++) {
                    responseObject = responseArray.getJSONObject(i);
                    inventory tempInventory = new inventory();
                    tempInventory.setId(UUID.fromString(responseObject.getString("ID")));
                    tempInventory.setProductItem(responseObject.getString("name"));
                    tempInventory.setCost(responseObject.getDouble("cost"));
                    tempInventory.setQuantity(responseObject.getInt("count"));
                    tempInventory.setReorderLimit(responseObject.getInt("reorder"));
                    tempList.add(tempInventory);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return tempList;
    }
    */

    public void setInventories(inventory newInventory)
    {
        inventories.add(newInventory);
    }

}
