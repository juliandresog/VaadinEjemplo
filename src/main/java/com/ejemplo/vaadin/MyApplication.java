/*
 * MyApplication.java
 *
 * Created on 12 de abril de 2011, 04:09 PM
 */
package com.ejemplo.vaadin;

import com.ejemplo.vaadin.admusuarios.FormularioUsuario;
import com.ejemplo.vaadin.servicios.ServicioUsuarios;
import com.vaadin.Application;
import com.vaadin.ui.*;
import com.vaadin.data.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author josorio
 * @version
 */
public class MyApplication extends Application {

    @Autowired
    ServicioUsuarios servicioUsuarios;

    @Override
    public void init() {
        Window mainWindow = new Window("MyApplication");
        Label label = new Label("Ejemplo de Vaadin, Spring e Hibernate");
        mainWindow.addComponent(label);
        FormularioUsuario formUsuario = new FormularioUsuario();
        mainWindow.addComponent(formUsuario);
        setMainWindow(mainWindow);
    }

    /**
     * Método qu retorno el objeto injectado para que otras clases de la
     * aplicación puedan usarlo
     *
     * @return El objeto ServicioUsuario injectado
     */
    public ServicioUsuarios getServicioUsuarios() {
        return servicioUsuarios;
    }
}
