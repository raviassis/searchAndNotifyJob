import entities.SearchAndNotify;
import repositories.DBConnection;
import repositories.SearchAndNotifyRepository;
import services.GetHtmlPage;
import services.SearchAndNofityService;
import services.sendemail.SendEmailService;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args)
            throws SQLException {
	// write your code here
        var getHtmlPage = new GetHtmlPage();
        var session = GenerateMailSession.getSession();
        var sendEmailService = new SendEmailService(
                () -> new MimeMessage(session),
//                (Message msg) -> Transport.send(msg)
                Transport::send
        );
        var searchAndNotifyService = new SearchAndNofityService(
            getHtmlPage,
            sendEmailService
        );
        var searchAndNotifyRepository = new SearchAndNotifyRepository(
            new DBConnection()
        );

        List<SearchAndNotify> searchAndNotifies = searchAndNotifyRepository.findAll();

        searchAndNotifies.forEach(
            (s) -> {
                try {
                    searchAndNotifyService.search(s);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        );

    }
}
