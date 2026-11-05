package com.raposo.mensagensSMS.api.controller;

import com.raposo.mensagensSMS.api.dto.SmsMessageInDTO;
import com.raposo.mensagensSMS.api.dto.SmsMessageOutDTO;
import com.raposo.mensagensSMS.business.service.SmsMessageService;
import com.raposo.mensagensSMS.infrastructure.enums.StatusEnvio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensagens")
@RequiredArgsConstructor
@Tag(name = "Mensagens", description = "Cadastro e busca de mensagens")
public class SmsMessageController {

    private final SmsMessageService smsMessageService;

    @PostMapping
    @Operation(summary = "Salvar mensagem", description = "Cria uma nova mensagem")
    @ApiResponse(responseCode = "201", description = "Mensagem salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<SmsMessageOutDTO> salvarMensagem(@RequestBody SmsMessageInDTO dto){
       return ResponseEntity.ok(smsMessageService.gravar(dto));
    }

    @PatchMapping
    @Operation(summary = "Atualizar status mensagens", description = "Atualiza o status de envio das mensagem por ID")
    public ResponseEntity<SmsMessageOutDTO> atualizarStatus(@RequestParam Long id, @RequestParam StatusEnvio statusEnvio){
        return ResponseEntity.ok(smsMessageService.atualizarStatus(id,statusEnvio));
    }

    @GetMapping
    @Operation(summary = "Buscar mensagens por status das últimas 24 horas", description = "Retorne as mensagens com um status específico das últimas 24 horas.")
    public ResponseEntity<List<SmsMessageOutDTO>> buscarPorPeriodoStatus(@RequestParam StatusEnvio statusEnvio){
        return ResponseEntity.ok(smsMessageService.buscarMensagemPorPeriodoStatus(statusEnvio));
    }


}
