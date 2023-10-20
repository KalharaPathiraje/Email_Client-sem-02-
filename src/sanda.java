/// your index number - 200607V
// Name - SIRIWARDANA H B K S
//import libraries

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

class AddEmailDetails {
    private static final File f = new File("email.ser"); //--------------------
    public AddEmailDetails(){
    }

    public static void addDetails(Email c) {
        if (c != null) {
            try {
                FileOutputStream fos;
                fos = new FileOutputStream("email.ser", true);//-------------------------------------

                if (f.length() == 0) {
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(c);
                    oos.close();
                }
                else {

                    EmailObjectOutputStream oos;
                    oos = new EmailObjectOutputStream(fos);
                    oos.writeObject(c);

                    oos.close();
                }

                fos.close();
                System.out.println("update email.ser");
            }


            catch (Exception e) {


                System.out.println("Error Occurred  in adding -" + e);
            }


        }

    }
    public static void readDetailsFile(String d)
    {


        try {


            f.createNewFile();
        }


        catch (Exception ignored) {
        }


        if (f.length() != 0) {

            try {


                FileInputStream fis;

                fis = new FileInputStream("email.ser");//------------------------------
                ObjectInputStream ois = new ObjectInputStream(fis);

                Email c;
                boolean haveEmail=false;
                while (fis.available() != 0) {
                    c = (Email) ois.readObject();
                    String Curntdate= c.getDate();

                    //------------------------------------------print letter
                    if(Curntdate.equals(d)){
                        haveEmail=true;
                        System.out.println("To :"+c.getToEmail());
                        System.out.println("At :"+c.getTime());
                        System.out.println("Subject :"+c.getSubject());
                        System.out.println("Content :");
                        System.out.println(c.getContent());
                        System.out.println("--------------------------------------------");

                    }
                }

                if(!haveEmail){
                    System.out.println("No emails sent on "+d);
                }
                ois.close();
                fis.close();


            }


            catch (Exception e) {


                System.out.println("Error Occurred" + e);


                e.printStackTrace();
            }
        }
    }

}

class BirthdayGreeting {
    private static final File f = new File("clientList.txt");
    public static void checkBirthDay(String d)
    {


        try {


            f.createNewFile();
        }


        catch (Exception ignored) {
        }


        if (f.length() != 0) {
            try {


                FileInputStream fis;

                fis = new FileInputStream("clientList.txt");//------------------------------
                ObjectInputStream ois = new ObjectInputStream(fis);

                Recipients c;
                boolean haveEmail=false;
                while (fis.available() != 0) {
                    c = (Recipients) ois.readObject();
                    String b= c.getStatus();

                    String C_date;
                    LocalDate Ldate = LocalDate.now(); // get current date
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    C_date= formatter.format(Ldate);
                    String[] arrOfStr = C_date.split("/", 2);
                    String currentMD=arrOfStr[1];
                    String[] arrOfStrInp = d.split("/", 2);
                    String inputDM=arrOfStrInp[1];
                    //------------------------------------------print letter
                    if(b.equals("Office_friend")){
                        Office_friendRecipients mail=(Office_friendRecipients)c;
                        String[] arr = mail.getBirthDay().split("/", 2);
                        String DM=arr[1];

                        if(DM.equals(inputDM)){
                            haveEmail=true;
                            System.out.println("Office friend who celebrate their birth day that day "+c.getName());
                            if(currentMD.equals(inputDM) && C_date.equals(d)){
                                mail.birthdayGreeting(c.getEmail(),"Birthday Wish","Wish you a Happy Birthday.\n       ~~ Gotabaya Rajapakshe ~~ ");
                            }
                        }

                    }
                    if(b.equals("Personal")){
                        PersonalRecipients mail1=(PersonalRecipients) c;//---------------------------------------------
                        String[] arr1 = mail1.getBirthDay().split("/", 2);
                        String DM1=arr1[1];

                        if(DM1.equals(inputDM)){
                            haveEmail=true;
                            System.out.println("Personal friend who celebrate their birth day that day "+c.getName());
                            if(currentMD.equals(inputDM) && C_date.equals(d)){
                                mail1.birthdayGreeting(c.getEmail(),"Birthday Wish","Hugs and love on your birthday.\n      ~~ Gotabaya Rajapakshe ~~ ");
                            }
                        }

                    }
                }

                if(!haveEmail){
                    System.out.println("No any birthday in "+d);
                }
                ois.close();
                fis.close();


            }


            catch (Exception e) {


                System.out.println("Error Occurred in send wish" + e);


                e.printStackTrace();
            }
        }
    }

}

