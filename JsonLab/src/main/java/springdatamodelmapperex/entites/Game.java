package springdatamodelmapperex.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import org.hibernate.Length;
import springdatamodelmapperex.entites.users.User;

import javax.validation.constraints.Size;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    @Size(min = 1, max = 3)
    private String title;
    @Column(name = "trailer_id", nullable = false)
    private String trailerId;
    @Column(name = "thumbnail_url", nullable = false)
    private String thumbnailUrl;
    private float size;
    @Column(nullable = false)
    private BigDecimal price;

    private String description;
    @Column(name = "release_date", nullable = false)
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

//    @ManyToOne(optional = true)
//    @JoinColumn(name = "cart_id", referencedColumnName = "id")
//    private Cart cart;

    public Game(){}

    public Game(String title, String trailerId, String thumbnailUrl, float size, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.trailerId = trailerId;
        this.thumbnailUrl = thumbnailUrl;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;

        this.validateGame();
    }

    public void validateGame() {
        if(this.price.compareTo(BigDecimal.valueOf(0)) == -1){
            throw new IllegalArgumentException("Price must be positive!");
        }
        char firstLetter = Character.toUpperCase(title.charAt(0));
        if((firstLetter != title.charAt(0)) || title.length() < 3 || title.length() > 100
                || this.size < 0
                || !this.trailerId.contains("www.youtube.com")
                || this.description.length() < 20
                || (!this.thumbnailUrl.contains("http://") && !this.thumbnailUrl.contains("https://"))){
            throw new IllegalArgumentException("Invalid title!");
        }
        this.trailerId = this.trailerId.substring(this.trailerId.length() - 11);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(String trailerId) {
        this.trailerId = trailerId;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /*public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return title.equals(game.title) && releaseDate.equals(game.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, releaseDate);
    }
}
