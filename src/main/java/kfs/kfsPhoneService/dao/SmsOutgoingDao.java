package kfs.kfsPhoneService.dao;

import java.util.List;
import kfs.kfsPhoneService.domain.SmsOutgoing;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfsstats.domain.KfsStatSet;
import kfs.springutils.BaseDao;

/**
 *
 * @author pavedrim
 */
public interface SmsOutgoingDao extends BaseDao<SmsOutgoing, Long>{
    SmsOutgoing pop(String userId);

    public List<SmsOutgoing> load(KfsContact cont);
}
