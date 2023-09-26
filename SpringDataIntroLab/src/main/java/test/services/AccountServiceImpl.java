package test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.models.Account;
import test.models.User;
import test.repositories.AccountRepository;
import test.repositories.UserRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if(account == null || account.isEmpty()){
            System.out.println("Not existing id!");
        }else {
            User user = account.get().getUser();
            boolean isSameAccount = user.getAccounts().stream()
                    .noneMatch(a -> a.equals(account));
            BigDecimal balance = account.get().getBalance();

            if (isSameAccount && (balance.compareTo(amount) == 1)) {
                account.get().setBalance(balance.subtract(amount));
                System.out.printf("Successfully withdrawal - %.2f%n!", amount);
                accountRepository.save(account.get());
            }else{
                System.out.println("Invalid Transfer!");
            }
        }
    }

    @Override
    public void transferMoney(BigDecimal amount, Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if(account == null || account.isEmpty()){
            System.out.println("Not existing id!");
        }else {
            User user = account.get().getUser();
            boolean isSameAccount = user.getAccounts().stream()
                    .noneMatch(a -> a.equals(account));
            BigDecimal balance = account.get().getBalance();

            if (isSameAccount && (balance.compareTo(amount) == 1) && (amount.compareTo(new BigDecimal("0")) == 1)) {
                account.get().setBalance(balance.subtract(amount));
                System.out.printf("Successfully money transfer - %.2f%n!", amount);
                accountRepository.save(account.get());
            }else{
                System.out.println("Invalid Transfer!");
            }
        }
    }
}
