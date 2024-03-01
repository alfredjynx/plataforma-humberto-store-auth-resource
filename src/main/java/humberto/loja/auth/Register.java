package humberto.loja.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Accessors(fluent = true, chain = true)
public class Register {
    String name;
    String email;
    String password;
}
