package email;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
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
