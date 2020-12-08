////////////////////////////////////////////////////////////////////
// [Marco] [Uderzo] [1201290]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.model.User;
import java.util.List;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class CashRegister {

    private List<Bill> m_BillList;
    private List<User> m_UserList;
    private LocalTime start = LocalTime.of(18, 00);
    private LocalTime end = LocalTime.of(19, 00);
    private int giftCount = 0;

    CashRegister(){
        m_BillList = new ArrayList<Bill>();
        m_UserList = new ArrayList<User>();
    }

    CashRegister(List<Bill> lb,List<User> lu)
    {
        m_BillList = lb;
        m_UserList = lu; 
    }

    public List<Bill> getBillList()
    {
        return m_BillList;
    }

    public List<User> getUserList()
    {
        return m_UserList;
    }

    public int getGiftCount()
    {
    
        return giftCount;
    }

    public void AddBill(Bill b)
    {
        m_BillList.add(b);
        m_UserList.add(b.getUser());
    }

    public boolean gift(){

    if(!(m_BillList.get(m_BillList.size()-1).getLocalTime().isAfter(start) && m_BillList.get(m_BillList.size()-1).getLocalTime().isBefore(end)) || (!m_UserList.get(m_UserList.size()-1).isMinorenne())){ 
        return false;
    }
    for(int i = 0;i <= m_UserList.size()-2;i++) 
    {
        if(m_UserList.get(i).getId()== (m_UserList.get(m_UserList.size()-1).getId()))
        {
            if(m_UserList.get(i).getName()== (m_UserList.get(m_UserList.size()-1).getName())) 
            {
                if(m_UserList.get(i).getSurname()== (m_UserList.get(m_UserList.size()-1).getSurname())) 
                {
                    if(m_UserList.get(i).getAge()== (m_UserList.get(m_UserList.size()-1).getAge())) 
                    {
                        return false;
                    }
                }
            }
        }
    }
        
    if(new Random().nextInt()%2==1 && giftCount<10){ 
        giftCount++;
        return true;
    }
    return false;
    }
}