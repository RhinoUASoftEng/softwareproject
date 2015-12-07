package org.softwareenginnering.projecthoneybadger;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionService {
    ArrayList<Transaction> transactions;
    Transaction t1;
    Transaction t2;
    Transaction t3;
    TransactionService()
    {
        t1 = new Transaction();
        t2 = new Transaction();
        t3 = new Transaction();
        t1.setId(UUID.randomUUID());
        t2.setId(UUID.randomUUID());
        t3.setId(UUID.randomUUID());
        t1.setItem("Waffles");
        t2.setItem("Bagels");
        t3.setItem("Cookies");
        t1.setAmount("30.00");
        t2.setAmount("45.00");
        t3.setAmount("50.00");
        t1.setEmployee("Karen");
        t2.setEmployee("Leroy");
        t3.setEmployee("Floppy Loser");
        t1.setDate("11-22-16");
        t2.setDate("11-26-16");
        t3.setDate("12-1-16");
        transactions = new ArrayList<>();
    }

    public List<Transaction> getTransactions()
    {
        transactions.add(t1);
        transactions.add(t2);
        transactions.add(t3);
        return transactions;
    }

    public void setTransaction(Transaction transaction)
    {
        transactions.add(transaction);
    }

    public List<Transaction> getAll() {
        return get("transactionsid");
    }

    public Transaction getOne(String uuid) {
        List<Transaction> list = get("transactionsid/" + uuid);
        return list.get(0);
    }

    public void create(Transaction cli) {
        Gson gson = new Gson();
        String item = gson.toJson(cli);
        try {
            ServerCommunication.post("transactionsid", item);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void modifyNotAddressFields(Transaction cli, String fieldName, String newValue) {
        try {
            ServerCommunication.put("transactionsid/" + cli.getId() + "/" + fieldName + "/" + newValue);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void delete(Transaction cli) {
        try {
            ServerCommunication.delete("transactionsid/" + cli.getId());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private List<Transaction> get(String command) {
        JSONArray responseArray;
        JSONObject responseObject;
        List<Transaction> tempList = new ArrayList<Transaction>();

        try {
            String data = ServerCommunication.get(command);
            if (data != null) {
                responseArray = new JSONArray(data);
                for (int i = 0; i < responseArray.length(); i++) {
                    responseObject = responseArray.getJSONObject(i);
                    Transaction tempTransaction = new Transaction();
                    tempTransaction.setId(UUID.fromString(responseObject.getString("ID")));
                    tempTransaction.setEmployee(responseObject.getString("emp_id"));
                    tempTransaction.setItem(responseObject.getString("product_id"));
                    tempTransaction.setDate(responseObject.getString("date_time"));
                    tempList.add(tempTransaction);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return tempList;
    }

}
