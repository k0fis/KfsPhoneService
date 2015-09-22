package kfs.kfsPhoneService.dao.jpa;

import java.util.List;
import javax.persistence.NoResultException;
import kfs.kfsPhoneService.dao.SmsOutgoingDao;
import kfs.kfsPhoneService.domain.SmsOutgoing;
import kfs.kfsPhoneService.domain.SmsStatus;
import kfs.kfscrm.domain.KfsContact;
import kfs.kfsstats.domain.KfsStatSet;
import kfs.springutils.BaseDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pavedrim
 */
@Repository
public class JpaSmsOutgoingDao extends BaseDaoJpa<SmsOutgoing, Long> implements SmsOutgoingDao {

    @Override
    protected Class<SmsOutgoing> getDataClass() {
        return SmsOutgoing.class;
    }

    @Override
    protected Long getId(SmsOutgoing data) {
        return data.getId();
    }

    @Override
    public SmsOutgoing pop(String userId) {
        try {
            return (SmsOutgoing) em.createQuery("select so from SmsOutgoing so where so.status = :smsStatus and ( so.outgoingUserId is null or so.outgoingUserId = : userId) order by so.createdTime")
                    .setParameter("userId", userId)
                    .setParameter("smsStatus", SmsStatus.created)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<SmsOutgoing> load(KfsContact cont) {
        return em.createQuery("SELECT a FROM SmsOutgoing a WHERE a.contact = :cont ORDER BY a.createdTime")
                .setParameter("cont", cont)
                .getResultList();
    }

}
