package com.exito.tasks;

import com.exito.interactions.Espera;
import com.exito.models.ModelInformacionUer;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static com.exito.userinterfaces.HomePageExito.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SearchProduct implements Task {
    private final DataTable dataTable;

    public SearchProduct(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Espera.unTiempo(5000));
        if (BTN_CLOSED.resolveFor(actor).isPresent()) {
            actor.attemptsTo(Click.on(BTN_CLOSED));
        }
        actor.attemptsTo(Click.on(TXT_BARRA_BUSQUEDA),
                Enter.theValue(new ModelInformacionUer(dataTable).getProductos()).into(TXT_BARRA_BUSQUEDA));
        if (BTN_BUSQUEDA.resolveFor(actor).isPresent()) {
            actor.attemptsTo(Click.on(BTN_BUSQUEDA));
        }
        if (LBL_TODOS_LOS_RESULTADOS.resolveFor(actor).isPresent()) {
            actor.attemptsTo(Click.on(LBL_TODOS_LOS_RESULTADOS));
        }
    }

    public static SearchProduct homePage(DataTable dataTable) {
        return instrumented(SearchProduct.class, dataTable);
    }

}
