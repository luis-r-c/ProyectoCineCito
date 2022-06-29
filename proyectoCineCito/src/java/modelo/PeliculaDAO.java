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

public class PeliculaDAO extends Conexion {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    public Pelicula ListarPorId(int id) {
        Pelicula p = new Pelicula();
        String consulta = "SELECT * FROM pelicula WHERE idPelicula=" + id;
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setNombres(rs.getString(2));
                p.setImagen(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setCalidad(rs.getString(5));
                p.setLink(rs.getString(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;

    }

    public List buscar(String nombre) {
        List list = new ArrayList();
        String sql = "select * from pelicula where Nombres like '%" + nombre + "%'";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setImagen(rs.getString(3));
//                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setCalidad(rs.getString(5));
                p.setLink(rs.getString(6));
                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Pelicula listarId(int id) {
        Pelicula p = new Pelicula();
        String sql = "select * from pelicula where idPelicula=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setImagen(rs.getString(3));
//                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setCalidad(rs.getString(5));
                p.setLink(rs.getString(6));
            }
        } catch (Exception e) {
        }
        return p;
    }

    public List listar() {
        List lista = new ArrayList();
        String sql = "select * from pelicula";
        try {
            ps = getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setImagen(rs.getString(3));
//                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setCalidad(rs.getString(5));
                p.setLink(rs.getString(6));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public void listarImg(int id, HttpServletResponse response) {
        String sql = "select * from pelicula where idPelicula=" + id;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        response.setContentType("image/*");
        try {
            outputStream = response.getOutputStream();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                inputStream = rs.getBinaryStream("Foto");
            }
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
        } catch (Exception e) {
        }
    }

    public int AgregarNuevoProducto(Pelicula p) {
        String sql = "insert into pelicula(Nombres,Foto,Descripcion,Calidad,Link)values(?,?,?,?,?)";
        try {
            ps = getConnection().prepareStatement(sql);
            ps.setString(1, p.getNombres());
            ps.setString(2, p.getImagen());
            ps.setString(3, p.getDescripcion());
            ps.setString(4, p.getCalidad());
            ps.setString(5, p.getLink());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    public int Actualizar(Pelicula pelicula) {
        String sentencia = "UPDATE pelicula set Nombres=?,Foto=?,Descripcion=?,Calidad=?,Link=? WHERE idPelicula=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sentencia);
            ps.setString(1, pelicula.getNombres());
            ps.setString(2, pelicula.getImagen());
            ps.setString(3, pelicula.getDescripcion());
            ps.setString(4, pelicula.getCalidad());
            ps.setString(5, pelicula.getLink());
            ps.setInt(7, pelicula.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public void Eliminar(int id) {

        String sql = "DELETE FROM pelicula WHERE idPelicula=" + id;
        con = cn.getConnection();
        try {
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Pelicula> listarP() {
        Conexion cn = new Conexion();
        String sql = "select * from pelicula";
        List<Pelicula> lista = new ArrayList<>();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setImagen(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setCalidad(rs.getString(5));
                p.setLink(rs.getString(6));
                lista.add(p);
            }
        } catch (Exception e) {
        }
        return lista;
    }
}
