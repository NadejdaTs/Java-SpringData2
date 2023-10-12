package carDealer.entities.parts;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PartSimpleDTO implements Serializable {
    @SerializedName("parts")
    private String id;



    public String getName() {
        return id;
    }
}
