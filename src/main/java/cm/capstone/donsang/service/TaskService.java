package cm.capstone.donsang.service;

import cm.capstone.donsang.model.*;
import cm.capstone.donsang.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.*;
import static cm.capstone.donsang.model.Enums.*;

@Service @RequiredArgsConstructor
public class TaskService {
    private final ProfilDonneurRepository donneurs;
    private final DemandeUrgenteRepository demandes;
    private final NotificationRepository notifications;
    private final EmailService email;

    @Scheduled(cron="0 0 2 * * *")
    @Transactional
    public void traitementAutomatique(){
        LocalDate today=LocalDate.now();
        donneurs.findAll().forEach(d->{
            if(d.getDateDernierDon()!=null && !d.getDateDernierDon().plusDays(90).isAfter(today)){
                String msg="Vous êtes de nouveau éligible au don de sang.";
                notifications.save(Notification.builder().utilisateur(d.getUtilisateur()).message(msg)
                        .type(TypeNotification.ELIGIBILITE).lue(false).dateEnvoi(LocalDateTime.now()).build());
                email.send(d.getUtilisateur().getEmail(),"Vous êtes éligible au don",msg,TypeEmail.RAPPEL);
            }
        });
        demandes.findByStatutAndDateLimiteBefore(StatutDemande.OUVERTE,LocalDateTime.now())
                .forEach(d->{d.setStatut(StatutDemande.CLOTUREE); demandes.save(d);});
    }
}
