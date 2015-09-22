package kfs.kfsPhoneService.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import kfs.kfscrm.api.KfsCrmDetail;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfsvaalib.kfsForm.KfsField;
import kfs.kfsvaalib.kfsForm.KfsMField;
import kfs.kfsvaalib.kfsTable.KfsTablePos;
import kfs.kfsvaalib.kfsTable.Pos;

/**
 *
 * @author pavedrim
 */
@Entity
public class SmsOutgoing implements KfsCrmDetail {
    
    @KfsTablePos(@Pos(value = 5, name = "SmsOutgoingList"))
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @KfsMField(@KfsField(name = "SmsOutgoingDlg", pos = 10))
    @OneToOne(optional = false)
    private KfsContact contact;    

    @KfsMField(@KfsField(name = "SmsOutgoingDlg", pos = 20, readOnly = true))
    @KfsTablePos(@Pos(value = 20, name = "SmsOutgoingList"))
    private Timestamp createdTime;
    
    
    @KfsMField(@KfsField(name = "SmsOutgoingDlg", pos = 30, readOnly = true))
    @KfsTablePos(@Pos(value = 30, name = "SmsOutgoingList"))
    private Timestamp commitTime;

    @KfsMField(@KfsField(name = "SmsOutgoingDlg", pos = 40, readOnly = true))
    @KfsTablePos(@Pos(value = 40, name = "SmsOutgoingList"))
    private String recepient;

    @KfsMField(@KfsField(name = "SmsOutgoingDlg", pos = 50, readOnly = true))
    @Column(length = 2048)
    private String smsText;

    @KfsMField(@KfsField(name = "SmsOutgoingDlg", pos = 60, readOnly = true))
    private String outgoingUserId;
    
    @KfsMField(@KfsField(name = "SmsOutgoingDlg", pos = 70, readOnly = true))
    @KfsTablePos(@Pos(value = 70, name = "SmsOutgoingList"))
    @Enumerated(EnumType.STRING)
    private SmsStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Timestamp commitTime) {
        this.commitTime = commitTime;
    }

    public String getRecepient() {
        return recepient;
    }

    public void setRecepient(String recepient) {
        this.recepient = recepient;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    public String getOutgoingUserId() {
        return outgoingUserId;
    }

    public void setOutgoingUserId(String outgoingUserId) {
        this.outgoingUserId = outgoingUserId;
    }

    public SmsStatus getStatus() {
        return status;
    }

    public void setStatus(SmsStatus status) {
        this.status = status;
    }

    public KfsContact getContact() {
        return contact;
    }

    public void setContact(KfsContact contact) {
        this.contact = contact;
    }

    @Override
    public Timestamp getDetailDate() {
        return createdTime;
    }

    public static String crmDetailName = "SMS O";

    @Override
    public String getDetailName() {
        return crmDetailName;
    }

    @Override
    public String getDetailText() {
        return smsText;
    }

}
