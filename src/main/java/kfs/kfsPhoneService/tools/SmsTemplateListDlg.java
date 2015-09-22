package kfs.kfsPhoneService.tools;

import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import kfs.kfsPhoneService.service.PhoneService;
import kfs.kfsvaalib.utils.KfsI18n;
import kfs.kfsvaalib.utils.KfsRefresh;

/**
 *
 * @author pavedrim
 */
public class SmsTemplateListDlg extends Window implements KfsRefresh {

    private final UI ui;
    private final SmsTemplateList l;
    private final PhoneService psvc;

    public SmsTemplateListDlg(UI ui, KfsI18n i18n, PhoneService psvc, Component... comps) {
        this(ui, i18n, psvc, "SmsTemplateListDlg", comps);
    }

    public SmsTemplateListDlg(UI ui, KfsI18n i18n, PhoneService psvc, 
            String i18nPrefix, Component... comps) {
        super(i18n.getMsg(i18nPrefix + ".title"));
        this.ui = ui;
        this.psvc = psvc;
        l = new SmsTemplateList(ui, i18n, psvc);
        VerticalLayout vl = new VerticalLayout();
        vl.addComponent(l);
        vl.addComponents(comps);
        vl.setSpacing(true);
        vl.setMargin(true);
        setContent(vl);
        setSizeFull();
        setModal(true);
        
    }
    
    public void show() {
        kfsRefresh();
        ui.addWindow(this);
    }

    @Override
    public void kfsRefresh() {
        l.show(psvc.templateLoad(), this);
    }

}
