package com.exito.userinterfaces;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("https://www.exito.com/")
public class HomePageExito extends PageObject {

    public static final Target TXT_BARRA_BUSQUEDA = Target.the("Barra de busqueda pagina principal").located(By.xpath("//input[@placeholder='Buscar en exito.com']"));
    public static final Target BTN_CLOSED = Target.the("Boton cerrar modal inicial").located(By.xpath("//div[@id='wps-overlay-close-button']"));
    public static final Target BTN_BUSQUEDA = Target.the("Boton busqueda barra espaciadora").located(By.xpath("//span[@class='c-muted-2 fw5 flex items-center t-body bg-base  vtex-input__suffix br2 bl-0 br--right pr5 pl4 ']"));
    public static final Target LBL_TODOS_LOS_RESULTADOS = Target.the("opcion ver todos los resultados").located(By.xpath("//a[contains(.,'Ver todos los resultados')]"));
    public static final Target LBL_MODAL = Target.the("Modal envio pedido").located(By.xpath("//header[contains(.,'mo quieres recibir tu pedido')]"));
    public static final Target BTN_CONFIRM = Target.the("Boton confirmar").located(By.xpath("//button[contains(.,'Confirmar')]"));

    public static final Target CHK_SELECT = Target.the("CHECK CONFIRMACION DIRECCION").located(By.xpath("//input[@id='addressInput-0']"));

    public static final Target BTN_DOMICILIO = Target.the("Boton opcion domicilio").located(By.xpath("//button[contains(@class,'inactive')]"));
    public static final Target TXT_EMAIL = Target.the("Campo ingreso email").located(By.xpath("//input[@placeholder='cuenta@correo.com']"));
    public static final Target BTN_CART_LINK = Target.the("Boton link carrito de compras").located(By.xpath("//span[contains(.,'Carrito')]"));
    public static final Target BTN_AGREGAR = Target.the("Boton agregar producto").located(By.xpath("(//span[contains(.,'Agregar')])[1]"));
    public static final Target LBL_DESCRIPCION_PRODUCTO_CART = Target.the("Descripcion producto carrito de compras").located(By.xpath("//div[@data-molecule-product-detail-name='true']"));

    public static Target btnPosicionProducto(String posicion) {
        return Target.the("Selecciona tipo de poliza posicion").located(By.xpath("(//div[contains(@data-testid,'product')])[" + posicion + "]"));
    }

    public static Target txtDescripcionProducto(String nombre) {
        return Target.the("Selecciona tipo de poliza posicion").located(By.xpath("(//h1[contains(.,'" + nombre + "')])[1]"));
    }
}
