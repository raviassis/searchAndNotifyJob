package services.sendemail;

import javax.mail.internet.MimeMessage;

public interface GenerateMimeMessage {
    MimeMessage getNew();
}
