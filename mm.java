//INDEX NO: 200447E
//NAME:PATHIRAJE PMHK
//imported lib
//INDEX NO :200447E
//NAME:PATHIRAJE P.M.H.K
//IMPORTED FIKES
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;


abstract class recipient implements Serializable {
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

class Official extends recipient implements Serializable {
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
class Office_friend extends recipient implements Serializable{
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
class Personal extends recipient implements Serializable{
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
class EmailObj implements Serializable{
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
class EmailFile {
    // object of File class
    private static File fe = new File("EmailList.scr");

    // Method 1
    // To read from the file
    public static ArrayList<EmailObj>  readFile()
    {
        ArrayList<EmailObj> reci = new ArrayList<EmailObj>();
        // Try block to check for exceptions
        try {

            // Creating new file using File object above
            fe.createNewFile();
        }

        // Catch block to handle the exception
        catch (Exception e) {
        }

        // If the file is empty
        if (fe.length() != 0) {

            try {

                // If file doesn't exists
                FileInputStream fis = null;

                fis = new FileInputStream("EmailList.scr");
                ObjectInputStream ois= new ObjectInputStream(fis);

                EmailObj c = null;

                while (fis.available() != 0) {
                    c = (EmailObj) ois.readObject();
                    reci.add(c);
                }
                ois.close();
                fis.close();
            }

            catch (Exception e) {

                System.out.println("Error Occurred" + e);

                e.printStackTrace();
            }
        }
        return reci;
    }

    // Method 2
    // To add a new customer
    public static void AddNewCustomer(EmailObj c)
    {

        // If customer is not present
        if (c != null) {
            // try block to check for exception
            try {

                // Initially assigning the object null to
                // avoid GC involvement
                FileOutputStream fos = null;

                // Creating an new FileOutputStream object
                fos = new FileOutputStream("EmailList.scr", true);

                // If there is nothing to be write onto file
                if (f.length() == 0) {
                    ObjectOutputStream oos= new ObjectOutputStream(fos);
                    oos.writeObject(c);
                    oos.close();
                }

                // There is content in file to be write on
                else {

                    MyObjectOutputStream oos = null;
                    oos = new MyObjectOutputStream(fos);
                    oos.writeObject(c);

                    oos.close();
                }
                fos.close();
            }
            catch (Exception e) {
                System.out.println("Error Occurred" + e);
            }
        }
    }
}
class MyObjectOutputStream extends ObjectOutputStream {

    MyObjectOutputStream() throws IOException
    {
        super();
    }

    MyObjectOutputStream(OutputStream o) throws IOException
    {
        super(o);
    }

    // Method of this class
    public void writeStreamHeader() throws IOException
    {
        return;
    }
}
class save {


    private static File f = new File("ClientList.src");

