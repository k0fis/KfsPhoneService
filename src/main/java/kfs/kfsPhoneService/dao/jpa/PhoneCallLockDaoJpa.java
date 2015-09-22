package kfs.kfsPhoneService.dao.jpa;

import kfs.kfsPhoneService.dao.PhoneCallLockDao;
import kfs.kfsPhoneService.domain.PhoneCallLock;
import kfs.kfsdbolock.KfsLockDaoJpa;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pavedrim
 */
@Repository
public class PhoneCallLockDaoJpa extends KfsLockDaoJpa<PhoneCallLock, Long> implements PhoneCallLockDao{


}
