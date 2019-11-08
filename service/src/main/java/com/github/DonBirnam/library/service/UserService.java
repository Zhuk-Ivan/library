package com.github.DonBirnam.library.service;


import com.github.DonBirnam.library.model.Role;
import com.github.DonBirnam.library.model.UserDTO;
import com.github.DonBirnam.library.model.UserRegDTO;

import java.util.List;
    public interface UserService {

        UserDTO saveUser(UserRegDTO userRegDTO);

        void deleteUser(Long id);

        boolean isExist(String login);

        UserDTO getByLogin(String login);

        List<UserDTO> getAllUsers();

        void updateUser(UserDTO userDTO);

        void block(Role role, Long id);



}
