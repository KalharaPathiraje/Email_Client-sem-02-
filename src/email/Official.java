package email;
import java.io.Serializable;

public class Official extends recipient implements Serializable {
    private String designation;
    private final String states="official";

    public Official(String name, String email,String designation) {
        super(name, email);
        this.designation=designation;
    }
    public String getdesignation() {return this.designation;}
    public String getStates() {return states;}


    @Override
    public String getStatus() {
        return states;
    }

}