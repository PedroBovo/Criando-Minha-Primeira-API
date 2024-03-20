package br.com.pedro.pbpag.pbpagapi.domain.service;

import br.com.pedro.pbpag.pbpagapi.domain.exeption.NegocioExeption;
import br.com.pedro.pbpag.pbpagapi.domain.model.Cliente;
import br.com.pedro.pbpag.pbpagapi.domain.model.Parcelamento;
import br.com.pedro.pbpag.pbpagapi.domain.repository.ClienteRepository;
import br.com.pedro.pbpag.pbpagapi.domain.repository.ParcelamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ParcelamentoService {
    private final ParcelamentoRepository parcelamentoRepository;
    private final CadastroClienteService cadastroClienteService;

    @Transactional
    public Parcelamento cadastrar(Parcelamento novoParcelamento){
        if (novoParcelamento.getId() !=null){
            throw new NegocioExeption("Parcelamento a ser criado não deve possuir um codigo");
        }

        Cliente cliente = cadastroClienteService.buscar(novoParcelamento.getCliente().getId());

        novoParcelamento.setCliente(cliente);
        novoParcelamento.setDataCriacao(LocalDateTime.now());

        return parcelamentoRepository.save(novoParcelamento);
    }
}
