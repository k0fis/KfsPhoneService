package kfs.kfsPhoneService.service.xchange;

/**
 * Class defined for exchange information
 * <br/>
 * outgoing SMS <br/><br/>
 *       smsOutgoing oo = new smsOutgoing();<br/>
 *       oo.id = "3";<br/>
 *       oo.numb = "4343";<br/>
 *       oo.text = "cus pico, " ;<br/>
 * <br/>
 * {"numb":"4343","text":"cus pico, ","id":"3"}
 * 
 * @author Pavel Drimalka
 */
public class smsOutgoing {
    /**
     * destination SMS number
     */
    public String numb;
    
    /**
     * destination SMS text
     */
    public String text;
    
    /**
     * SMS id - for commit operation
     */
    public String id;
}
