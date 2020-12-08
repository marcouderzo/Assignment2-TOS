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
    private User us;
    private boolean gifted;
    private static CashRegister CRegister = new CashRegister();

    public Bill(LocalTime t, User user)
    {
        this.t = t;
        this.us = user;
        CRegister.AddBill(this);
        this.gifted = CRegister.gift();
    }
    
    public LocalTime getLocalTime() 
    {
        return t;
    }    
    
    public User getUser() 
    {
        return us;
    }
    
    public boolean getGifted() 
    {
        return gifted;
    }

    public double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws TakeAwayBillException {

        double tot=0;
        int icecreamCount = 0;
        int totWithoutDrinks=0; 
        double minPrice = Double.MAX_VALUE;
        
        if(itemsOrdered.size() >= 30) 
        {
            
            throw new TakeAwayBillException("Orders Exceed Limit");
        }
        
        if(!gifted)
        {
            for(MenuItem mi : itemsOrdered) {
                if(mi.getType() == MenuItem.items.Gelato) 
                {
                    icecreamCount ++;
                    if(mi.getPrice() < minPrice) {
                        minPrice = mi.getPrice();
                    }
                }
                if(mi.getType() != MenuItem.items.Bevanda) 
                {
                    totWithoutDrinks += mi.getPrice();
                }
                tot += mi.getPrice();
            }
            if (icecreamCount >= 5)
            {
                tot -= minPrice/2;
            }
            if (totWithoutDrinks >= 50)
            {
                tot *= 0.9;
            }
            if (tot < 10) 
            {
                tot += 0.5;
            }
        }
            return tot;
        
    }
}
