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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;

public class BillTest {
    Bill m_bill;
    List<MenuItem> m_list;
    User m_user;
    
    @Before
    public void setup (){   
         m_list = new ArrayList<MenuItem>();
         m_user = new User("Marco","Uderzo",21,1);
         m_bill = new Bill(LocalTime.of(17,0,0,0), m_user);
     }
  

     @Test
     public void getUserTest() {
        assertEquals(m_user,m_bill.getUser());
     }

     @Test
     public void getLocalTimeTest() {
         assertEquals(LocalTime.of(17,0,0,0),m_bill.getLocalTime());
     }

     @Test
     public void getGiftedTest() {
        assertFalse(m_bill.getGifted());
     }
    
    @Test
    public void ComputeTotalTest() {
        
        m_list.add(new MenuItem("Cola",MenuItem.items.Bevanda,7.00));
        m_list.add(new MenuItem("Coppa Nafta",MenuItem.items.Gelato, 4.00));
        try {
            assertEquals(11.00,m_bill.getOrderPrice(m_list,m_user),0.0);
        } catch (TakeAwayBillException e) {
            System.out.println("Error");
        }
    }
    
    @Test
    public void ComputeTotalWith5ItemsTest() {
        m_list.add(new MenuItem("Banana Split",MenuItem.items.Gelato, 10.00));
        m_list.add(new MenuItem("Banana Split",MenuItem.items.Gelato, 10.00));
        m_list.add(new MenuItem("Banana Split",MenuItem.items.Gelato, 10.00));
        m_list.add(new MenuItem("Banana Split",MenuItem.items.Gelato, 10.00));
        m_list.add(new MenuItem("Banana Split",MenuItem.items.Gelato, 6.00));
        try {
            assertEquals(43.00,m_bill.getOrderPrice(m_list,m_user),0.0);
        } catch (TakeAwayBillException e) {
            System.out.println("Error");
        }
    }
    
    @Test
    public void ComputeTotalWith10pctDiscountTest() {
        
        m_list.add(new MenuItem("Pinguino",MenuItem.items.Gelato, 10.00));
        m_list.add(new MenuItem("Banana split",MenuItem.items.Gelato, 10.00));
        m_list.add(new MenuItem("Banana split",MenuItem.items.Gelato, 10.00));
        m_list.add(new MenuItem("Biancaneve",MenuItem.items.Budino, 10.00));
        m_list.add(new MenuItem("Banana split",MenuItem.items.Gelato, 10.00));
        try {
            assertEquals(45,m_bill.getOrderPrice(m_list,m_user),0.0);
        } catch (TakeAwayBillException e) {
            System.out.println("Error");
        }
    }
    
    @Test (expected = TakeAwayBillException.class)
    public void ErrorForNumberOfOrders() throws TakeAwayBillException{
        
        
        for(int i = 0; i < 30; i++) {
            
            m_list.add(new MenuItem("Cola",MenuItem.items.Bevanda,2.50));
        }
     
        m_bill.getOrderPrice(m_list, m_user);
    }
    
    @Test
    public void Add50CommissionInOrdersLessThan10Test()
    {
        m_list.add(new MenuItem("Banana Split", MenuItem.items.Gelato, 7.00));
        try {
            assertEquals(7.50, m_bill.getOrderPrice(m_list, m_user), 0.0);
        }
        catch(TakeAwayBillException exception)
        {
            System.out.println("Errore");
        }
    }
    
    @Test
    public void GiftTest() {
        boolean a = false;
        for (int i = 0;i<1000;i++) {
            Bill b = new Bill(LocalTime.of(18,30),new User("Marco","Uderzo",12,i));
            if(b.getGifted()) {
                a = true;
            }
        }
        assertTrue(a);
    }
}
