package kfs.kfsPhoneService.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import kfs.kfsPhoneService.dao.*;
import kfs.kfsPhoneService.domain.*;
import kfs.kfsPhoneService.service.PhoneCallLockService;
import kfs.kfsPhoneService.service.PhoneService;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfscrm.service.CrmService;
import kfs.kfsstats.domain.KfsStatSet;
import kfs.kfsvaalib.utils.KfsI18n;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pavedrim
 */
@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    SmsIncomingDao smsIncomingDao;
    @Autowired
    SmsOutgoingDao smsOutgoingDao;
    @Autowired
    PhoneCallDao phoneCallDao;
    @Autowired
    SmsTemplateDao templateDao;
    @Autowired
    CrmService crmService;
    @Autowired
    KfsI18n i18n;
    @Autowired
    PhoneCallLockService phoneCallLockService;

    @Override
    public void incomingSave(SmsIncoming sms, String statusName) {
        sms.setContact(updateContact(sms.getContact(), sms.getFromNumber(), sms.getIncomingUserId(), statusName));
        if (sms.getId() == null) {
            smsIncomingDao.insert(sms);
        } else {
            smsIncomingDao.update(sms);
        }
    }

    @Override
    public void outgoingSave(SmsOutgoing sms) {
        if (sms.getId() == null) {
            smsOutgoingDao.insert(sms);
        } else {
            smsOutgoingDao.update(sms);
        }
    }

    @Override
    public SmsOutgoing outgoingSmsPop(String userId) {
        return smsOutgoingDao.pop(userId);
    }

    @Override
    public void callSave(PhoneCall call, String statusName) {
        call.setContact(updateContact(call.getContact(), call.getFromNumber(), call.getUserId(), statusName));
        if (call.getId() == null) {
            phoneCallDao.insert(call);
        } else {
            phoneCallDao.update(call);
        }
    }

    KfsContact updateContact(KfsContact cont, String number, String user, String statusName) {
        if (cont == null) {
            cont = crmService.contactFindByPhone(number, user);
        }
        cont.setLastUpdate(new Timestamp(new Date().getTime()));
        cont.setLastUpdateBy(user);
        cont.setLastPhone(number);
        if (statusName != null) {
            cont.setStatus(statusName);
        }
        crmService.contactSave(cont, user);
        return cont;
    }

    @Override
    public SmsOutgoing outgoingFindById(Long smsOutId) {
        return smsOutgoingDao.findById(smsOutId);
    }

    @Override
    public List<PhoneCall> callLoadNotDone() {
        return phoneCallDao.loadNotDone();
    }

    @Override
    public List<PhoneCall> callLoad(KfsContact cont) {
        return phoneCallDao.load(cont);
    }

    @Override
    public List<SmsIncoming> incomingLoad(KfsContact cont) {
        return smsIncomingDao.load(cont);
    }

    @Override
    public List<SmsOutgoing> outgoingLoad(KfsContact cont) {
        return smsOutgoingDao.load(cont);
    }

    @Override
    public String getczValidPhone(String s) {
        if ((s == null) || (s.isEmpty())) {
            return null;
        }
        s = s.replaceAll("\\D", "");

        if (s.startsWith("+")) {
            s = s.substring(1);
        }
        if (s.startsWith(".")) {
            s = s.substring(1);
        }
        if (s.startsWith("00")) {
            s = s.substring(2);
        }
        if (s.startsWith("0")) {
            s = s.substring(1);
        }
        if (s.startsWith("Tel.")) {
            s = s.substring(4);
        }
        if (s.isEmpty()) {
            return null;
        }
        if (s.startsWith("420")) {
            return s;
        } else if (isCzPhone(s)) {
            return "420" + s;

        }
        return s;
    }

    public static boolean isCzPhone(String s) {
        if (s.startsWith("420")) {
            return isCzPhone2(s.substring(3));
        }
        return isCzPhone2(s);
    }

    public static boolean isCzPhone2(String s) {
        return (s.length() == 9) /*
                 && (
                 s.startsWith("77") || //
                 s.startsWith("72") || //
                 s.startsWith("73") || //
                 s.startsWith("601") || //
                 s.startsWith("602") || //
                 s.startsWith("603") || //
                 s.startsWith("604") || //
                 s.startsWith("605") || //
                 s.startsWith("606") || //
                 s.startsWith("607") || //
                 s.startsWith("608")
                 )*/;
    }

    @Override
    public SmsTemplate templateInit() {
        SmsTemplate st = new SmsTemplate();
        st.setName("");
        st.setText("");
        return st;
    }

    @Override
    public SmsTemplate templateCreate(String name) {
        SmsTemplate t = templateDao.findByName(name);
        if (t != null) {
            return t;
        }
        t = templateInit();
        t.setName(name);
        t.setText(name);
        templateDao.insert(t);
        return t;
    }

    @Override
    public void templateDelete(SmsTemplate data) {
        templateDao.delete(data);
    }

    @Override
    public void templateSave(SmsTemplate template) {
        templateDao.save(template);
    }

    @Override
    public List<SmsTemplate> templateLoad() {
        return templateDao.load();
    }

    @Override
    public void templateSend(SmsTemplate template, KfsContact contact, String rcpt) {
        SmsOutgoing so = new SmsOutgoing();
        so.setContact(contact);
        so.setRecepient(rcpt);
        so.setSmsText(template.getText());
        so.setStatus(SmsStatus.created);
        outgoingSave(so);
    }

    public KfsStatSet statIncomingSmsMonthly(int months) {
        //em.createNativeQuery(null, null);
        return null;
    }

    @Override
    public PhoneCallLockService getPhoneCallLockService() {
        return phoneCallLockService;
    }

}
