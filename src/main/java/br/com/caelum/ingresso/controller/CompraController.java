package br.com.caelum.ingresso.controller;

import br.com.caelum.ingresso.dao.CompraDao;
import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.Cartao;
import br.com.caelum.ingresso.model.Compra;
import br.com.caelum.ingresso.model.form.CarrinhoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Transactional
public class CompraController {

    @Autowired
    private Carrinho carrinho;

    @Autowired
    private SessaoDao sessaoDao;

    @Autowired
    private LugarDao lugarDao;

    @Autowired
    private CompraDao compraDao;

    @GetMapping("/compra")
    public ModelAndView checkout() {
        ModelAndView modelAndView = new ModelAndView("/compra/pagamento");

        modelAndView.addObject("carrinho", carrinho);

        return modelAndView;
    }

    @PostMapping("/compra/ingressos")
    public ModelAndView mandaParaPagamentos(CarrinhoForm carrinhoForm) {

        ModelAndView modelAndView = new ModelAndView("redirect:/compra");

        carrinhoForm.toIngressos(sessaoDao, lugarDao).forEach(carrinho::add);

        return modelAndView;
    }

    @PostMapping("/compra/comprar")
    public ModelAndView comprar(Cartao cartao, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");

        if (cartao.isValido()) {
            Compra compra = new Compra(carrinho.getIngressos());
            compraDao.save(compra);
        } else {
            result.rejectValue("vencimento","Vencimento inválido");
            return checkout();
        }

        return modelAndView;
    }

//    @GetMapping("/compra")
//    public ModelAndView checkout(Cartao cartao) {
//        ModelAndView modelAndView = new ModelAndView("/");
//        modelAndView.addObject("cartao",cartao);
//        return modelAndView;
//
//    }

}
