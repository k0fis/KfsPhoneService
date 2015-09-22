package kfs.kfsPhoneService.domain;

import com.vaadin.ui.TextArea;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class SmsIncoming implements KfsCrmDetail{

    @KfsTablePos(@Pos(value = 5, name = "SmsIncomingList"))
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @KfsMField(@KfsField(name = "SmsIncomingDlg", pos = 10))
    @OneToOne(optional = false)
    private KfsContact contact;    
    
    @KfsMField(@KfsField(name = "SmsIncomingDlg", pos = 20, readOnly = true))
    @KfsTablePos(@Pos(value = 20, name = "SmsIncomingList"))
    @Column(length = 20, nullable = false)
    private String fromNumber;
        
    @KfsMField(@KfsField(name = "SmsIncomingDlg", pos = 30, readOnly = true))
    @KfsTablePos(@Pos(value = 30, name = "SmsIncomingList"))
    private Timestamp smsTime;
    
    @KfsMField(@KfsField(name = "SmsIncomingDlg", pos = 40, readOnly = true, fieldClass = TextArea.class))
    @KfsTablePos(@Pos(value = 40, name = "SmsIncomingList"))
    @Column(length = 3072)
    private String smsText;

    @KfsMField(@KfsField(name = "SmsIncomingDlg", pos = 50, readOnly = true))
    @KfsTablePos(@Pos(value = 50, name = "SmsIncomingList"))
    private String incomingUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    public String getSmsText() {
        return smsText;
    }

    public void setSmsText(String smsText) {
        this.smsText = smsText;
    }

    public Timestamp getSmsTime() {
        return smsTime;
    }

    public void setSmsTime(Timestamp smsTime) {
        this.smsTime = smsTime;
    }

    public String getIncomingUserId() {
        return incomingUserId;
    }

    public void setIncomingUserId(String incomingUserId) {
        this.incomingUserId = incomingUserId;
    }

    public KfsContact getContact() {
        return contact;
    }

    public void setContact(KfsContact contact) {
        this.contact = contact;
    }

    @Override
    public Timestamp getDetailDate() {
        return smsTime;
    }

    public static String crmDetailName = "SMS I";

    @Override
    public String getDetailName() {
        return crmDetailName;
    }


    @Override
    public String getDetailText() {
        return smsText;
    }
    
    
}
