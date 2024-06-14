package cmpt276.asn2.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  List<User> findByWidthAndHeight(int width, int height);

  List<User> findByName(String name);
}