package com.project.onlineStore.User;

import java.util.List;

public interface IUserService {

    List<User> getUsers();
    void createUser(User user);
    void updateUser(Long id, User user);
    void deleteUser(Long id);
}
