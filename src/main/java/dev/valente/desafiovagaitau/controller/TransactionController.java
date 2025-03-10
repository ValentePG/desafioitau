package dev.valente.desafiovagaitau.controller;

import dev.valente.desafiovagaitau.config.openapi.SchemaBadRequest;
import dev.valente.desafiovagaitau.config.openapi.SchemaUnprocessableEntity;
import dev.valente.desafiovagaitau.domain.Transaction;
import dev.valente.desafiovagaitau.dto.TransactionDTO;
import dev.valente.desafiovagaitau.service.TransactionService;
import dev.valente.desafiovagaitau.validator.ValidTransactionInThePast;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;

@RestController
@RequestMapping("transacao")
@Log4j2
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final ValidTransactionInThePast validator;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Por favor atente-se a data e ao horário da transação que será enviada para o teste, " +
                    "modifique o exemplo para que a transação seja criada " +
                    "o mais próximo do horário e da data que estiver testando," +
                    " caso contrário não irá funcionar corretamente " +
                    "(Você pode pedir ao GPT ou a alguma outra IA para gerar um OffsetDateTime na data e no horário atual)",
            summary = "Salvar transação",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Json correto para requisição ser aceita",
                    required = true,
                    content = @Content(schema = @Schema(implementation = TransactionDTO.class))),
            responses = {
                    @ApiResponse(description = "Transação salva",
                            responseCode = "201"),
                    @ApiResponse(description = "Retorna Unprocessable entity se a transação for feita no futuro ou " +
                            "se o valor for negativo",
                            responseCode = "422",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SchemaUnprocessableEntity.class))),
                    @ApiResponse(description = "Retorna bad request se o json for inválido",
                            responseCode = "400",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SchemaBadRequest.class))),
            }
    )
    public ResponseEntity<Void> saveTransaction(@RequestBody @Valid TransactionDTO transactionDTO) {
        log.debug("Recebida nova transação: {}", transactionDTO);

        var formattedData = transactionDTO.dataHora()
                .atZoneSameInstant(ZoneId.of("America/Sao_Paulo")).toOffsetDateTime();

        validator.validTransactionInThePast(formattedData);
        log.debug("Data e hora formatadas para America/Sao_Paulo: {}", formattedData);

        var transaction = Transaction.builder()
                .valor(transactionDTO.valor())
                .dataHora(formattedData)
                .build();
        log.debug("DTO mapeado para entidade: {}", transaction);

        transactionService.saveTransaction(transaction);
        log.info("Transação salva com sucesso. Valor: {}, Data: {}",
                transaction.getValor(), transaction.getDataHora());

        return ResponseEntity.status(201).build();
    }

    @DeleteMapping
    @Operation(
            summary = "Deletar transações",
            responses = {
                    @ApiResponse(description = "Deletar transações",
                            responseCode = "200")
            }
    )
    public ResponseEntity<Void> deleteTransaction() {


        transactionService.clearAll();
        log.info("Fila limpa com sucesso");

        return ResponseEntity.ok().build();
    }
}
