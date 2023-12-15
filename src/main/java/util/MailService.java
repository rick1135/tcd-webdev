package util;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author rktds
 */
@Stateless
public class MailService implements MailServiceLocal {

    @Resource(name = "java:/NovoEmailOutlook")
    private Session mailSession;

    @Override
    public void sendMail(String name, String to, String link) throws MessagingException {
        MimeMessage mail = new MimeMessage(mailSession);
        mail.setFrom("tcdwebdevenem@hotmail.com");
        mail.setSubject("Chave de Ativação");
        mail.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        MimeMultipart content = new MimeMultipart();

        MimeBodyPart body = new MimeBodyPart();
        body.setContent(
                String.format("<html><h1>Chave de ativação</h1>"
                        + "<h2>Hi, %s!</h2>"
                        + "<p style=\"background-color: #eee; padding: .25em; border: solid #999 thin; border-left: solid #999 4px;\">Click <a href=\"%s\" style=\"padding: 0 .25em; background-color: #ccc;\">here</a> to <b>activate</b> your account.</p></html>", name, link),
                "text/html; charset=utf-8");

        content.addBodyPart(body);
        mail.setContent(content);
        Transport.send(mail);
    }

}
