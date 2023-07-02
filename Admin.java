import java.util.*;
import java.io.*;

class Admin {
    private static final String FILE_DIRECTORY = "/home/davielehax/Files/";
    private static ArrayList<ClientManager> clients = new ArrayList<ClientManager>();
    private static Scanner esc;
    private String useradmin;
    private String passadmin;

    Admin(String useradmin, String passadmin) {
        this.useradmin = useradmin;
        this.passadmin = passadmin;
    }

    public String getUseradmin() {
        return useradmin;
    }

    public String getPassadmin() {
        return passadmin;
    }

    public void setPassadmin(String passadmin) {
        this.passadmin = passadmin;
    }

    public void adminLogin() {
        esc = new Scanner(System.in);
        String tryagain = "No";
        try {
            FileWriter writer = new FileWriter(FILE_DIRECTORY + "AdminCredentials.txt");
            writer.write("Username : " + getUseradmin() + '\n' + "Password : " + getPassadmin());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        do {
            System.out.println("\n+===================================+");
            System.out.println("|---------ISUFST BANK SYSTEM--------|");
            System.out.println("|-----WELCOME TO OUR BANK SYSTEM----|");
            System.out.println("+===================================+\n");
            System.out.println("+-----------------------------------+");
            System.out.println("|----|LOG IN YOUR ADMIN ACCOUNT|----|");
            System.out.println("+-----------------------------------+");
            System.out.print("Enter your username : ");
            String username = esc.nextLine();
            System.out.print("Enter your password : ");
            String password = esc.nextLine();
            if (username.equals(getUseradmin()) && password.equals(getPassadmin())) {
                System.out.println("\nLOGGED IN SUCCESSFULLY!");
                adminpage();
                break;
            } else {
                System.out.print("\nIncorrect username or password!\nDo you want to try again? (Yes/No) : ");
                tryagain = esc.nextLine();
            }
        } while (tryagain.equalsIgnoreCase("Yes") || tryagain.equalsIgnoreCase("y"));
    }

    public void adminpage() {
        esc = new Scanner(System.in);
        int choice;
        try {
            do {
                System.out.println("\n+===================================+");
                System.out.println("|---------ISUFST BANK SYSTEM--------|");
                System.out.println("|-----WELCOME TO OUR BANK SYSTEM----|");
                System.out.println("+===================================+\n");
                System.out.println("+-----------------------------------+");
                System.out.println("|---|WELCOME ADMIN|CHOOSE A TASK|---|");
                System.out.println("+-----------------------------------+");
                System.out.println("| [1] Add Client                    |");
                System.out.println("| [2] List Client                   |");
                System.out.println("| [3] Delete Client                 |");
                System.out.println("| [4] Change Admin Password         |");
                System.out.println("| [5] Go Back To Previous Menu      |");
                System.out.println("| [0] Exit System                   |");
                System.out.println("+-----------------------------------+");
                System.out.print(">>> ");
                choice = esc.nextInt();
                switch (choice) {
                    case 1:
                        addClients();
                        break;
                    case 2:
                        listClients();
                        break;
                    case 3:
                        deleteClient();
                        break;
                    case 4:
                        changeAdminPassword();
                        break;
                    case 5:
                        return;
                    case 0:
                        System.out.println("Thank you for using our system.\nComeback next time!\nExiting System...");
                        System.exit(0);
                        break;
                }
            } while (choice != 0);
        } catch (InputMismatchException i) {
            System.out.println("\nNOTICE : Input number only!\n");
        } catch (Exception e) {
            System.out.println("\nAn Error Occured!\n");
        }

    }

    public void addClients() {
        esc = new Scanner(System.in);
        System.out.println("\n+===================================+");
        System.out.println("|---------ISUFST BANK SYSTEM--------|");
        System.out.println("|-----WELCOME TO OUR BANK SYSTEM----|");
        System.out.println("+===================================+\n");
        System.out.println("-------------------------------------");
        System.out.println("|----------|ADD A CLIENT|-----------|");
        System.out.println("-------------------------------------");
        System.out.print("\nUsername : ");
        String username = esc.nextLine();
        boolean userExists = false;

        for (ClientManager client : clients) {
            if (client.getUserClient().equalsIgnoreCase(username)) {
                userExists = true;
                break;
            }
        }

        if (userExists) {
            System.out.println("\nUser already exists. Unable to add client.\n");
            
        } else {
            System.out.print("Password : ");
            String password = esc.nextLine();
            System.out.print("Last Name : ");
            String lastname = esc.nextLine();
            System.out.print("First Name : ");
            String firstname = esc.nextLine();
            System.out.print("Middle Name : ");
            String middlename = esc.nextLine();
            System.out.print("Address : ");
            String address = esc.nextLine();
            System.out.print("Contact Number : ");
            long contactnumber = esc.nextLong();
            System.out.print("Initial Balance : ");
            int initialbalance = esc.nextInt();
            ClientManager cl = new ClientManager(username, password, lastname, firstname, middlename, address,
                    contactnumber, initialbalance);
            clients.add(cl);
            System.out.println("\nClient Added Successfully");
            try {
                FileWriter fw = new FileWriter(FILE_DIRECTORY + username + ".txt");
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("Username : " + cl.getUserClient() + '\n');
                bw.write("Password : " + cl.getPassClient() + '\n');
                bw.write("Last Name : " + cl.getLastName() + '\n');
                bw.write("First Name : " + cl.getFirstName() + '\n');
                bw.write("Middle Name : " + cl.getMiddleName() + '\n');
                bw.write("Address : " + cl.getAddress() + '\n');
                bw.write("Contact Number : " + cl.getContactNumber() + '\n');
                bw.write("Initial Balance : " + cl.getInitialBalance() + '\n');
                bw.newLine();
                bw.close();
                fw.close();
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage() + "\n");
            }
        }
    }

