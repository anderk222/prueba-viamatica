package viamatica.prueba.module.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import viamatica.prueba.exception.ResourceNotFoundException;
import viamatica.prueba.module.user.domain.User;

@Service
public class UserService {
    
    
    @Autowired
    private BCryptPasswordEncoder encoder;
    
    @Autowired
    private UserRepository userRepository;


    public User find(long id){

        return userRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException(id, "id", "user"));

    }

    public User findByUsernameOrMail(String value){

        User user =  userRepository.findByUserNameOrMail(value, value)
        .orElseThrow(()-> new ResourceNotFoundException(value, "name or email", "user"));

        return user;

    }

    public User update(User user){
        
        String encode = encoder.encode(user.getPassword());
        
        user.setPassword(encode);
        
        return userRepository.save(user);

    }
    
    public List<User> findByMail(String mail,Sort sort){
        
        Pageable pageable = PageRequest.of(0, 5,sort);
        
        return userRepository.findByMailContaining(mail, pageable);
        
    }

    
}
