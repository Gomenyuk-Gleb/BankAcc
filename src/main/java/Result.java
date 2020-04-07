import javax.persistence.*;
import java.util.*;

public class Result {
    private static final String PATH = "JPATest";
    private static EntityManagerFactory entityManagerFactory;
    public static EntityManager entityManager;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory(PATH);
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static void transactionCommit(Object c) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        try {
            entityManager.persist(c);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<User> allUser() {
        AddUserAccTraCar.addUser();
        List<User> users;
        TypedQuery<User> userTypedQuery = entityManager.createQuery("Select u from User u", User.class);
        users = userTypedQuery.getResultList();
        return users;
    }

    public List<Carency> allCure() {
        AddUserAccTraCar.addCar();
        List<Carency> carencies;
        TypedQuery<Carency> carencyTypedQuery = entityManager.createQuery("select c FROM Carency c", Carency.class);
        carencies = carencyTypedQuery.getResultList();
        return carencies;
    }

    public List<Accaunt> allAcc() {
        AddUserAccTraCar.addAcc();
        List<Accaunt> carencies;
        TypedQuery<Accaunt> carencyTypedQuery = entityManager.createQuery("select c FROM Accaunt c", Accaunt.class);
        carencies = carencyTypedQuery.getResultList();
        return carencies;
    }

    public Accaunt getAccountByUserAndCurrency(String number, Double balanc, Carency carency) {
        TypedQuery<Accaunt> accauntTypedQuery = entityManager.createQuery("SELECT c from Accaunt  c where  c.number  = :name and c.carency  = :carency", Accaunt.class);
        accauntTypedQuery.setParameter("name", number);
        accauntTypedQuery.setParameter("carency", carency);
        Accaunt accaunt = accauntTypedQuery.getSingleResult();
        accaunt.setBalans(accaunt.getBalans() + balanc);
        return accaunt;
    }

    public Carency carency(String val) {
        Carency carency;
        TypedQuery<Carency> carencyTypedQuery = entityManager.createQuery("SELECT c FROM  Carency c WHERE c.name = :val", Carency.class);
        carencyTypedQuery.setParameter("val", val);
        carency = carencyTypedQuery.getSingleResult();
        return carency;
    }

    public Accaunt number(String number) {
        Accaunt accaunt;
        TypedQuery<Accaunt> balancTypedQuery = entityManager.createQuery("SELECT c FROM  Accaunt c WHERE c.number = :number", Accaunt.class);
        balancTypedQuery.setParameter("number", number);
        accaunt = balancTypedQuery.getSingleResult();
        return accaunt;
    }

    public List<Accaunt> transferBalans(Accaunt accauntFrom, Accaunt accauntTo, Double money) {
        List<Accaunt> accaunts = new ArrayList<>();
        convert(accauntFrom, accauntTo);
        if (accauntFrom.getBalans() >= money) {
            accauntFrom.setBalans(accauntFrom.getBalans() - money);
            accauntTo.setBalans(accauntTo.getBalans() + money);
            accaunts.add(accauntFrom);
            accaunts.add(accauntTo);
            return accaunts;
        } else return null;
    }


    public Accaunt convert(Accaunt accauntFrom, Accaunt accauntTO) {
        if (accauntFrom.getCarency().toString() != accauntTO.getCarency().toString()) {
            if (accauntFrom.getCarency().toString() == "UAH" && accauntTO.getCarency().toString() == "USD") {
                accauntFrom.setBalans(accauntFrom.getBalans() / 28);
                accauntFrom.setCarency(accauntTO.getCarency());
            }
            if (accauntFrom.getCarency().toString() == "UAH" && accauntTO.getCarency().toString() == "EUR") {
                accauntFrom.setBalans(accauntFrom.getBalans() / 30);
                accauntFrom.setCarency(accauntTO.getCarency());
            }
            if (accauntFrom.getCarency().toString() == "USD" && accauntTO.getCarency().toString() == "EUR") {
                accauntFrom.setBalans(accauntFrom.getBalans() / 1.5);
                accauntFrom.setCarency(accauntTO.getCarency());
            }
            if (accauntFrom.getCarency().toString() == "USD" && accauntTO.getCarency().toString() == "UAH") {
                accauntFrom.setBalans(accauntFrom.getBalans() * 28);
                accauntFrom.setCarency(accauntTO.getCarency());

            }
            if (accauntFrom.getCarency().toString() == "EUR" && accauntTO.getCarency().toString() == "UAH") {
                accauntFrom.setBalans(accauntFrom.getBalans() / 30);
                accauntFrom.setCarency(accauntTO.getCarency());
            }
            if (accauntFrom.getCarency().toString() == "EUR" && accauntTO.getCarency().toString() == "USD") {
                accauntFrom.setBalans(accauntFrom.getBalans() * 1.5);
                accauntFrom.setCarency(accauntTO.getCarency());
            }
        }
        return accauntFrom;
    }

    public SortedMap<Double, String> convertAcc(Accaunt accauntFrom) {
        SortedMap<Double, String> doubleStringHashMap = new TreeMap<>();
        if (accauntFrom.getCarency().toString() == "UAH") {
            doubleStringHashMap.put(accauntFrom.getBalans(), accauntFrom.getCarency().toString());
            doubleStringHashMap.put(accauntFrom.getBalans() / 25, "USD");
            doubleStringHashMap.put(accauntFrom.getBalans() / 28, "EUR");
        }
        if (accauntFrom.getCarency().toString() == ("USD")) {
            doubleStringHashMap.put(accauntFrom.getBalans(), accauntFrom.getCarency().toString());
            doubleStringHashMap.put(accauntFrom.getBalans() * 25, "UAH");
            doubleStringHashMap.put(accauntFrom.getBalans() / 1.5, "EUR");
        }
        if (accauntFrom.getCarency().toString() == ("EUR")) {
            doubleStringHashMap.put(accauntFrom.getBalans(), accauntFrom.getCarency().toString());
            doubleStringHashMap.put(accauntFrom.getBalans() * 1.5, "USD");
            doubleStringHashMap.put(accauntFrom.getBalans() * 28, "UAH");
        }
        return doubleStringHashMap;
    }

    public List<Accaunt> countAcc(User User){
        List<Accaunt> accaunts = new ArrayList<>();
        TypedQuery<Accaunt> accauntTypedQuery = entityManager.createQuery("SELECT c FROM  Accaunt c WHERE c.user = :User", Accaunt.class);
        accauntTypedQuery.setParameter("User", User);
        accaunts = accauntTypedQuery.getResultList();
        return accaunts;
    }

    public User userNumber(String number) {
        User accaunt;
        TypedQuery<User> balancTypedQuery = entityManager.createQuery("SELECT c FROM  User c WHERE c.phone = :number", User.class);
        balancTypedQuery.setParameter("number", number);
        accaunt = balancTypedQuery.getSingleResult();
        return accaunt;
    }
    public Double summUAH(List<Accaunt> accaunts){
        Double sum = 0D;
        for (int i = 0; accaunts.size()>i; i++){
            if(accaunts.get(i).getCarency().toString()=="UAH") {
                sum += accaunts.get(i).getBalans();
            }
            else
                sum += conv(accaunts.get(i));
        }
        return sum;
    }
    public Double conv(Accaunt accaunt){
        Double aDouble = 0D;
        if(accaunt.getCarency().toString()=="USD"){
            aDouble = accaunt.getBalans()*28;
        }
        else
            aDouble = accaunt.getBalans()*30;
        return aDouble;
    }


}