    public void listClients() {
        if (clients.size() > 0) {
            for (int i = 0; i < clients.size(); i++) {
                System.out.println("\n+-----------------------------------+");
                System.out.println(clients.get(i).toString());
                System.out.println("+-----------------------------------+");
            }
        } else {
            System.out.println("\nNo clients found!");
        }
    }

    public void deleteClient() {
        esc = new Scanner(System.in);
        System.out.print("\nEnter the username you want to delete : ");
        String username = esc.nextLine();
        boolean clientFound = false;
        for (int i = 0; i < clients.size(); i++) {
            ClientManager client = clients.get(i);
            if (client.getUserClient().equals(username)) {
                clients.remove(i);
                System.out.println("\nClient with username " + username + " has been deleted.");
                File deleteFile = new File(FILE_DIRECTORY + client.getUserClient() + ".txt");
                deleteFile.delete();
                clientFound = true;
                break;
            }
        }
        if (!clientFound) {
            System.out.println("Client with username " + username + " was not found.");
        }
    }

    public void changeAdminPassword() {
        esc = new Scanner(System.in);
        System.out.print("Enter your current password: ");
        String currPass = esc.nextLine();
        if (currPass.equals(getPassadmin())) {
            System.out.print("Enter your new password: ");
            String newPass = esc.nextLine();
            setPassadmin(newPass);
            try {
                FileWriter writer = new FileWriter(FILE_DIRECTORY + "AdminCredentials.txt");
                writer.write("Username: " + getUseradmin() + "\nPassword: " + getPassadmin());
                writer.close();
                System.out.println("Password updated successfully!");
                System.out.println("Your password is now change to : " + getPassadmin());
            } catch (IOException e) {
                System.out.println("\nAn error occured!\n");
                e.printStackTrace();
            }
        } else {
            System.out.println("\nYour current password is incorrect!\n");
        }
    }
}