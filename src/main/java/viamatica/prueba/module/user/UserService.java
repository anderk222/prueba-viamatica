package viamatica.prueba.module.user;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import viamatica.prueba.exception.ResourceNotFoundException;
import viamatica.prueba.module.user.domain.User;

@Service
public class UserService {
    
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
        
        return userRepository.save(user);

    }

    // public void updateAttemps(int attemps, long id){

    //     userRepository.update(attemps, id);

    // }

}
