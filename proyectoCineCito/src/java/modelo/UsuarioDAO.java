package modelo;

import config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

public class UsuarioDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;
    
    public Usuario Validar(String email, String pass) {
        String sql="select * from usuario where Email=? and Password=?";
        Usuario u=new Usuario();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                u.setId(rs.getInt(1));
                u.setUsuario(rs.getString(2));
                u.setNombres(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setPassword(rs.getString(5));
            }
        } catch (Exception e) {
        }
        return u;        
    }
    
    public Usuario BuscarCliente(String usua) {
        Usuario usuario = new Usuario();
        String consulta = "SELECT * FROM usuario WHERE usuario = ?";
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(consulta);
            ps.setString(1, usua);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario.setId(rs.getInt("idUsuario"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setNombres(rs.getString("Nombres"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setPassword(rs.getString("Password"));
                System.err.println("" + usuario.getNombres());
            }
        } catch (Exception e) {
        }
        return usuario;
    }

    public Usuario ValidarU(String usua, String password) {
        Usuario usuario = new Usuario();
        String consulta = "SELECT * FROM usuario WHERE Usuario = ? AND Password = ?";
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(consulta);
            ps.setString(1, usua);
            ps.setString(2, password);
            rs = ps.executeQuery();
            rs.next();
            do {
                usuario.setId(rs.getInt("idUsuario"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setNombres(rs.getString("Nombres"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setPassword(rs.getString("password"));
            } while (rs.next());
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    public List Listar() {
        String consulta = "SELECT * FROM usuario";
        List<Usuario> lista = new ArrayList();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsuario(rs.getString("Usuario"));
                usuario.setNombres(rs.getString("Nombres"));
                usuario.setEmail(rs.getString("Email"));
                usuario.setPassword(rs.getString("Password"));
                lista.add(usuario);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }

    public int Agregar(Usuario usuario) {

        String sentencia = "INSERT INTO usuario (Usuario,Nombres,Email,Password) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getNombres());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getPassword());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public Usuario ListarPorId(int id) {
        Usuario usuario = new Usuario();
        String consulta = "SELECT * FROM usuario WHERE idUsuario=" + id;
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario.setUsuario(rs.getString(2));
                usuario.setNombres(rs.getString(3));
                usuario.setEmail(rs.getString(4));
                usuario.setPassword(rs.getString(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;

    }

    public int Actualizar(Usuario usuario) {
        String sentencia = "UPDATE usuario set Usuario=?,Nombre=?,Email=?,Password=? WHERE idUsuario=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getNombres());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getPassword());
            ps.setInt(7, usuario.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public void Eliminar(int id) {

        String sql = "DELETE FROM usuario WHERE idUsuario=" + id;
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Usuario> listarU() {
        con = cn.getConnection();
        String sql = "select * from usuario";
        List<Usuario> lista = new ArrayList<>();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt(1));
                u.setUsuario(rs.getString(2));
                u.setNombres(rs.getString(3));
                u.setEmail(rs.getString(4));
                u.setPassword(rs.getString(5));
                lista.add(u);
            }
        } catch (Exception e) {
        }
        return lista;
    }
}
