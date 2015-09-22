package kfs.kfsPhoneService.dao;

import java.util.List;
import kfs.kfsPhoneService.domain.SmsTemplate;
import kfs.springutils.BaseDao;

/**
 *
 * @author pavedrim
 */
public interface SmsTemplateDao extends BaseDao<SmsTemplate, Long>{

    List<SmsTemplate> load();

    SmsTemplate findByName(String name);
    
}
