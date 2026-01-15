public class Ticket {
    private static int counter = 1001;
    private int ticketId;
    private User user;
    private Train trainName;
    private int seatBooked;

    public Ticket(User user, Train trainName, int seatBooked) {
        this.ticketId = ticketId++;
        this.user = user;
        this.trainName = trainName;
        this.seatBooked = seatBooked;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Ticket.counter = counter;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Train getTrainName() {
        return trainName;
    }

    public void setTrainName(Train trainName) {
        this.trainName = trainName;
    }

    public int getSeatBooked() {
        return seatBooked;
    }

    public void setSeatBooked(int seatBooked) {
        this.seatBooked = seatBooked;
    }

    @Override
    public String toString() {
        return "Ticket id : "+ticketId+" | Train : "+trainName.getTrainName()+
                " | Route : " +trainName.getSource()+" -> "+trainName.getDestination()
                +"Seats "+seatBooked+" booked by "+ user.getFullName();
    }
}
