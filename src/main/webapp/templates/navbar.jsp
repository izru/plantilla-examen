
	<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
	  
	  <a href="index.jsp">
	  	<img src="img/logo.png" class="logo" alt="Logo Nidea">
	  </a>	
            
      <nav class="my-2 my-md-0 mr-md-10">
        <a class="p-2 text-dark" href="generar-mesa">Mesa</a>
        <a class="p-2 text-dark" href="materiales">Materiales</a>          
        <a class="p-2 text-dark" href="calculadora">Calculadora</a>     
      </nav>
      
     <!-- <a class="btn btn-outline-primary" href="loginUsuario.jsp">Login Usuario</a> -->
      
      
      <c:if test="${empty usuario}">
      	<a class="btn btn-outline-primary" href="login">Login</a>
      </c:if>
            
      <c:if test="${!empty usuario}">
      
		 <c:if test="${usuario.rol.id == 2}">      
	      	<!-- Frontoffice -->      
	      	<nav class="my-2 my-md-0 mr-md-10">
	      		<a class="p-2 text-dark" href="frontoffice/materiales">Mis Materiales</a>      		
	      		<a href="#" class="badge badge-primary">${usuario.nombre}</a>
	      	   	<a class="btn btn-outline-danger" href="logout">Logout</a>
	      	</nav>
	     </c:if> 	
      
       	<c:if test="${usuario.rol.id == 1}"> 
	      	<!-- Backoffice -->
	      	<nav class="my-2 my-md-0 mr-md-10">
	      		<a class="p-2 text-dark" href="backoffice/materiales">Materiales</a>
	      		<a class="p-2 text-dark" href="backoffice/roles">Roles</a>
	      		<a href="#" class="badge badge-secondary">${usuario.nombre}</a>
	      	   	<a class="btn btn-outline-danger" href="logout">Logout</a>
	      	</nav>
      	</c:if>
      	   	
      </c:if>

     
    </div>
    
    <div class="container">