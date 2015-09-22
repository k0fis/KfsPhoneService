package kfs.kfsPhoneService.utils;

/**
 *
 * @author pavedrim
 */
public class PhoneServiceException extends RuntimeException {
    public PhoneServiceException(String msg) {
        super(msg);
    }
    public PhoneServiceException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
