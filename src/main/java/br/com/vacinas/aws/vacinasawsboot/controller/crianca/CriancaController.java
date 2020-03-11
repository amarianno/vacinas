package br.com.vacinas.aws.vacinasawsboot.controller.crianca;

import br.com.vacinas.aws.vacinasawsboot.Crianca;
import br.com.vacinas.aws.vacinasawsboot.Mensagem;
import br.com.vacinas.aws.vacinasawsboot.Vacina;
import br.com.vacinas.aws.vacinasawsboot.negocio.CriancaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/crianca")
public class CriancaController {

    @Autowired
    CriancaService criancaService;

    @PostMapping
    public Mensagem salvarCrianca(@RequestBody SalvarCriancaRequest request) {

        //Crianca crianca = Crianca.builder().nome("Julianinha").dataNascimento(LocalDate.now()).build();

        Crianca crianca = Crianca
                .builder()
                .nome(request.getNome())
                .dataNascimento(LocalDate.of(Integer.parseInt(request.getAno()), Integer.parseInt(request.getMes()), 1))
                .build();

        return Mensagem.builder().mensagem(criancaService.salvar(crianca, request.getUserId())).build();
    }



}
