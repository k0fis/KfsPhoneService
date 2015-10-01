package kfs.kfsPhoneService.service;

import java.sql.Timestamp;
import kfs.kfsPhoneService.service.xchange.*;

/**
 *
 * @author pavedrim
 */
public interface PhoneServletService {

    //// commands
    /**
     * Call this command for handset incoming SMS from GSM and post this sms on the server
     * <br/><br/>
     * <b>"i"</b>
     * postfix after serverPostFix. SMSS implements in POST action and waiting for JSON for
     * smsIncoming message in http variable varName
     *
     * @return command code
     * @see #serverPostFix
     * @see #varName
     * @see smsIncoming smsIncoming
     */
    String getCmdSmsIncoming();

    /**
     * Call this command for obtain data for send SMS from server to the handset/gsm
     * <br/><br/>
     * <b>"o"</b>
     * postfix after serverPostFix. SMSS implements it in both action (POST, GET) and returns null
     * or JSON smsOutgoing. This command don't awaits any input data
     *
     * @return command code
     * @see #serverPostFix
     * @see #varName
     * @see smsOutgoing
     */
    String getCmdSmsOutgoing();

    /**
     * Call this command with correctly sent SMS - commit this SMS on server
     * <br/><br/>
     * <b>"c"</b>
     * postfix after serverPostFox. SMSS implements this command in POST action and awaits JSON for
     * class smsCommitOut
     *
     * @return command code
     * @see #serverPostFix
     * @see #varName
     * @see smsCommitOut
     */
    String getCmdSmsCommit();

    /**
     * Call this command with error during send SMS optionally with error message
     * <br/><br/>
     * <b>"f"</b>
     * postfix after serverPostFox. SMSS implements this command in POST action and awaits JSON for
     * class smsFailOut
     *
     * @return command code
     * @see #serverPostFix
     * @see #varName
     * @see smsFailOut *
     */
    String getCmdSmsFail();

    /**
     * Call this command logout handset from server
     * <br/><br/>
     * <b>"q"</b>
     * postfix after serverPostFox. SMSS implements this command in POST and GET action and don't
     * awaits any input
     *
     * @return command code
     * @see #serverPostFix
     */
    String getCmdLogout();

    String getCmdCallMissed();

    String getCmdCallIncoming();

    String getCmdCallOutgoing();

    /**
     * <b>"data"</b><br/><br/>
     * HTTP variable name for data in POST action
     *
     * @return http variable name
     */
    String getVarName();

    /**
     * <b>text/html;charset=UTF-8</b><br/><br/>
     *
     * @return SMSS server content type for outgoing messages
     */
    String getCntType();

    /**
     * format date string for date formating. Use: String.format(fmtDate, new Date());
     *
     * default is
     * <pre>%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS</pre>
     *
     * @return format date
     * @see String#format(String, Object []) String.format
     *
     */
    String getFmtDate();

    /**
     * Servlet info text
     *
     * @return
     */
    String getServletInfo();

    //void log(String userId, String text, boolean hb);
    
    String getSmsDateFormat();
    boolean isInInterval(Timestamp ts);
    boolean isWorkingHoursInUse();
    int getWorkingHoursBegin();
    int getWorkingHoursEnd();
    Object smsIncoming(String userId, smsIncoming smsIn, String statusName);
    Object callMissed(String userId, call call, String statusName);
    Object callIncoming(String userId, call call, String statusName);
    Object callOutgoing(String userId, call call);
    smsOutgoing smsOutgoingSending(String userId);
    Object smsOutgoingFail(String userId, smsFailOut smsOut);
    Object smsOutgoingCommit(String userId, smsCommitOut smsOut);
}
