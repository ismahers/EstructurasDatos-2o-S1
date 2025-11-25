package estdatos;

import java.nio.BufferOverflowException;
import java.util.Comparator;

public class BagSorted<E> extends BagMod<E> {

    private Comparator<? super E> comparator;

    // Constructor con capacidad inicial y orden natural
    public BagSorted(int capacidadInicial) {
        super(capacidadInicial);
        this.comparator = null;
    }

    // Constructor con capacidad y comparador
    public BagSorted(int initialCapacity, Comparator<? super E> comparator) {
        super(initialCapacity);
        this.comparator = comparator;
    }

    // Constructor por defecto
    public BagSorted() {
        super(100);
        this.comparator = null;
    }

    // Constructor solo con comparador
    public BagSorted(Comparator<? super E> comparator) {
        super(100);
        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
	@Override
    public boolean add(E e) {
        if (e == null) {
            throw new IllegalArgumentException("No se permiten elementos nulos.");
        }

        if (comparator == null && !(e instanceof Comparable)) {
            throw new IllegalArgumentException(
                "El elemento no implementa Comparable y no se proporcionó un Comparator."
            );
        }

        if (size == data.length) {
            throw new BufferOverflowException();
        }

        // Busco la posición donde se inserta ordenadamente utilizando compare
        int insertPos = 0;
        while (insertPos < size) {
            E actual = data[insertPos];
            int cmp;
            if (comparator != null) {
                cmp = comparator.compare(e, actual);
            } else {
                cmp = ((Comparable<E>) e).compareTo(actual);
            }

            if (cmp <= 0) {
                break;
            }

            insertPos++;
        }

        // Muevo los elementos hacia la derecha
        for (int i = size; i > insertPos; i--) {
            data[i] = data[i - 1];
        }

        data[insertPos] = e;
        size++;
        return true;
    }
}

