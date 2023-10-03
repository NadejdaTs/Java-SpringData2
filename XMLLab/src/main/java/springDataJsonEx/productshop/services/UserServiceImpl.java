package springDataJsonEx.productshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import springDataJsonEx.productshop.entities.users.User;
import springDataJsonEx.productshop.entities.users.UserWithSoldProductsDTO;
import springDataJsonEx.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    @Transactional
    public List<UserWithSoldProductsDTO> getUserWithSoldProducts() {
        List<User> allWithSoldProducts = this.userRepository.findAllWithSoldProducts();

        return allWithSoldProducts.stream()
                .map(user -> this.mapper.map(user, UserWithSoldProductsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<User> getUserWithSoldProductsOrderByCount() {
        List<User> all = this.userRepository.findAllWithSoldProductsOrderByCount();

        all.get(0).getSellingItems().size();

        return null;
    }
}
