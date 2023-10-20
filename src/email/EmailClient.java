package email;
//INDEX NO : 200447E
//NAME : PATHIRAJE P.M.H.K
//import libraries
import java.util.ArrayList;
import java.util.Scanner;

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
                    recipient name1=  new Office_friend(arr2[0],arr2[1],arr2[2],arr2[3]);
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

                        // input format - Official: nimal,nimal@gmail.com,ceo
                        // Use a single input to get all the details of a recipient
                        // code to add a new recipient
                        // store details in clientList.txt file
                        // Hint: use methods for reading and writing files
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
                        // input format - email, subject, content
                        // code to send an email
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
                        // input format - yyyy/MM/dd (ex: 2018/09/17)
                        // code to print recipients who have birthdays on the given date
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
                        // input format - yyyy/MM/dd (ex: 2018/09/17)
                        // code to print the details of all the emails sent on the input date
                        break;
                    case 5:
                        System.out.println("There are "+recicopy.size()+ " number of recipent objects are in the application.\n");
                        // code to print the number of recipient objects in the application
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


            // start email client
            // code to create objects for each recipient in clientList.txt
            // use necessary variables, methods and classes
        }

}

// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)


