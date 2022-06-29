<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>        
        <title>CineCito</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a class="navbar-brand" href="#">
                <img src="img/logo.png" alt="80" width="60"/><label><h1 fonnt:arial>  CineCito  </h1></label>
            </a>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item active">
                        <a class="nav-link" href="./Controlador?accion=Nuevo"><i class="fas fa-home"></i> Home<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./Controlador?accion=home"><i class="fas fa-arrow-right"></i> Peliculas</a>
                    </li>                   
                    <li class="nav-item">
                        <a class="nav-link" href="Controlador?menu=Usuarios&accion=Usuarios"><i class="fas fa-user-tie"></i> ADM-Usuarios</a>
                    </li> 
                    <li class="nav-item">
                        <a class="nav-link" href="./Controlador?accion=Peliculas"><i class="fas fa-arrow-right"></i> ADM-Peliculas</a>
                    </li> 
                </ul>
                                              
                <ul class="navbar-nav btn-group my-2 my-lg-0" role="group">
                    <a style="color: white; cursor: pointer" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fas fa-user-tie"></i> ${logueo}</a>
                    <div class="dropdown-menu text-center dropdown-menu-right">
                        <a class="dropdown-item" href="#"><img src="img/user.png" alt="60" height="60"/></a>                        
                        <a class="dropdown-item" href="#">${user}</a>
                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#myModal">${correo}</a>
                        <div class="dropdown-divider"></div>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="./Controlador?accion=Salir"> <i class="fas fa-arrow-right"> Salir</i></a>                        
                    </div>
                </ul>     
            </div>
        </nav>
        <div class="container-fluid mt-4" >            
            <div class="row" >
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-header">
                            <h3>Agregar Usuarios</h3>
                        </div>                
                        <div class="card-body">
                            <form action="Controlador?menu=Usuarios" method="POST">
                                <div class="form-group">
                                    <label>Usuario</label>
                                    <input type="text" class="form-control" name="txtUsuario" value="${usuarioSeleccionado.getUsuario()}">
                                </div>
                                <div class="form-group">
                                    <label>Nombres</label>
                                    <input type="text" class="form-control" name="txtNombres" value="${usuarioSeleccionado.getNombres()}">
                                </div>
                                <div class="form-group">
                                    <label>Email</label>
                                    <input type="text" class="form-control" name="txtEmail" value="${usuarioSeleccionado.getEmail()}">
                                </div>
                                <div class="form-group">
                                    <label>Password</label>
                                    <input type="text" class="form-control" name="txtPassword" value="${usuarioSeleccionado.getPassword()}">
                                </div>
                                <input type="submit" class="btn btn-primary" name="accion" value="UGuardar" >
                                    <input type="submit" class="btn btn-success" name="accion" value="UActualizar" >
                                </form>
                        </div>               
                    </div>
                </div> 
                <div class="col-sm-8">
                    <table class="table table-responsive">
                        <thead class="">
                            <tr class="text-center">
                                <th>ID</th>
                                <th>USUARIO</th>
                                <th>NOMBRES</th>                               
                                <th>EMAIL</th>
                                <th>PASSWORD</th>
                                <th>ACCIONES</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="u" items="${usuarios}">
                                <tr class="text-center">
                                    <td>${u.getId()}</td>
                                    <td>${u.getUsuario()}</td>
                                    <td>${u.getNombres()}</td>
                                    <td>${u.getEmail()}</td>                                    
                                    <td>${u.getPassword()}</td>
                                    <td class="d-flex">
                                        <a href="Controlador?accion=UCargar&id=${u.getId()}" class="btn btn-warning">Editar</a>
                                        <a href="Controlador?accion=UEliminar&id=${u.getId()}" class="btn btn-danger">Eliminar</a>
                                    </td>
                                </tr>
                            </c:forEach>                           
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.js" integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU=" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
