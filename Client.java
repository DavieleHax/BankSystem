import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Client {
    private static final String FILE_DIRECTORY = "/home/davielehax/Files/";
    private static ClientManager clientManager = new ClientManager();
    private static Scanner esc;

    public void clientLogin() {
        esc = new Scanner(System.in);
        String yesNo = "No";
        int choice;
        do {
            System.out.println("\n+===================================+");
            System.out.println("|---------ISUFST BANK SYSTEM--------|");
            System.out.println("|-----WELCOME TO OUR BANK SYSTEM----|");
            System.out.println("+===================================+\n");
            System.out.println("+-----------------------------------+");
            System.out.println("|----|LOG IN YOUR CLIENT ACCOUNT|---|");
            System.out.println("+-----------------------------------+");
            System.out.print("\nEnter your username: ");
            String username = esc.nextLine();
            System.out.print("Enter your password: ");
            String password = esc.nextLine();
            try {
                File file = new File(FILE_DIRECTORY + username + ".txt");
                Scanner fileScanner = new Scanner(file);
                String clientUser = fileScanner.nextLine().split(": ")[1];
                String clientPass = fileScanner.nextLine().split(": ")[1];
                if (username.equals(clientUser) && password.equals(clientPass)) {
                    System.out.println("\nLOGGED IN SUCCESSFULLY");
                    while (true) {
                        System.out.println("\n+===================================+");
                        System.out.println("|---------ISUFST BANK SYSTEM--------|");
                        System.out.println("|-----WELCOME TO OUR BANK SYSTEM----|");
                        System.out.println("+===================================+\n");
                        System.out.println("+-----------------------------------+");
                        System.out.println("|--|WELCOME CLIENT|CHOOSE A TASK|---|");
                        System.out.println("+-----------------------------------+");
                        System.out.println("| [1] Check Balance                 |");
                        System.out.println("| [2] Withdraw                      |");
                        System.out.println("| [3] Deposit                       |");
                        System.out.println("| [4] Edit Password                 |");
                        System.out.println("| [5] View Profile                  |");
                        System.out.println("| [6] Edit Profile                  |");
                        System.out.println("| [7] Go Back To Previous Menu      |");
                        System.out.println("| [0] Exit System                   |");
                        System.out.println("+-----------------------------------+");
                        System.out.print(">>> ");
                        choice = esc.nextInt();
                        esc.nextLine();
                        switch (choice) {
                            case 1:
                                fileScanner = new Scanner(file);
                                while (fileScanner.hasNextLine()) {
                                    String line = fileScanner.nextLine();
                                    if (line.startsWith("Initial Balance : ")) {
                                        String initialBalance = line.split(": ")[1];
                                        System.out.println("\n+-----------------------------------+");
                                        System.out.println("HELLO, " + username.toUpperCase());
                                        System.out.println("Your current balance is: ".toUpperCase() + initialBalance);
                                        System.out.println("+-----------------------------------+");

                                        break;
                                    }
                                }
                                break;
                            case 2:
                                System.out.print("\nEnter the amount you want to withdraw: ");
                                double amount = esc.nextDouble();
                                esc.nextLine();
                                double minWithdrawal = 100;
                                fileScanner = new Scanner(file);
                                while (fileScanner.hasNextLine()) {
                                    String ln = fileScanner.nextLine();
                                    if (ln.startsWith("Initial Balance : ")) {
                                        String initialBalance = ln.split(": ")[1];
                                        double intBal = Double.parseDouble(initialBalance);
                                        if (amount < minWithdrawal) {
                                            System.out.println(
                                                    "\nWithdrawal failed! Please withdraw atleast : " + minWithdrawal);
                                        } else {
                                            if (intBal >= amount) {
                                                double newBal = intBal - amount;
                                                System.out.println("\n+-----------------------------------+");
                                                System.out.println("Withdraw Successful!");
                                                System.out.println("Your new balance is: " + newBal);
                                                System.out.println("+-----------------------------------+");

                                                try {
                                                    File tempFile = new File(FILE_DIRECTORY + "temp.txt");
                                                    BufferedReader reader = new BufferedReader(new FileReader(file));
                                                    BufferedWriter writer = new BufferedWriter(
                                                            new FileWriter(tempFile));
                                                    String line;
                                                    while ((line = reader.readLine()) != null) {
                                                        if (line.startsWith("Initial Balance : ")) {
                                                            writer.write("Initial Balance : " + newBal);
                                                        } else {
                                                            writer.write(line);
                                                        }
                                                        writer.newLine();
                                                    }
                                                    reader.close();
                                                    writer.close();
                                                    file.delete();
                                                    tempFile.renameTo(file);
                                                } catch (NumberFormatException i) {
                                                    System.out.println("Invalid input! Please enter a valid amount.");
                                                } catch (IOException e) {
                                                    System.out.println("An error occurred!\n");
                                                }
                                            } else {
                                                System.out.println("Insufficient Balance!");
                                            }
                                            break;
                                        }
                                    }
                                }
                                break;
                            case 3:
                                System.out.print("\nEnter the amount you want to deposit: ");
                                double depositAmount = esc.nextDouble();
                                esc.nextLine();
                                double minDeposit = 100;
                                fileScanner = new Scanner(file);
                                while (fileScanner.hasNextLine()) {
                                    String line = fileScanner.nextLine();
                                    if (line.startsWith("Initial Balance : ")) {
                                        String initialBalance = line.split(" : ")[1];
                                        double intBal = Double.parseDouble(initialBalance);
                                        if (depositAmount > minDeposit) {
                                            double newBal = intBal + depositAmount;
                                            System.out.println("\n+-----------------------------------+");
                                            System.out.println("Deposit Successful!");
                                            System.out.println("Your new balance is: " + newBal);
                                            System.out.println("+-----------------------------------+");

                                            try {
                                                File tempFile = new File(FILE_DIRECTORY + "temp.txt");
                                                BufferedReader reader = new BufferedReader(new FileReader(file));
                                                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                                                String tempLine;
                                                while ((tempLine = reader.readLine()) != null) {
                                                    if (tempLine.startsWith("Initial Balance : ")) {
                                                        writer.write("Initial Balance : " + newBal);
                                                    } else {
                                                        writer.write(tempLine);
                                                    }
                                                    writer.newLine();
                                                }
                                                reader.close();
                                                writer.close();
                                                file.delete();
                                                tempFile.renameTo(file);
                                            } catch (NumberFormatException i) {
                                                System.out.println("Invalid input! Please enter a valid amount.");
                                            } catch (IOException e) {
                                                System.out.println("An error occurred!\n");
                                            }
                                        } else {
                                            System.out.println("\nPlease deposit at least : " + minDeposit);
                                            break;
                                        }
                                        break;
                                    }
                                }
                                break;
                            case 4:
                                System.out.print("\nEnter your new password: ");
                                String newClientPass = esc.nextLine();
                                try {
                                    File tempFile = new File(FILE_DIRECTORY + "temp.txt");
                                    BufferedReader reader = new BufferedReader(new FileReader(file));
                                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                                    String line;
                                    while ((line = reader.readLine()) != null) {
                                        if (line.startsWith("Password : ")) {
                                            writer.write("Password : " + newClientPass);
                                        } else {
                                            writer.write(line);
                                        }
                                        writer.newLine();
                                    }
                                    reader.close();
                                    writer.close();
                                    file.delete();
                                    tempFile.renameTo(file);
                                    System.out.println("\nPassword updated successfully!");
                                    System.out.println("Your new password is : " + newClientPass);
                                } catch (IOException e) {
                                    System.out.println("\nAn error occurred!\n");
                                }
                                break;
                            case 5:
                                fileScanner = new Scanner(file);
                                while (fileScanner.hasNextLine()) {
                                    String line = fileScanner.nextLine();
                                    if (line.startsWith("Last Name") || line.startsWith("First Name") ||
                                            line.startsWith("Middle Name") || line.startsWith("Address") ||
                                            line.startsWith("Contact Number")) {
                                        System.out.println("\n+-----------------------------------+");
                                        System.out.println(line);
                                        System.out.println("+-----------------------------------+");
                                    }
                                }
                                break;
                            case 6:
                                try {
                                    File tempFile = new File(FILE_DIRECTORY + "temp.txt");
                                    BufferedReader reader = new BufferedReader(new FileReader(file));
                                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                                    String tempLine;
                                    while ((tempLine = reader.readLine()) != null) {
                                        if (tempLine.startsWith("Last Name : ")) {
                                            System.out.println("\nCurrent last name: " + tempLine.split(": ")[1]);
                                            System.out.print("Do you want to edit the last name? (Yes/No): ");
                                            String editChoice = esc.nextLine();
                                            if (editChoice.equalsIgnoreCase("Yes")
                                                    || editChoice.equalsIgnoreCase("Y")) {
                                                System.out.print("Enter your new last name: ");
                                                String lastName = esc.nextLine();
                                                clientManager.setLastName(lastName);
                                                writer.write("Last Name : " + clientManager.getLastName());
                                            } else {
                                                writer.write(tempLine);
                                            }
                                        } else if (tempLine.startsWith("First Name : ")) {
                                            System.out.println("\nCurrent first name: " + tempLine.split(": ")[1]);
                                            System.out.print("Do you want to edit the first name? (Yes/No): ");
                                            String editChoice = esc.nextLine();
                                            if (editChoice.equalsIgnoreCase("Yes")
                                                    || editChoice.equalsIgnoreCase("Y")) {
                                                System.out.print("Enter your new first name: ");
                                                String firstName = esc.nextLine();
                                                clientManager.setFirstName(firstName);
                                                writer.write("First Name : " + clientManager.getFirstName());
                                            } else {
                                                writer.write(tempLine);
                                            }
                                        } else if (tempLine.startsWith("Middle Name : ")) {
                                            System.out.println("\nCurrent middle name: " + tempLine.split(": ")[1]);
                                            System.out.print("Do you want to edit the middle name? (Yes/No): ");
                                            String editChoice = esc.nextLine();
                                            if (editChoice.equalsIgnoreCase("Yes")
                                                    || editChoice.equalsIgnoreCase("Y")) {
                                                System.out.print("Enter your new middle name: ");
                                                String middleName = esc.nextLine();
                                                clientManager.setMiddleName(middleName);
                                                writer.write("Middle Name : " + clientManager.getMiddleName());
                                            } else {
                                                writer.write(tempLine);
                                            }
                                        } else if (tempLine.startsWith("Address : ")) {
                                            System.out.println("\nCurrent address: " + tempLine.split(": ")[1]);
                                            System.out.print("Do you want to edit the address? (Yes/No): ");
                                            String editChoice = esc.nextLine();
                                            if (editChoice.equalsIgnoreCase("Yes")
                                                    || editChoice.equalsIgnoreCase("Y")) {
                                                System.out.print("Enter your new address: ");
                                                String newAddress = esc.nextLine();
                                                clientManager.setAddress(newAddress);
                                                writer.write("Address : " + clientManager.getAddress());
                                            } else {
                                                writer.write(tempLine);
                                            }
                                        } else if (tempLine.startsWith("Contact Number : ")) {
                                            System.out.println("\nCurrent contact number: " + tempLine.split(": ")[1]);
                                            System.out.print("Do you want to edit the contact number? (Yes/No): ");
                                            String editChoice = esc.nextLine();
                                            if (editChoice.equalsIgnoreCase("Yes")
                                                    || editChoice.equalsIgnoreCase("Y")) {
                                                System.out.print("Enter your new contact number: ");
                                                long newContactNumber = esc.nextLong();
                                                clientManager.setContactNumber(newContactNumber);
                                                writer.write("Contact Number : " + clientManager.getContactNumber());
                                            } else {
                                                writer.write(tempLine);
                                            }
                                        } else {
                                            writer.write(tempLine);
                                        }
                                        writer.newLine();
                                    }
                                    reader.close();
                                    writer.close();
                                    file.delete();
                                    tempFile.renameTo(file);
                                    System.out.println("\nProfile updated successfully!");
                                } catch (FileNotFoundException i) {
                                    System.out.println("File not found! Make sure the file exists.");
                                } catch (IOException e) {
                                    System.out.println("An error occurred!\n");
                                }
                                break;
                            case 7:
                                return;
                            case 0:
                                System.out.println(
                                        "\nThank you for using our system.\nCome back next time!\nExiting System...");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("\nSelect from the list only!\n");
                                break;
                        }
                    }
                } else {
                    System.out.print("\nInvalid password!\nDo you want to try again? (Yes/No): ");
                    yesNo = esc.nextLine();
                }
                fileScanner.close();
            } catch (FileNotFoundException e) {
                System.out.println(
                        "\nInvalid username or You don't have an account yet.\nPlease contact the administrator!\n");
            }
        } while (yesNo.equalsIgnoreCase("Yes") || yesNo.equalsIgnoreCase("Y"));
    }
}

