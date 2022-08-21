package services;

import entities.SearchAndNotify;
import services.sendemail.SendEmailService;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.regex.Pattern;

public class SearchAndNofityService {
    private final GetHtmlPage getHtmlPage;
    private final SendEmailService sendEmailService;

    public SearchAndNofityService(
        GetHtmlPage getHtmlPage, 
        SendEmailService sendEmailService
    ) {
        this.getHtmlPage = getHtmlPage;
        this.sendEmailService = sendEmailService;
    }

    public void search(SearchAndNotify searchAndNotify)
            throws MessagingException, IOException, InterruptedException {
        var pattern = Pattern.compile(searchAndNotify.getTextRegex(), Pattern.CASE_INSENSITIVE);
        var matcher = pattern.matcher(getHtmlPage.get(searchAndNotify.getPage()));

        if (matcher.find())
            sendEmailService.notify(searchAndNotify);
    }
}
