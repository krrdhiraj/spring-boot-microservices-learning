package com.elearn.app.repositories;

import com.elearn.app.dtos.RoleDto;
import com.elearn.app.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, String> {

    Optional<Role> findByRoleName(String roleName);
}
