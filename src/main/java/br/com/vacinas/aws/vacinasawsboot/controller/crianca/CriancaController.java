package br.com.vacinas.aws.vacinasawsboot.controller.crianca;

import br.com.vacinas.aws.vacinasawsboot.Crianca;
import br.com.vacinas.aws.vacinasawsboot.Vacina;
import br.com.vacinas.aws.vacinasawsboot.negocio.CriancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/crianca")
public class CriancaController {

    @Autowired
    CriancaService criancaService;


    @PostMapping
    public List<Vacina> salvarCrianca(@RequestBody SalvarCriancaRequest request) {

        Crianca crianca = Crianca
                .builder()
                .nome(request.getNome())
                .dataNascimento(LocalDate.of(Integer.parseInt(request.getAno()), Integer.parseInt(request.getMes()), 1))
                .build();

        return criancaService.salvar(crianca);
    }



}
