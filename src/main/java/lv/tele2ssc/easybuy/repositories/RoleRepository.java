package lv.tele2ssc.easybuy.repositories;

import lv.tele2ssc.easybuy.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    
    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    Role findByName(String name);
    
}
