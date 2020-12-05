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
           li.add(new MenuItem("Pinguino",MenuItem.items.Budino,12.00));
           li.add(new MenuItem("Banana Split",MenuItem.items.Gelato,12.00));
           us = new User("Marco","Uderzo",21,1);
       }
    
    @Test
    public void ComputeTotalTest() {
        
        li.add(new MenuItem("Cola",MenuItem.items.Bevanda,2.50));
        li.add(new MenuItem("Coppa Nafta",MenuItem.items.Gelato, 4.00));
        try {
            assertEquals(30.50,bi.getOrderPrice(li,us),0.0);
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
            assertEquals(67.00,bi.getOrderPrice(li,us),0.0);
        } catch (TakeAwayBillException e) {
            System.out.println("Error");
        }
    }
    
    @After
    public void Empty()
    {
         li.clear();
    }

}
