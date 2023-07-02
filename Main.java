import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    private static Scanner esc;
    private static Admin admin;
    private static Client client;
    public static void main(String[] args) {
        esc = new Scanner(System.in);
        admin = new Admin("Admin", "admin123");
        client = new Client();
        int choice;
        try {
            do {
                System.out.println("\n+===================================+");
                System.out.println("|---------ISUFST BANK SYSTEM--------|");
                System.out.println("|-----WELCOME TO OUR BANK SYSTEM----|");
                System.out.println("+===================================+\n");
                System.out.println("+-----------------------------------+");
                System.out.println("|----------|CHOOSE A TASK|----------|");
                System.out.println("+-----------------------------------+");
                System.out.println("| [1] Admin                         |");
                System.out.println("| [2] Client                        |");
                System.out.println("| [0] Exit Sytem                    |");
                System.out.println("+-----------------------------------+");
                System.out.print(">>> ");
                choice = esc.nextInt();
                System.out.println("+-----------------------------------+");
                switch (choice) {
                    case 1:
                        admin.adminLogin();
                        break;
                    case 2:
                        client.clientLogin();
                        break;
                    case 0:
                        System.out.println("Thank you for using our system.\nComeback next time!\nExiting System...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nCHOOSE IN THE LIST ONLY!");
                }
            } while (choice != 0);
        } catch (InputMismatchException i) {
            System.out.println("\nNOTICE : Invalid input! Please enter a valid number.");
            main(args);
        } catch (Exception e) {
            System.out.println("\nAn Error Occured!\n");
        }
    }
}