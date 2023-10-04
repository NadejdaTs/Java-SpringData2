package springXmlEx.productshop.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springXmlEx.productshop.entities.users.ExportSellersDTO;
import springXmlEx.productshop.entities.users.ExportUserWithSoldProductsDTO;
import springXmlEx.productshop.entities.users.User;
import springXmlEx.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
    public ExportSellersDTO findAllWithSoldProducts() {
        List<User> users = this.userRepository.findAllWithSoldProducts();

        List<ExportUserWithSoldProductsDTO> dtos = users.stream()
                .map(u -> this.mapper.map(u, ExportUserWithSoldProductsDTO.class))
                .collect(Collectors.toList());

        return new ExportSellersDTO(dtos);
    }
}
