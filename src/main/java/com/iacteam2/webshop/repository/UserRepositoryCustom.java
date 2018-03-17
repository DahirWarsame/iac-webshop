package com.iacteam2.webshop.repository;

import com.iacteam2.webshop.model.User;

public interface UserRepositoryCustom {
    boolean checkUserNameExistance(String username);
    User getUserByUsername(String username);
}
