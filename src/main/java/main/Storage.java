package main;

import main.model.Deal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage
{
    private static int currentId = 1;
    private static HashMap<Integer, Deal> deals = new HashMap<Integer, Deal>();

    public synchronized static List<Deal> getAllDeals()
    {
        ArrayList<Deal> dealsList = new ArrayList<Deal>();
        dealsList.addAll(deals.values());
        return dealsList;
    }

    public synchronized static int addDeal(Deal deal)
    {
        int id = currentId++;
        deal.setId(id);
        deals.put(id, deal);
        return id;
    }

    public synchronized static Deal getDeal(int dealId)
    {
        if(deals.containsKey(dealId)) {
            return deals.get(dealId);
        }
        return null;
    }
}