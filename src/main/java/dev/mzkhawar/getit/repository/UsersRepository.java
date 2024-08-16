package dev.mzkhawar.getit.repository;

import dev.mzkhawar.getit.model.entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {

}
