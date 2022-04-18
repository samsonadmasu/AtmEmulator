package com.Bank.Branch.Services;


import com.Bank.Branch.Http.HttpResponses;
import com.Bank.Branch.Models.Model;
import com.Bank.Branch.Utils.Reflection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class BaseService<T, K> {

    protected  T t;

    private final JpaRepository<T, K> repository;

    public BaseService(JpaRepository<T, K> repository, T t){
        this.repository = repository;
        this.t = t;
    }

    public T create(T obj){
        try {
            return repository.save(obj);
        }catch (Exception e){
            e.printStackTrace();
            throw errorWhileProcessing();
        }

    }

    public List<T> create(List<T> objs){
        List<T> ts = new ArrayList<>();
        for (T t: objs) {
            T result = create(t);
            ts.add(result);
        }
        return ts;
    }

    public T get(K id){
        Optional<T> result;
        try {
          result =  repository.findById(id);
        }catch (Exception e){
            e.printStackTrace();
           throw  errorWhileProcessing();
        }

        if(result.isEmpty()){
            throw notFund(id);
        }else{
            return result.get();
        }
    }

    public T find(K id){
        Optional<T> result;
        try{
            result = repository.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw errorWhileProcessing();
        }

        if(result.isEmpty()){
            throw notFund(id);
        }

        return result.get();
    }

    public List<T> getAll(){
        try {
            return repository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            throw errorWhileProcessing();
        }
    }

    public T update(T obj){
            Model model = Reflection.convertInstanceOfObject(obj, Model.class);
            if(!repository.existsById((K) model.getId())){
                throw notFund((K) model.getId());
            }

            try {
                return repository.save(obj);
            }catch (Exception e){
                e.printStackTrace();
                throw errorWhileProcessing();
            }

    }

    public K delete(K id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            if(repository.existsById(id)){
                throw errorWhileProcessing();
            }
            return id;
        }else {
            throw badRequest();
        }
    }

    public void deleteAll(){
        try{
            repository.deleteAll();
        }catch (Exception e){
            e.printStackTrace();
            throw errorWhileProcessing();
        }
    }

    public ResponseStatusException notFund(K id){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                HttpResponses.notFoundResponse(t, id));
    }

    public ResponseStatusException notFound(){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                HttpResponses.NO_RECORD_FOUND);
    }

    public ResponseStatusException errorWhileProcessing(){
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                HttpResponses.errorWhileProcessing(t));
    }

    public ResponseStatusException badRequest(){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                HttpResponses.badRequest(t));
    }

    public ResponseStatusException duplicationNotAllowed(String field){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, HttpResponses.duplicateResponse(t, field));
    }

}
