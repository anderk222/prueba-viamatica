package viamatica.prueba.module.session;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import viamatica.prueba.domain.Pagination;
import viamatica.prueba.exception.ResourceNotFoundException;
import viamatica.prueba.module.session.domain.Session;
import viamatica.prueba.module.user.UserService;
import viamatica.prueba.module.user.domain.User;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    UserService userService;

    public Pagination<Session> findAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Session> data = sessionRepository.findAll(pageable);

        Pagination<Session> res = new Pagination<>(page, size, data.getContent());
        res.setTotalItems(data.getTotalElements());
        res.setTotalPages(data.getTotalPages());

        return res;
    }

    // Encontrar session por id de usuario

    public Session findByUser(long userid) {

        User user = userService.find(userid);

        Optional<Session> session = sessionRepository.findByUserIdUsuario(userid);

        if (session.isPresent())
            return session.get();

        Session _session = new Session();
        _session.setUser(user);

        return sessionRepository.save(_session);

    }

    // Encontrar session por mail o username de usuario

    public Session findByUser(String value) {

        User user = userService.findByUsernameOrMail(value);

        Optional<Session> session = sessionRepository.findByUserIdUsuario(user.getIdUsuario());

        if (session.isPresent())
            return session.get();

        Session _session = new Session();
        _session.setUser(user);

        return sessionRepository.save(_session);

    }

    // Encontrar session por id

    public Session find(long id) {

        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "session"));

        return session;

    }

    // Guardar session

    public Session save(Session session) {

        return sessionRepository.save(session);

    }

    // Editar session

    public Session update(Session session, long id) {

        Session updated = sessionRepository.findById(id).map((_session) -> {

            return sessionRepository.save(_session);
        })
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "session"));

        return updated;
    };

    // Eliminar sessiona por id

    public Session delete(long id) {

        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "session"));

        sessionRepository.deleteById(id);

        return session;

    }

}