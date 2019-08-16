package tacos.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import tacos.Order;
import tacos.Taco;

import java.util.*;

//@Repository
public class JdbcOrderRepository {

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert orderIns;
    private SimpleJdbcInsert tacoIns;
    private ObjectMapper mapper;

    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        orderIns = new SimpleJdbcInsert( jdbc ).withTableName("Taco_Order").usingGeneratedKeyColumns("id");
        tacoIns = new SimpleJdbcInsert( jdbc ).withTableName("Taco_Order_Tacos");
        mapper = new ObjectMapper();
    }

//    @Override
    public Order save(Order order) {
        order.setPlaceAt( new Date() );
        long orderId = saveOrderDetails( order );
        order.setId( orderId );
        List<Taco> tacos = order.getOrderedTaco();
        for( Taco taco : tacos ){
            saveTacoToOrder( taco, orderId );
        }
        return order;
    }

    private long saveOrderDetails( Order order ){
        Map<String, Object> values = mapper.convertValue( order, Map.class );
        values.put( "placedAt", order.getPlaceAt() );

        long orderId = orderIns.executeAndReturnKey( values ).longValue();
        return orderId;
    }

    private void saveTacoToOrder( Taco taco, long orderId ){
        Map<String, Object> values = new HashMap<>();
        values.put( "tacoOrder", orderId );
        values.put( "taco", taco.getId() );
        tacoIns.execute( values );
    }
}
