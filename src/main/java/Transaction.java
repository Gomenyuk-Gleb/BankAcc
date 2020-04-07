import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Transaction")
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accaunt accountTo;

    public Transaction(Accaunt accountTo) {
        this.accountTo = accountTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Accaunt getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Accaunt accountTo) {
        this.accountTo = accountTo;
    }


}