class Email implements Serializable {
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
        LocalTime t = LocalTime.now(); // Gets the current time
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
    public Email(String toEmail, String subject, String content){
        this.toEmail=toEmail;this.subject=subject;this.content=content;
    }
    public void send(String toEmail, String subject, String content){    // code to send an email
        System.out.println("please wait,E-mail is ready ");

        String[] arr=content.split("\\\\n",0);// add new line to content
        StringBuilder letter= new StringBuilder();
        for(String i :arr){
            letter.append(i).append("\n");
        }
        setContent(letter.toString());
        setDate();
        setTime();
        final String username = "csetest607v@gmail.com";
        final String password = "gessvsjnnmiwvyap";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);}});

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(letter.toString());
            try {
                Transport.send(message);
                AddEmailDetails.addDetails(this);// add details to text file
                System.out.println("Email sent Successfully.");
            }
            catch (Exception e){
                System.out.println(toEmail+" - Is Invalid Addresses");
                System.out.println("The email address that you have saved is NOT VALID.");
            }



        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}

class EmailCollection {
    private static final File f = new File("clientList.txt");
    public static void AddNewRecipiet(Recipients c)
    {

        if (c != null) {
            try {
                FileOutputStream fos;
                fos = new FileOutputStream("clientList.txt", true);//-------------------------------------

                if (f.length() == 0) {
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(c);
                    oos.close();
                }
                else {

                    EmailObjectOutputStream oos;
                    oos = new EmailObjectOutputStream(fos);
                    oos.writeObject(c);
                    oos.close();
                }

                fos.close();
                System.out.println("Saved successfully...");
                System.out.println("update clientList.txt");
            }


            catch (Exception e) {


                System.out.println("Error Occurred  in adding" + e);
            }


        }

    }
}

class EmailObjectOutputStream extends ObjectOutputStream {

    EmailObjectOutputStream() throws IOException
    {

        // Super keyword refers to parent class instance
        super();
    }


    public EmailObjectOutputStream(OutputStream o) throws IOException
    {
        super(o);
    }

    // Method of this class
    public void writeStreamHeader() throws IOException
    {
        return;
    }
}

class Office_friendRecipients extends Recipients implements Serializable {
    private String name;
    private String email;
    private String designation;
    private String birthDay;
    private final String status="Office_friend";

    public Office_friendRecipients(String name,String email,String designation){
        this.name=name;
        this.email=email;
        this.designation=designation;
    }
    @Override
    public void setName(String name) {this.name=name;}
    @Override
    public String getName() {return this.name;}
    @Override
    public void setEmail(String email) {this.email=email;}
    @Override
    public String getEmail() {return this.email;}
    @Override
    public void makeRecipien() {EmailCollection.AddNewRecipiet(this);}//---------------------------------------------
    public void setDesignation(String designation){this.designation=designation;}
    public String getDesignation(){
        return this.designation;
    }
    public void setBirthDay(String w){this.birthDay=w;}
    public String getBirthDay(){return this.birthDay;}
    public String getStatus(){return this.status;}
    public void birthdayGreeting(String toEmail,String subject,String content){
        System.out.println("Today have office friend Birth Day,wait for sending birth day Greetings ");
        Email birthdayWish=new Email(toEmail, subject, content);
        birthdayWish.send(toEmail, subject, content);
    }

}

