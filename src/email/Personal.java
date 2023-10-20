package email;
import java.io.Serializable;

public class Personal extends recipient implements Serializable{
    private String birthday;
    private String nickName;
    private final String status ="personal";
    public Personal(String name,String nickName, String email,String birthday) {
        super(name, email);
        this.birthday=birthday;
        this.nickName=nickName;

    }
    public String getStatus(){return this.status;}
    public String getBirthday(){return this.birthday;}
    public String getNickName(){return this.nickName;}
}
