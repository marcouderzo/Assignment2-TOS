////////////////////////////////////////////////////////////////////
// [Marco] [Uderzo] [1201290]
////////////////////////////////////////////////////////////////////


package it.unipd.tos.business;

import java.util.List;
import java.time.LocalTime;
import java.util.ArrayList;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

public class BillTest {
    Bill bi;
    List<MenuItem> li;
    User us;
    
    @Before
       public void setup (){
           bi = new Bill(LocalTime.of(17,0,0,0));
           li = new ArrayList<MenuItem>();
           us = new User("Marco","Uderzo",21,1);
       }
    
    @Test
    public void ComputeTotalTest() {
        
        li.add(new MenuItem("Cola",MenuItem.items.Bevanda,2.00));
        li.add(new MenuItem("Coppa Nafta",MenuItem.items.Gelato, 4.00));
        try {
            assertEquals(6.00,bi.getOrderPrice(li,us),0.0);
        } catch (TakeAwayBillException e) {
            System.out.println("Error");
        }
    }
    
    @Test
    public void ComputeTotalWith5ItemsTest() {
        li.add(new MenuItem("Banana Split",MenuItem.items.Gelato, 10.00));
        li.add(new MenuItem("Banana Split",MenuItem.items.Gelato, 10.00));
        li.add(new MenuItem("Banana Split",MenuItem.items.Gelato, 10.00));
        li.add(new MenuItem("Banana Split",MenuItem.items.Gelato, 10.00));
        li.add(new MenuItem("Banana Split",MenuItem.items.Gelato, 6.00));
        try {
            assertEquals(43.00,bi.getOrderPrice(li,us),0.0);
        } catch (TakeAwayBillException e) {
            System.out.println("Error");
        }
    }
    
    @Test
    public void ComputeTotalWith10pctDiscountTest() {
        
        li.add(new MenuItem("Pinguino",MenuItem.items.Gelato, 10.00));
        li.add(new MenuItem("Banana split",MenuItem.items.Gelato, 10.00));
        li.add(new MenuItem("Banana split",MenuItem.items.Gelato, 10.00));
        li.add(new MenuItem("Biancaneve",MenuItem.items.Budino, 10.00));
        li.add(new MenuItem("Banana split",MenuItem.items.Gelato, 10.00));
        try {
            assertEquals(45,bi.getOrderPrice(li,us),0.0);
        } catch (TakeAwayBillException e) {
            System.out.println("Error");
        }
    }
    
    @Test (expected = TakeAwayBillException.class)
    public void ErrorForNumberOfOrders() throws TakeAwayBillException{
        
        
        for(int i = 0; i < 30; i++) {
            
            li.add(new MenuItem("Cola",MenuItem.items.Bevanda,2.50));
        }
     
        bi.getOrderPrice(li, us);
    }
    
    
    @After
    public void Empty()
    {
         li.clear();
    }

}
