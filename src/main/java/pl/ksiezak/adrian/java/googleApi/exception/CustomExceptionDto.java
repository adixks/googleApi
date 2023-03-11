package pl.ksiezak.adrian.java.googleApi.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomExceptionDto {
    private LocalDateTime timestamp;
    private int status;
    private List<String> messages;
    private String path;
    private String method;
}