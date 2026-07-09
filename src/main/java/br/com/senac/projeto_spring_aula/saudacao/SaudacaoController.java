package br.com.senac.projeto_spring_aula.saudacao;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saudacao")
@RequiredArgsConstructor
public class SaudacaoController {

    private final SaudacaoService saudacaoService;

    @GetMapping
    public String exibirSaudacao(){
        return saudacaoService.executar();
    }
}
