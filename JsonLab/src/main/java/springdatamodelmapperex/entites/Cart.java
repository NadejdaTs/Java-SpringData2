package springdatamodelmapperex.entites;

import jakarta.persistence.*;
import springdatamodelmapperex.entites.users.User;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "carts_games",
    joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
    private Set<Game> games;

    public Cart() {
        this.games = new HashSet<>();
    }

    public Cart(User user) {
        this();
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
