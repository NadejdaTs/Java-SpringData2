package springdatamodelmapperex.entites;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class ManyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //@Column(nullable = false)
    private String name;
    private int age;

    @ManyToMany
//    @JoinTable(name = "many_entity_friends",
//    joinColumns = @JoinColumn(name = "many_entity_id", referencedColumnName = "id"),
//    inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<ManyEntity> friends;

    public ManyEntity(){
        this.friends = new HashSet<>();
    }

    public ManyEntity(String name, int age) {
        this();
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<ManyEntity> getFriends() {
        return friends;
    }

    public void setFriends(Set<ManyEntity> friends) {
        this.friends = friends;
    }
}
