package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Taco;

import java.util.List;

public interface TacoRepository extends CrudRepository<Taco, Long> {
//    Taco save( Taco taco );
//
//   List<Taco> findAll();
}
