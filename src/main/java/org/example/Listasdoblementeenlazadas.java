package org.example;
import java.util.HashSet;
import java.util.HashSet;

public class Listasdoblementeenlazadas {
    // Clase interna para representar los nodos de la lista doblemente enlazada
    private class Nodo {
        private Object dato; // Dato almacenado en el nodo
        private Nodo anterior; // Referencia al nodo anterior
        private Nodo siguiente; // Referencia al nodo siguiente

        // Constructor del Nodo
        public Nodo(Object dato) {
            this.dato = dato;
            this.anterior = null;
            this.siguiente = null;
        }

        // Getters y setters para acceder y modificar los campos del Nodo
        public Object getDato() {
            return dato;
        }

        public void setDato(Object dato) {
            this.dato = dato;
        }

        public Nodo getAnterior() {
            return anterior;
        }

        public void setAnterior(Nodo anterior) {
            this.anterior = anterior;
        }

        public Nodo getSiguiente() {
            return siguiente;
        }

        public void setSiguiente(Nodo siguiente) {
            this.siguiente = siguiente;
        }
    }

    // Referencias al primer y último nodo de la lista
    private Nodo primero;
    private Nodo ultimo;

    // Constructor de la lista doblemente enlazada
    public Listasdoblementeenlazadas() {
        this.primero = null; // Inicialmente la lista está vacía
        this.ultimo = null;
    }

    // Método para verificar si la lista está vacía
    public boolean vacia() {
        return primero == null; // La lista está vacía si el primer nodo es nulo
    }

    // Método para agregar un nuevo nodo al inicio de la lista
    public void agregarInicio(Object dato) {
        Nodo nuevo = new Nodo(dato); // Creamos un nuevo nodo con el dato proporcionado
        if (vacia()) {
            primero = ultimo = nuevo; // Si la lista está vacía, el nuevo nodo es el único nodo en la lista
        } else {
            nuevo.setSiguiente(primero); // Establecemos el siguiente del nuevo nodo al actual primer nodo
            primero.setAnterior(nuevo); // Establecemos el anterior del actual primer nodo al nuevo nodo
            primero = nuevo; // El nuevo nodo ahora es el primer nodo de la lista
        }
    }

    // Método para agregar un nuevo nodo en la mitad de la lista
    public void agregarMedio(Object dato) {
        if (vacia()) {
            agregarInicio(dato); // Si la lista está vacía, simplemente agregamos al inicio
        } else {
            Nodo nuevo = new Nodo(dato); // Creamos un nuevo nodo con el dato proporcionado
            int tamaño = 0;
            Nodo actual = primero;

            // Determinamos el tamaño de la lista
            while (actual != null) {
                tamaño++;
                actual = actual.getSiguiente();
            }

            int mitad = tamaño / 2; // Calculamos el índice de la mitad de la lista
            actual = primero;
            for (int i = 0; i < mitad - 1; i++) { // Avanzamos hasta llegar al nodo que estará antes del nuevo nodo
                actual = actual.getSiguiente();
            }

            // Ajustamos los enlaces para insertar el nuevo nodo en la mitad de la lista
            nuevo.setSiguiente(actual.getSiguiente());
            if (actual.getSiguiente() != null) {
                actual.getSiguiente().setAnterior(nuevo);
            }
            nuevo.setAnterior(actual);
            actual.setSiguiente(nuevo);
        }
    }

    // Método para eliminar el primer nodo de la lista
    public void eliminarAlInicio() {
        if (!vacia()) { // Verificamos si la lista no está vacía
            if (primero == ultimo) { // Si solo hay un nodo en la lista
                primero = ultimo = null; // La lista queda vacía
            } else {
                primero = primero.getSiguiente(); // El segundo nodo pasa a ser el primero
                primero.setAnterior(null); // Se elimina la referencia al nodo anterior
            }
        }
    }

