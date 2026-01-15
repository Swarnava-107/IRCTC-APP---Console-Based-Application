import java.util.HashMap;
import java.util.Map;

public class UserService {
    // username ---> user
    private Map<String,User> userMap = new HashMap<>();
    private User currUser = null;

    public boolean registerUser (String userName,String password,String fullName,String contact){
        if(userMap.containsKey(userName)){
            System.out.println("Username already exist. Please choose another ");
            return false;
        }
        User user = new User(userName,password,fullName,contact);
        userMap.put(userName,user);
        System.out.println("Registration Successful !");
        return true;
    }

    public boolean userLogin(String userName, String password){
        if(!userMap.containsKey(userName)){
            System.out.println("Username not found !!");
            return false;
        }
        User user = userMap.get(userName);
        if(!user.getPassword().equals(password)){
            System.out.println("Invalid Password..");
            return false;
        }
        currUser = user;
        System.out.println("Welcome "+currUser.getFullName());
        return true;
    }

    public void logOut(){
        if(currUser != null){
            System.out.println("Logged out "+currUser.getFullName());
        }
        currUser = null;
    }

    public User getCurrUser() {
        return currUser;
    }

    public boolean isLoggedIn(){
        return currUser!=null;
    }
}
