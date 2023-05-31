/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomarvelstratego;

/**
 *
 * @author Jorge Hernandez
 */
public class Usuario {
    private String usuario;
    private String contrasena;
    private int punto = 0;
    private int partidasBuenos = 0;
    private int partidasMalos = 0;
    
    
    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario.trim();
        this.contrasena = contrasena.trim();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        if (!usuario.trim().isEmpty()) {
            this.usuario = usuario.trim();
        }
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        if (!contrasena.trim().isEmpty()) {
            this.contrasena = contrasena.trim();        
        }
    }
    
    /**
     * Verifica que el nombre de usuario y la contraseña especificada sea igual a a guardada por el usuario.
     * @return 
     */
    public boolean validarCredenciales(String usuario, String contrasena) {
        return (this.usuario.equals(usuario) && this.contrasena.equals(contrasena));
    }
    
    
    
    
    
}
