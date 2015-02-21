/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejemplo.vaadin.admusuarios;

import com.ejemplo.vaadin.MyApplication;
import com.ejemplo.vaadin.entidades.Usuario;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class FormularioUsuario extends Form {

    /**
     * Caja de texto para ingresar los nombres del usuario
     */
    TextField txtNombres;
    /**
     * Caja de texto para ingresar los apellidos del usuario
     */
    TextField txtApellidos;
    /**
     * Caja de texto para ingresar el correo del usuario
     */
    TextField txtCorreo;
    /**
     * Caja de texto para ingresar la clave para el usuario
     */
    TextField txtClave;
    /**
     * Botón para realizar la acción de guardado
     */
    Button btnGuardar;

    /**
     * Constructor
     */
    public FormularioUsuario() {
        txtNombres = new TextField("Nombres");
        txtNombres.setDescription("Ingrese los nombres del usuario");
        txtNombres.setRequired(true);
        txtNombres.setRequiredError("Debe ingresar los nombres del usuario");
        txtApellidos = new TextField("Apellidos");
        txtApellidos.setDescription("Ingrese los apellidos del usuario");
        txtApellidos.setRequired(true);
        txtApellidos.setRequiredError("Debe ingresar los apellidos del usuario");
        txtCorreo = new TextField("Correo");
        txtCorreo.setDescription("Ingrese el correo electronico del usuario");
        txtCorreo.setRequired(true);
        txtCorreo.setRequiredError("El correo es un campo obligatorio, por favor ingrese un valor");
        txtCorreo.addValidator(new EmailValidator("Debe ingresar una dirección de correo válida"));
        txtClave = new TextField("Clave");
        txtClave.setDescription("Ingrese la clave para el usuario");
        txtClave.setRequired(true);
        txtClave.setRequiredError("Debe ingresar una clave para el usuario");
        addField("txtNombres", txtNombres);
        addField("txtApellidos", txtApellidos);
        addField("txtCorreo", txtCorreo);
        addField("txtClave", txtClave);
        btnGuardar = new Button("Guardar Usuario");
        btnGuardar.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                if (isValid()) {
                    Usuario usuario = formularioAEntidad();
                    //ejecuto el guardado
                    Integer respuesta = ((MyApplication) getApplication()).getServicioUsuarios().guardarUsuario(usuario);
                    switch (respuesta.intValue()) {
                        // Si la solicitud se procesó bien
                        case 0:
                            getWindow().showNotification("Se ha guardado el usuario correctamente");
                            //Reseteamos el formulario para que quede limpio
                            reset();
                            break;
                        // Si el usuario no existe
                        case 1:
                            getWindow().showNotification("Al parecer usted no "
                                    + "cuenta con privilegios para realizar ésta acción", Notification.TYPE_WARNING_MESSAGE);
                            break;
                        // Si se presentó un error
                        case 2:
                            getWindow().showNotification("No se ha encontrado una sesión activa "
                                    + "al procesar la solicitud", Notification.TYPE_ERROR_MESSAGE);
                            break;
                        //si excepcion de duplicate data
                        case 4:
                            getWindow().showNotification("Ya existe un usuario definido para este correo!", Notification.TYPE_ERROR_MESSAGE);
                            break;
                        // Si el resultado no es el esperado
                        default:
                            getWindow().showNotification("El servidor respondió "
                                    + "de forma inesperada, por favor "
                                    + "reporte este mensaje de error", Notification.TYPE_ERROR_MESSAGE);
                    }
                } else {
                    getWindow().showNotification("Por favor ingrese todos los datos marcados con * "
                            + "en el formulario", Notification.TYPE_ERROR_MESSAGE);
                }
            }
        });
        addField("btnGuardar", btnGuardar);
    }

    /**
     * Método para cargar el formulario con los datos del objeto parámetro
     *
     * @param usuario - Entidad usuario
     */
    public void entidadAFormuario(Usuario usuario) {
        txtNombres.setValue(usuario.getNombres());
        txtApellidos.setValue(usuario.getApellidos());
        txtCorreo.setValue(usuario.getCorreo());
    }

    /**
     * Método para crear una entidad con los datos ingresados al formulario
     *
     * @return El usuario creado
     */
    public Usuario formularioAEntidad() {
        Usuario retorno;
        retorno = new Usuario();
        retorno.setClave((txtClave.getValue().toString()));
        retorno.setNombres(txtNombres.getValue().toString());
        retorno.setApellidos(txtApellidos.getValue().toString());
        retorno.setCorreo(txtCorreo.getValue().toString());
        return retorno;
    }

    /**
     * Resetear contenido formulario.
     */
    public void reset() {
        txtNombres.setValue("");
        txtApellidos.setValue("");
        txtCorreo.setValue("");
        txtClave.setValue("");
    }
}
