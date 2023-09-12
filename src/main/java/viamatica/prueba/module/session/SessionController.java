package viamatica.prueba.module.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/session")
@CrossOrigin({"*"})
public class SessionController {
    
    @Autowired 
    private SessionService sessionService;

    //

}
