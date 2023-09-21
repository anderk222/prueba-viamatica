package viamatica.prueba.module.roleoption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import viamatica.prueba.domain.Pagination;
import viamatica.prueba.exception.ResourceNotFoundException;
import viamatica.prueba.module.role.RoleService;
import viamatica.prueba.module.role.domain.Role;
import viamatica.prueba.module.roleoption.domain.RolOptions;

@Service
public class RolOptionsService {

    @Autowired
    private RolOptionsRepository repository;

    private RoleService roleService;

    public RolOptions find(long id){


        return repository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException(id, "id", "role option"));
    }

    public Pagination<RolOptions> findAll(int page, int size){

            Pageable pageable = PageRequest.of(page, size);

        Page<RolOptions> data = repository.findAll(pageable);

        Pagination<RolOptions> res = new Pagination<>(page, size, data.getContent());
        res.setTotalItems(data.getTotalElements());
        res.setTotalPages(data.getTotalPages());

        return res;

    }


    public RolOptions addRole(long roloptId,long rolId){

        Role role = roleService.find(rolId);

        RolOptions rolOptions = this.find(role.getIdRol());

        rolOptions.addRole(role);

        return repository.save(rolOptions); 

    }
    
}
