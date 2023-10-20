package email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class email implements Serializable {
    private String toEmail;
    private String subject;
    private String content;
    private String date;
    private String time;

    public email(String toEmail, String subject, String content){
        this.toEmail=toEmail;this.subject=subject;this.content=content;
    }
    public void setDate(){
        LocalDate Ldate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.date= formatter.format(Ldate);
    }
    public void setTime(){
        LocalTime t = LocalTime.now(); // Gets the current time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.time= formatter.format(t);
    }
    public void setContent(String x){this.content=x;}
    public String getTime(){return this.time;}
    public String getToEmail(){return this.toEmail;}
    public String getDate(){return this.date;}
    public String getSubject(){return this.subject;}
    public String getContent(){return  this.content;}


    public static void send(String toEmail, String subject, String content){    // code to send an email
        System.out.println("please wait,\nE-mail is ready ");

        String[] arr=content.split("\\\\n",0);// add new line to content
        StringBuilder letter= new StringBuilder();
        for(String i :arr){
            letter.append(i).append("\n");
        }
//        setContent(letter.toString());
//        setDate();
//        setTime();
        final String username = "kalharapathiraje@gmail.com";
        final String password = "lbjmgnzgguhzbvcx";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);}});

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(letter.toString());

            Transport.send(message);
//            AddEmailDetails.addDetails(this);// add details to text file


//            Transport.send(message);
            System.out.println("Email sent Successfully.");
            System.out.println();

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
