package kfs.kfsPhoneService.service.xchange;

/**
 * Class defined for exchange information
 * This is for commit send outgoing SMS <br/><br/>
 *      smsCommitOut oo = new smsCommitOut();<br/>
 *      oo.id = "3";<br/>
 *      oo.time = String.format(fmtDate, new Date());<br/>
 * <br/>
 * {"numb":"4343","text":"cus pico, ","id":"3"}
 * 
 * @author Pavel Drimalka
 * @see smsConsts#fmtDate fmtDate 
 */
public class smsCommitOut {
    /**
     * SMS id. It's copy from smsOutgoing.id
     */
    public String id;
    
    /**
     * This is time SMS send
     */
    public String time;
}
