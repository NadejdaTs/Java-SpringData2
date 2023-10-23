package exam.models.dto;

import exam.models.entity.DayOfWeek;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Default;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalTime;

@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportForecastDTO {
    @XmlElement(name = "day_of_week")
    @NotNull
    private DayOfWeek dayOfWeek;

    @XmlElement(name = "max_temperature")
    @NotNull
    @Min(-20)
    @Max(60)
    private double maxTemperature;

    @XmlElement(name = "min_temperature")
    @NotNull
    @Min(-50)
    @Max(40)
    private double minTemperature;

    @XmlElement
    @NotNull
    private String sunrise;

    @XmlElement
    @NotNull
    private String sunset;

    @XmlElement
    @NotNull
    private Long city;

    public ImportForecastDTO() {}

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public Long getCity() {
        return city;
    }
}
