package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import test.models.Account;
import test.models.User;
import test.repositories.AccountRepository;
import test.services.AccountService;
import test.services.UserService;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class ConsoleRunner implements CommandLineRunner {
//    @Autowired
//    private UserRepository userRepository;

    private final UserService userService;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService, AccountRepository accountRepository) {
        this.userService = userService;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User first = new User("Qna1", 22);
        userService.registerUser(first);

        User second = new User("Lily1", 38);
        userService.registerUser(second);

        /*userRepository.save(first);
        userRepository.save(second);
        System.out.println("--------------"+userRepository.count());
        userRepository.delete(second);
        System.out.println("--------------"+userRepository.count());*/

        Account account = new Account(new BigDecimal("25000"), first);
        accountRepository.save(account);

        first.setAccounts(new HashSet<>(){{
            add(account);
        }});

//        accountService.withdrawMoney(new BigDecimal("20000"), (long)account.getId());
        accountService.transferMoney(new BigDecimal("30000"), (long)account.getId());
    }
}
