package com.project.onlineStore.User;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getUsers() {
       return userRepository.findAll();
    }

    @Override
    public void createUser(User user) {
        validEmail(user.getEmail());
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, User user) {
        User userToUpdate = userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException(String.format("User with id: %s doesn't exists. ",id)));
        validEmail(user.getEmail());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userRepository.save(userToUpdate); // save it
    }


    public void validEmail(String email){
        Optional<User> userOptional = userRepository.getUserByEmail(email);
        if (userOptional.isPresent()){
            throw new IllegalStateException(String.format("Email address %s already exist",email));
        }
    }


    @Override
    public void deleteUser(Long id){
        boolean userExists = userRepository.existsById(id);
        if(!userExists)
            throw new IllegalStateException(String.format("Employee with %s id doesn't exist",id));
        userRepository.deleteById(id);

    }


}
