package humberto.loja.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import humberto.loja.account.AccountController;
import humberto.loja.account.AccountIn;
import humberto.loja.account.AccountOut;
import humberto.loja.account.LoginIn;

@Service
public class AuthService {

    @Autowired
    private AccountController accountController;

    @Autowired
    private JwtService jwtService;
    
    public String register(Register in){
        
        return accountController.create(AccountIn.builder()
            .name(in.name())
            .email(in.email())
            .password(in.password())
            .build()
        ).getBody().id();


    }


    public LoginOut authenticate(String email, String password){
        ResponseEntity<AccountOut> response = accountController.login(LoginIn.builder()
        .email(email)
        .password(password)
        .build());

        if(response.getStatusCode().isError()) throw new IllegalArgumentException("Invalid Credentials");
        if(null == response.getBody()) throw new IllegalArgumentException("Invalid Credentials");

        final AccountOut account = response.getBody();

        // Cria um token JWT
        final String token = jwtService.create(account.id(), account.name(), "user");

        return LoginOut.builder().
            token(token).
            build();
            
    }
}
