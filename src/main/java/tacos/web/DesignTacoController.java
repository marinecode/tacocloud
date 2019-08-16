package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import tacos.Design;
import tacos.Order;
import tacos.Taco;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;


@Slf4j
@Controller
@SessionAttributes( value = "order")

@RequestMapping("/design")
public class DesignTacoController {

    private final IngredientRepository ingRepo;
    private final TacoRepository tacoRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingRepo, TacoRepository tacoRepo) {
        this.ingRepo = ingRepo;
        this.tacoRepo = tacoRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        Model modelNew = fillModelByIngs(model);
        modelNew.addAttribute("design", new Taco());
        return "design";
    }
    @PostMapping
    public String processDesign( @Valid Design design, @ModelAttribute Order order,
                                 Errors errors, Model model) {

        if(errors.hasErrors()){
            Model mod = fillModelByIngs(model);
            return "design";
        }
        Taco taco = new Taco( design );
        Taco savedTaco = tacoRepo.save( taco );
        order.addTaco( savedTaco );
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType( List<Ingredient> list, Ingredient.Type type ){
        return list.stream().filter( ing -> ing.getType().equals(type) )
                            .collect(Collectors.toList());
    }

    private Model fillModelByIngs( Model model ){
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        ingRepo.findAll().forEach( ingredients::add );
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        return model;
    }
}