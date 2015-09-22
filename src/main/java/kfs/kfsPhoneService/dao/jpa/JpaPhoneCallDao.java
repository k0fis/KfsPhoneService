package kfs.kfsPhoneService.dao.jpa;

import java.util.List;
import kfs.kfsPhoneService.dao.PhoneCallDao;
import kfs.kfsPhoneService.domain.PhoneCall;
import kfs.kfscrm.domain.KfsContact;
import kfs.springutils.BaseDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pavedrim
 */
@Repository
public class JpaPhoneCallDao extends BaseDaoJpa<PhoneCall, Long> implements PhoneCallDao{

    @Override
    protected Class<PhoneCall> getDataClass() {
        return PhoneCall.class;
    }

    @Override
    protected Long getId(PhoneCall data) {
        return data.getId();
    }

    @Override
    public List<PhoneCall> loadNotDone() {
        return em.createQuery("SELECT a FROM PhoneCall a WHERE a.workDone = :wd ORDER BY a.callDate")
                .setParameter("wd", false)
                .getResultList();
    }

    @Override
    public List<PhoneCall> load(KfsContact cont) {
        return em.createQuery("SELECT a FROM PhoneCall a WHERE a.contact = :cont ORDER BY a.callDate DESC")
                .setParameter("cont", cont)
                .getResultList();
    }

}
