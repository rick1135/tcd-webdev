package util;

import javax.ejb.Local;
import javax.mail.MessagingException;

/**
 *
 * @author rktds
 */
@Local
public interface MailServiceLocal {

    public void sendMail(String name, String to, String link)
            throws MessagingException;
}
