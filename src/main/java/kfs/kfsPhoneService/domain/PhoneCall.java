package kfs.kfsPhoneService.domain;

import java.io.Serializable;
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
public class PhoneCall implements KfsCrmDetail, Serializable {

    @KfsTablePos(@Pos(value = 5, name = "PhoneCallList"))
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @KfsTablePos(@Pos(value = 10, name = "PhoneCallList"))
    @KfsMField(@KfsField(name = "PhoneCallDlg", pos = 10, readOnly = true))
    @Column(length = 20, nullable = false, updatable = false)
    private String fromNumber;

    @KfsMField(@KfsField(name = "PhoneCallDlg", pos = 20, readOnly = true))
    @Column(length = 50, nullable = false, updatable = false)
    private String userId;

    @KfsMField(@KfsField(name = "PhoneCallDlg", pos = 30))
    @OneToOne(optional = false)
    private KfsContact contact;

    @KfsTablePos(@Pos(value = 40, name = "PhoneCallList"))
    @KfsMField(@KfsField(name = "PhoneCallDlg", pos = 40, readOnly = true))
    @Column(nullable = false, updatable = false)
    private Timestamp callDate;

    @KfsMField(@KfsField(name = "PhoneCallDlg", pos = 50, readOnly = true))
    @Column(nullable = false, updatable = false)
    private Long miliDate;

    @KfsMField(@KfsField(name = "PhoneCallDlg", pos = 60, readOnly = true))
    @Column(nullable = false, updatable = false)
    private Integer duration;

    @KfsTablePos(@Pos(value = 70, name = "PhoneCallList"))
    @KfsMField(@KfsField(name = "PhoneCallDlg", pos = 70, readOnly = true))
    @Column(length = 20, nullable = false, updatable = false)
    private String number;

    @KfsTablePos(@Pos(value = 80, name = "PhoneCallList"))
    @KfsMField(@KfsField(name = "PhoneCallDlg", pos = 80, readOnly = true))
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private PhoneCallType type;

    @KfsMField(@KfsField(name = "PhoneCallDlg", pos = 100))
    private String note;
    
    @KfsMField(@KfsField(name = "PhoneCallDlg", pos = 110))
    private String noteUser;
    
    @KfsMField(@KfsField(name = "PhoneCallDlg", pos = 120))
    private Timestamp noteDate;
    
    @KfsTablePos(@Pos(value = 130, name = "PhoneCallList"))
    @KfsMField(@KfsField(name = "PhoneCallDlg", pos = 130))
    private boolean workDone = false;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getMiliDate() {
        return miliDate;
    }

    public void setMiliDate(Long miliDate) {
        this.miliDate = miliDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Timestamp getCallDate() {
        return callDate;
    }

    public void setCallDate(Timestamp callDate) {
        this.callDate = callDate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneCallType getType() {
        return type;
    }

    public void setType(PhoneCallType type) {
        this.type = type;
    }

    public KfsContact getContact() {
        return contact;
    }

    public void setContact(KfsContact contact) {
        this.contact = contact;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNoteUser() {
        return noteUser;
    }

    public void setNoteUser(String noteUser) {
        this.noteUser = noteUser;
    }

    public Timestamp getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(Timestamp noteDate) {
        this.noteDate = noteDate;
    }

    public boolean isWorkDone() {
        return workDone;
    }

    public void setWorkDone(boolean workDone) {
        this.workDone = workDone;
    }

    @Override
    public Timestamp getDetailDate() {
        return getCallDate();
    }

    @Override
    public String getDetailName() {
        return getClass().getSimpleName();
    }

    @Override
    public String getDetailText() {
        return note;
    }

}