class Office_friendRecipientsController extends RecipientsController {

    @Override
    public Recipients RecipientsFactory(String x, String y, String z) {
        return new Office_friendRecipients(x,y,z);
    }
    public void makeRecipient(String name, String email, String designation,String birthDay){
        Recipients Recipt=RecipientsFactory(name, email, designation);
        Office_friendRecipients convet =(Office_friendRecipients)Recipt;
        convet.setBirthDay(birthDay);
        Recipt.makeRecipien();

    }
}

class OfficialRecipients extends Recipients implements Serializable{
    private String designation;
    private String name;
    private String email;
    private final String status="Official";

    public OfficialRecipients(String name, String email, String designation) {
        this.name=name;
        this.email=email;
        this.designation=designation;
    }

    public void setDesignation(String designation){this.designation=designation;}

    public String getStatus(){
        return this.status;
    }

    public String getDesignation(){
        return this.designation;
    }
    @Override
    public void setName(String name) {
        this.name=name;
    }
    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public void setEmail(String email) {
        this.email=email;
    }
    @Override
    public String getEmail() {
        return this.email;
    }
    @Override
    public void makeRecipien() {
        EmailCollection.AddNewRecipiet(this);
    } // serialize the recipe objects
}
class OfiicialRecipientsController extends RecipientsController {
    @Override
    public Recipients RecipientsFactory(String name,String email,String designation) {
        return new OfficialRecipients(name,email,designation);
    }

}

class PersonalRecipients extends Recipients implements Serializable {
    private String name;
    private String nickName;
    private String email;
    private String birthDay;
    private final String status="Personal";

    public PersonalRecipients(String name,String nickName,String email){
        this.name=name;
        this.email=email;
        this.nickName=nickName;
    }
    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setEmail(String email) {
        this.email=email;
    }

    @Override
    public String getEmail() {
        return this.email;
    }
    public void setNickName(String nickName){
        this.nickName=nickName;
    }
    public String getNickName(){
        return this.nickName;
    }
    @Override
    public void makeRecipien() {
        EmailCollection.AddNewRecipiet(this);
    }

    @Override
    public String getStatus() {
        return this.status;
    }

    public void setBirthDay(String birthDay){this.birthDay=birthDay;}

    public String getBirthDay(){return this.birthDay;}

    public void birthdayGreeting(String toEmail,String subject,String content){
        System.out.println("Today have one of personal friend Birth Day,wait for sending birth day Greetings ");
        Email birthdayWish=new Email(toEmail, subject, content);
        birthdayWish.send(toEmail, subject, content);
    }
}

class PersonalRecipientsController extends RecipientsController{
    @Override
    public Recipients RecipientsFactory(String name, String nikName, String email) {
        return new PersonalRecipients(name,nikName,email);
    }
    public void makeRecipient(String name, String nickName,String email,String birthDay){
        Recipients Recipt=RecipientsFactory(name, nickName, email);
        PersonalRecipients convet =(PersonalRecipients) Recipt;
        convet.setBirthDay(birthDay);
        Recipt.makeRecipien();
    }
}

abstract class Recipients {

    public abstract void setName(String name);
    public  abstract String getName();
    public abstract void setEmail(String email);
    public abstract String getEmail();
    public abstract void makeRecipien();
    public abstract String getStatus();
    public void sendEmail(String toEmail,String subject,String content){
        Email newOne=new Email(toEmail, subject, content); // make email
        newOne.send(toEmail,subject,content);
    }
}
abstract class RecipientsController {
    //private Recipients Recipe;
    // factory method
    public abstract Recipients RecipientsFactory(String name,String email,String designation);

