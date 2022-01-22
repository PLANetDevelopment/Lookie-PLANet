package com.planet.develop.Repository;

import com.planet.develop.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
