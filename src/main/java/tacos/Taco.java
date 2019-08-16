package tacos;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    @Column( name = "createdAt" )
    private Date createdAt;

    @NotNull
    @Size( min = 5, message = "Name must contain at least 5 letters")
    private String name;

    @ManyToMany( targetEntity = Ingredient.class )
    @NotEmpty( message = "You must choose at least 1 ingredient")
    @Fetch( FetchMode.JOIN )
    private List<Ingredient> ingredients;

    public Taco(){};

    public Taco( Design design ){
        name = design.getName();
        ingredients = design.getIngredients();
    }

    @PrePersist
    void createdAt(){
        createdAt = new Date();
    }
}
