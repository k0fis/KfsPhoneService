package kfs.kfsPhoneService.service.impl;

import kfs.kfsPhoneService.dao.PhoneCallLockDao;
import kfs.kfsPhoneService.domain.PhoneCallLock;
import kfs.kfsPhoneService.service.PhoneCallLockService;
import kfs.kfsdbolock.KfsLockDao;
import kfs.kfsdbolock.KfsLockToolsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pavedrim
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PhoneCallLockServiceImpl extends KfsLockToolsImpl<PhoneCallLock, Long>
        implements PhoneCallLockService {

    @Value("${PhoneService.lockMinutesDelay:10}")
    private int lockMinutesDelay;
    @Autowired
    PhoneCallLockDao dao;

    @Override
    public Class<PhoneCallLock> getCls() {
        return PhoneCallLock.class;
    }

    @Override
    public KfsLockDao<PhoneCallLock, Long> getDao() {
        return dao;
    }

    @Override
    public int getMinutesDelay() {
        return lockMinutesDelay;
    }

}
