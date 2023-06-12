/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomarvelstratego;

/**
 *
 * @author Jorge Hernandez
 */

/*
    Esta clase administra el inicio de sesion y la informacion de los usuarios
*/

import java.util.ArrayList;
public class SistemaUsuarios {

   private static SistemaUsuarios instancia; // Instancia única de SistemaUsuarios

    int usuariosHistoricos = 0;
    ArrayList<Usuario> usuariosActivos = new ArrayList<Usuario>();
    Usuario usuarioIniciado = null;
    
    // CONSTRUCTOR
    public SistemaUsuarios() {
    }
    
    public static SistemaUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new SistemaUsuarios();
        }
        return instancia;
    }
    
    /**
     * 
     * Comprueba que el nombre de usuario sea unico.
     * @param usuario
     * @return 
     */
    public boolean esUsuarioUnico(String usuario) {
       
        // Es el primer usuario en registrarse
        if (usuariosHistoricos == 0) {
            return true;
        }
        
        for (int i=0; i<usuariosActivos.toArray().length;i++) {
            // En el caso de que usuario ya exista se retorna false para evitar que se registre el usuario.
            String usuarioActual = usuariosActivos.get(i).getUsuario();
            
            if (usuario.equals(usuarioActual)) {
               return false;
           };
        }
        
        return true;
    }
    
    public Usuario getUsuario(String username) {
        for (Usuario user:usuariosActivos) {
            if (user.getUsuario().equals(username)) {
                return user;
            }
        }
        
        return null;
    }
    
    
    public boolean verificarEspaciosUsuario(String usuario) {
        boolean validUsername = true;
        
         // Asegurarse de que no haya espacios en el usuario.
        for (int i =0;i<usuario.length();i++) {
            char charActual = usuario.charAt(i);
            
            if (charActual == ' ') {
                validUsername = false;
                break;
            }
        }
        
        return validUsername;
    }
    
    public void registrarUsuario(String usuario, String contrasena) {
        Usuario nuevoUsuario = new Usuario(usuario, contrasena);
        
        usuariosActivos.add(nuevoUsuario);
            
        
        // Agregar el usuario a los usuarios historicos.
        usuariosHistoricos++;    
    }
    
    public Usuario[] getUsuariosActivos() {
        Usuario usuarios[] = usuariosActivos.toArray(Usuario[]::new);
        return usuarios;
    }
    
    /**
     * Retorna el usuario con el que las credenciales son correctas. Devuelve `null` si no fue encontrado
     * @param usuario
     * @param contrasena
     * @return 
     */
    public Usuario iniciarSesion(String usuario, String contrasena) {        
        for (int i =0;i<usuariosActivos.toArray().length;i++) {
           Usuario usuarioActual = usuariosActivos.get(i);
           if (usuarioActual == null) continue;
           
            if (usuarioActual.validarCredenciales(usuario, contrasena)) {
                // Guardar el usuario que esta actualmente iniciado en el manager.
                this.usuarioIniciado = usuarioActual;
                return usuarioActual;
            }            
        }
        
        return null;
    }
    
    public void eliminarUsuario(Usuario usuario) {
    if (usuariosActivos != null) {
        // Crear un nuevo array sin el usuario a eliminar
        usuariosActivos.remove(usuario);
    }
}
    
    public void actualizarUsuario(Usuario usuarioActualizado) {
        for (int i = 0; i < usuariosActivos.toArray().length; i++) {
            Usuario usuario = usuariosActivos.get(i);

            // Buscar al usuario en base al nombre de usuario
            if (usuario.getUsuario().equals(usuarioActualizado.getUsuario())) {

                // Actualizar los datos del usuario
                usuario.setContrasena(usuarioActualizado.getContrasena());
                usuariosActivos.set(i, usuario);  // Guardar el usuario actualizado en el array
                break;
            }
        }
    }
    /**
     * Retorna el usuario que tiene la sesion activa.
     * @return 
     */
    public Usuario getUsuarioActual() {
        return usuarioIniciado;
    }
    
    
}

