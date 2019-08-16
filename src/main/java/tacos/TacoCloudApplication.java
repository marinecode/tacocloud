package tacos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TacoCloudApplication {
    public static void main(String[] args) throws Exception {
       ApplicationContext ctx = SpringApplication.run(TacoCloudApplication.class, args);


    }
//    @Bean
//    public CommandLineRunner commandLineRunner ( TacoRepository repo, IngredientRepository ingrepo ){
//        return args -> {
//            System.out.println("Im HERE");
//            Taco t = new Taco();
//            t.setCreatedAt( new Date() );
//            List<Ingredient> ing = new ArrayList<>();
//            ing.add( ingrepo.findById( "SLSA").get() );
//            t.setIngredients( ing );
//            t.setName( " Main ");
//            System.out.println("Before saving");
//            repo.save( t );
//            System.out.println("After saving");
//            System.out.println( repo.findAll());
//        };
//    }


}