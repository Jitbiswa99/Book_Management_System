package com.bookStore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookStore.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>
{

}
