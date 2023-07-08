public class ClientManager {
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