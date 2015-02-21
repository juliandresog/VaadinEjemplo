/*

 * To change this template, choose Tools | Templates

 * and open the template in the editor.

 */
package com.ejemplo.vaadin.servicios.impl;

import com.ejemplo.vaadin.dao.DaoUsuario;
import com.ejemplo.vaadin.entidades.Usuario;
import com.ejemplo.vaadin.servicios.ServicioUsuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * 
 * @author josorio
 */
public class ServicioUsuariosImpl implements ServicioUsuarios, Serializable {

    @Autowired
    private DaoUsuario daoUsuarios;

    /**
     * @{inheritDoc}
     */
    @Override
    public List<Usuario> usuarios() {

        List<Usuario> retorno = null;

        retorno = daoUsuarios.getActivos();

        if (retorno == null) {

            retorno = new ArrayList<Usuario>();

        }

        return retorno;

    }

    /**
     * @{inheritDoc}
     */
    @Override
    public Integer guardarUsuario(Usuario usuario) {

        Integer retorno = 1;

        try {

            daoUsuarios.guardar(usuario);

            retorno = 0;

        } catch (ConstraintViolationException e) {

            retorno = 4;

        } catch (DataIntegrityViolationException e) {

            retorno = 4;

        } catch (Exception e) {

            e.printStackTrace();

            retorno = 3;

        }

        return retorno;

    }

}
