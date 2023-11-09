package weatherwear.console;

import weatherwear.cloth.CurrentCloth;
import weatherwear.cloth.FutureCloth;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        int choice;
        String date;
        String codeIATA;

        Scanner scanner = new Scanner(System.in);
        CurrentCloth currentCloth = new CurrentCloth();
        FutureCloth futureCloth = new FutureCloth();

        do {
            System.out.println("WeatherWear.com");
            System.out.println("---------------");
            System.out.println("1. Recommend clothing for current location");
            System.out.println("2. Recommend clothing for future location");
            System.out.println("3. Exit");
            try {
                choice = scanner.nextInt();
            }
            catch (Exception e){
                System.out.println("Error, make sure to enter only a number. " + e.getMessage());

                //avoid infinite loop
                choice = 0;
                scanner.nextLine();

                continue;
            }

            System.out.println();

            switch (choice) {
                case 1:
                    try {
                        //Is the weather warm?
                        if(currentCloth.isCurrentlyWarm())
                            System.out.println("It is warm so you should wear light clothing.");
                        else
                            System.out.println("It is cold so you should wear warm clothing.");
                        //Is it raining?
                        if(currentCloth.isCurrentlyRainy())
                            System.out.println("It is currently raining so you do need an umbrella.");
                        else
                            System.out.println("It is not currently raining so you don't need an umbrella.");
                    } catch (IOException e) {
                        System.out.println("IO EXCEPTION caught. " + e.getMessage());
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted Exception caught. " + e.getMessage());
                    }
                    System.out.println();
                    break;

                case 2:
                    System.out.println("Enter the arrival date (not more than 10 days) [yyyy-mm-dd]");
                    scanner.nextLine();
                    date = scanner.nextLine();
                    System.out.println();

                    System.out.println("Enter the code IATA of the destination");
                    codeIATA = scanner.nextLine();
                    System.out.println();

                    try {
                        //will it be warm?
                        System.out.println("Debug: " + date);
                        if(futureCloth.isFutureWarm(date, codeIATA))
                            System.out.println("It will be warm so you should wear light clothing.");
                        else
                            System.out.println("It will be cold so you should wear warm clothing.");

                        //will it rain?
                        if(currentCloth.isCurrentlyRainy())
                            System.out.println("It will be raining so you do need an umbrella.");
                        else
                            System.out.println("It won't be raining so you don't need an umbrella.");
                    } catch (IOException e) {
                        System.out.println("IO EXCEPTION caught. " + e.getMessage());
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted Exception caught. " + e.getMessage());
                    }
                    catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    catch (DateTimeParseException e){
                        System.out.println("Error Date, check the format");
                    }
                    System.out.println();
                    break;

                case 3:
                    // exit if choice == value3
                    break;

                default:
                    System.out.println("Number not valid, enter a number between 1 and 3");
                    System.out.println();
                    break;
            }
        }while(choice != 3);
    }
}
