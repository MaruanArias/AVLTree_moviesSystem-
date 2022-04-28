package co.edu.unbosque.controller;

import co.edu.unbosque.model.Pelicula;
import modelo.ArbolAvl;
import modelo.NodoAvl;




public class LanzadorPrueba {
	static final int TOPEMAX = 999;

	public static void main(String[] a) throws Exception {
		
		ArbolAvl avl = new ArbolAvl();
		Pelicula elemento;
		int numNodos;
		int i=0;
		while (altura(avl.raizArbol()) < 5) {
			//elemento = new Numero((int) (Math.random() * TOPEMAX) + 1);
			String[] versionesT= {"4:03","LBX"};
			elemento = new Pelicula("Pull Fuction","HOLLYWOOD","Fuera",versionesT,1000,"Publico General",2008,"Drama","10/05/2008",i);
			avl.insertar(elemento);
			i++;
		}
		numNodos = visualizar(avl.raizArbol());
		System.out.println("\n Número de nodos: " + numNodos);
		//Numero elemento1 = new Numero(2);
		//avl.eliminar(elemento1);
		numNodos = visualizar(avl.raizArbol());
		System.out.println("\n Número de nodos: " + numNodos);
		System.out.println("PostOrden");
		avl.postorden(avl.raizArbol());
		System.out.println(" ");
		System.out.println("PreOrden");
		avl.preorden(avl.raizArbol());
		System.out.println(" ");
		System.out.println("InOrden");
		avl.preorden(avl.raizArbol());
		System.out.println(" ");
		System.out.println("Buscar Elemento ");
	    System.out.println("--------");
		//avl.imprimirNivel();
       //Numero lol = new Numero(12);
       Object lol1 = new Object ();
       lol1=5;
//       Object lol1 = new Object ();
//       lol1=5;
      // Numero x = (Numero) avl.buscarIterativo(lol).valorNodo();
	   //System.out.println("elemento buscado: "+ x.getValor());
       //avl.buscarAvl( avl.raizArbol(),lol);
	}

	static int visualizar(NodoAvl r) {// escribir claves de árbol
		if (r != null) {
			int cuantosIzquierda, cuantosDerecha;
			cuantosIzquierda = visualizar((NodoAvl) r.subarbolIzdo());
			System.out.print(r.valorNodo());
			cuantosDerecha = visualizar((NodoAvl) r.subarbolDcho());
			return cuantosIzquierda + cuantosDerecha + 1;
		} else
			return 0;
	}

	// calcula y devuelve altura
	static int altura(NodoAvl r) {
		if (r != null)
			return mayor(altura((NodoAvl) r.subarbolIzdo()), altura((NodoAvl) r.subarbolDcho())) + 1;
		else
			return 0;
	}

	static int mayor(int x, int y) {
		return (x > y ? x : y);
	}
}
