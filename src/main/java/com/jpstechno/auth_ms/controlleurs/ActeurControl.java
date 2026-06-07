package com.jpstechno.auth_ms.controlleurs;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.auth_ms.modeles.Acteurs;
import com.jpstechno.auth_ms.services.ActeurServ;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/acteurs")
public class ActeurControl {

    private final ActeurServ acteurServ;

    @PostMapping("/enregistrer/nouveau")
    @PreAuthorize("permitAll()")
    public Acteurs register(@RequestBody Acteurs acteur) {
        return acteurServ.enregistrer(acteur);
    }

    @GetMapping("/newActeur/confirmer/email/{id}/{token}")
    @PreAuthorize("permitAll()")
    public void confirmerEmail(@PathVariable("id") long id, @PathVariable("token") String token) {
        acteurServ.confirmationEmail(id, token);
    }

    @GetMapping("/liste/ecole/{school_id}")
    @PreAuthorize("hasRole('WEBMASTER') or #id == authentication.principal.id")
    public List<Acteurs> getAllActeurParEcole(@PathVariable("id") long school_id) {
        return acteurServ.listeActeurParEcole(school_id);
    }

    @GetMapping("courant/{id}")
    @PreAuthorize("hasRole('WEBMASTER' or #id == authentication.principal.id)") // if current user id = param id
    public Acteurs getCurrentActeurDetails(@PathVariable long id) {
        return acteurServ.rechercherActeurParId(id);

    }

}
