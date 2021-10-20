package modelo;

public class Medicamento {

	public final static float IVA = 0.04f;

	private String nombre; // tama�o 30, 60 bytes
	private double precio; // 8 bytes
	private int cod; // 4 bytes
	private static int cont = 0;
	private int stock; // 4 bytes
	private int stockMaximo; // 4 bytes
	private int stockMinimo; // 4 bytes
	private int codProveedor; // 4 bytes


	public Medicamento() {
	}

	public Medicamento(String nombre, double precio, int stock, int stockMaximo, int stockMinimo, int codProveedor) {
		cont++;
		this.nombre = nombre;
		this.precio = precio;
		this.cod=cont;
		this.stock = stock;
		this.stockMaximo = stockMaximo;
		this.stockMinimo = stockMinimo;
		this.codProveedor = codProveedor;
	}

	public Medicamento(String nombre, int cod,double precio, int stock, int stockMaximo, int stockMinimo, int codProveedor) {
		this.nombre = nombre;
		this.precio = precio;
		this.cod = cod;
		this.stock = stock;
		this.stockMaximo = stockMaximo;
		this.stockMinimo = stockMinimo;
		this.codProveedor = codProveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getStockMaximo() {
		return stockMaximo;
	}

	public void setStockMaximo(int stockMaximo) {
		this.stockMaximo = stockMaximo;
	}

	public int getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public int getCodProveedor() {
		return codProveedor;
	}

	public void setCodProveedor(int codProveedor) {
		this.codProveedor = codProveedor;
	}

	@Override
	public String toString() {
		return "Codigo: " + cod +
				"\nNombre: " + nombre +
				"\nPrecio: " + precio +
				"\nStock: " + stock +
				"\nStock maximo: " + stockMaximo +
				"\nStock minimo: " + stockMinimo +
				"\nProveedor: " + codProveedor;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Medicamento that = (Medicamento) o;
		return cod == that.cod;
	}
}
