package kfs.kfsPhoneService.tools;

import com.vaadin.ui.UI;
import kfs.kfsPhoneService.domain.SmsIncoming;
import kfs.kfsvaalib.dlg.SimpleListTemplate;
import kfs.kfsvaalib.utils.KfsI18n;

/**
 *
 * @author pavedrim
 */
public class SmsIncomingList extends SimpleListTemplate<SmsIncoming> {

    public SmsIncomingList(UI ui, KfsI18n i18n) {
        super(ui, i18n, SmsIncoming.class, new SmsIncomingDlg(ui, i18n),
                "SmsIncomingList", "SmsIncomingList", "id", false, false);
    }

    @Override
    protected SmsIncoming createNew() {
        return null;
    }

}
