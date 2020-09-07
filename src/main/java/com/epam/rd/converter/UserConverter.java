package com.epam.rd.converter;

import com.epam.rd.dto.UserDto;
import com.epam.rd.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {


    public static UserDto userView(User user) {

        return new UserDto(user);
    }

    public static List<UserDto> usersView(List<User> users) {
        List<UserDto> usersDto = new ArrayList<>();

        for (User user : users) {
            usersDto.add(new UserDto(user));
        }

        return usersDto;
    }

}
