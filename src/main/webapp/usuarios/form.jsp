<%@page
	import="com.ipartek.controller.UsuariosController"%>
<%@include file="/templates/head.jsp"%>
<%@include file="/templates/navbar.jsp"%>
<%@include file="/templates/alert.jsp"%>
<!-- ${material}-->
<hr>
 ${usuario}
<div class="container">
	<div class="form-group row">
		<a class="btn btn-outline-dark btn-lg" href="usuarios">Volver</a>
	</div>
	<form action="usuarios" method="post">
		<div class="form-group row">
			<label for="idUsuario" class="col-sm-2 col-form-label">ID:</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" name="idUsuario" readonly	value="${usuario.idUsuario}">
			</div>
		</div>
		<div class="form-group row">
			<label for="nombre" class="col-sm-2 col-form-label">Usuario</label>
			<div class="col-sm-5">
				<input type="text" value="${usuario.nombre}" class="form-control"
					name="nombre" placeholder="Introduce el nombre del usuario">
			</div>
		</div>
		<div class="input-group row">
			<label for="apellido" class="col-sm-2 col-form-label">Apellido</label>
			<div class="col-sm-5">
				<input type="text" class="form-control" value="${usuario.apellido}"
					name="apellido" placeholder="Introduce el apellido">
			</div>
		</div>
		
				
		
		<c:if test="${usuario.idUsuario == -1}">
			<div class="form-group row">
				<div class="col-sm-12">
					<input type="hidden" name="op" value="<%=UsuariosController.OP_GUARDAR%>">
					<button type="submit" class="btn btn-primary btn-lg btn-block">Crear</button>
				</div>
			</div>
		</c:if>
		<c:if test="${usuario.idUsuario > -1}">
			<div class="form-group row">
				<div class="col-sm-6">
					<input type="hidden" name="op" value="<%=UsuariosController.OP_GUARDAR%>">
					<button type="submit" class="btn btn-success btn-lg btn-block">Modificar</button>
				</div>
				<div class="col-sm-6">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-danger btn-lg btn-block"
				data-toggle="modal" data-target="#exampleModal">Cuidado</button>

					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Eliminar</h5>
									<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">¿ Estas seguro que deseas Eliminar ?
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Cancelar</button>
									<a
								href="usuarios?idUsuario=${usuario.idUsuario}&op=<%=UsuariosController.OP_ELIMINAR%>">
										<button type="button" class="btn btn-primary">Aceptar</button>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
		<!-- "col-sm-6"  -->
			</div>
		</c:if> 
	</form>
</div>


<jsp:include page="/templates/footer.jsp"></jsp:include>