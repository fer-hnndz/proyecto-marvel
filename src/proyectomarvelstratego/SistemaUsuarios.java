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

    int usuariosHistoricos = 0;
    Usuario usuariosActivos[] = new Usuario[1];
    /**
     * Actualiza la ventana para mostrar el menu principal
     * @param ventana La ventana de la aplicacion
     */
    public SistemaUsuarios() {
    }
    
    public boolean esUsuarioUnico(String usuario) {
        
        // Es el primer usuario en registrarse
        if (usuariosHistoricos == 0) {
            return true;
        }
        
        for (int i=0; i<usuariosActivos.length;i++) {
            // En el caso de que usuario ya exista se retorna false para evitar que se registre el usuario.
           if (usuariosActivos[i].usuario.equals(usuario)) return false;
        }
        
        return true;
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
}

