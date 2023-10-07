package springdatamodelmapperex.entites;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameViewDTO {
    private String title;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;

    public GameViewDTO() {}

    public GameViewDTO(String title, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    /*public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }*/

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return String.format("Title: %s%n" +
                        "Price: %.2f%n" +
                        "Description: %s%n" +
                        "Release date: %s",
                        this.title, this.price, this.description, this.releaseDate);
    }
}
