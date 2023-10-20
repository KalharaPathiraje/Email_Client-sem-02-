package email;
import java.io.Serializable;

public class EmailObj implements Serializable{
    private String email;
    private String subject;
    private String content;
    private String date;
    private String recip;

    public EmailObj(String date,String recip,String email,String subject,String content){
        this.email=email;
        this.subject=subject;
        this.content=content;
        this.date=date;
        this.recip=recip;
    }
    public String getContent() {return content;}

    public String getEmail() {return email;}

    public String getDate() {return date;}

    public String getSubject() {return subject;}

    public String getRecip() {return recip;}
}