    public static ArrayList<recipient>  readFile()
    {
        ArrayList<recipient> reci = new ArrayList<recipient>();

        try {

            fn.createNewFile();
        }

        catch (Exception e) {
        }
        if (fn.length() != 0) {

            try {
                FileInputStream fis = null;

                fis = new FileInputStream("ClientList.src");
                ObjectInputStream ois= new ObjectInputStream(fis);

                recipient c = null;

                while (fis.available() != 0) {
                    c = (recipient)ois.readObject();
                    reci.add(c);
                }

                ois.close();
                fis.close();
            }

            catch (Exception e) {

                System.out.println("Error Occurred" + e);

                e.printStackTrace();
            }
        }
        return reci;
    }
    public static void AddNewCustomer(recipient c)
    {
        if (c != null) {

            try {
                FileOutputStream fos = null;

                fos = new FileOutputStream("ClientList.src", true);


                if (fn.length() == 0) {
                    ObjectOutputStream oos= new ObjectOutputStream(fos);
                    oos.writeObject(c);
                    oos.close();
                }
                else {

                    MyObjectOutputStream oos = null;
                    oos = new MyObjectOutputStream(fos);
                    oos.writeObject(c);

                    oos.close();
                }

                fos.close();
            }

            catch (Exception e) {

                System.out.println("Error Occurred" + e);
            }
        }
    }
}
class email implements Serializable {
    private String toEmail;
    private String subject;
    private String content;
    private String date;
    private String time;
    public void setDate(){
        LocalDate Ldate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.date= formatter.format(Ldate);
    }
    public void setTime(){
        LocalTime t = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.time= formatter.format(t);
    }
    public void setContent(String x){this.content=x;}
    public String getTime(){
        return this.time;
    }
    public String getToEmail(){
        return this.toEmail;
    }
    public String getDate(){
        return this.date;
    }
    public String getSubject(){
        return this.subject;
    }
    public String getContent(){
        return  this.content;
    }
    public email(String toEmail, String subject, String content){
        this.toEmail=toEmail;this.subject=subject;this.content=content;
    }
    public static void send(String toEmail, String subject, String content){    // code to send an email
        System.out.println("please wait,E-mail is ready ");

        String[] arr=content.split("\\\\n",0);// add new line to content
        StringBuilder letter= new StringBuilder();
        for(String i :arr){
            letter.append(i).append("\n");
        }
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

            Transport.send(message);
            System.out.println("Email sent Successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

class Date {
    public static boolean sendmail(String date){
        LocalDate myObj = LocalDate.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedDate = myObj.format(myFormatObj);
        String[] arr=formattedDate.split("/",2);
        if (arr[1].equals(date))
            return true;
        else
            return false;
    }
    public static String exactdate(){
        LocalDate myObj = LocalDate.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedDate = myObj.format(myFormatObj);
        return formattedDate;
    }
}


public class EmailClient {
    public static ArrayList<recipient> recicopy=new ArrayList<recipient>();
    public static ArrayList<EmailObj> maillist=new ArrayList<EmailObj>();

    public static String checkname(String mail){
        for(recipient j:recicopy) {
            if (j.getemail().equals(mail))
                return j.getname();
        }
        return null;
    }
    public static boolean checkmail(String mail){
        for(recipient i:recicopy){
            if(i.getemail().equals(mail))
                return true;
        }
        return false;
    }
    public static void select_recipient(String inpu){
        String[] arr =inpu.split(":");
        String[] arr2=arr[1].split(",");
        switch(arr[0].toLowerCase()){
            case "official":
                recipient name = new Official(arr2[0],arr2[1],arr2[2]);
                save.AddNewCustomer(name);
                recicopy.clear();
                recicopy=save.readFile();
                break;
            case "official_friend" :
                recipient name1 = new Office_friend(arr2[0],arr2[1],arr2[2],arr2[3]);
                save.AddNewCustomer(name1);
                recicopy.clear();
                recicopy=save.readFile();
                break;
            case "personal" :
                recipient name2 = new Personal(arr2[0],arr2[1],arr2[2],arr2[3]);
                save.AddNewCustomer(name2);
                recicopy.clear();
                recicopy=save.readFile();
                break;
            default :
                System.out.println("Invalid input.....");
                break;
        }
    }
    public static void main(String[] args) {
        int flag=0;
        recicopy=save.readFile();
        while(flag==0){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter option type: \n"
                    + "1 - Adding a new recipient\n"
                    + "2 - Sending an email\n"
                    + "3 - Printing out all the recipients who have birthdays\n"
                    + "4 - Printing out details of all the emails sent\n"
                    + "5 - Printing out the number of recipient objects in the application\n"
                    +"6- Exit..");

            int option = scanner.nextInt();

            switch(option){
                case 1:
                    System.out.println("input should be like this -Official: nimal,nimal@gmail.com,ceo");
                    System.out.println("official_friend:darshani,darshaniweerakkody@gmail.com,1966/08/12,clark");
                    System.out.println("personal:kalhara,kala,kalhara.20@cse.mrt.ac.lk,2000/07/29");
                    System.out.println("personal:peshala,sepala,peshala1999@gmail.com,2344/07/29");
                    System.out.print("Enter a mail :");
                    Scanner scanner1 = new Scanner(System.in);
                    String input=scanner1.nextLine();
                    select_recipient(input);
                    break;
                case 2:
                    System.out.println("input should be like this -email, subject, content");
                    System.out.print("Enter a mail :");
                    Scanner scanner3 = new Scanner(System.in);
                    String mail=scanner3.nextLine();
                    String[] spl=mail.split(",");
                    if(checkmail(spl[0])){
                        EmailObj mal=new EmailObj(Date.exactdate(),checkname(spl[0]),spl[0],spl[1],spl[2]);
                        EmailFile.AddNewCustomer(mal);
                        email.send(spl[0],spl[1],spl[2]);
                    }
                    else
                        System.out.println("There isn't such a mail");
                    break;
                case 3:
                    System.out.println("input format - yyyy/MM/dd (ex: 2018/09/17)");
                    System.out.print("Enter a date :");
                    Scanner scanner4 = new Scanner(System.in);
                    String birthday=scanner4.nextLine();

                    String[] spl2=birthday.split("/",2);
                    for (recipient i:recicopy){
                        if (i.getStatus().equals("personal")){
                            Personal c=(Personal) i;
                            String[] arr=c.getBirthday().split("/",2);
                            if(arr[1].equals(spl2[1]))
                                System.out.println(c.getname());
                            if (Date.sendmail(arr[1])){
                                email.send(c.getemail(),"Happy Birthday","Hugs and love on your birthday\nKalhara");
                                EmailObj mal=new EmailObj(Date.exactdate(),c.getname(),c.getemail(),"Happy Birthday","Hugs and love on your birthday\nKalhara");
                                EmailFile.AddNewCustomer(mal);
                            }
                        }else if(i.getStatus().equals("office_friend")){
                            Office_friend c=(Office_friend) i;
                            String[] arr=c.getBirthday().split("/",2);
                            if(arr[1].equals(spl2[1]))
                                System.out.println(c.getname());
                            if (Date.sendmail(arr[1])){
                                email.send(c.getemail(),"Happy Birthday","Wish you a Happy Birthday\nKalhara");
                                EmailObj mal=new EmailObj(Date.exactdate(),c.getname(),c.getemail(),"Happy Birthday","Wish you a Happy Birthday\nKalhara");
                                EmailFile.AddNewCustomer(mal);
                            }

                        }
                    }
                    break;
                case 4:
                    maillist=EmailFile.readFile();
                    System.out.println("input format - yyyy/MM/dd (ex: 2018/09/17)");
                    System.out.print("Enter a date :");
                    Scanner scanner5 = new Scanner(System.in);
                    String date=scanner5.nextLine();
                    for (EmailObj i : maillist){
                        if (i.getDate().equals(date)){
                            System.out.println();
                            System.out.println("Name : "+i.getRecip());
                            System.out.println("Mail : "+i.getEmail());
                            System.out.println("Date : "+i.getDate());
                            System.out.println("Subject : "+i.getSubject());
                            System.out.println("Content : "+i.getContent());
                            System.out.println("--------------------------------------");
                        }
                    }
                    maillist.clear();
                    break;
                case 5:
                    System.out.println("There are "+recicopy.size()+ " number of recipent objects are in the application.");
                    break;
                case 6:
                    System.out.println("\tExiting......\n\tBye");
                    flag=1;
                    break;
                default:
                    flag=1;
                    break;
            }
        }
    }

}
