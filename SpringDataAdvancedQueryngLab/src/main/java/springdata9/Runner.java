package springdata9;

import springdata9.entities.Size;
import springdata9.repositories.ShampooRepository;
import springdata9.services.IngredientService;
import springdata9.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {
    private final ShampooRepository shampooRepository;
    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired
    public Runner(ShampooRepository shampooRepository, ShampooService shampooService, IngredientService ingredientService) {
        this.shampooRepository = shampooRepository;
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        this.shampooService.selectMoreExpensiveThan(BigDecimal.valueOf(5))
                .forEach(System.out::println);

        //try last
        /*this.shampooService.selectBySizeOrLabelId(Size.MEDIUM, 10)
                .forEach(System.out::println);*/


        /*this.ingredientService.deleteByName("Nettle");
        this.ingredientService.increasePriceByPercentage(0.1);
                //.forEach(System.out::println);*/
    }

    private void demo(){
        /*this.shampooRepository.findByBrand("Cotton Fresh")
                .forEach(s -> System.out.println(s.getId()));
        this.shampooRepository.findAllByBrandAndSize("Cotton Fresh", Size.SMALL)
                .forEach(s -> System.out.println(s.getId()));*/
        //Scanner sc = new Scanner(System.in);
        /*String sizeName = sc.nextLine();
        Size size = Size.valueOf(sizeName);

        this.shampooRepository.findBySizeOrderById(size)
                .forEach(System.out::println);*/

        /*String first = sc.nextLine();
        String second = sc.nextLine();

        Set<String> names = Set.of(first, second);
        this.shampooRepository.findByIngredientsNames(names)
                .forEach(System.out::println);*/
        //01
        /*this.shampooService.selectBySize(Size.MEDIUM)
                .forEach(System.out::println);*/
        //02
        //this.shampooService.selectBySizeOrLabelId(Size.MEDIUM, 10).forEach(System.out::println);
        //03 - Order By must to do
        /*this.shampooService.selectMoreExpensiveThan(BigDecimal.valueOf(5))
                .forEach(System.out::println);*/
        //04
        /*this.ingredientService.selectNameStartWith("M")
                .forEach(System.out::println);*/
        //05
        /*List<String> listOfNames = List.of("Lavender", "Herbs", "Apple");
        this.ingredientService.selectInNames(listOfNames)
                .forEach(System.out::println);*/
        //06
        //System.out.println(this.shampooService.countPriceLowerThan(BigDecimal.valueOf(8.5)));


        //08
        //this does not work
        /*this.shampooService.selectByIngredientsCount(2)
                .forEach(System.out::println);*/
    }
}
