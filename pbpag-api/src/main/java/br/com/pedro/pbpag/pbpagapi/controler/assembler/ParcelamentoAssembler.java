package br.com.pedro.pbpag.pbpagapi.controler.assembler;

import br.com.pedro.pbpag.pbpagapi.controler.model.ParcelamentoModel;
import br.com.pedro.pbpag.pbpagapi.controler.model.input.ParcelamentoInput;
import br.com.pedro.pbpag.pbpagapi.domain.model.Parcelamento;
import ch.qos.logback.core.model.Model;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;


@AllArgsConstructor
@Component
public class ParcelamentoAssembler {

    private final ModelMapper modelMapper;

    public ParcelamentoModel toModel(Parcelamento parcelamento){
        return modelMapper.map(parcelamento, ParcelamentoModel.class);
    }

    public List<ParcelamentoModel> toCollectionModel(List<Parcelamento> parcelamentos){
        return parcelamentos.stream()
                .map(this::toModel)
                .toList();
    }

    public Parcelamento toEntity(ParcelamentoInput parcelamentoInput){
        return modelMapper.map(parcelamentoInput, Parcelamento.class);
    }
}
