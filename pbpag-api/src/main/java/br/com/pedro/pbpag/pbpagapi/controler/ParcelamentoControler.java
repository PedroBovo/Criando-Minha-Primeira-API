package br.com.pedro.pbpag.pbpagapi.controler;


import br.com.pedro.pbpag.pbpagapi.controler.assembler.ParcelamentoAssembler;
import br.com.pedro.pbpag.pbpagapi.controler.model.ParcelamentoModel;
import br.com.pedro.pbpag.pbpagapi.controler.model.input.ParcelamentoInput;
import br.com.pedro.pbpag.pbpagapi.domain.model.Parcelamento;
import br.com.pedro.pbpag.pbpagapi.domain.repository.ParcelamentoRepository;
import br.com.pedro.pbpag.pbpagapi.domain.service.ParcelamentoService;
import jakarta.validation.Valid;
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
    private ParcelamentoAssembler parcelamentoAssembler;
    @GetMapping
    public List<ParcelamentoModel> listar(){
        return parcelamentoAssembler.toCollectionModel( parcelamentoRepository.findAll());
    }


    @GetMapping("/{parcelamentoId}")
    public ResponseEntity<ParcelamentoModel> bsucar(@PathVariable Long parcelamentoId){
        return parcelamentoRepository.findById(parcelamentoId)
                .map(parcelamentoAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParcelamentoModel cadastrar(@Valid  @RequestBody ParcelamentoInput parcelamentoInput){
        Parcelamento novoParcelamento = parcelamentoAssembler.toEntity(parcelamentoInput);
        Parcelamento parcelamentoCadastrado = parcelamentoService.cadastrar(novoParcelamento);

        return parcelamentoAssembler.toModel(parcelamentoCadastrado);
    }

}
