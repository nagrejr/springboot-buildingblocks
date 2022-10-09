package com.newlife.springbootbuildingblocks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newlife.springbootbuildingblocks.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	//created custom method for findUserByUsername
	User findUserByUsername(String username);
}
