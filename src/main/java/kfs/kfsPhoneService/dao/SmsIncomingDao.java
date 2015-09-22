package kfs.kfsPhoneService.dao;

import java.util.List;
import kfs.kfsPhoneService.domain.SmsIncoming;
import kfs.kfscrm.domain.KfsContact;
import kfs.springutils.BaseDao;

/**
 *
 * @author pavedrim
 */
public interface SmsIncomingDao extends BaseDao<SmsIncoming, Long>{

    public List<SmsIncoming> load(KfsContact cont);

}
