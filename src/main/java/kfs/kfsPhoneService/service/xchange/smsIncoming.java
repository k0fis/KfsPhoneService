package kfs.kfsPhoneService.service.xchange;

/**
 * Class defined for exchange information
 * <br/>
 * This is SMS for upload incoming SMS to server <br/><br/>
 *       smsIncoming oo = new smsIncoming();<br/>
 *       oo.numb = "420777123456";<br/>
 *       oo.time = String.format(fmtDate, new Date());<br/>
 *       oo.text = "neprud'";<br/><br/>
 * {"numb":"420777123456","text":"neprud\u0027","time":"2012-03-02 09:12:48"}
 *
 * @see smsConsts#fmtDate fmtDate 
 * @author Pavel Drimalka
 */
public class smsIncoming {
    /**
     * incoming number MSISDN
     */
    public String numb;
    
    /**
     * text of incoming SMS
     */
    public String text;
    
    /**
     * Incoming SMS time
     */
    public String time;
    
}
