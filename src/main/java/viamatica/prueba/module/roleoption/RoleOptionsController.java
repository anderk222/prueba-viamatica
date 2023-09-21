package viamatica.prueba.module.roleoption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import viamatica.prueba.domain.Pagination;
import viamatica.prueba.module.roleoption.domain.RolOptions;


@RequestMapping("/api/roleopt")
@CrossOrigin("*")
@RestController
public class RoleOptionsController {

    @Autowired
    private RolOptionsService service;

    @GetMapping("/{id}")
    public RolOptions find(@PathVariable long id) {

        return service.find(id);
    }

    @GetMapping
    public Pagination<RolOptions> findAlll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size) {

        return service.findAll(page, size);

    }

    @PostMapping("/add/{roloptId}/role/{rolId}")

    public RolOptions addRole(long roloptId, long rolId) {

        return service.addRole(roloptId, rolId);

    }

}
