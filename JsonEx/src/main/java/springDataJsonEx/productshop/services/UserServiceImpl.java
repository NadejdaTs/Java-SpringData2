package springDataJsonEx.productshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springDataJsonEx.productshop.entities.products.ProductNameAndPriceDto;
import springDataJsonEx.productshop.entities.products.SoldProductsForUserDto;
import springDataJsonEx.productshop.entities.users.User;
import springDataJsonEx.productshop.entities.users.UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto;
import springDataJsonEx.productshop.entities.users.UserWithSoldProductsDTO;
import springDataJsonEx.productshop.entities.users.UsersWithSalesListDto;
import springDataJsonEx.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public List<UserWithSoldProductsDTO> getUserWithSoldProducts() {
        List<User> allWithSoldProducts = this.userRepository.findAllWithSoldProducts();

        return allWithSoldProducts.stream()
                .map(user -> this.mapper.map(user, UserWithSoldProductsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getUserWithSoldProductsOrderByCount() {
        List<User> all = this.userRepository.findAllWithSoldProductsOrderByCount();

        all.get(0).getSellingItems().size();

        return null;
    }

    @Override
    public UsersWithSalesListDto getUsersAndSoldProducts(){
        List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> users = this.userRepository
                .getAllBySellContainsProduct_Buyer()
                .stream()
                .map(user -> {
                    final UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto userDto =
                            this.mapper.map(user, UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto.class);

                    final SoldProductsForUserDto soldProductsForUserDto = new SoldProductsForUserDto();

                    soldProductsForUserDto.setProducts(user
                            .getSellingItems()
                            .stream()
                            .filter(sale -> sale.getBuyer() != null)
                            .map(sale -> this.mapper.map(sale, ProductNameAndPriceDto.class))
                            .collect(Collectors.toSet()));

                    soldProductsForUserDto.setCount(soldProductsForUserDto.getProducts().size());

                    userDto.setSoldProducts(soldProductsForUserDto);

                    return userDto;
                })
                .sorted((u1, u2) -> {
                    int cmp = u2.getSoldProducts().getCount() - u1.getSoldProducts().getCount();
                    if (cmp == 0) {
                        cmp = u1.getLastName().compareTo(u2.getLastName());
                    }
                    return cmp;
                })
                .collect(Collectors.toList());

        UsersWithSalesListDto usersWithSalesListDto = new UsersWithSalesListDto();
        usersWithSalesListDto.setUsers(users);
        usersWithSalesListDto.setUsersCount(users.size());

        return usersWithSalesListDto;
    }
}
