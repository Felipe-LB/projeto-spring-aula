package br.com.senac.projeto_spring_aula.saudacao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaudacaoService {
    private final Saudacao saudacao;

    public String executar(){
        return saudacao.mensagem();
    }
}
