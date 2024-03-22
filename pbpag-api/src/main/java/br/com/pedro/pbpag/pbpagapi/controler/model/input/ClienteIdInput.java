package br.com.pedro.pbpag.pbpagapi.controler.model.input;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteIdInput {

    @NotNull
    private long id;
}
