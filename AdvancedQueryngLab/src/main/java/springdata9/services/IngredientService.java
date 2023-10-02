package springdata9.services;

import springdata9.entities.Ingredient;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService {
    List<Ingredient> selectNameStartWith(String startWith);

    List<Ingredient> selectInNames(List<String> listOfNames);

    int deleteByName(String name);

    void increasePriceByPercentage(double v);

    int updatePriceByGivenName(String name, double percent);

    int updatePriceByListOfNames(List<String> listOfNames, double percent);
}
