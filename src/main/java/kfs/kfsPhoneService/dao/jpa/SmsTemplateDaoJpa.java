package kfs.kfsPhoneService.dao.jpa;

import java.util.List;
import kfs.kfsPhoneService.dao.SmsTemplateDao;
import kfs.kfsPhoneService.domain.SmsTemplate;
import kfs.springutils.BaseDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pavedrim
 */
@Repository
public class SmsTemplateDaoJpa extends BaseDaoJpa<SmsTemplate, Long> implements SmsTemplateDao {

    @Override
    protected Class<SmsTemplate> getDataClass() {
        return SmsTemplate.class;
    }

    @Override
    protected Long getId(SmsTemplate data) {
        return data.getId();
    }

    @Override
    public List<SmsTemplate> load() {
        return em.createQuery("SELECT a FROM SmsTemplate a ORDER BY a.name")
                .getResultList();
    }

    @Override
    public SmsTemplate findByName(String name) {
        List<SmsTemplate> lt = em.createQuery("SELECT a FROM SmsTemplate a WHERE a.name = :name")
                .setParameter("name", name)
                .setMaxResults(1)
                .getResultList();
        if (lt == null) {
            return null;
        }
        if (lt.size() < 1) {
            return null;
        }
        return lt.get(0);
    }

}
