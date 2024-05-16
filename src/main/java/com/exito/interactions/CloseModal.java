package com.exito.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static com.exito.userinterfaces.HomePageExito.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CloseModal implements Interaction {
    @Override
    public <T extends Actor> void performAs(T actor) {

        if (LBL_MODAL.resolveFor(actor).isPresent()) {
            actor.attemptsTo(Espera.unTiempo(5000),
                    Click.on(BTN_DOMICILIO),
                    Enter.theValue("pruebas@gmail.com").into(TXT_EMAIL),
                    Click.on(BTN_CONFIRM),
                    Espera.unTiempo(5000));
            if (CHK_SELECT.resolveFor(actor).isPresent()) {
                actor.attemptsTo(Click.on(CHK_SELECT));
            }
            actor.attemptsTo(Click.on(BTN_CONFIRM),
                    Espera.unTiempo(5000));
        }

    }

    public static CloseModal inPage() {
        return instrumented(CloseModal.class);
    }

}
