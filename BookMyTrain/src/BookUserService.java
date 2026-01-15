import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookUserService {
    private List<Train> trainList = new ArrayList<>();
    private List<Ticket> ticketList = new ArrayList<>();

    public BookUserService(){
        trainList.add(new Train(101,"Rajdhani Express","Delhi","Nagpur",100));
        trainList.add(new Train(102,"Satapdi Express","Mumbai","Kolkata",72));
        trainList.add(new Train(103,"Jammu-Tawi Express","Pune","Chennai",81));
        trainList.add(new Train(104,"KannyaKumari Express","Delhi","Dehradun",9));
        trainList.add(new Train(105,"Black-Diamond Express","Sealdah","NJP",123));
        trainList.add(new Train(106,"Pratap Express","Howrah","Dhanbad",107));
    }

    public List<Train> searchTrain(String source,String destination){
        List<Train> res = new ArrayList<>();
        for(Train train : trainList){
            if(train.getSource().equalsIgnoreCase(source) && train.getDestination().equalsIgnoreCase(destination)){
                res.add(train);
            }
        }
        return res;
    }

    public Ticket bookTicket(User user,int trainId,int seatCount){
        for (Train train:trainList){
            if(train.getTrainId()==trainId){
                if(train.bookSeats(seatCount)){
                    Ticket ticket = new Ticket(user,train,seatCount);
                    ticketList.add(ticket);
                    return ticket;
                }else {
                    System.out.println("Not enough seats available..");
                    return null;
                }
            }
        }
        System.out.println("TrainId Not Found..");
        return null;
    }

    public List<Ticket> getTicketByUser (User user){
        List<Ticket> res = new ArrayList<>();
        for(Ticket ticket : ticketList){
            if(ticket.getUser().getUserName().equalsIgnoreCase(user.getUserName())){
                res.add(ticket);
            }
        }
        return res;
    }

    public boolean cancelTicket(int ticketId, User user){
        Iterator<Ticket> iterator = ticketList.iterator();
        while(iterator.hasNext()){
            Ticket ticket = iterator.next();
            if(ticket.getTicketId() == ticketId &&
                     ticket.getUser().getUserName().equalsIgnoreCase(user.getUserName())){
                Train train = ticket.getTrainName();
                train.cancelSeats(ticket.getSeatBooked());
                iterator.remove();
                System.out.println("Ticket "+ticketId+" get cancelled");
                return true;
            }
        }
        System.out.println("Ticket not found or does not belong to current user");
        return false;
    }

    public void listAllTrain(){
        for (Train train:trainList){
            System.out.println(train);
        }
    }

}
