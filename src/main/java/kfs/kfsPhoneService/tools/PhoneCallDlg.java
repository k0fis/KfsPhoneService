package kfs.kfsPhoneService.tools;

import com.vaadin.ui.Field;
import com.vaadin.ui.UI;
import kfs.kfsPhoneService.domain.*;
import kfs.kfsPhoneService.service.PhoneService;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfsvaalib.dlg.SimpleDlgLockTemplate;
import kfs.kfsvaalib.fields.KfsEditorField;
import kfs.kfsvaalib.kfsForm.KfsField;
import kfs.kfsvaalib.kfsForm.MFieldGroup;
import kfs.kfsvaalib.utils.KfsI18n;

/**
 *
 * @author pavedrim
 */
public class PhoneCallDlg extends SimpleDlgLockTemplate<PhoneCall, PhoneCallLock, Long> {

    private final PhoneService phoneService;

    public PhoneCallDlg(UI ui, KfsI18n i18n, PhoneService phoneService, String userName) {
        this(ui, i18n, phoneService, "PhoneCallDlg", "PhoneCallDlg", null, userName);
    }

    public PhoneCallDlg(UI ui, KfsI18n i18n, PhoneService phoneService, 
            String i18nPrefix, String fname,
            final KfsEditorField.Editor<KfsContact> contactEditor, String userName) {
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
        }, PhoneCall.class), phoneService.getPhoneCallLockService(), userName);
        this.phoneService = phoneService;
    }

    @Override
    public String getKfsInfo(PhoneCall data) {
        return i18n.getMsg(PhoneCallType.class.getSimpleName() + "." + 
                data.getType().name()) + "  " + data.getNote();
    }

    @Override
    protected void kfsSave(PhoneCall data) {
        phoneService.callSave(data, null);
    }

    @Override
    protected Long getId(PhoneCall data) {
        return data.getId();
    }
}
