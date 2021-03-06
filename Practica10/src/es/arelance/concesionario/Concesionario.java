package es.arelance.concesionario;

import java.util.LinkedList;
import java.util.List;

/**
 * Clase que representa al concesionario y puede almacenar vehiculos
 * 
 * @author Molina Sanchez, Francisco
 *
 */
public class Concesionario {
	private final static int plazasDisponibles = 20;
	private String nombre;
	private String direccion;
	private String telefono;
	private Vehiculo[] vehiculos;

	/**
	 * Crea un concesionario sin vehiculos
	 * 
	 * @param nombre
	 * @param direccion
	 * @param telefono
	 */
	public Concesionario(String nombre, String direccion,
			String telefono) {
		this.nombre = nombre;
		this.direccion = direccion;
		if (esValido(telefono)) {
			this.telefono = telefono;
		} else {
			this.telefono = "No disponible";
		}

		this.vehiculos = new Vehiculo[Concesionario.plazasDisponibles];
	}

	/**
	 * Comprueba si el numero de telefono es valido (9 digitos y emepzando por 9 o
	 * 6) y si no es valido lo cambia a "No disponible"
	 * 
	 * @param telefono
	 * @return
	 */
	private boolean esValido(String telefono) {
		// TODO Auto-generated method stub

		return telefono.matches("[96]{1}\\d{8}");
	}

	/**
	 * Reserva un vehiculo dada su matricula
	 * 
	 * @param matricula
	 * @return el vehiculo reservado o null si no lo encuentra
	 */
	public Vehiculo reservar(String matricula) {
		Vehiculo vehiculo = null;
		if ((vehiculo = this.buscar(matricula)) != null) {
			buscar(matricula).setReservado(true);
		} else {
			// System.err.println("Vehiculo no encontrado.\n");
		}
		return vehiculo;
	}

	/**
	 * A�ade un vehiculo al cocnesionario si hay espacio disponible
	 * 
	 * @param vehiculo
	 */
	public void introducirVehiculo(Vehiculo vehiculo) {
		int indice = 0;

		while (indice < vehiculos.length) {
			if (vehiculos[indice] == null) {
				vehiculos[indice] = vehiculo;
				break;
			}
			indice++;
		}
		if (indice > vehiculos.length) {
			System.err.println(
					"No se ha podido introducir el vehiculo. Espacio insuficiente\n");
		}
	}

	/**
	 * Devuelve una lista con los vehiculos almacenados en el concesionario y sus
	 * datos
	 * 
	 * @return
	 */
	public String verVehiculos() {
		int indice = 0;
		String resultado = "";
		while (indice < vehiculos.length && vehiculos[indice] != null) {
			resultado += vehiculos[indice] + "\n";
			indice++;
		}
		return resultado;
	}

	/**
	 * Devuelve una lista con los coches almacenados en el concesionario y sus datos
	 * 
	 * @return
	 */
	public String verCoches() {
		int indice = 0;
		String resultado = "Coches:";
		while (indice < vehiculos.length && vehiculos[indice] != null) {
			if (vehiculos[indice] instanceof Coche) {
				resultado += vehiculos[indice] + "\n";

			}
			indice++;
		}
		return resultado;
	}

	/**
	 * Devuelve una lista con los coches almacenados en el concesionario y sus datos
	 * 
	 * @return
	 */
	public String verMotos() {
		int indice = 0;
		String resultado = "";
		while (indice < vehiculos.length && vehiculos[indice] != null) {
			if (vehiculos[indice] instanceof Moto) {
				resultado += vehiculos[indice] + "\n";

			}
			indice++;
		}
		return resultado;
	}

	/**
	 * Busca un vehiculo en el cocnesionario y lo devuelve
	 * 
	 * @param matricula
	 * @return
	 */
	public Vehiculo buscar(String matricula) {
		boolean encontrado = false;
		int indice = 0;
		Vehiculo resultado = null;

		while (!encontrado && indice < vehiculos.length) {
			if (vehiculos[indice] == null) {
				break;
			} else if (vehiculos[indice].getMatricula()
					.equalsIgnoreCase(matricula)) {
				encontrado = true;
				resultado = vehiculos[indice];
			}
			indice++;
		}
		return resultado;
	}

	@Override
	public String toString() {
		return "Concesionario [nombre=" + nombre + ", direccion="
				+ direccion + ", telefono=" + telefono + "]";
	}

	/**
	 * Filtra los vehiculos del concesionario segun lso parametros
	 * 
	 * @param filtroMatriculaCadena
	 *            matricula parcial a buscar;null si no se usa
	 * @param colorEnum
	 *            color a buscar;null si no se usa
	 * @param precioMinimo
	 *            precio minimo;-1 si no se usa
	 * @param precioMaximo
	 *            precio maximo
	 * @return string con los vehiculos que cumplen los filtros
	 */
	public String filtrarPrecio(String filtroMatriculaCadena,
			ColorVehiculo colorEnum, int precioMinimo, int precioMaximo) {
		List<Vehiculo> lista = new LinkedList<Vehiculo>();
		List<Vehiculo> listaTemporal = new LinkedList<Vehiculo>();
		;
		for (Vehiculo vehiculo : this.vehiculos) {
			if (vehiculo != null) {
				lista.add(vehiculo);
			}

		}
		if (filtroMatriculaCadena != null) {
			for (Vehiculo vehiculo : lista) {
				if (vehiculo.getMatricula().toLowerCase()
						.contains(filtroMatriculaCadena.toLowerCase())) {
					listaTemporal.add(vehiculo);
				}
			}
			lista = listaTemporal;
			listaTemporal = new LinkedList<Vehiculo>();
		}
		if (colorEnum != null) {
			for (Vehiculo vehiculo : lista) {
				if (vehiculo.getColor().equals(colorEnum)) {
					listaTemporal.add(vehiculo);
				}
			}
			lista = listaTemporal;
			listaTemporal = new LinkedList<Vehiculo>();
		}
		if (precioMinimo != -1) {
			for (Vehiculo vehiculo : lista) {
				if (vehiculo.getPrecio() > precioMinimo
						&& vehiculo.getPrecio() < precioMaximo) {
					listaTemporal.add(vehiculo);
				}
			}
			lista = listaTemporal;
			listaTemporal = new LinkedList<Vehiculo>();
		}
		String res = "";
		for (Vehiculo vehiculo : lista) {
			res += vehiculo + "\n";
		}

		return res;
	}

}
