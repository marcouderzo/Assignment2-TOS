////////////////////////////////////////////////////////////////////
// [Marco] [Uderzo] [1201290]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import it.unipd.tos.model.MenuItem;


import static org.junit.Assert.assertEquals;
//import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;

public class MenuItemTest {

    MenuItem item;

    @Before
    public void setup()
    {
           item = new MenuItem("Pinguino", MenuItem.items.Budino, 6.00);
    }

    @Test
    public void getNameTest()
    {
            assertEquals("Pinguino", item.getName());	
    }

    @Test
    public void getTypeTest()
    {
            assertEquals(MenuItem.items.Budino, item.getType());
    }

    @Test
    public void getPrice()
    {
            assertEquals(6.00, item.getPrice(), 0.0);
    }
}
