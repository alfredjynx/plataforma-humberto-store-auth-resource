package humberto.loja.auth;

import org.springframework.beans.factory.annotation.Autowired;
// import java.security.NoSuchAlgorithmException;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AuthResource implements AuthController{

    @Autowired
    private AuthService authService;

    @Override
    public ResponseEntity<?> create(RegisterIn in) {

        final String password = in.password().trim();

        if(null==password | password.isEmpty()) throw new IllegalArgumentException("Password is Empty");
        if(password.length() < 4) throw new IllegalArgumentException("Password Must be At Least 4 Characters Long");

        final String id = authService.register(Register.builder()
            .name(in.name())
            .email(in.email())
            .password(in.password())
            .build()
        );

        return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri())
            .build();
            
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public ResponseEntity<LoginOut> authenticate(CredentialIn in) {

        return ResponseEntity.ok(authService.authenticate(in.email(),in.password()));

        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }

}

   