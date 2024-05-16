package com.exito.utils.runnerpersonalizado;

import com.exito.utils.datosexcel.BeforeSuite;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunnerPersonalizado extends Runner {
    private static final Logger logger = LogManager.getLogger(RunnerPersonalizado.class.getName());
    private final Class<CucumberWithSerenity> classValue;
    private CucumberWithSerenity cucumberWithSerenity;

    public RunnerPersonalizado(Class<CucumberWithSerenity> classValue) throws InitializationError {
        this.classValue = classValue;
        cucumberWithSerenity = new CucumberWithSerenity(classValue);
    }

    @Override
    public Description getDescription() {
        return cucumberWithSerenity.getDescription();
    }

    private void runAnnotatedMethods() throws InvocationTargetException, IllegalAccessException {

        if (!BeforeSuite.class.isAnnotation()) {
            return;
        }

        Method[] methods = this.classValue.getMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation item : annotations) {
                if (item.annotationType().equals(BeforeSuite.class)) {
                    method.invoke(null);
                    break;
                }
            }
        }
    }


    @Override
    public void run(RunNotifier notifier) {
        try {
            runAnnotatedMethods();
            cucumberWithSerenity = new CucumberWithSerenity(classValue);
        } catch (InvocationTargetException | IllegalAccessException | InitializationError e) {
            logger.info(e);
        }
        cucumberWithSerenity.run(notifier);
    }
}
