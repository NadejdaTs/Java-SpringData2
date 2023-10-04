package springdatamodelmapperex;

import springdatamodelmapperex.exceptions.UserNotLoggedInException;
import springdatamodelmapperex.exceptions.ValidationException;
import springdatamodelmapperex.services.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        /*
        For this exercise input should be:
        first row - command, second row - data!
         */
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        String commandData = sc.nextLine();

        while(!command.equals("END")) {
            String result;
            try {
                result = executorService.execute(command, commandData);
            } catch (ValidationException | UserNotLoggedInException ex) {
                result = ex.getMessage();
            }
            System.out.println(result);
            command = sc.nextLine();
            commandData = sc.nextLine();
        }
    }
}
