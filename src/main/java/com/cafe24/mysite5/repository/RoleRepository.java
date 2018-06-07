package com.cafe24.mysite5.repository;

import com.cafe24.mysite5.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository< Role, String >{
	Role findByName( String name );
}
