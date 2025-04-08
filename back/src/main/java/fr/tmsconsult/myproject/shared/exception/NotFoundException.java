package fr.tmsconsult.myproject.shared.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
@Data
@RequiredArgsConstructor
public class NotFoundException extends RuntimeException {
    private final String objectName;
}

