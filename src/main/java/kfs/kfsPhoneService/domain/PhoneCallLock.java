package kfs.kfsPhoneService.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import kfs.kfsdbolock.KfsLock;

/**
 *
 * @author pavedrim
 */
@Entity
public class PhoneCallLock extends KfsLock<Long> {

    @Id
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

}
