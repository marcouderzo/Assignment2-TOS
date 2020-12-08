////////////////////////////////////////////////////////////////////
// [Marco] [Uderzo] [1201290]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.model.User;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;



public class CashRegisterTest {

    private List<Bill> m_BillList;
    private List<User> m_UserList;
    private CashRegister CRegister;
    
    @Before
    public void Setup() {
        
        m_UserList = new ArrayList<User>();
        m_BillList = new ArrayList<Bill>();
        for (int i = 0;i<25;i++) 
        {
            m_UserList.add(new User("Marco","Uderzo",i%2==0 ? 10 : 20, i));
        }
        for (int i = 0;i<25;i++)
        {
            m_BillList.add(new Bill(i%2==0 ? LocalTime.of(18, 30) : LocalTime.of(19, 30),m_UserList.get(i)));
        }
        CRegister = new CashRegister(m_BillList,m_UserList);
    }
    
    @Test
    public void getGiftCountTest()
    {
        assertEquals(0,CRegister.getGiftCount());
    }
    
    @Test
    public void getBillListTest()
    {
        assertEquals(m_BillList,CRegister.getBillList());
    }
    
    @Test
    public void getUserListTest()
    {
        assertEquals(m_UserList,CRegister.getUserList());
    }
    
    @Test
    public void addBillTest()
    {
        Bill b = new Bill(LocalTime.of(12,0,0,0),new User("Marco","Uderzo",21,90));
        CRegister.AddBill(b);
        assertEquals(b,CRegister.getBillList().get(CRegister.getBillList().size()-1));
    }
    
    @Test
    public void InTimeOverageGiftTest()
    {
        CRegister.AddBill(new Bill(LocalTime.of(18,40),new User("Marco","Uderzo",21,91)));
        assertFalse(CRegister.gift());
    }
    
    @Test
    public void NotInTimeUnderageGiftTest()
    {
        CRegister.AddBill(new Bill(LocalTime.of(19,40),new User("Marco","Uderzo",15,92)));
        assertFalse(CRegister.gift());
    }
    
    @Test
    public void NotInTimeOverageGiftTest(){
        
        CRegister.AddBill(new Bill(LocalTime.of(19,40),new User("Marco","Uderzo",21,93)));
        assertFalse(CRegister.gift());
    }
    
    @Test
    public void isSameElegiblePersonGiftTest()
    {
        CRegister.AddBill(new Bill(LocalTime.of(18,40),new User("Marco","Uderzo",10,22)));
        assertFalse(CRegister.gift());
    }

    @Test
    public void hasGiftedWithTrueResultAndFalseResultTest()
    {
        boolean b1 = false,b2 = true;
        for(int i = 0;i < 1000;i++) {
            CRegister.AddBill(new Bill(LocalTime.of(18,40),new User("Marco","Uderzo",10, i+25)));
            boolean b = CRegister.gift();
            if(b) {
                b1=b;
            }
            else b2=b;
        }
        assertTrue(b1 && !b2);
    }
}