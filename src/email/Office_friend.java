package email;
import java.io.Serializable;
import java.security.PublicKey;

public class Office_friend extends recipient implements Serializable{
    private String birthday;
    private String designation;
    private final String status="office_friend";
    public Office_friend(String name, String email,String birthday,String designation) {
        super(name,email);
        this.birthday = birthday;
        this.designation=designation;
    }
    public String getBirthday(){return  this.birthday;}
    public String getStatus(){return status;}
    public String getDesignation() {return designation;}
}
