import javax.persistence.*;

@Entity
@Table(name = "carency")
public class Carency {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "carency")
    private Accaunt accaunt;

    public String name;

    public Carency(String name) {
        this.name = name;
    }

    public Carency() {
    }

    @Override
    public String toString() {
        return name;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Accaunt getAccaunt() { return accaunt; }

    public void setAccaunt(Accaunt accaunt) { this.accaunt = accaunt; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
