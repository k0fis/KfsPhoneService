package kfs.kfsPhoneService.tools;

import com.vaadin.ui.Field;
import com.vaadin.ui.UI;
import kfs.kfsPhoneService.domain.SmsIncoming;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfsvaalib.dlg.SimpleDlgTemplate;
import kfs.kfsvaalib.fields.KfsEditorField;
import kfs.kfsvaalib.kfsForm.KfsField;
import kfs.kfsvaalib.kfsForm.MFieldGroup;
import kfs.kfsvaalib.utils.KfsI18n;

/**
 *
 * @author pavedrim
 */
public class SmsIncomingDlg extends SimpleDlgTemplate<SmsIncoming> {

    public SmsIncomingDlg(UI ui, KfsI18n i18n) {
        this(ui, i18n, "SmsIncomingDlg", "SmsIncomingDlg", null);
    }

    public SmsIncomingDlg(UI ui, KfsI18n i18n, 
            String i18nPrefix, String fname,
            final KfsEditorField.Editor<KfsContact> contactEditor) {
        super(ui, i18n, i18nPrefix, new MFieldGroup(i18n, fname, new MFieldGroup.MFieldFactory() {

            @Override
            public Field createField(Class objectClass, String filedName, KfsField kfsField, Class fieldClass) {
                if (fieldClass.equals(KfsContact.class)) {
                    if (contactEditor != null) {
                        return new KfsEditorField<>("", KfsContact.class, contactEditor, null);
                    }
                }
                return null;
            }
        }, SmsIncoming.class));
    }

    @Override
    public String getKfsInfo(SmsIncoming data) {
        if (data == null) {
            return "";
        }
        return data.getFromNumber() + " - " + data.getSmsText().substring(0,35);
    }

    @Override
    protected void kfsSave(SmsIncoming data) {
    }
}
