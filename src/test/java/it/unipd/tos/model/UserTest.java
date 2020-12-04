////////////////////////////////////////////////////////////////////
// [Marco] [Uderzo] [1201290]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import it.unipd.tos.model.MenuItem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
//import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;


public class UserTest {
    User user;

    @Before
    public void setup()
    {
           user = new User("Marco", "Uderzo", 21, 0);
    }

    @Test
    public void getNameTest()
    {
           assertEquals("Marco", user.getName());
    }

    @Test
    public void getSurnameTest()
    {
            assertEquals("Uderzo", user.getSurname());
    }

    @Test
    public void getAgeTest()
    {
             assertEquals(21, user.getAge());
    }

    @Test
    public void isMinorenneTest()
    {
             assertFalse(user.isMinorenne());
    }
    
    @Test
    public void getId(){
        assertEquals(0, user.getId());
    }
}