class ClientManager {
    private String userClient;

    public String getUserClient() {
        return userClient;
    }

    public void setUserClient(String newUserClient) {
        this.userClient = newUserClient;
    }

    private String passClient;

    public String getPassClient() {
        return passClient;
    }

    public void setPassClient(String newPassClient) {
        this.passClient = newPassClient;
    }

    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    private String middleName;

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String newMiddleName) {
        this.middleName = newMiddleName;
    }

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    private long contactNumber;

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long newContactNumber) {
        this.contactNumber = newContactNumber;
    }

    private int initialBalance;

    public int getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(int newInitialBalance) {
        this.initialBalance = newInitialBalance;
    }

    public ClientManager() {
        this.userClient = "";
        this.passClient = "";
        this.lastName = "";
        this.firstName = "";
        this.middleName = "";
        this.address = "";
        this.contactNumber = 0;
        this.initialBalance = 0;
    }

    public ClientManager(String userClient, String passClient, String lastName, String firstName, String middleName,
            String address, long contactNumber, int initialBalance) {
        this.userClient = userClient;
        this.passClient = passClient;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.address = address;
        this.contactNumber = contactNumber;
        this.initialBalance = initialBalance;
    }

    public String toString() {
        return "Username: " + getUserClient() +
                "\nPassword: " + getPassClient() +
                "\nLast Name: " + getLastName() +
                "\nFirst Name: " + getFirstName() +
                "\nMiddle Name: " + getMiddleName() +
                "\nAddress: " + getAddress() +
                "\nContact Number: " + getContactNumber() +
                "\nInitial Balance: " + getInitialBalance();
    }
}