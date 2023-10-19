package softuni.exam.models.dto;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Town;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentDTO {
    @XmlElement(name = "apartmentType")
    private ApartmentType type;

    @XmlElement
    private double area;

    @XmlElement
    private String town;

    public ApartmentDTO() {}

    public ApartmentType getType() {
        return type;
    }

    public double getArea() {
        return area;
    }

    public String getTown() {
        return town;
    }
    public boolean validate(){
        if(this.area < 40.00){
            return false;
        }
        return true;
    }
}
