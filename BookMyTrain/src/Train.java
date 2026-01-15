//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Train {

    private int trainId;
    private String trainName ;
    private String source ;
    private String destination ;
    private int totalSeats ;
    private int avlSeats ;

    public Train(int trainId, String trainName, String source, String destination, int avlSeats) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        //this.totalSeats = totalSeats;
        this.avlSeats = avlSeats;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvlSeats() {
        return avlSeats;
    }

    public void setAvlSeats(int avlSeats) {
        this.avlSeats = avlSeats;
    }

    public boolean bookSeats(int count){
        if(count <= avlSeats){
            avlSeats -= count;
            return true;
        }
        return false;
    }
    public void cancelSeats(int count){
        avlSeats += count;
    }

    @Override
    public String toString() {
        return trainId +" | "+trainName + " | " +source+" -> "+destination+" available setas : "+avlSeats;
    }
}