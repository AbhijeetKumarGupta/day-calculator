import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static boolean isLeap(int year) {
        if (year % 400 == 0) {
            return true;
        }

        if (year % 100 == 0) {
            return false;
        }

        return year % 4 == 0;
    }

    public static String getDayName(int dayNum) {
        String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        return days[dayNum];
    }

    public static String getMonthName(int month) {
        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        return months[month];
    }

    public static String convertDate(String dateString) {

        String[] date = dateString.split("/");

        int month = Integer.parseInt(date[0]);
        int day = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        int currentDay = 0;

        int[] lNoOfDay = { 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };

        int[] monthDaysNotLeap = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        int[] monthDaysLeap = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        if (month > 12 || month < 1) {
            return "Invalid Month!";
        } else if (day > monthDaysNotLeap[month - 1] || day < 1) {
            if (isLeap(year) && month == 2 && day == monthDaysLeap[1]) {
                // do nothing
            } else {
                return "Invalid Date!";
            }
        } else if (year > 2020 || year < 1800) {
            return "Invalid Year!";
        }

        year -= (month < 3) ? 1 : 0;

        currentDay = (year + year / 4 - year / 100 + year / 400 + lNoOfDay[month - 1] + day) % 7;

        if (currentDay == 0) {
            currentDay = 1;
        }

        return getDayName(currentDay - 1) + ", " + getMonthName(month - 1) + " " + year;
    }

    public static void main(String[] arg) throws IOException {
        String fileName;
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the name of the file with extension txt: ");
        fileName = scan.nextLine();

        File file = new File(fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String dateString;
        while ((dateString = br.readLine()) != null) {
            System.out.println(convertDate(dateString));
        }

        br.close();
        scan.close();
    }
}
