package com.exito.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Task2PruebaCommitRama2 implements Task {
    private final DataTable dataTable;
    String nameProduct;

    public Task2PruebaCommitRama2(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

    }

    public static Task2PruebaCommitRama2 homePage(DataTable dataTable) {
        return instrumented(Task2PruebaCommitRama2.class, dataTable);
    }

}
