package com.exito.tasks;

import com.exito.interactions.CloseModal;
import com.exito.interactions.Espera;
import com.exito.models.ModelInformacionUer;
import com.exito.utils.constants.Constants;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static com.exito.userinterfaces.HomePageExito.BTN_AGREGAR;
import static com.exito.userinterfaces.HomePageExito.txtDescripcionProducto;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AddProduct implements Task {
    private final DataTable dataTable;
    String nameProduct;

    public AddProduct(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
//                WaitUntil.the(txtDescripcionProducto(new ModelInformacionUer(dataTable).getProductos()), WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds(),
                Espera.unTiempo(8000));
        if (txtDescripcionProducto(new ModelInformacionUer(dataTable).getProductos()).resolveFor(actor).isPresent()) {
            nameProduct = txtDescripcionProducto(new ModelInformacionUer(dataTable).getProductos()).resolveFor(actor).getText();
        }
        if (txtDescripcionProducto(new ModelInformacionUer(dataTable).getProductos().toUpperCase()).resolveFor(actor).isPresent()) {
            nameProduct = txtDescripcionProducto(new ModelInformacionUer(dataTable).getProductos().toUpperCase()).resolveFor(actor).getText();
        }
        actor.remember(Constants.NAME_PRODUCT, nameProduct);
        actor.attemptsTo(
                Click.on(BTN_AGREGAR),
                Espera.unTiempo(4000),
                CloseModal.inPage());
        if (BTN_AGREGAR.resolveFor(actor).isPresent()) {
            actor.attemptsTo(Click.on(BTN_AGREGAR));
        }

    }

    public static AddProduct homePage(DataTable dataTable) {
        return instrumented(AddProduct.class, dataTable);
    }

}
