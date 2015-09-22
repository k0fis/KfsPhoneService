package kfs.kfsPhoneService.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import kfs.kfsPhoneService.domain.*;
import kfs.kfsPhoneService.service.PhoneService;
import kfs.kfsPhoneService.service.PhoneServletService;
import kfs.kfsPhoneService.service.xchange.*;
import kfs.kfsPhoneService.utils.PhoneServiceException;
import kfs.kfslog.service.KfsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pavedrim
 */
@Service
@Transactional
public class PhoneServletServiceImpl implements PhoneServletService {

    private String cmdSmsIncoming = "i";
    private String cmdSmsOutgoing = "o";
    private String cmdSmsCommit = "c";
    private String cmdSmsFail = "f";
    private String cmdLogout = "q";
    private String cmdCallMissed = "cm";
    private String cmdCallIncoming = "ci";
    private String cmdCallOutgoing = "co";
    private String varName = "data";
    private String cntType = "text/html;charset=UTF-8";
    private String fmtDate = "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS";
    private String servletInfo = "KfsSmsBot server";
    private String smsDateFormat = "yyyy-MM-dd HH:mm:ss";
    private SimpleDateFormat sdf = new SimpleDateFormat(smsDateFormat);

    private boolean workingHoursInUse = true;
    private int workingHoursBegin = 8;
    private int workingHoursEnd = 20;

    @Autowired
    PhoneService phoneService;
    @Autowired
    KfsLogService kfsLog;

