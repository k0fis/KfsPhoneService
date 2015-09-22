package kfs.kfsPhoneService.tools;

import com.vaadin.ui.UI;
import kfs.kfsPhoneService.domain.PhoneCall;
import kfs.kfsPhoneService.service.PhoneService;
import kfs.kfsvaalib.dlg.SimpleListTemplate;
import kfs.kfsvaalib.utils.KfsI18n;

/**
 *
 * @author pavedrim
 */
public class PhoneCallList extends SimpleListTemplate<PhoneCall> {

    public PhoneCallList(UI ui, KfsI18n i18n, PhoneService psvc, String userName) {
        super(ui, i18n, PhoneCall.class, new PhoneCallDlg(ui, i18n, psvc, userName),
                "PhoneCallList", "PhoneCallList", "id", false, false);
    }

    @Override
    protected PhoneCall createNew() {
        return null;
    }

}
