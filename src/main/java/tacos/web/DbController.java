package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.Taco;
import tacos.data.TacoRepository;


import java.util.List;

//@Controller
@RequestMapping("db")
public class DbController {

    private TacoRepository tacoRepo;

    @Autowired
    public DbController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping
    private String showDataBase( Model model ){
      Iterable<Taco> tacos =  tacoRepo.findAll();
      model.addAttribute("tacos", tacos );
      return "data";
    }
}
