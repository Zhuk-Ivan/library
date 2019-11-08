package com.github.DonBirnam.library.dao;


import com.github.DonBirnam.library.model.Role;
import com.github.DonBirnam.library.model.UserDTO;
import com.github.DonBirnam.library.model.UserRegDTO;

import java.util.List;

public interface UserDao {

    Long saveUser(UserRegDTO userRegDTO);

    void deleteUser(Long id);

    UserDTO showUser(String login);

    UserDTO getById(Long id);

    List<UserDTO> getAllUsers();

    void changePersonalData(UserDTO userDTO);

    void blockUser(Role role, Long id);



}