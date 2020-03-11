package br.com.vacinas.aws.vacinasawsboot.negocio;

import br.com.vacinas.aws.vacinasawsboot.Crianca;
import br.com.vacinas.aws.vacinasawsboot.Vacina;
import br.com.vacinas.aws.vacinasawsboot.database.CriancaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CriancaService {

    @Autowired
    CriancaRepository criancaRepository;

    @Autowired
    VacinaService vacinaService;

    public String salvar(Crianca crianca, String userId) {

        criancaRepository.salvar(crianca, userId);

        crianca = buscarPorNome(crianca.getNome());
        List<Vacina> proximaVacinas = vacinaService.calcularProximaVacina(crianca.getDataNascimento());

        String resposta = "A criança " + crianca.getNome();

        if (proximaVacinas.size() > 1) {
            resposta += " tem " + proximaVacinas.size() + " vacinas para tomar. ";
        } else {
            resposta += " tem uma vacina para tomar. ";
        }

        for (Vacina vacina : proximaVacinas) {
            resposta += vacina.resumo(crianca.getDataNascimento());
        }

        return resposta;
    }

    public Crianca buscarPorNome(String nome) {
        return criancaRepository.buscarCriancaPorNome(nome);
    }

}
