package com.exito.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class TaskPruebaCommit implements Task {
    private final DataTable dataTable;
    String nameProduct;

    public TaskPruebaCommit(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

    }

    public static TaskPruebaCommit homePage(DataTable dataTable) {
        return instrumented(TaskPruebaCommit.class, dataTable);
    }

}
