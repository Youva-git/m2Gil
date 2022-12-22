package com.fullstack.backend.service;

import com.fullstack.backend.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto user);
    List<UserDto> read();
    UserDto findById(Integer id);
    UserDto findByMail(String mail);
    UserDto update(Integer id, UserDto user);
    String delete(Integer id);
}
