# Email_Client-sem-02-
Developed an email client using Java language to send birthday wishes automatically .

# Project Description

The email client has two types of recipients, official and personal. Some official recipients are close friends.

Details of the recipient list should be stored in a text file. An official recipient’s record in the text file has the following format: official: , ,. A sample record for official recipients in the text file looks as follows:

Official: nimal,nimal@gmail.com,ceo

A sample record for official friends in the text file looks as follows (last value is the recipient's birthday):

Office_friend: kamal,kamal@gmail.com,clerk,2000/12/12

A sample record for personal recipients in the text file looks as follows (last value is the recipient's birthday):

Personal: sunil,,sunil@gmail.com,2000/10/10

The user should be given the option to update this text file, i.e. the user should be able to add a new recipient through command-line, and these details should be added to the text file. [file handling will be covered in the 11th week]

When the email client is running, an object for each email recipient should be maintained in the application. For this, you will have to load the recipient details from the text file into the application. For each recipient having a birthday, a birthday greeting should be sent on the correct day. Official friends and personal recipients should be sent different messages (e.g. Wish you a Happy Birthday. for an office friend, and hugs and love on your birthday. for personal recipients). But all personal recipients receive the same message, and all office friends should receive the same message. A list of recipients to whom a birthday greeting should be sent is maintained in the application, when it is running. When the email client is started, it should traverse this list, and send a greeting email to anyone having their birthday on that day.

The system should be able to keep a count of the recipient objects. Use static members to keep this count.

All the emails sent out by the email client should be saved into the hard disk, in the form of objects – object serialization can be used for this. The user should be able to retrieve information of all the mails sent on a particular day by using a command-line option. [object serialization will be covered in the 11th week]

You only have to send out messages. No need to implement the logic to receive messages.

Command-line options should be available for:

Adding a new recipient Sending an email Printing out all the names of recipients who have their birthday set to current date Printing out details (subject and recipient) of all the emails sent on a date specified by user input Printing out the number of recipient objects in the application You may use the code given in this link to implement the basic functionality of the mail client (But it is perfectly ok to use a different code as well). In the given code, note that it imports the javax.mail package. This package is included in the javax.mail.jar, which can be downloaded from here.

You are given marks for the

Correct implementation of the mail sending functions (i.e. sending a birthday greeting, sending an email based on the instructions given through command-line, ability to serialize email objects, etc). Correct use of OOP principles Use of coding best practices Use the given Email_Client.java file as the starting point.

Save the recipient data into clientList.txt.

# Prerequisites
java IDEA- [IntelliJ IDEA ]Java 17SE(https://www.google.com/search?kgmid=/m/03v0mn&hl=en-LK&q=IntelliJ+IDEA&kgs=7722d18efb67c316&shndl=0&source=sh/x/kp/m1/1)

Getting Started

# startup code

```
// your index number

//import libraries

public class Email_Client {

      public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter option type: \n"
                  + "1 - Adding a new recipient\n"
                  + "2 - Sending an email\n"
                  + "3 - Printing out all the recipients who have birthdays\n"
                  + "4 - Printing out details of all the emails sent\n"
                  + "5 - Printing out the number of recipient objects in the application");

            int option = scanner.nextInt();

            switch(option){
                  case 1:
                      // input format - Official: nimal,nimal@gmail.com,ceo
                      // Use a single input to get all the details of a recipient
                      // code to add a new recipient
                      // store details in clientList.txt file
                      // Hint: use methods for reading and writing files
                      break;
                  case 2:
                      // input format - email, subject, content
                      // code to send an email
                      break;
                  case 3:
                      // input format - yyyy/MM/dd (ex: 2018/09/17)
                      // code to print recipients who have birthdays on the given date
                      break;
                  case 4:
                      // input format - yyyy/MM/dd (ex: 2018/09/17)
                      // code to print the details of all the emails sent on the input date
                      break;
                  case 5:
                      // code to print the number of recipient objects in the application
                      break;

            }

            // start email client
            // code to create objects for each recipient in clientList.txt
            // use necessary variables, methods and classes

        }
}

// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)
```
