////////////////////////////////////////////////////////////////////
// [Marco] [Uderzo] [1201290]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;


public class Bill implements TakeAwayBill{

    private LocalTime t;
    private double price;

    public Bill(LocalTime t)
    {
        this.t=t;
    }

    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException {

        double tot=0;
        int icecreamCount = 0;
        double minPrice = Double.MAX_VALUE;
        for(MenuItem mi : itemsOrdered)
        {
            if(mi.getType().equals(MenuItem.items.Gelato ))
            {
                icecreamCount++;
                if(mi.getPrice() < minPrice)
                {
                     minPrice = mi.getPrice();
                }
            }
            tot +=mi.getPrice();
        }
        if(icecreamCount >= 5)
        {
            tot-=minPrice/2;
        }
        return tot;
    }
}
