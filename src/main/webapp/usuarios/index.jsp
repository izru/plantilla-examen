<%@page import="com.ipartek.controller.UsuariosController"%>
<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp" %>
<%@include file="/templates/alert.jsp" %>

<h1>Usuarios</h1>

<div class="row">

	<div class="col-md-6">
		<a class="btn btn-outline-primari" href="usuarios?op=<%=UsuariosController.OP_MOSTRAR_FORMULARIO%>">Crear Nuevo</a>
	</div> 

	<div class="col-md-6">
		<form action="usuarios" method="get">
			<input type="hidden" name="op" value="<%=UsuariosController.OP_BUSQUEDA%>">
			<input type="text" name="search" required placeholder="Nombre del usuario">
			<input type="submit" value="Buscar" class="btn btn-outline-primari">	
		</form>
	</div>	

</div>


<table class="tabla table table-striped table-bordered" style="width:100%">
   <thead>
       <tr>
           <th>Nombre</th>
           <th>Apellido</th>                      
       </tr>
   </thead>
   <tbody>
		<c:forEach items="${usuarios}" var="usuario">

			<tr>				
				<td><a href="usuarios?idUsuario=${usuario.idUsuario}&op=<%=UsuariosController.OP_MOSTRAR_FORMULARIO %>">${usuario.nombre}</a></td>
				<td>${usuario.apellido}</td>
			</tr>

		</c:forEach>

	</tbody>
</table>

<jsp:include page="/templates/footer.jsp"></jsp:include>