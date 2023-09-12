package viamatica.prueba.module.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import viamatica.prueba.domain.Pagination;
import viamatica.prueba.exception.ResourceNotFoundException;
import viamatica.prueba.module.session.domain.Session;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

        public Pagination<Session> findAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Session> data = sessionRepository.findAll(pageable);

        Pagination<Session> res = new Pagination<>(page, size, data.getContent());
        res.setTotalItems(data.getTotalElements());
        res.setTotalPages(data.getTotalPages());

        return res;
    }

    // Encontrar sessiona por id

    public Session find(long id) {

        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "session"));

        return session;

    }

    // Guardar sessiona 

    public Session save(Session session) {

        return sessionRepository.save(session);

    }

    // Editar sessiona

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