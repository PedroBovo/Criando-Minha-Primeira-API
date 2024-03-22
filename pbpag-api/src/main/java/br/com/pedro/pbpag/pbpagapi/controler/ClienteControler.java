package br.com.pedro.pbpag.pbpagapi.controler;

import br.com.pedro.pbpag.pbpagapi.domain.exeption.NegocioExeption;
import br.com.pedro.pbpag.pbpagapi.domain.model.Cliente;
import br.com.pedro.pbpag.pbpagapi.domain.repository.ClienteRepository;
import br.com.pedro.pbpag.pbpagapi.domain.service.CadastroClienteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PostLoad;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/clientes")
@AllArgsConstructor
@RestController
public class ClienteControler {

    private final CadastroClienteService cadastroClienteService;

    private final ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> teste() {
        return clienteRepository.findAll();
//        return  clienteRepository.findAll();

    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> busca(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return cadastroClienteService.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable long clienteId,
                                             @Valid @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);
        cliente = cadastroClienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }
    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> excluir(@PathVariable long clienteId){
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        cadastroClienteService.excluir(clienteId);
        return ResponseEntity.noContent().build();
    }



}
