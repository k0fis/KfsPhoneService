package kfs.kfsPhoneService.domain;

import com.vaadin.ui.TextArea;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import kfs.kfsvaalib.kfsForm.KfsField;
import kfs.kfsvaalib.kfsForm.KfsMField;
import kfs.kfsvaalib.kfsTable.KfsTablePos;
import kfs.kfsvaalib.kfsTable.Pos;

/**
 *
 * @author pavedrim
 */
@Entity
public class SmsTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @KfsTablePos({
        @Pos(value = 1, name = "SmsTemplateList", genName = "id0"),})
    private Long id;

    @KfsMField({
        @KfsField(name = "SmsTemplateDlg", pos = 10, isRequired = true),})
    @Column(unique = true)
    @KfsTablePos({
        @Pos(value = 10, name = "SmsTemplateList"),})
    private String name;

    @KfsMField({
        @KfsField(name = "SmsTemplateDlg", pos = 20, fieldClass = TextArea.class),})
    @KfsTablePos({
        @Pos(value = 20, name = "SmsTemplateList"),})
    @Column(length = 1024)
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SmsTemplate other = (SmsTemplate) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
