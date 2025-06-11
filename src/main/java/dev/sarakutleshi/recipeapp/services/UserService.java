package dev.sarakutleshi.recipeapp.services;

import dev.sarakutleshi.recipeapp.dtos.user.CreateUserRequestDto;
import dev.sarakutleshi.recipeapp.dtos.user.UpdateUserRequestDto;
import dev.sarakutleshi.recipeapp.dtos.user.UserDto;
import dev.sarakutleshi.recipeapp.dtos.user.UserListingDto;
import dev.sarakutleshi.recipeapp.services.baseServices.*;

public interface UserService extends
        FindAll<UserListingDto>,
        FindById<UserDto, Long>,
        Addable<CreateUserRequestDto>,
        Modifiable<UpdateUserRequestDto, Long>,
        Removable<Long> {
}
