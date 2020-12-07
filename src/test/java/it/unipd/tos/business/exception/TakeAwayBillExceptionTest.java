////////////////////////////////////////////////////////////////////
// [Marco] [Uderzo] [1201290]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TakeAwayBillExceptionTest {

    @Test
    public void getMessageTest() {
        
        String s = "Orders Exceed Limit";
        TakeAwayBillException exception = new TakeAwayBillException(s);
        assertEquals(s, exception.getMessage());
    }
}
