package com.exito.questions;

import com.exito.interactions.Espera;
import com.exito.userinterfaces.HomePageExito;
import com.exito.utils.constants.Constants;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.Click;

import static com.exito.userinterfaces.HomePageExito.LBL_DESCRIPCION_PRODUCTO_CART;

public class ValidateAddProduct implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.attemptsTo(Click.on(HomePageExito.BTN_CART_LINK),
                Espera.unTiempo(8000));
        String product = LBL_DESCRIPCION_PRODUCTO_CART.resolveFor(actor).getText().replace(" ", "").toUpperCase();
        String nameProduct = actor.recall(Constants.NAME_PRODUCT);
        return nameProduct.replace(" ", "").toUpperCase().equals(product);
    }

    public static ValidateAddProduct enviadaPendiente() {
        return new ValidateAddProduct();
    }

}
