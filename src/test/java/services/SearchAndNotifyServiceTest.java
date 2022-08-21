package services;

import static org.mockito.Mockito.*;
import entities.SearchAndNotify;
import org.junit.jupiter.api.Test;
import org.mockito.verification.VerificationMode;
import services.sendemail.SendEmailService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URI;

public class SearchAndNotifyServiceTest {

    @Test
    public void shouldNotify() throws MessagingException, IOException, InterruptedException {
        var getHtmlPageMock = mock(GetHtmlPage.class);
        var sendEmailServiceMock = mock(SendEmailService.class);
        var searchAndNotifyService = new SearchAndNofityService(
            getHtmlPageMock,
            sendEmailServiceMock
        );
        var searchAndNotify = new SearchAndNotify(
                1l,
                URI.create("www.google.com"),
                "google",
                "ravi.g.assis@gmail.com"
        );
        when(getHtmlPageMock.get(searchAndNotify.getPage()))
            .thenReturn("<html> page google </html>");

        searchAndNotifyService.search(searchAndNotify);
        verify(getHtmlPageMock, times(1)).get(searchAndNotify.getPage());
        verify(sendEmailServiceMock, times(1)).notify(searchAndNotify);
    }

    @Test
    public void shouldNotNotify() throws IOException, InterruptedException, MessagingException {
        var getHtmlPageMock = mock(GetHtmlPage.class);
        var sendEmailServiceMock = mock(SendEmailService.class);
        var searchAndNotifyService = new SearchAndNofityService(
                getHtmlPageMock,
                sendEmailServiceMock
        );
        var searchAndNotify = new SearchAndNotify(
                1l,
                URI.create("www.google.com"),
                "google",
                "ravi.g.assis@gmail.com"
        );
        when(getHtmlPageMock.get(searchAndNotify.getPage()))
                .thenReturn("<html> page yahoo </html>");
        searchAndNotifyService.search(searchAndNotify);
        verify(getHtmlPageMock, times(1)).get(searchAndNotify.getPage());
        verify(sendEmailServiceMock, never()).notify(searchAndNotify);
    }
}
