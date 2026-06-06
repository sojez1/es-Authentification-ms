package com.jpstechno.auth_ms.controlleurs;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpstechno.auth_ms.modeles.Acteurs;

@RestController
@RequestMapping("/acteurs")
public class ActeurControl {

    @PostMapping("/register")
    public Acteurs register(Acteurs acteurEcoles) {
        return null;
    }

}
