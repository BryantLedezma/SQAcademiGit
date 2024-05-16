package com.exito.tasks;

import com.exito.interactions.CloseModal;
import com.exito.interactions.Espera;
import com.exito.utils.datos.GenerateData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Scroll;

import static com.exito.userinterfaces.HomePageExito.btnPosicionProducto;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SelectedProduct implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Espera.unTiempo(8000),
                CloseModal.inPage());
        String position = String.valueOf(GenerateData.posicion());
        actor.attemptsTo(
                Scroll.to(btnPosicionProducto(position)),
                Click.on(btnPosicionProducto(position)));
    }

    public static SelectedProduct homePage() {
        return instrumented(SelectedProduct.class);
    }

}
