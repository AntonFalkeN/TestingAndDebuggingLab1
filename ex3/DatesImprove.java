/*
  Origin from http://pages.cs.wisc.edu/~vernon/cs367/tutorials/jdb.tutorial.html
  Modified by Jeff C.
*/

import java.io.*;

class Dates {

    /* Precondition: month is between 1 and 12, inclusive */
    /* Postcondition: returns the number of days in the given month */
    public static int daysInMonth (int month) {
        if ((month == 4) || (month == 9) || (month == 6) || (month == 11)) {
            return 30;
        }
        else if(month == 2){
            return 28;
        }
        else {
            return 31;
        }
    }

    public static int daysBetween(int firstMonth, int firstDay, int secondMonth, int secondDay){
        int i;

        /* Used to record what day in the year the first day  */
        /* of someMonth and laterMonth are. */
        int daysToFirstMonth = 0;
        int daysToSecondMonth = 0;

        for (i = 1; i < firstMonth; i = i + 1) {
            daysToFirstMonth = daysToFirstMonth + daysInMonth(i);
        }

        for (i = 1; i < secondMonth; i = i + 1) {
            daysToSecondMonth = daysToSecondMonth + daysInMonth(i);
        }

        /* The answer */
        int daysBetween = 0;

        int daysToDate1 = daysToFirstMonth + firstDay;
        int daysToDate2 = daysToSecondMonth + secondDay;

        if (daysToDate2 >= daysToDate1) {
            daysBetween = daysToDate2 - daysToDate1;
        }
        else {
            daysBetween = 365 - daysToDate1 + daysToDate2;
        }
        return daysBetween;
    }

    public static void main (String[] args) {
        int firstMonth, firstDay;
        int secondMonth, secondDay;

        firstMonth = Integer.parseInt(args[0]);
        firstDay = Integer.parseInt(args[1]);

        secondMonth = Integer.parseInt(args[2]);
        secondDay = Integer.parseInt(args[3]);

        /* Used to record what day in the year the first day  */
        /* of someMonth and laterMonth are. */

        System.out.println("The difference in days between " +
                firstMonth + "/" + firstDay + " and " +
                secondMonth + "/" + secondDay + " is: ");

        System.out.println(daysBetween(firstMonth,firstDay,secondMonth,secondDay));
    }
}
