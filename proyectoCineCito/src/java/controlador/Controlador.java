package controlador;

//import modelo.Carrito;
//import modelo.Cliente;
//import modelo.ClienteDAO;
//import modelo.Compra;
//import modelo.ComprasDAO;
//import modelo.DetalleCompra;
//import modelo.Pago;
import modelo.Pelicula;
import modelo.PeliculaDAO;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Usuario;
import modelo.UsuarioDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Controlador extends HttpServlet {

    UsuarioDAO udao = new UsuarioDAO();
    Usuario u = new Usuario();
    PeliculaDAO pdao = new PeliculaDAO();
    Pelicula p = new Pelicula();

    List peliculas = new ArrayList();
    List usuarios = new ArrayList();

    int idPelicula;
    int idUsuario;

    String logueo = "Iniciar Sesion";
    String correo = "Iniciar Sesion";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.setAttribute("logueo", logueo);
        session.setAttribute("correo", correo);

        peliculas = pdao.listar();
        usuarios = udao.Listar();

        String accion = request.getParameter("accion");
        String menu = request.getParameter("menu");

        switch (accion) {
            case "Principal":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "Peliculas":

                List lista = pdao.listarP();
                request.setAttribute("peliculas", lista);
                request.getRequestDispatcher("vistas/Pelicula.jsp").forward(request, response);

                break;
            case "Listar":
                //List lista = pdao.listarP();
                //request.setAttribute("peliculas", lista);

                break;
            case "Guardar":
                ArrayList<String> pel = new ArrayList<>();
                try {
                    FileItemFactory file = new DiskFileItemFactory();
                    ServletFileUpload fileUpload = new ServletFileUpload(file);
                    List items = fileUpload.parseRequest(request);
                    for (int i = 0; i < items.size(); i++) {
                        FileItem fileItem = (FileItem) items.get(i);
                        if (!fileItem.isFormField()) {
                            File f = new File("C:\\xampp\\htdocs\\img\\" + fileItem.getName());
                            fileItem.write(f);
                            p.setImagen("http://localhost/img/" + fileItem.getName());
                        } else {
                            pel.add(fileItem.getString());
                        }
                    }
                    p.setNombres(pel.get(0));
                    p.setDescripcion(pel.get(1));
                    p.setCalidad(pel.get(2));
                    p.setLink(pel.get(3));
                    pdao.AgregarNuevoProducto(p);

                } catch (Exception e) {
                    System.err.println("" + e);
                }
                request.getRequestDispatcher("Controlador?accion=Peliculas").forward(request, response);
                break;
            case "Eliminar":
                idPelicula = Integer.parseInt(request.getParameter("id"));
                pdao.Eliminar(idPelicula);
                request.getRequestDispatcher("Controlador?accion=Peliculas").forward(request, response);

                break;
            case "Actualizar":
                Pelicula pelicula1 = new Pelicula();
                String nombresUpdate = request.getParameter("txtNombres");
                String fotoUpdate = request.getParameter("txtFoto");
                String descripcionUpdate = request.getParameter("txtDescripcion");
                String calidadUpdate = request.getParameter("txtCalidad");
                String linkUpdate = request.getParameter("txtLink");

                pelicula1.setNombres(nombresUpdate);
                pelicula1.setImagen(fotoUpdate);
                pelicula1.setDescripcion(descripcionUpdate);
                pelicula1.setCalidad(calidadUpdate);
                pelicula1.setLink(linkUpdate);

                pelicula1.setId(idPelicula);
                pdao.Actualizar(pelicula1);
                request.getRequestDispatcher("Controlador?accion=Peliculas").forward(request, response);

                break;

            case "Cargar":

                idPelicula = Integer.parseInt(request.getParameter("id"));
                p = pdao.ListarPorId(idPelicula);
                request.setAttribute("pelicula", p);
                request.getRequestDispatcher("Controlador?accion=Peliculas").forward(request, response);
                break;

            case "Usuarios":

                List list = udao.listarU();
                request.setAttribute("usuarios", list);
                request.getRequestDispatcher("vistas/Usuarios.jsp").forward(request, response);

                break;
            case "UListar":
                //List list = udao.Listar();
                //request.setAttribute("usuarios", list);

                break;
            case "UGuardar":

                String usuariou = request.getParameter("txtUsuario");
                String nombresu = request.getParameter("txtNombres");
                String emailu = request.getParameter("txtEmail");
                String passwordu = request.getParameter("txtPassword");
                u.setUsuario(usuariou);
                u.setNombres(nombresu);
                u.setEmail(emailu);
                u.setPassword(passwordu);
                udao.Agregar(u);
                request.getRequestDispatcher("Controlador?accion=Usuarios").forward(request, response);

                break;
            case "UActualizar":

                Usuario usuario1 = new Usuario();
                String usuarioUpdate = request.getParameter("txtUsuario");
                String nombresUpdat = request.getParameter("txtNombres");
                String emailUpdate = request.getParameter("txtEmail");
                String passwordUpdate = request.getParameter("txtPassword");
                usuario1.setUsuario(usuarioUpdate);
                usuario1.setNombres(nombresUpdat);
                usuario1.setEmail(emailUpdate);
                usuario1.setPassword(passwordUpdate);
                usuario1.setId(idUsuario);
                udao.Actualizar(usuario1);
                request.getRequestDispatcher("Controlador?accion=Usuarios").forward(request, response);

                break;
            case "UCargar":
                idUsuario = Integer.parseInt(request.getParameter("id"));
                u = udao.ListarPorId(idUsuario);
                request.setAttribute("usuarioSeleccionado", u);
                request.getRequestDispatcher("Controlador?accion=Usuarios").forward(request, response);

                break;
            case "UEliminar":

                idUsuario = Integer.parseInt(request.getParameter("id"));
                udao.Eliminar(idUsuario);
                request.getRequestDispatcher("Controlador?accion=Usuarios").forward(request, response);

                break;
            case "Registrar":
                String usu = request.getParameter("txtUsu");
                String nom = request.getParameter("txtNom");
                String em = request.getParameter("txtEma");
                String pas = request.getParameter("txtPass");
                u.setUsuario(usu);
                u.setNombres(nom);
                u.setEmail(em);
                u.setPassword(pas);
                udao.Agregar(u);
                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                break;
            case "Validar":
                String email = request.getParameter("txtEmail");
                String pass = request.getParameter("txtPass");
                u = udao.Validar(email, pass);
                if (u.getId() != 0) {
                    logueo = u.getNombres();
                    correo = u.getEmail();
                }
                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                break;
            case "Salir":
                
                session.invalidate();
                logueo = "Iniciar Sesion";
                correo = "Iniciar Sesion";
                request.getRequestDispatcher("Controlador?accion=Salirr").forward(request, response);
                break;
            default:
                request.setAttribute("peliculas", peliculas);
                request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