    public void makeRecipient(String name, String email, String designation){
        Recipients Recipt=RecipientsFactory(name, email, designation);
        Recipt.makeRecipien();

    }
}
public class sanda {
    public static ArrayList<Recipients> objArr = new ArrayList<>();
    public static void readFile()
    {
        File f = new File("clientList.txt");
        try {
            f.createNewFile();
        }
        catch (Exception ignored) {
        }
        if (f.length() != 0) {

            try {
                FileInputStream fis;

                fis = new FileInputStream("clientList.txt");
                ObjectInputStream ois = new ObjectInputStream(fis);

                Recipients c;
                while (fis.available() != 0) {                  // check if already save or not
                    c = (Recipients) ois.readObject();
                    objArr.add(c);
                }
                ois.close();
                fis.close();
            }
            catch (Exception e) {
                System.out.println("Error Occurred in reading file" + e);
                e.printStackTrace();
            }
        }
    }
    static boolean Checkdate(String date){
        boolean OK=false;
        boolean dd=false;
        boolean mm=false;
        String[] arr= date.split("/");
        String[] D={"01","02","03","04","05","06","08","7","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String[] M={"01","02","03","04","05","06","08","7","09","10","11","12"};
        if(arr.length==3){
            for(String i:D){
                if(arr[2].equals(i)){
                    dd=true;
                    break;
                }
            }for(String i:M){
                if(arr[1].equals(i)){
                    mm=true;
                    break;
                }
            }if(mm && dd){
                return true;
            }
        }
        return OK;
    }
    static void addRecipt(String input){    // store details in clientList.txt file

        String[] arr= input.split(":", 2);
        String type=arr[0];
        boolean have=false;
        boolean valied=false;
        switch (type) {
            case "Official" : {

                String[] offarr1 = arr[1].split(",", 3);
                for(Recipients i:objArr){
                    if(i.getEmail().equals(offarr1[1])){
                        have=true;
                    }
                }
                String[] chcek1=offarr1[1].split("@");
                try {
                    if(chcek1[1].equals("gmail.com"))
                        valied=true;
                }
                catch (Exception e){

                }
                if(!have && valied ){
                    OfiicialRecipientsController newEmail = new OfiicialRecipientsController();
                    try {
                        newEmail.makeRecipient(offarr1[0], offarr1[1], offarr1[2]);
                    }
                    catch (Exception e){
                        System.out.println("Invalid input type.try again");
                    }
                }
                else if(have){
                    System.out.println("Already saved this email");
                }else if(!valied) {
                    System.out.println("Invalid gmail");
                    System.out.println("please enter an email address in the form - abcd@gmail.com");}
            }
            case "Office_friend" : {
                String[] offarr2 = arr[1].split(",", 4);
                for(Recipients i:objArr){
                    if(i.getEmail().equals(offarr2[1])){
                        have=true;
                    }
                }
                String[] chcek2=offarr2[1].split("@");
                try {
                    if(chcek2[1].equals("gmail.com"))
                        valied=true;
                }
                catch (Exception e){
                }
                boolean datetie=Checkdate(offarr2[3]);
                if(!have && valied && datetie){

                    Office_friendRecipientsController newOFFfriend = new Office_friendRecipientsController();
                    try{newOFFfriend.makeRecipient(offarr2[0], offarr2[1], offarr2[2], offarr2[3]);}
                    catch (Exception e){
                        System.out.println("Invalid input type.try again");
                    }
                }
                else if(have){
                    System.out.println("Already saved this email");
                }else if(!valied){
                    System.out.println("Invalid gmail");
                    System.out.println("please enter an email address in the form - abcd@gmail.com");}
                else if(!datetie)
                    System.out.println("Invalid date input");
            }
            case "Personal" : {
                String[] offarr3 = arr[1].split(",", 4);
                for(Recipients i:objArr){
                    if(i.getEmail().equals(offarr3[2])){
                        have=true;
                    }
                }
                String[] chcek3=offarr3[2].split("@");
                try {
                    if(chcek3[1].equals("gmail.com"))
                        valied=true;
                }
                catch (Exception e){
                }
                boolean datetie=Checkdate(offarr3[3]);
                if(!have && valied && datetie){
                    PersonalRecipientsController newPesonal = new PersonalRecipientsController();
                    try {
                        newPesonal.makeRecipient(offarr3[0], offarr3[1], offarr3[2], offarr3[3]);
                    }catch (Exception e){
                        System.out.println("Invalid input type.try again");
                    }
                }
                else if(have){
                    System.out.println("Already saved this email");
                }else if(!valied){
                    System.out.println("Invalid gmail");
                    System.out.println("please enter an email address in the form - abcd@gmail.com");
                }
                else if(!datetie)
                    System.out.println("Invalid date input");

            }
            default : System.out.println("Invalid receipt type");
        }
    }
    public static void proccesingEmail(String x, String y, String z){
        boolean save=false;
        objArr.clear();
        readFile();
        for (Recipients i:objArr){
            String temp=i.getEmail();
            if(temp.equals(x)) {
                save=true;
                i.sendEmail(x, y, z);
                break;
            }
        }
        if(!save){
            System.out.println("Invalid email, Check again");
        }
    }
    public static void main(String[] args) {
        boolean x=true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter option type: \n"
                + "1 - Adding a new recipient(Official,Office_friend,Personal)\n"
                + "2 - Sending an email\n"
                + "3 - Printing out all the recipients who have birthdays\n"
                + "4 - Printing out details of all the emails sent in given date\n"
                + "5 - Printing out the number of recipient objects in the application\n"
                + "6 - Exit");
        while (x){
            int option=0;
            readFile();
            try {
                int o = scanner.nextInt();
                option=o;
            }catch (Exception e){
                System.out.println("You must input integer");
                break;}
            switch(option){
                case 1:
                    System.out.println("***  input format  ***");
                    System.out.println("Official: nimal,nimal@gmail.com,ceo\n"+"Office_friend: kamal,kamal@gmail.com,clerk,2000/12/12\n"+"Personal: sunil,<nick-name>,sunil@gmail.com,2000/10/10");

                    System.out.print("Add new client :");
                    scanner.nextLine();
                    String inp1=scanner.nextLine();// input format - Official: nimal,nimal@gmail.com,ceo
                    addRecipt(inp1);
                    // Use a single input to get all the details of a recipient
                    // code to add a new recipient
                    // store details in clientList.txt file
                    // Hint: use methods for reading and writing files
                    break;
                case 2:
                    // input format - email, subject, content
                    System.out.println("input format - <email>, <subject>, <content>");
                    System.out.println("next line - \\n");
                    scanner.nextLine();
                    String inp2=scanner.nextLine();
                    String[] arrOfStr = inp2.split(",", 3);
                    while(true){
                        try {
                            proccesingEmail(arrOfStr[0], arrOfStr[1], arrOfStr[2]);
                        }
                        catch (Exception e){
                            System.out.println("Invalid input.Try again.");
                        }}
                    // code to send an email
//                    break;
                case 3:
                    scanner.nextLine();
                    System.out.print("Enter date in the format yyyy/MM/dd: ");
                    String dateStr1 = scanner.nextLine();
                    BirthdayGreeting.checkBirthDay(dateStr1);
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print recipients who have birthdays on the given date
                    break;
                case 4:// check mails
                    scanner.nextLine();
                    System.out.print("Enter date in the format yyyy/MM/dd: ");
                    String dateStr2 = scanner.nextLine();
                    AddEmailDetails.readDetailsFile(dateStr2);
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print the details of all the emails sent on the input date
                    break;
                case 5:
                    objArr.clear();
                    readFile();
                    System.out.println("total number of recipe save :"+objArr.size());
                    int count=1;
                    for(Recipients i:objArr){
                        System.out.println(count+"."+i.getStatus()+" - "+i.getName()+" - "+i.getEmail());
                        count++;
                    }
                    break;
                case 6:
                    x=false;
                    System.out.println("The program has executed successfully");
                    break;
//                default:
//                    System.out.println("Invalid input Try again");
            }
        }

        // start email client
        // code to create objects for each recipient in clientList.txt
        // use necessary variables, methods and classes

    }
}

// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)
