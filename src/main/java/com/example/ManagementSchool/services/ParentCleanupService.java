package com.example.ManagementSchool.services;

import com.example.ManagementSchool.entity.Parent;
import com.example.ManagementSchool.repository.ParentRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ParentCleanupService {
    private final ParentRepo parentRepo;

    public ParentCleanupService(ParentRepo parentRepo) {
        this.parentRepo = parentRepo;
    }

    @Scheduled(cron = "0 0 2 * * *")
    public void deleteInactiveParent(){
        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);

        // Trouver les parents inactifs depuis plus de 6 mois
        // (Cela nécessite l'ajout d'une date de désactivation dans l'entité)
      //  List<Parent> parentsToDelete = parentRepo.findInactiveParentsOlderThan(sixMonthsAgo);

        // Supprimer physiquement les entités
      //  parentRepo.deleteAll(parentsToDelete);

    }
}
