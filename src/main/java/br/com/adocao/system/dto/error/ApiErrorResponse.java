package br.com.adocao.system.dto.error;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        int status,
        String message,
        String path,
        LocalDateTime timestamp
) {
}