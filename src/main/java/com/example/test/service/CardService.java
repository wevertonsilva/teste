package com.example.test.service;

import com.example.test.dto.CardDTO;
import com.example.test.model.Card;
import com.example.test.repository.CardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardService {

    public static final String CARTA_REMOVIDA_COM_SUCESSO = "Carta removida com sucesso";
    @Autowired
    private CardRepository repository;

    private ModelMapper modelMapper = new ModelMapper();;

    public ResponseEntity<List<CardDTO>> findAll(){
        List<Card> cards = repository.findAll();
        return ResponseEntity.ok().body(cards.stream()
                .map(card -> modelMapper.map(card, CardDTO.class))
                .collect(Collectors.toList())
        );
    }

    public ResponseEntity<CardDTO> persist (CardDTO dto){
        return ResponseEntity.ok(modelMapper.map(repository.save(modelMapper.map(dto, Card.class)), CardDTO.class));
    }

    public ResponseEntity<CardDTO> findById(Long id){
        Optional<Card> card = repository.findById(id);
        if(card.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(modelMapper.map(card.get(), CardDTO.class));
    }

    public ResponseEntity<String> delete(Long id){
        Optional<Card> card = repository.findById(id);
        if(card.isEmpty())
            return ResponseEntity.notFound().build();

        repository.delete(card.get());
        return ResponseEntity.ok().body(CARTA_REMOVIDA_COM_SUCESSO);
    }

}
