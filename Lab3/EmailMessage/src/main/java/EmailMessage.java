import java.util.LinkedList;
import java.util.regex.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Scanner;

public class EmailMessage {
    //required
    private String from; //required (must be e-mail)
    private LinkedList<String> to; //required at least one (must be e-mail)

    //optional
    private String subject; //optional
    private String content; //optional
    private String mimeType;  // optional
    private LinkedList<String> cc; //optional
    private LinkedList<String> bcc; // optional


    private EmailMessage(Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
        this.subject = builder.subject;
        this.content = builder.content;
        this.mimeType = builder.mimeType;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
    }

    //GETTERS
    private String getFrom() {
        return from;
    }

    private LinkedList<String> getTo() {
        return to;
    }

    private String getSubject() {
        return subject;
    }

    private String getContent() {
        return content;
    }

    private String getMimeType() {
        return mimeType;
    }

    private LinkedList<String> getCc() {
        return cc;
    }

    private LinkedList<String> getBcc() {
        return bcc;
    }

    private static class Builder {
        private boolean testValidEmail(String s) {
            Pattern pattern = Pattern.compile(".+\\@.+\\..+");
            Matcher matcher = pattern.matcher(s);
            return matcher.find();
        }

        //required
        private String from; //required (must be e-mail)
        private LinkedList<String> to; //required at least one (must be e-mail)


        //optional
        private String subject; //optional


        private String content; //optional
        private String mimeType;  // optional
        private LinkedList<String> cc; //optional
        private LinkedList<String> bcc; // optional

        Builder(String From, LinkedList<String> To) {
            this.from = From;
            this.to = To;
        }


        //SETTERS
        private Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        private Builder setContent(String content) {
            this.content = content;
            return this;
        }

        private Builder setMimeType(String mimeType) {
            this.mimeType = mimeType;
            return this;
        }

        private Builder setCc(LinkedList<String> cc) {
            this.cc = cc;
            return this;
        }

        private Builder setBcc(LinkedList<String> bcc) {
            this.bcc = bcc;
            return this;
        }

        private EmailMessage build() {
            EmailMessage emMess = new EmailMessage(this);
            emMess.mimeType = this.mimeType;
            emMess.content = this.content;
            emMess.subject = this.subject;
            emMess.bcc = this.bcc;
            emMess.cc = this.cc;
            return emMess;
        }
    }

        private void send() throws MessagingException{
            final String HOST = "smtp.gmail.com";
            final int PORT = 465;
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtps.auth", "true");

            Scanner input = new Scanner(System.in);
            System.out.println("Podaj haslo do maila: ");
            String PASSWORD = input.nextLine();

            // Inicjalizacja sesji
            Session mailSession = Session.getDefaultInstance(props);

            mailSession.setDebug(true);

            // Tworzenie wiadomości email
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject(getSubject());
            message.setContent(getContent(), "text/plain; charset=ISO-8859-2");
            for (String aTo : getTo()) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(aTo));

                Transport transport = mailSession.getTransport();
                transport.connect(HOST, PORT, getFrom(), PASSWORD);

                // wysłanie wiadomości
                transport.sendMessage(message, message
                        .getRecipients(Message.RecipientType.TO));
                transport.close();
            }
        }

    private static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        if(!result){System.out.println("Bledny email");}
        return result;
    }




    public static void main(String[] args) {
        LinkedList<String> a = new LinkedList<String>();
        a.add("test@gmail.com");
        EmailMessage em = new EmailMessage.Builder("test@Fromgmail.com",a).setSubject("Test1").setContent("Test1").build();
        if(isValidEmailAddress(em.from)){
        try {
            em.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        }}
    }
}