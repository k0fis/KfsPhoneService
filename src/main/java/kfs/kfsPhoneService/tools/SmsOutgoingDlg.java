package kfs.kfsPhoneService.tools;

import com.vaadin.ui.Field;
import com.vaadin.ui.UI;
import kfs.kfsPhoneService.domain.SmsOutgoing;
import kfs.kfsPhoneService.domain.SmsStatus;
import kfs.kfsPhoneService.service.PhoneService;
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
public class SmsOutgoingDlg extends SimpleDlgTemplate<SmsOutgoing> {

    private final PhoneService phoneService;

    public SmsOutgoingDlg(UI ui, KfsI18n i18n, PhoneService phoneService) {
        this(ui, i18n, phoneService, "SmsOutgoingDlg", "SmsOutgoingDlg", null);
    }

    public SmsOutgoingDlg(UI ui, KfsI18n i18n, PhoneService phoneService, 
            String i18nPrefix, String fname,
            final KfsEditorField.Editor<KfsContact> contactEditor) {
        super(ui, i18n, i18nPrefix, new MFieldGroup(i18n, fname, new MFieldGroup.MFieldFactory() {

            @Override
            public Field createField(Class objectClass, String filedName, KfsField kfsField, Class fieldClass) {
                if (fieldClass.equals(KfsContact.class)) {
                    if (contactEditor != null) {
                        return new  KfsEditorField<>("", KfsContact.class, contactEditor, null);
                    }
                }
                return null;
            }
        }, SmsOutgoing.class));
        this.phoneService = phoneService;
    }

    @Override
    public String getKfsInfo(SmsOutgoing data) {
        return i18n.getMsg(SmsStatus.class.getSimpleName() + "." + 
                data.getStatus().name()) + "  " + data.getSmsText().substring(0,30);
    }

    @Override
    protected void kfsSave(SmsOutgoing data) {
        phoneService.outgoingSave(data);
    }
}
