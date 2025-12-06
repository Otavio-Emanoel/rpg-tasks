package com.example.rpg_tasks.repository;

import com.example.rpg_tasks.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest, Long> {
    // Espero que funcione
}
