package tacos;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import tacos.data.IngredientRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class Design {
    private IngredientRepository ingRepo;

  @Autowired
    public Design(IngredientRepository ingRepo) {
        this.ingRepo = ingRepo;
    }

    @NotNull
    @Size( min = 5, message = "Name must contain at least 5 letters")
    private String name;

    @NotEmpty( message = "You must choose at least 1 ingredient")
    private List<String> ingredients;

    public List<Ingredient> getIngredients(){
        List<Ingredient> list = new ArrayList<>();
        for( String id : ingredients ){
            System.out.println( ingRepo == null);
            Ingredient ing = ingRepo.findById( id ).get();
            list.add( ing );
        }
        return list;
    }
}
