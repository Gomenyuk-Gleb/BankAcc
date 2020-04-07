import java.util.ArrayList;
import java.util.List;

public class AddUserAccTraCar {
    private static  User userFir = new User("Gleb", "0992312132");
    private static  User userSec = new User("Vova", "0992312122");
    private static  User userTHr = new User("Yolo", "0994212152");
    private static Carency fircCu = new Carency("UAH");
    private static Carency secCu = new Carency("EUR");
    private static Carency thrCu = new Carency("USD");


    public static List<User> addUser() {
        List<User> users = new ArrayList<>();
        Result.transactionCommit(userFir);
        Result.transactionCommit(userSec);
        Result.transactionCommit(userTHr);
        return null;
    }

    public static void addCar() {
        Result.transactionCommit(fircCu);
        Result.transactionCommit(secCu);
        Result.transactionCommit(thrCu);
    }

    public static void addAcc() {
        Result.transactionCommit(new Accaunt("5555", userFir, fircCu, 950D));
        Result.transactionCommit(new Accaunt("5556", userSec, secCu, 23455D));
        Result.transactionCommit(new Accaunt("5557", userTHr, thrCu, 12553D));
        Result.transactionCommit(new Accaunt("5558", userFir, secCu, 9D));
        Result.transactionCommit(new Accaunt("5559", userFir, thrCu, 5D));
    }

}
