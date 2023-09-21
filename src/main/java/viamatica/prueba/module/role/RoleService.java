package viamatica.prueba.module.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import viamatica.prueba.domain.Pagination;
import viamatica.prueba.exception.ResourceNotFoundException;
import viamatica.prueba.module.role.domain.Role;
import viamatica.prueba.module.user.UserService;
import viamatica.prueba.module.user.domain.User;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    @Autowired
    private UserService userService;

    public Role save(Role role) {

        role.setIdRol(Long.MIN_VALUE);

        return repository.save(role);

    }

    public Pagination<Role> findAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Role> data = repository.findAll(pageable);

        Pagination<Role> res = new Pagination<>(page, size, data.getContent());
        res.setTotalItems(data.getTotalElements());
        res.setTotalPages(data.getTotalPages());

        return res;
    }

    public List<Role> findByUserId(long userId) {

        return repository.findByUsersIdUsuario(userId);

    }

    public Role find(long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "id", "role"));

    }

    public Role addRoleToUser(long roleid, long userid) {

        Role role = this.find(userid);

        User user = userService.find(userid);

        role.addUser(user);

        return repository.save(role);
    }

    public Role delete(long id) {

        Role role = this.find(id);

        repository.deleteById(id);

        return role;

    }

    public Role update(Role role, long id) {

        Role _role = this.find(id);

        role.setIdRol(_role.getIdRol());

        return repository.save(role);

    }

}
