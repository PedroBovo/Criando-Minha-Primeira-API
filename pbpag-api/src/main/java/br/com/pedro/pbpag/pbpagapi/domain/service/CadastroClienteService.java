package br.com.pedro.pbpag.pbpagapi.domain.service;

import br.com.pedro.pbpag.pbpagapi.domain.exeption.NegocioExeption;
import br.com.pedro.pbpag.pbpagapi.domain.model.Cliente;
import br.com.pedro.pbpag.pbpagapi.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CadastroClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente buscar(Long clienteId){
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NegocioExeption("Cliente não encontrado"));
    }

    @Transactional
    public Cliente salvar(Cliente cliente){
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .filter(c ->!c.equals(cliente))
                .isPresent();
        if(emailEmUso){
            throw new NegocioExeption("Ja existe um negocio cadastrado com esse E-mail");
        }
        return clienteRepository.save(cliente);
    }
    @Transactional
    public void excluir(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }
}
