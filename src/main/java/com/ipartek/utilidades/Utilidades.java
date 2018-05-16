package com.ipartek.utilidades;

public class Utilidades {

	/**
	 * Metodo estatico para poder usarse desde la propia Clase, sin tener que
	 * instanciar un objeto.<br>
	 * 
	 * Limpia los caracteres vacios " " de una cadena String.<br>
	 * Hacemos trim, ademas de sustituir todos los espacios en blanco por uno unico,
	 * ej:<br>
	 * " hola que haxe " => "hola que haxe"
	 * 
	 * @param cadena
	 * @return en caso de null retorna cadena vacia ""
	 */
	public static String limpiarEspacios(String cadena) {
		String resul = "";
		if (cadena != null) {
			resul = cadena.trim();
			resul = resul.replaceAll("\\s+", " ");
		}
		return resul;
	}

	

}
