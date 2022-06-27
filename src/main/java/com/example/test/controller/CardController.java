package com.example.test.controller;

import com.example.test.dto.CardDTO;
import com.example.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService service;

    @GetMapping
    public ResponseEntity<List<CardDTO>> findAll(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<CardDTO> persist (@RequestBody @Valid CardDTO dto){
        return service.persist(dto);
    }

    @PutMapping
    public ResponseEntity<CardDTO> update (@RequestBody @Valid CardDTO dto){
        return ResponseEntity.ok().body(CardDTO.builder().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDTO> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return service.delete(id);
    }

}
