package org.softwareenginnering.projecthoneybadger;

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
}
