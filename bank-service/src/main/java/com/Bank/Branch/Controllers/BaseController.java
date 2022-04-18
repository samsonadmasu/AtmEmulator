package com.Bank.Branch.Controllers;


import com.Bank.Branch.Services.BaseService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public class BaseController<T, ID> {

    private final BaseService<T, ID> customService;

    public BaseController(BaseService<T, ID> customService){
        this.customService = customService;
    }

    @PostMapping("/add")
    public T create(T obj){
        return customService.create(obj);
    }

    @GetMapping()
    public T get(ID id){
        return customService.get(id);
    }

    @GetMapping("/all")
    public List<T> getAll(){
        return customService.getAll();
    }

    @PutMapping()
    public T update(T obj){
        return customService.update(obj);
    }

    @DeleteMapping
    public ID delete (ID id){
        return customService.delete(id);
    }
}
