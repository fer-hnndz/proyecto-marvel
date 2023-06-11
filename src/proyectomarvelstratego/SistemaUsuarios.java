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

public class SistemaUsuarios {

   private static SistemaUsuarios instancia; // Instancia única de SistemaUsuarios

    int usuariosHistoricos = 0;
    Usuario usuariosActivos[] = new Usuario[1];
    Usuario usuarioActual = null;
    
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
        
        System.out.println("BUSCANDO SI " + usuario + " ES USUARIO UNICO.");
        for (int i=0; i<usuariosActivos.length;i++) {
            // En el caso de que usuario ya exista se retorna false para evitar que se registre el usuario.
            String usuarioActual = usuariosActivos[i].getUsuario();
            
            if (usuario.equals(usuarioActual)) {
               return false;
           };
        }
        
        return true;
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
        
        // Es el primer usuario en registrarse 
        if (usuariosHistoricos == 0) {
            usuariosActivos[0] = nuevoUsuario;
        } else {
            // Actualizar tamano del array
            
            int nuevaLongitud = usuariosActivos.length + 1;
            
            Usuario nuevosUsuarios[] = new Usuario[nuevaLongitud];
            
            for (int i=0;i<usuariosActivos.length;i++) {
                nuevosUsuarios[i] = usuariosActivos[i];
            }
            
            // Agregar el ultimo usuario registrado
            nuevosUsuarios[nuevaLongitud - 1] = nuevoUsuario;            
            usuariosActivos = nuevosUsuarios;
        }
        
        // Agregar el usuario a los usuarios historicos.
        usuariosHistoricos++;    
    }
    
    public Usuario[] getUsuariosActivos() {
        return usuariosActivos;
    }
    /**
     * Retorna el usuario con el que las credenciales son correctas. Devuelve `null` si no fue encontrado
     * @param usuario
     * @param contrasena
     * @return 
     */
    public Usuario iniciarSesion(String usuario, String contrasena) {
        Usuario usuarioIniciado = null;
        
        for (int i =0;i<usuariosActivos.length;i++) {
           Usuario usuarioActual = usuariosActivos[i];
            if (usuarioActual.validarCredenciales(usuario, contrasena)) {
                usuarioIniciado = usuarioActual;
                
                // Guardar el usuario que esta actualmente iniciado en el manager.
                this.usuarioActual = usuarioActual;
                break;
            }
        }
        
        return usuarioIniciado;
    }
    
    public void eliminarUsuario(Usuario usuario) {
    if (usuariosActivos != null) {
        // Crear un nuevo array sin el usuario a eliminar
        Usuario[] nuevosUsuarios = new Usuario[usuariosActivos.length - 1];
        int indice = 0;

        for (int i = 0; i < usuariosActivos.length; i++) {
            if (usuariosActivos[i] != null && !usuariosActivos[i].equals(usuario)) {
                nuevosUsuarios[indice] = usuariosActivos[i];
                indice++;
            }
        }

        // Actualizar el array de usuarios activos
        usuariosActivos = nuevosUsuarios;
    }
}
    
    public void actualizarUsuario(Usuario usuarioActualizado) {
    for (int i = 0; i < usuariosActivos.length; i++) {
        Usuario usuario = usuariosActivos[i];
        if (usuario.getUsuario().equals(usuarioActualizado.getUsuario())) {
            // Actualizar los datos del usuario
            usuario.setContrasena(usuarioActualizado.getContrasena());
            usuariosActivos[i] = usuario;  // Guardar el usuario actualizado en el array
            break;
        }
    }
    }
    /**
     * Retorna el usuario que tiene la sesion activa.
     * @return 
     */
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
    
}

