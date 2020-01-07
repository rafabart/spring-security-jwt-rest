package com.invillia.springsecurityjwtrest.repository;

import com.invillia.springsecurityjwtrest.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
