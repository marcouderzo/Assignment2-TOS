////////////////////////////////////////////////////////////////////
// [Marco] [Uderzo] [1201290]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Throwable{

    private String msg;
    
  public TakeAwayBillException(String m){
      
      this.msg = m;
  }
  
  public String getMessage() {
      
      return msg;
  }
}