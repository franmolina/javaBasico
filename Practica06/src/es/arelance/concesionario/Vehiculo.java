package es.arelance.concesionario;

/**
 * Clase que representa vehiculos
 * 
 * @author Molina Sanchez, Francisco
 *
 */
public class Vehiculo {
	private String matricula;
	private String color;
	private boolean reservado;
	private double precio;

	/**
	 * Crea vehiculos
	 * 
	 * @param matricula
	 * @param color
	 * @param reservado
	 * @param precio
	 */
	public Vehiculo(String matricula, String color, boolean reservado, double precio) {
		this.matricula = matricula;
		this.color = color;
		this.reservado = reservado;
		this.precio = precio;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getColor() {
		return color;
	}

	public boolean isReservado() {
		return reservado;
	}

	public double getPrecio() {
		return precio;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}

	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", color=" + color + ", reservado=" + reservado + ", precio="
				+ precio + "]";
	}

}
