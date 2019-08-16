package tacos.data;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import org.springframework.stereotype.Repository;

import tacos.Ingredient;
import tacos.Taco;

import javax.swing.text.StyledEditorKit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//@Repository
public class JdbcTacoRepository {
    private JdbcTemplate jdbc;

    public JdbcTacoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
//    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for (Ingredient ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient.getId(), tacoId);
        }
        return taco;
    }


    private long saveTacoInfo(Taco taco) {
            taco.setCreatedAt(new Date());
            PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into Taco (name, createdAt) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP);
            pscf.setReturnGeneratedKeys(true);
            PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList( taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update( psc, keyHolder );

            return keyHolder.getKey().longValue();
            }
    private void saveIngredientToTaco( String ingredientId, long tacoId ) {
            jdbc.update(
            "insert into Taco_Ingredients (taco, ingredient) " +
            "values (?, ?)",
            tacoId, ingredientId);
            }

    public List<Taco> findAll(){
       List<Taco> tacos = jdbc.query("select * from Taco", this::mapTaco);
       return tacos;
    }

    private Taco mapTaco(ResultSet set, int num) throws SQLException {
        Taco t = new Taco();
        t.setId(set.getLong("id"));
        t.setName( set.getString("name"));
        t.setCreatedAt( set.getDate("createdAt"));
        return t;
    }

}
