package springdatamodelmapperex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springdatamodelmapperex.entites.exceptions.UserNotLoggedInException;
import springdatamodelmapperex.entites.exceptions.ValidationException;
import springdatamodelmapperex.services.ExecutorService;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final ExecutorService executorService;

    @Autowired
    public ConsoleRunner(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        String result;
        while(!command.equals("END")) {
            try {
                result = executorService.execute(command);
            } catch (ValidationException | UserNotLoggedInException ex) {
                result = ex.getMessage();
            }
            System.out.println(result);
        }
    }
}
