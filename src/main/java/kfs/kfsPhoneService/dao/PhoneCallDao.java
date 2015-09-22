package kfs.kfsPhoneService.dao;

import java.util.List;
import kfs.kfsPhoneService.domain.PhoneCall;
import kfs.kfscrm.domain.KfsContact;
import kfs.springutils.BaseDao;

/**
 *
 * @author pavedrim
 */
public interface PhoneCallDao extends BaseDao<PhoneCall, Long>{

    public List<PhoneCall> loadNotDone();

    public List<PhoneCall> load(KfsContact cont);
    
}
