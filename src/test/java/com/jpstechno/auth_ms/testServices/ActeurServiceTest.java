package com.jpstechno.auth_ms.testServices;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpstechno.auth_ms.modeles.Acteurs;
import com.jpstechno.auth_ms.modeles.Sexes;
import com.jpstechno.auth_ms.repositories.ActeurRepos;
import com.jpstechno.auth_ms.services.ActeurServ;

@SpringBootTest
public class ActeurServiceTest {

    @Mock
    private ActeurRepos acteuRepo;

    @InjectMocks
    private ActeurServ acteurServ;

    @Test
    public void addUser() {

        // Arrange
        Acteurs usr = new Acteurs();
        usr.setEmailPersonnel("sojez");
        usr.setNom("sovi");
        usr.setPrenom("Jacob");
        usr.setSexe(Sexes.MASCULIN);
        usr.setTelephone("514");

        when(acteuRepo.findByEmail(usr.getEmailPersonnel())).thenReturn(Optional.empty());
        when(acteuRepo.save(any(Acteurs.class))).thenReturn(usr);

        // act
        Acteurs savedUser = acteurServ.enregistrer(usr);

        // Assert
        assertNotNull(savedUser);

    }

}
