//Emiliano Parra Varela ID:609042
// Clase Nodo

class Nodo {
	int clave;
	Nodo izquierdo, derecho;

	public Nodo(int elemento) {
		clave = elemento;
		izquierdo = derecho = null;
	}
}

public class ArbolBinario {
	Nodo raiz;

	public ArbolBinario() {
		raiz = null;
	}


	// INSERCIÓN

	public void insertar(int clave) {
		raiz = insertarRec(raiz, clave);
	}

	private Nodo insertarRec(Nodo raiz, int clave) {
		if (raiz == null) return new Nodo(clave);
		if (clave < raiz.clave)
			raiz.izquierdo = insertarRec(raiz.izquierdo, clave);
		else if (clave > raiz.clave)
			raiz.derecho = insertarRec(raiz.derecho, clave);
		return raiz;
	}


	// VISUALIZACIÓN SIMPLIFICADA DEL ÁRBOL

	public void imprimirArbol() {
		imprimirSimple(raiz, 0);
	}

	private void imprimirSimple(Nodo nodo, int nivel) {
		if (nodo == null) return;

		// 1. Subárbol derecho (queda arriba en la pantalla)
		imprimirSimple(nodo.derecho, nivel + 1);

		// 2. Nodo actual con sangría proporcional al nivel
		for (int i = 0; i < nivel; i++) {
			System.out.print("    "); // 4 espacios por nivel
		}
		System.out.println(nodo.clave);

		// 3. Subárbol izquierdo (queda abajo en la pantalla)
		imprimirSimple(nodo.izquierdo, nivel + 1);
	}


	// RECORRIDOS
	public void inorden() {
		inordenRec(raiz);
	}
	private void inordenRec(Nodo raiz) {
		if (raiz != null) {
			inordenRec(raiz.izquierdo);
			System.out.print(raiz.clave + " ");
			inordenRec(raiz.derecho);
		}
	}

	public void preorden() {
		preordenRec(raiz);
	}
	private void preordenRec(Nodo raiz) {
		if (raiz != null) {
			System.out.print(raiz.clave + " ");
			preordenRec(raiz.izquierdo);
			preordenRec(raiz.derecho);
		}
	}

	public void postorden() {
		postordenRec(raiz);
	}
	private void postordenRec(Nodo raiz) {
		if (raiz != null) {
			postordenRec(raiz.izquierdo);
			postordenRec(raiz.derecho);
			System.out.print(raiz.clave + " ");
		}
	}


	// BÚSQUEDA

	public boolean buscar(int clave) {
		return buscarRec(raiz, clave);
	}

	private boolean buscarRec(Nodo raiz, int clave) {
		// Caso base 1: El árbol/subárbol está vacío o llegamos al final
		if (raiz == null) return false;

		// Caso base 2: Se encuentra la clave
		if (raiz.clave == clave) return true;

		// Llamada recursiva según el valor
		if (clave < raiz.clave)
			return buscarRec(raiz.izquierdo, clave);
		else
			return buscarRec(raiz.derecho, clave);
	}


	// ELIMINACIÓN: ACTIVIDAD 2

	public void eliminar(int clave) {
		raiz = eliminarRec(raiz, clave);
	}

	private Nodo eliminarRec(Nodo raiz, int clave) {
		if (raiz == null) return null;

		if (clave < raiz.clave) {
			raiz.izquierdo = eliminarRec(raiz.izquierdo, clave);
		} else if (clave > raiz.clave) {
			raiz.derecho = eliminarRec(raiz.derecho, clave);
		} else {
			// Nodo encontrado

			// Caso 1 y 2: Sin hijos (hoja) o con un solo hijo
			if (raiz.izquierdo == null) return raiz.derecho;
			if (raiz.derecho == null) return raiz.izquierdo;

			// Caso 3: Nodo con dos hijos (sustituir por el mínimo del subárbol derecho)
			raiz.clave = obtenerMinimo(raiz.derecho);
			raiz.derecho = eliminarRec(raiz.derecho, raiz.clave);
		}
		return raiz;
	}


	// MÍNIMO DEL SUBÁRBOL

	private int obtenerMinimo(Nodo raiz) {
		while (raiz.izquierdo != null) {
			raiz = raiz.izquierdo;
		}
		return raiz.clave;
	}


	// PRUEBA PRINCIPAL (MAIN)
	public static void main(String[] args) {
		ArbolBinario arbol = new ArbolBinario();

		// Insertar elementos
		arbol.insertar(50);
		arbol.insertar(30);
		arbol.insertar(20);
		arbol.insertar(40);
		arbol.insertar(70);
		arbol.insertar(60);
		arbol.insertar(80);

		System.out.println("=== ESTRUCTURA INICIAL DEL ÁRBOL ===");
		arbol.imprimirArbol();

		System.out.println("\n--- RECORRIDOS ---");
		System.out.print("Inorden: ");
		arbol.inorden();
		System.out.print("\nPreorden: ");
		arbol.preorden();
		System.out.print("\nPostorden: ");
		arbol.postorden();

		System.out.println("\n\n--- BÚSQUEDA ---");
		int claveBuscada = 40;
		if (arbol.buscar(claveBuscada))
			System.out.println("La clave " + claveBuscada + " se encontró.");
		else
			System.out.println("La clave " + claveBuscada + " no se encontró.");

		System.out.println("\n--- ELIMINACIONES ---");

		int nodo = 20;
		arbol.eliminar(nodo);
		System.out.println("\nDespués de eliminar " + nodo + " (hoja):");
		arbol.imprimirArbol();

		nodo = 70;
		arbol.eliminar(nodo);
		System.out.println("\nDespués de eliminar " + nodo + " (nodo con 2 hijos):");
		arbol.imprimirArbol();

		nodo = 50;
		arbol.eliminar(nodo);
		System.out.println("\nDespués de eliminar " + nodo + " (raíz):");
		arbol.imprimirArbol();
	}
}