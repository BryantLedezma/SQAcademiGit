package com.exito.interactions;

import net.serenitybdd.core.time.InternalSystemClock;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Espera implements Interaction {

    private final int tiempo;

    protected Espera(int tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        new InternalSystemClock().pauseFor(tiempo);


    }

    public static Espera unTiempo(int tiempo){
        return instrumented(Espera.class,tiempo);
    }

}
