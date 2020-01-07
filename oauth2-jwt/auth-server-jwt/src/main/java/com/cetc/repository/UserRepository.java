package com.cetc.repository;

import com.cetc.domain.User;

public interface UserRepository extends BaseRepository<User, Long>{

    User findByUsername(String username);

}
