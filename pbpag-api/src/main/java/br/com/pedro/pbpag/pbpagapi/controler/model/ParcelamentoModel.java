package br.com.pedro.pbpag.pbpagapi.controler.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class ParcelamentoModel {
    private Long id;
//    private String nomeCliente;
    private ClienteResumoModel cliente;
    private String descricao;
    private BigDecimal valorTotal;
    private Integer quantidadeParcelas;
    private OffsetDateTime dataCriacao;

}
