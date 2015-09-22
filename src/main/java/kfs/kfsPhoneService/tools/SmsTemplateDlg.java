package kfs.kfsPhoneService.tools;

import com.vaadin.ui.UI;
import kfs.kfsPhoneService.domain.SmsTemplate;
import kfs.kfsPhoneService.service.PhoneService;
import kfs.kfsvaalib.dlg.SimpleDlgTemplate;
import kfs.kfsvaalib.kfsForm.MFieldGroup;
import kfs.kfsvaalib.utils.KfsI18n;

/**
 *
 * @author pavedrim
 */
public class SmsTemplateDlg  extends SimpleDlgTemplate<SmsTemplate>{

    private final PhoneService phoneService;
    
    public SmsTemplateDlg(UI ui, KfsI18n i18n, PhoneService phoneService) {
        super(ui, i18n, "SmsTemplateDlg", new MFieldGroup(i18n, "SmsTemplateDlg", 
                null, SmsTemplate.class));
        this.phoneService = phoneService;
    }

    @Override
    protected void kfsSave(SmsTemplate data) {
        phoneService.templateSave(data);
    }

    @Override
    public String getKfsInfo(SmsTemplate data) {
        return data.getName();
    }

}
