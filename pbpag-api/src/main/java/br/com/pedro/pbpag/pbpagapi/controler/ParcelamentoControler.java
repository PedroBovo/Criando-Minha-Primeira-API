package br.com.pedro.pbpag.pbpagapi.controler;


import br.com.pedro.pbpag.pbpagapi.domain.exeption.NegocioExeption;
import br.com.pedro.pbpag.pbpagapi.domain.model.Parcelamento;
import br.com.pedro.pbpag.pbpagapi.domain.repository.ParcelamentoRepository;
import br.com.pedro.pbpag.pbpagapi.domain.service.ParcelamentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoControler {

    private ParcelamentoRepository parcelamentoRepository;
    private ParcelamentoService parcelamentoService;
    @GetMapping
    public List<Parcelamento> listar(){
        return parcelamentoRepository.findAll();
    }
    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<Parcelamento> bsucar(@PathVariable Long parcelamentoId){
        return parcelamentoRepository.findById(parcelamentoId)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Parcelamento cadastrar(@RequestBody Parcelamento parcelamento){
        return parcelamentoService.cadastrar(parcelamento);
    }
    @ExceptionHandler(NegocioExeption.class)
    public ResponseEntity<String> capturar(NegocioExeption e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
