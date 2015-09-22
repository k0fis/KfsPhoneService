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
public class PhoneCallListDlg extends Window implements KfsRefresh{

    private final UI ui;
    private final PhoneService psvc;
    private final PhoneCallList phoneCallList;
    
    public PhoneCallListDlg(UI ui, KfsI18n i18n, PhoneService psvc, String userName, Component... comps){
        this(ui, i18n, psvc, "PhoneCallListDlg", userName, comps);
    }
    public PhoneCallListDlg(UI ui, KfsI18n i18n, PhoneService psvc, String i18nPrefix, String userName, Component... comps){
        super(i18n.getMsg(i18nPrefix + ".title"));
        this.ui = ui;
        this.psvc = psvc;
        phoneCallList = new PhoneCallList(ui, i18n, psvc, userName);
        VerticalLayout vl = new VerticalLayout();
        vl.addComponent(phoneCallList);
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
        phoneCallList.show(psvc.templateLoad(), this);
    }    
}
