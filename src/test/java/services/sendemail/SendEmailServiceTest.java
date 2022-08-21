package services.sendemail;

import entities.SearchAndNotify;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URI;

public class SendEmailServiceTest {

    @Test
    public void shouldNotify() throws MessagingException {
        GenerateMimeMessage generateMimeMessage = mock(GenerateMimeMessage.class);
        ITransport transport = mock(ITransport.class);
        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(generateMimeMessage.getNew()).thenReturn(mimeMessage);

        var sendEmailService = new SendEmailService(generateMimeMessage, transport);
        var searchAndNotify = new SearchAndNotify(
            1l,
            URI.create("www.google.com"),
            "google",
            "ravi.g.assis@gmail.com"
        );

        sendEmailService.notify(searchAndNotify);

        verify(generateMimeMessage).getNew();
        verify(mimeMessage).addRecipient(Message.RecipientType.TO, new InternetAddress(searchAndNotify.getEmail()));
        verify(mimeMessage).setSubject("Search Found");
        verify(mimeMessage).setText("This is actual message");
        verify(transport).send(mimeMessage);
    }
}
