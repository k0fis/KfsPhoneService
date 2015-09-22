package kfs.kfsPhoneService.dao.jpa;

import java.util.List;
import kfs.kfsPhoneService.dao.SmsIncomingDao;
import kfs.kfsPhoneService.domain.SmsIncoming;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfsstats.domain.KfsStatSet;
import kfs.springutils.BaseDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pavedrim
 */
@Repository
public class JpaSmsIncomingDao extends BaseDaoJpa<SmsIncoming, Long> implements SmsIncomingDao {

    @Override
    protected Class<SmsIncoming> getDataClass() {
        return SmsIncoming.class;
    }

    @Override
    protected Long getId(SmsIncoming data) {
        return data.getId();
    }

    @Override
    public List<SmsIncoming> load(KfsContact cont) {
        return em.createQuery("SELECT a FROM SmsIncoming a WHERE a.contact = :cont ORDER BY a.smsTime DESC")
                .setParameter("cont", cont)
                .getResultList();
    }

}