    // Método para eliminar el último nodo de la lista
    public void eliminarAlFinal() {
        if (!vacia()) { // Verificamos si la lista no está vacía
            if (primero == ultimo) { // Si solo hay un nodo en la lista
                primero = ultimo = null; // La lista queda vacía
            } else {
                ultimo = ultimo.getAnterior(); // El penúltimo nodo pasa a ser el último
                ultimo.setSiguiente(null); // Se elimina la referencia al nodo siguiente
            }
        }
    }

    // Método para eliminar el nodo en la mitad de la lista
    public void eliminarMedio() {
        if (!vacia()) { // Verificamos si la lista no está vacía
            int tamaño = 0;
            Nodo actual = primero;
            while (actual != null) { // Calculamos el tamaño de la lista
                tamaño++;
                actual = actual.getSiguiente();
            }
            int mitad = tamaño / 2; // Calculamos el índice de la mitad de la lista
            actual = primero;
            for (int i = 0; i < mitad; i++) { // Avanzamos hasta llegar al nodo que estará en la mitad
                actual = actual.getSiguiente();
            }
            if (actual == primero) { // Si el nodo a eliminar es el primero
                eliminarAlInicio();
            } else if (actual == ultimo) { // Si el nodo a eliminar es el último
                eliminarAlFinal();
            } else { // Si el nodo a eliminar está en medio de la lista
                // Ajustamos los enlaces para eliminar el nodo en medio de la lista
                actual.getAnterior().setSiguiente(actual.getSiguiente());
                actual.getSiguiente().setAnterior(actual.getAnterior());
            }
        }
    }

    // Método para recorrer y listar los nodos de la lista de inicio a fin
    public void listarRecorrer() {
        Nodo actual = primero;
        while (actual != null) { // Recorremos la lista mientras haya nodos
            System.out.println(actual.getDato()); // Imprimimos el dato del nodo actual
            actual = actual.getSiguiente(); // Avanzamos al siguiente nodo
        }
    }

    // Método para eliminar duplicados en la lista
    public void removerDuplicado() {
        if (primero == null) { // Si la lista está vacía, no hay nada que hacer
            return;
        }
        HashSet<Object> set = new HashSet<>(); // Utilizamos un HashSet para almacenar valores únicos
        Nodo actual = primero;
        while (actual != null) { // Recorremos la lista
            if (set.contains(actual.getDato())) { // Si el dato ya está en el conjunto, es un duplicado
                Nodo siguiente = actual.getSiguiente(); // Guardamos referencia al siguiente nodo
                Nodo anterior = actual.getAnterior(); // Guardamos referencia al nodo anterior

                // Actualizamos los enlaces para eliminar el nodo duplicado
                if (anterior != null) {
                    anterior.setSiguiente(siguiente);
                } else {
                    primero = siguiente;
                }

                if (siguiente != null) {
                    siguiente.setAnterior(anterior);
                } else {
                    ultimo = anterior;
                }
                actual = siguiente; // Avanzamos al siguiente nodo
            } else {
                set.add(actual.getDato()); // Agregamos el dato al conjunto
                actual = actual.getSiguiente(); // Avanzamos al siguiente nodo
            }
        }
    }

    // Método para listar los nodos de la lista de fin a inicio
    public void listarAlReves() {
        Nodo actual = ultimo; // Comenzamos desde el último nodo
        while (actual != null) { // Recorremos la lista mientras haya nodos
            System.out.println("\n\t" + actual.getDato()); // Imprimimos el dato del nodo actual
            actual = actual.getAnterior(); // Retrocedemos al nodo anterior
        }
    }

    // Método para obtener el tamaño de la lista
    public void listarTamaño() {
        int tamaño = 0; // Inicializamos el tamaño en 0
        Nodo actual = primero;
        while (actual != null) { // Recorremos la lista contando los nodos
            tamaño++;
            actual = actual.getSiguiente();
        }
        System.out.println("El tamaño de la lista es de " + tamaño + " nodos"); // Imprimimos el tamaño
    }
}
