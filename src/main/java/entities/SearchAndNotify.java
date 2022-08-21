package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;
import java.net.URI;

@Data
@AllArgsConstructor
public class SearchAndNotify {
    private Long id;
    private URI page;
    private String textRegex;
    private String email;
}
