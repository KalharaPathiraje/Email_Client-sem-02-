package email;

// Java program to illustrate CustomerCollection.java

// Importing input output classes
import java.io.*;
import java.util.ArrayList;

// Importing utility classes
//import java.util.*;

// Class 1
// helper class
class MyObjectOutputStream extends ObjectOutputStream {

        // Constructor of this class
        // 1. Default
        MyObjectOutputStream() throws IOException
        {

            // Super keyword refers to parent class instance
            super();
        }

        // Constructor of this class
    // 1. Parameterized constructor
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

// Class 2
// Helper class
public class save {

    // Getting file from local machine by creating
    // object of File class
    private static File f = new File("ClientList.src");

    // Method 1
    // To read from the file
    public static ArrayList<recipient>  readFile()
    {
        ArrayList<recipient> reci = new ArrayList<recipient>();
        // Try block to check for exceptions
        try {

            // Creating new file using File object above
            f.createNewFile();
        }

        // Catch block to handle the exception
        catch (Exception e) {
        }

        // If the file is empty
        if (f.length() != 0) {

            try {

                // If file doesn't exists
                FileInputStream fis = null;

                fis = new FileInputStream("ClientList.src");
                ObjectInputStream ois= new ObjectInputStream(fis);

                recipient c = null;

                while (fis.available() != 0) {
                    c = (recipient)ois.readObject();
                    //official_recipient re=(official_recipient)c;
//                    System.out.println(c.getemail());
                    reci.add(c);
                }

                // Closing the connection to release memory
                // resources using close() method
                ois.close();
                fis.close();
            }

            // Catch block to handle the exception
            catch (Exception e) {

                // Print the exception on the console
                // along with display message
                System.out.println("Error Occurred" + e);

                // Exception encountered line is also
                // displayed on console using the
                // printStackTrace() method
                e.printStackTrace();
            }
        }
        return reci;
    }

    // Method 2
    // To add a new customer
    public static void AddNewCustomer(recipient c)
    {

        // If customer is not present
        if (c != null) {
            // try block to check for exception
            try {

                // Initially assigning the object null to
                // avoid GC involvement
                FileOutputStream fos = null;

                // Creating an new FileOutputStream object
                fos = new FileOutputStream("ClientList.src", true);

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

                    // Closing the FileOutputStream object
                    // to release memory resources
                    oos.close();
                }

                // Closing the File class object to avoid
                // read-write
                fos.close();
            }

            // Catch block to handle the exceptions
            catch (Exception e) {

                // Print the exception along with the
                // display message
                System.out.println("Error Occurred" + e);
            }
        }
    }
}
