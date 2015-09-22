package kfs.kfsPhoneService.domain;

import javax.persistence.Entity;
import kfs.kfsstats.domain.KfsStatValue;
import org.hibernate.annotations.Subselect;

/**
 *
 * @author pavedrim
 */
@Entity
@Subselect("SELECT to_char(a.smsTime , 'YYYY-MM') period, count(a.id) value from SmsIncoming a GROUP BY to_char(a.smsTime , 'YYYY-MM')")
public class SmsIncomingStat extends KfsStatValue{

}
