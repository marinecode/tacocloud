package tacos.web;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.support.SessionStatus;
import tacos.Order;
import tacos.data.OrderRepository;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes( "order" )
public class OrderController {

    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute  Order order, Errors errors, SessionStatus status ) {
        if( errors.hasErrors() ){
            return "orderForm";
        }

        orderRepo.save( order );
        status.setComplete();
        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}