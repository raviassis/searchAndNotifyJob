package services.sendemail;

import entities.SearchAndNotify;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailService {
    private final ITransport transport;
    private final GenerateMimeMessage generateMimeMessage;

    public SendEmailService(GenerateMimeMessage generateMimeMessage, ITransport transport) {
        this.generateMimeMessage = generateMimeMessage;
        this.transport = transport;
    }

    public void notify(SearchAndNotify searchAndNotify) throws MessagingException {
        MimeMessage message = this.generateMimeMessage.getNew();
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(searchAndNotify.getEmail()));
        message.setSubject("Search Found");
        StringBuilder text = new StringBuilder();
        text.append("Your search parameters was found.\n");
        text.append("page: " + searchAndNotify.getPage());
        text.append("\ntext: " + searchAndNotify.getTextRegex());
        message.setText(text.toString());
        transport.send(message);
    }

}
