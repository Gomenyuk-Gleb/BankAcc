import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Accaunt")
public class Accaunt {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "carency_id")
    private Carency carency;

    @OneToMany(mappedBy = "accountTo")
    private List<Transaction> transactionsTo;


    private Double balans;
    public Accaunt(String number, User user, Carency carency, Double balans) {
        this.number = number;
        this.user = user;
        this.carency = carency;
        this.balans = balans;
    }

    @Override
    public String toString() {
        return "Accaunt{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", user=" + user +
                ", carency=" + carency +
                ", transactions="  +
                ", balans=" + balans +
                '}';
    }

    public Accaunt() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getBalans() {
        return balans;
    }

    public void setBalans(Double balans) {
        this.balans = balans;
    }

    public Carency getCarency() {
        return carency;
    }

    public void setCarency(Carency carency) {
        this.carency = carency;
    }

}
