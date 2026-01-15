import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

public class IRCTCAPP {

    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final BookUserService bookUserService = new BookUserService();


    public static void main(String[] args) {
        new IRCTCAPP().start();
    }

    public void start(){
        while (true){
            System.out.println("----- Welcome to IRCTC App ------- ");

            if(!userService.isLoggedIn()){
                System.out.println("1. Register:");
                System.out.println("2. Login:");
                System.out.println("3. Exit:");
                System.out.println("Enter your choice:");

                int choice = scanner.nextInt();
                switch (choice){
                    case 1 -> register();
                    case 2 -> login();
                    case 3 -> exitApp();
                    default -> System.out.println("Invalid choice..");
                }
            }else{
                showUserMenu();
            }
        }
    }

    public void register(){
        System.out.println("Enter username: ");
        String username = scanner.next();
        System.out.println("Enter password: ");
        String password = scanner.next();
        System.out.println("Enter full name: ");
        scanner.nextLine();
        String fullName = scanner.nextLine();
        System.out.println("Enter contact details: ");
        String contact = scanner.next();

        userService.registerUser(username,password,fullName,contact);
    }

    public void login() {
        System.out.println("Enter username: ");
        String username = scanner.next();
        System.out.println("Enter password: ");
        String password = scanner.next();
        userService.userLogin(username,password);
    }

    public void showUserMenu(){
        while (userService.isLoggedIn()){
            System.out.println("\n ------User menu ------");
            System.out.println("1. Search Trains:");
            System.out.println("2. Book Ticket:");
            System.out.println("3. Show Ticket:");
            System.out.println("4. Cancel Ticket:");
            System.out.println("5. View All Ticket:");
            System.out.println("6. Log out");
            System.out.println("Enter Choice:");
            int choice = scanner.nextInt();
            switch (choice){
                case 1 -> searchTrain();
                case 2 -> bookTicekt();
                case 3 -> showTicket();
                case 4 -> cancelTicket();
                case 5 -> bookUserService.listAllTrain();
                case 6 -> userService.logOut();
                default -> System.out.println("Invalid Choice..");
            }
        }
    }

    public void searchTrain(){
        System.out.println("Enter source station:- ");
        String source = scanner.next();
        System.out.println("Enter destination station:- ");
        String destination = scanner.next();

        List<Train> trains = bookUserService.searchTrain(source,destination);
        if(trains.isEmpty()){
            System.out.println("No trains found between "+source+" and "+destination);
            return;
        }
        System.out.println("Trains found:");
        for(Train train : trains){
            System.out.println(train);
        }
        System.out.println("Want to book ? (yes/no):");
        String choice = scanner.next();
        if(choice.equalsIgnoreCase("yes")){
            System.out.println("Enter trainId to book:");
            int trainId = scanner.nextInt();
            System.out.println("Enter no of seats book:");
            int seats = scanner.nextInt();

            Ticket ticket = bookUserService.bookTicket(userService.getCurrUser(),trainId,seats);
            if(ticket != null){
                System.out.println("Booking successful..");
                System.out.println(ticket);
            }
        }else {
            System.out.println("Returning to user menu ....");
        }
    }

    private void bookTicekt(){
        System.out.println("Enter source station:- ");
        String source = scanner.next();
        System.out.println("Enter destination station:- ");
        String destination = scanner.next();

        List<Train> trains = bookUserService.searchTrain(source,destination);
        if(trains.isEmpty()){
            System.out.println("No trains available for booking");
            return;
        }
        System.out.println("Available Trains: ");
        for(Train train : trains){
            System.out.println(train);
        }
        System.out.println("Enter trainId to book:");
        int trainId = scanner.nextInt();
        System.out.println("Enter no of seats book:");
        int seats = scanner.nextInt();
        Ticket ticket = bookUserService.bookTicket(userService.getCurrUser(),trainId,seats);
        if(ticket != null){
            System.out.println("Booking successful..");
            System.out.println(ticket);
        }
    }

    private void showTicket(){
        List<Ticket> ticketByUser = bookUserService.getTicketByUser(userService.getCurrUser());
        if(ticketByUser.isEmpty()){
            System.out.println("Ticket not found.");
        }else{
            System.out.println("Your ticket");
            for (Ticket ticket:ticketByUser){
                System.out.println(ticket);
            }
        }
    }

    private void cancelTicket(){
        System.out.println("Enter Ticket ID to cancel :");
        int ticketID = scanner.nextInt();
        bookUserService.cancelTicket(ticketID,userService.getCurrUser());
    }

    private void exitApp(){
        System.out.println("Thank you for using IRCTC App.");
        System.exit(0);
    }
}
