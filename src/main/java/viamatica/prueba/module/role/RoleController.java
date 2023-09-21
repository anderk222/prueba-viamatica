package viamatica.prueba.module.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import viamatica.prueba.domain.Pagination;
import viamatica.prueba.module.role.domain.Role;

@RequestMapping("/api/role")
@CrossOrigin("*")
@RestController
public class RoleController {

    @Autowired
    private RoleService service;

    @PostMapping()
    public ResponseEntity<Role> save(@RequestBody Role role){

        Role res = service.save(role);

        return ResponseEntity.status(201).body(res);

    }


    @PostMapping("/add/{userId}/user/{roleId}")
    public Role addRoleToUser(@PathVariable long roleId,@PathVariable long userId) {

        return service.addRoleToUser(userId, userId);
    }

     @GetMapping("/{id}")
    public Role find(@PathVariable long id) {

        return service.find(id);

    }

    @DeleteMapping("/{id}")
    public Role delete(@PathVariable long id) {

        return service.delete(id);

    }

    @PutMapping("/{id}")
    public Role update(@RequestBody Role role,@PathVariable long id) {

        return service.update(role, id);

    }

    @GetMapping("/{user}/user")
    public List<Role> findByUser(@PathVariable long user){

        return service.findByUserId(user);


    }

    @GetMapping()
    public Pagination<Role> findAlll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {

        return service.findAll(page, size);

    }
}
