package team5.azienda.energia.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team5.azienda.energia.entities.User;
import team5.azienda.energia.exceptions.UnauthorizedException;
import team5.azienda.energia.payloads.LoginDTO;
import team5.azienda.energia.tools.JWT;

@Service
public class SecurityService {
    @Autowired
    private UserService userService;

    @Autowired
    private JWT jwt;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        User found = this.userService.findByEmail(body.email());

        if (bcrypt.matches(body.password(), found.getPassword())) {
            return jwt.createToken(found);
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}