    @Override
    public boolean isInInterval(Timestamp ts) {
        if (!workingHoursInUse) {
            return true;
        }
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(ts);
            int h = c.get(Calendar.HOUR_OF_DAY);
            return ((h >= workingHoursEnd) && (h < workingHoursEnd));
        } catch (Exception ex) {
            throw new PhoneServiceException("Cannot run in interval", ex);
        }
    }

    @Override
    public String getCmdSmsIncoming() {
        return cmdSmsIncoming;
    }

    public void setCmdSmsIncoming(String cmdSmsIncoming) {
        this.cmdSmsIncoming = cmdSmsIncoming;
    }

    @Override
    public String getCmdSmsOutgoing() {
        return cmdSmsOutgoing;
    }

    public void setCmdSmsOutgoing(String cmdSmsOutgoing) {
        this.cmdSmsOutgoing = cmdSmsOutgoing;
    }

    @Override
    public String getCmdSmsCommit() {
        return cmdSmsCommit;
    }

    public void setCmdSmsCommit(String cmdSmsCommit) {
        this.cmdSmsCommit = cmdSmsCommit;
    }

    @Override
    public String getCmdSmsFail() {
        return cmdSmsFail;
    }

    public void setCmdSmsFail(String cmdSmsFail) {
        this.cmdSmsFail = cmdSmsFail;
    }

    @Override
    public String getCmdLogout() {
        return cmdLogout;
    }

    public void setCmdLogout(String cmdLogout) {
        this.cmdLogout = cmdLogout;
    }

    @Override
    public String getCmdCallMissed() {
        return cmdCallMissed;
    }

    public void setCmdCallMissed(String cmdCallMissed) {
        this.cmdCallMissed = cmdCallMissed;
    }

    @Override
    public String getCmdCallIncoming() {
        return cmdCallIncoming;
    }

    public void setCmdCallIncoming(String cmdCallIncoming) {
        this.cmdCallIncoming = cmdCallIncoming;
    }

    @Override
    public String getCmdCallOutgoing() {
        return cmdCallOutgoing;
    }

    public void setCmdCallOutgoing(String cmdCallOutgoing) {
        this.cmdCallOutgoing = cmdCallOutgoing;
    }

    @Override
    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    @Override
    public String getCntType() {
        return cntType;
    }

    public void setCntType(String cntType) {
        this.cntType = cntType;
    }

    @Override
    public String getFmtDate() {
        return fmtDate;
    }

    public void setFmtDate(String fmtDate) {
        this.fmtDate = fmtDate;
    }

    @Override
    public String getServletInfo() {
        return servletInfo;
    }

    public void setServletInfo(String servletInfo) {
        this.servletInfo = servletInfo;
    }

    @Override
    public boolean isWorkingHoursInUse() {
        return workingHoursInUse;
    }

    public void setWorkingHoursInUse(boolean workingHoursInUse) {
        this.workingHoursInUse = workingHoursInUse;
    }

    @Override
    public int getWorkingHoursBegin() {
        return workingHoursBegin;
    }

    public void setWorkingHoursBegin(int workingHoursBegin) {
        this.workingHoursBegin = workingHoursBegin;
    }

    @Override
    public int getWorkingHoursEnd() {
        return workingHoursEnd;
    }

    public void setWorkingHoursEnd(int workingHoursend) {
        this.workingHoursEnd = workingHoursend;
    }

    @Override
    public String getSmsDateFormat() {
        return smsDateFormat;
    }

    public void setSmsDateFormat(String smsDateFormat) {
        this.smsDateFormat = smsDateFormat;
        sdf = new SimpleDateFormat(smsDateFormat);
    }

    public void log(String userId, String text) {
        kfsLog.log(userId, text);
    }

    @Override
    public Object smsIncoming(String userId, smsIncoming data) {
        if (data == null) {
            log(userId,
                    "SMSS.doIncoming - input data is empty");
            return "input data is null";
        }
        log(userId, "SMSS incoming - " + data.numb + " - " + data.text);
        SmsIncoming smsIn = new SmsIncoming();
        smsIn.setFromNumber(data.numb);
        smsIn.setSmsText(data.text);
        smsIn.setIncomingUserId(userId);
        try {
            smsIn.setSmsTime(new Timestamp(sdf.parse(data.time).getTime()));
        } catch (ParseException ex) {
            smsIn.setSmsTime(new Timestamp(new Date().getTime()));
            log(userId, "SMSS.doIncoming - Cannot parse incoming date: " + data.time);
        }
        phoneService.incomingSave(smsIn);
        return "danke";

    }

    @Override
    public Object callMissed(String userId, call mc) {
        log(userId,
                "Missed Call ts: " + mc.unxTimeMilisec + ", msisdn: " + mc.msisdn);
        PhoneCall co = new PhoneCall();
        co.setUserId(userId);
        co.setDuration(0);
        co.setCallDate(new Timestamp(mc.unxTimeMilisec));
        co.setNumber(mc.msisdn);
        co.setType(PhoneCallType.missed);
        phoneService.callSave(co);
        return "danke";
    }

    @Override
    public Object callIncoming(String userId, call mc) {
        log(userId,
                "Incoming Call ts: " + mc.unxTimeMilisec + ", msisdn: " + mc.msisdn
                + " duration: " + mc.durationMilisec);
        //smsService.createIncomingCall(user, mc.unxTimeMilisec, mc.durationMilisec, mc.msisdn);
        PhoneCall co = new PhoneCall();
        co.setUserId(userId);
        co.setDuration(mc.durationMilisec);
        co.setCallDate(new Timestamp(mc.unxTimeMilisec));
        co.setNumber(mc.msisdn);
        co.setType(PhoneCallType.incoming);
        phoneService.callSave(co);
        return "danke";
    }

    @Override
    public Object callOutgoing(String userId, call mc) {
        log(userId, "Outgoing Call ts: "
                + mc.unxTimeMilisec + ", msisdn: " + mc.msisdn + " duration: "
                + mc.durationMilisec);
        PhoneCall co = new PhoneCall();
        co.setUserId(userId);
        co.setDuration(mc.durationMilisec);
        co.setCallDate(new Timestamp(mc.unxTimeMilisec));
        co.setNumber(mc.msisdn);
        co.setType(PhoneCallType.outgoing);
        phoneService.callSave(co);
        return "danke";
    }

    @Override
    public smsOutgoing smsOutgoingSending(String userId) {
        SmsOutgoing smsOut = null;
        smsOutgoing oo = new smsOutgoing();

        if (isInInterval(new Timestamp(new Date().getTime()))) {
            smsOut = phoneService.outgoingSmsPop(userId);
        }
        if (smsOut != null) {
            oo.id = Long.toString(smsOut.getId());
            oo.numb = "+" + smsOut.getRecepient();
            oo.text = smsOut.getSmsText();
        } else {
            oo.id = null;
            oo.numb = null;
            oo.text = null;
        }
        return oo;
    }

    @Override
    public Object smsOutgoingFail(String userId, smsFailOut data) {
        if (data == null) {
            log(userId, "SMSS.doFail - input data is empty");
            return "input data is null";
        }
        SmsOutgoing smsOut = phoneService.outgoingFindById(Long.parseLong(data.id));
        if (smsOut == null) {
            log(userId, "SMSS.doFail - cannot find SmsOutgoing.id=" + data.id);
            return "cannot find out sms id: " + data.id;
        }
        smsOut.setStatus(SmsStatus.fail);
        phoneService.outgoingSave(smsOut);
        log(userId, "SMSS.doFail - SmsOutgoing.id=" + data.id + ": " + data.mesg);
        return "danke";
    }

    @Override
    public Object smsOutgoingCommit(String userId, smsCommitOut data) {
        if (data == null) {
            log(userId, "SMSS.doCommit - input data is empty");
            return "input data is null";
        }
        SmsOutgoing smsOut = phoneService.outgoingFindById(Long.parseLong(data.id));
        if (smsOut == null) {
            log(userId, "SMSS.doComit - cannot find SmsOutgoing.id=" + data.id);
            return "cannot find out sms id: " + data.id;
        }
        smsOut.setStatus(SmsStatus.commit);
        phoneService.outgoingSave(smsOut);
        log(userId, "SMSS.doCommit - SmsOutgoing.id=" + data.id);
        return "danke";

    }

}
