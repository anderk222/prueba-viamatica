package viamatica.prueba.module.roleoption;

import org.springframework.beans.factory.annotation.Autowired;

import viamatica.prueba.domain.Pagination;
import viamatica.prueba.module.roleoption.domain.RolOptions;

public class RolOptionsService {


    @Autowired
    private RolOptionsRepository repository;

    // public Pagination<RolOptions> findAll(int page, int size){

    //     return repository.findAll();

    // }
    
}
