package services.sendemail;

import javax.mail.Message;
import javax.mail.MessagingException;

public interface ITransport {
    void send(Message msg) throws MessagingException;
}
