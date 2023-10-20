package email;

import java.io.Serializable;

public abstract class recipient implements Serializable {
    private String name;
    private String email;

    public recipient(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public abstract  String getStatus();
    public String getname(){return name;}
    public String getemail() {return email;}

}
