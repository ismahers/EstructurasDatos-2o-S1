package estdatos;

public abstract class AbstractBinaryTree<E> implements BinaryTree<E> {

	private static String spaces(int n) {
		StringBuilder out = new StringBuilder("\n");
		for (int k = 0; k < n; k++) {
			out.append(' ');
		}
		return out.toString();
	}
	
	private static <E> String toStringLevel(BinaryTree<E> t, int n) {	
		StringBuilder out = new StringBuilder();
		String spaces = spaces(2 * n);
		
		out.append(spaces);
		
		if (t.isEmpty()) {
			out.append("()");
		} else {		
			out.append('(');
			if (!t.isEmpty()) {
				out.append(t.label());
				out.append(' ');
				out.append(toStringLevel(t.left(), n + 1));
				out.append(' ');
				out.append(toStringLevel(t.right(), n + 1));
			}
			out.append(spaces);
			out.append(')');
		}
		
		return out.toString();
	}
	
	@Override
	public String toString() {
		return toStringLevel(this, 0);
	}

}
