package com.example.demo.service;

import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class TodoService {
    @Autowired
    private TodoRepository repository;
    public String testService(){
        //return "Test Service";
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        repository.save(entity);
        TodoEntity savedEntity = repository.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }
    public List<TodoEntity> create(final TodoEntity entity){

        validate(entity);
        repository.save(entity);
        log.info("Entity Id : {} is saved", entity.getId());
        return repository.findByUserId(entity.getUserId());
    }
    //리팩토링한 메서드
    private static void validate(TodoEntity entity) {
        if (entity ==null){
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null");
        }
        if (entity.getUserId() ==null){
            log.warn("Unkown user.");
            throw new RuntimeException("Unkown user");
        }
    }
    public List<TodoEntity>retrieve(final String userId){
        return repository.findByUserId(userId);
    }
}
