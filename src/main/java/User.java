
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Accaunt> accauntList;




    public User() {}

    public User(String name, String phone){
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public List<Accaunt> getAccauntList() {
        return accauntList;
    }

    public void addAccaunt(Accaunt accaunt){
        if (! accauntList.contains(accaunt)){
            accauntList.add(accaunt);
            accaunt.setUser(this);
        }
    }

    public void setAccauntList(List<Accaunt> accauntList) {
        this.accauntList = accauntList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
