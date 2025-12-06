package com.example.rpg_tasks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rpg_tasks.model.Quest;
import com.example.rpg_tasks.repository.QuestRepository;

@RestController
@RequestMapping("/quests")
public class QuestController {

    @Autowired
    private QuestRepository repository;

    @GetMapping
    public List<Quest> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Quest criar(@RequestBody Quest quest) {
        if (quest.getXpReward() == 0) {
            quest.setXpReward(10);
        }
        return repository.save(quest);
    }

    @PutMapping("/{id}/completar")
    public Quest completar(@PathVariable Long id) {
        return repository.findById(id).map(quest -> {
            quest.setCompletada(true);
            // Aqui você poderia somar o XP ao usuário no futuro
            return repository.save(quest);
        }).orElse(null);
    }
    
    // Deletar Quest
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
