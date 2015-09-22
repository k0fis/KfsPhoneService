package kfs.kfsPhoneService.tools;

import com.vaadin.ui.UI;
import kfs.kfsPhoneService.domain.SmsTemplate;
import kfs.kfsPhoneService.service.PhoneService;
import kfs.kfsvaalib.dlg.SimpleListTemplate;
import kfs.kfsvaalib.utils.KfsI18n;

/**
 *
 * @author pavedrim
 */
public class SmsTemplateList extends SimpleListTemplate<SmsTemplate>{

    private final PhoneService psvc;
    
    public SmsTemplateList(UI ui, KfsI18n i18n, PhoneService psvc) {
        super(ui, i18n, SmsTemplate.class, new SmsTemplateDlg(ui, i18n, psvc),
                "SmsTemplateList", "SmsTemplateList", "id", true, true);
        this.psvc = psvc;
    }    
    
    @Override
    protected SmsTemplate createNew() {
        return psvc.templateInit();
    }

    @Override
    protected void deleteItem(SmsTemplate data) {
        psvc.templateDelete(data);
    }

    
}
