<%@page import="java.util.ArrayList"%>
<%@page import="com.ipartek.pojo.Usuario"%>


<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp" %>
<%@include file="/templates/alert.jsp" %>

<%
	// recoger atributo del controlador, si es que existe
	
	ArrayList<Usuario> usuarios = (ArrayList<Usuario>)request.getAttribute("usuarios");	
%>

${usuarios}

<div class="row">


<div class="col-sm-6">
	<div class="form-group row">
			<label for="usuario" class="col-sm-4 col-form-label">Usuario:</label>
			<select name="materialPrecio" class="col-sm-6  form-control">
				<option value="0">-- selecciona --</option>
				<% 
					Usuario usuario = null;
					for ( int i=0; i < usuarios.size(); i++ ) {
						usuario =usuarios.get(i);
				%>
					<option value="<%=usuario.getId()%>" 
					        <%=(usuario.getId() == usuario.getId())?"selected":""%>>
						<%=usuario.getNombre()%>
					</option>
				<% } %>
			</select>
		</div>
</div>



</div>
<!-- class="row" -->




<jsp:include page="templates/footer.jsp"></jsp:include>