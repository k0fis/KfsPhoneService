package kfs.kfsPhoneService.service.xchange;

/**
 * Class defined for exchange information 
 * This is for failed case in sending SMS
 * <br/> <br/>
 *      smsFailOut oo = new smsFailOut(); <br/>
 *      oo.id = "3"; <br/>
 *      oo.time = String.format(fmtDate, new Date());<br/>
 *      oo.mesg = "cus pico, fefunguje" ;<br/>
 *  <br/>
 *  {"id":"3","time":"2012-03-02 09:06:10","mesg":"cus pico, fefunguje"}
 * 
 * @see smsConsts#fmtDate fmtDate 
 * @author Pavel Drimalka
 */
public class smsFailOut {
    /**
     * SMS id. It's copy from smsOutgoing.id
     */
    public String id;
    
    /**
     * This is time SMS send
     */
    public String time;
    
    /**
     * Cause message for fail
     */
    public String mesg;
}
