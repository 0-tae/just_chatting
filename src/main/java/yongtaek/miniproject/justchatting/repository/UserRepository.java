package yongtaek.miniproject.justchatting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yongtaek.miniproject.justchatting.domain.User;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public interface UserRepository extends JpaRepository<User,AtomicLong> {
    Optional<User> findUserByUserIdAndPassword(String userId, String encodedPassword);
}
