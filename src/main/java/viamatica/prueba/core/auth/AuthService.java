package viamatica.prueba.core.auth;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import viamatica.prueba.core.auth.domain.Authentication;
import viamatica.prueba.exception.AuthAttempsExceededException;
import viamatica.prueba.exception.ResourceNotFoundException;
import viamatica.prueba.module.person.PersonRepository;
import viamatica.prueba.module.person.domain.Person;
import viamatica.prueba.module.session.SessionService;
import viamatica.prueba.module.user.UserService;
import viamatica.prueba.module.user.domain.User;

@Service
public class AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    SessionService sessionService;

    @Autowired
    JwtEncoder encoder;

    // Regsitro

    public ResponseEntity<String> singup(Person user) {

        user.setIdPersona(Long.MIN_VALUE);
        personRepository.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED).body("Usuario registrado");
    }

    // Authenticacion it return a json web token

    public String authenticate(Authentication auth) throws RuntimeException {
        Instant now = Instant.now();
        long expiry = 36000L;

        User user = userService.findByUsernameOrMail(auth.getUsername());

        verifyFailedAuth(user);

        boolean pass = user.getPassword().equalsIgnoreCase(auth.getPassword());

        if (!pass) {

           // userService.updateAttemps(user.getLogueosIncorrectos()+1, user.getIdUsuario());

            throw new ResourceNotFoundException("", "usuario o contrasena", "user");

        }

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(auth.getUsername())
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    };

    // Verify user have more than 3 failed authentications

    public void verifyFailedAuth(User user) {

        if (user.getLogueosIncorrectos() > 3)
            throw new AuthAttempsExceededException();

    }

}
