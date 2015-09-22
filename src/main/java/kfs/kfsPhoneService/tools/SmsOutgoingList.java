package kfs.kfsPhoneService.tools;

import com.vaadin.ui.UI;
import kfs.kfsPhoneService.domain.SmsOutgoing;
import kfs.kfsPhoneService.service.PhoneService;
import kfs.kfsvaalib.dlg.SimpleListTemplate;
import kfs.kfsvaalib.utils.KfsI18n;

/**
 *
 * @author pavedrim
 */
public class SmsOutgoingList extends SimpleListTemplate<SmsOutgoing> {

    public SmsOutgoingList(UI ui, KfsI18n i18n, PhoneService psvc) {
        super(ui, i18n, SmsOutgoing.class, new SmsOutgoingDlg(ui, i18n, psvc),
                "SmsOutgoingList", "SmsOutgoingList", "id", false, false);
    }

    @Override
    protected SmsOutgoing createNew() {
        return new SmsOutgoing();
    }

}
