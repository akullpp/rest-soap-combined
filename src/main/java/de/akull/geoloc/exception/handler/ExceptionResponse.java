package de.akull.geoloc.exception.handler;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ExceptionResponse {

    private UUID uuid;

    private String key;

    private String message;
}
