package productshop.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import productshop.entities.products.SoldListOfProductsDTO;
import productshop.entities.products.SoldProductsDTO;
import productshop.entities.users.*;
import productshop.repositories.UserRepository;
import productshop.services.api.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    @Transactional
    public ExportSellersDTO findAllWithSoldProducts() {
        List<User> users = this.userRepository.findAllWithSoldProducts();

        List<ExportUserWithSoldProductsDTO> dtos = users.stream()
                .map(u -> this.mapper.map(u, ExportUserWithSoldProductsDTO.class))
                .collect(Collectors.toList());

        return new ExportSellersDTO(dtos);
    }

    @Override
    public UsersWithSoldProductsDTO findAllWithSoldProductsOrderBySoldProductAndLastName() {
        List<UserWithSoldProductsDTO> users = this.userRepository.findAllWithSoldProductsOrderBySoldProductAndLastName().stream()
                .map(user -> {
                    UserWithSoldProductsDTO userDTO = this.mapper.map(user, UserWithSoldProductsDTO.class);

                    SoldListOfProductsDTO soldProductsDTO = new SoldListOfProductsDTO();

                    soldProductsDTO.setProducts(user.getSellingItems().stream()
                            .filter(sale -> sale.getBuyer() != null)
                            .map(sellItem -> this.mapper.map(sellItem, SoldProductsDTO.class))
                            .collect(Collectors.toSet()));
                    soldProductsDTO.setCount(soldProductsDTO.getProducts().size());

                    userDTO.setSoldProducts(soldProductsDTO);
                    return userDTO;
                })
                .sorted((u1, u2) -> {
                    int cmp = u2.getSoldProducts().getCount() - u1.getSoldProducts().getCount();
                    if (cmp == 0) {
                        cmp = u1.getLastName().compareTo(u2.getLastName());
                    }
                    return cmp;
                })
                .collect(Collectors.toList());

        UsersWithSoldProductsDTO usersWithSoldProdDTO = new UsersWithSoldProductsDTO();
        usersWithSoldProdDTO.setUsers(users);
        usersWithSoldProdDTO.setCount(users.size());
        return usersWithSoldProdDTO;
    }
}
