package kfs.kfsPhoneService.service;

import java.util.List;
import kfs.kfsPhoneService.domain.PhoneCall;
import kfs.kfsPhoneService.domain.SmsIncoming;
import kfs.kfsPhoneService.domain.SmsOutgoing;
import kfs.kfsPhoneService.domain.SmsTemplate;
import kfs.kfscrm.domain.KfsContact;

/**
 *
 * @author pavedrim
 */
public interface PhoneService {

    void incomingSave(SmsIncoming sms, String statusName);

    void outgoingSave(SmsOutgoing sms);
    SmsOutgoing outgoingSmsPop(String userId);
    SmsOutgoing outgoingFindById(Long smsOutId);

    
    SmsTemplate templateInit();
    SmsTemplate templateCreate(String name);
    void templateDelete(SmsTemplate data);
    void templateSave(SmsTemplate template);
    List<SmsTemplate> templateLoad();
    void templateSend(SmsTemplate template, KfsContact contact, String rcpt);
    
    void callSave(PhoneCall callOutgoing, String statusName);
    
    List<PhoneCall> callLoadNotDone();
    List<PhoneCall> callLoad(KfsContact cont);
    List<SmsIncoming> incomingLoad(KfsContact cont);
    List<SmsOutgoing> outgoingLoad(KfsContact cont);

    String getczValidPhone(String input);
    
    PhoneCallLockService getPhoneCallLockService();

    
}
