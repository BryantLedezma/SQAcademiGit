package com.exito.stepDefinitions;

import com.exito.exceptions.ResultadoNoEsperado;
import com.exito.questions.ValidateAddProduct;
import com.exito.tasks.AddProduct;
import com.exito.tasks.SearchProduct;
import com.exito.tasks.SelectedProduct;
import com.exito.userinterfaces.HomePageExito;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class buyProductsStepDefinitions {
    @Managed(driver = "chrome")
    private WebDriver navegador;
    public static Actor actor = Actor.named("Exito");

    @Before
    public void setStage() {
        actor.can(BrowseTheWeb.with(navegador));
        actor.wasAbleTo(Open.browserOn(new HomePageExito()));
        navegador.manage().window().maximize();
    }

    @Given("that the user selects the product")
    public void thatTheUserSelectsTheProduct(DataTable dataTable) {
        actor.attemptsTo(SearchProduct.homePage(dataTable));
        actor.attemptsTo(SelectedProduct.homePage());
    }

    @When("that the user adds the product to the shopping cart")
    public void thatTheUserAddsTheProductToTheShoppingCart(DataTable dataTable) {
        actor.attemptsTo(AddProduct.homePage(dataTable));
    }

    @Then("that the user views the product in the shopping cart")
    public void thatTheUserViewsTheProductInTheShoppingCart() {
        actor.should(seeThat(ValidateAddProduct.enviadaPendiente(), equalTo(true)).orComplainWith(ResultadoNoEsperado.class, ResultadoNoEsperado.ERROR_ADD_CART));
    }
}
