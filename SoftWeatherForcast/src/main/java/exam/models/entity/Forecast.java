package exam.models.entity;

import jakarta.persistence.*;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Default;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "forecasts")
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DayOfWeek dayOfWeek;
    private double maxTemperature;
    private double minTemperature;

    @Column(columnDefinition = "Time(0)")
    private LocalTime sunrise;

    @Column(columnDefinition = "Time(0)")
    private LocalTime sunset;

    @ManyToOne(optional = false)
    private City city;

    public Forecast() {}

    public Forecast(DayOfWeek dayOfWeek, double maxTemperature, double minTemperature, LocalTime sunrise, LocalTime sunset, City city) {
        this.dayOfWeek = dayOfWeek;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("City: %s:%n" +
        "-min temperature: %.2f%n" +
        "--max temperature: %.2f%n" +
        "---sunrise: %s%n" +
        "----sunset: %s%n",
        city.getCityName(), minTemperature, maxTemperature, sunrise, sunset);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
