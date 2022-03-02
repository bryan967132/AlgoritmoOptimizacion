package OrdenAlfabetico;
public class Alfabetico {
	public static void main(String[] args) {
		String[] arreglo = {"keneth","bryan","hugo","estela","bryan","luna"};
		Alfabetico alf = new Alfabetico();
		String[] ordenado = alf.ordenar(arreglo);
		for(int i = 0; i < ordenado.length; i++) {
			System.out.println(ordenado[i]);
		}
	}
	public String[] ordenar(String[] palabras) {
		for(int i = 0; i < palabras.length; i++) {
			for(int j = i + 1; j < palabras.length; j++) {
				if(comparar(palabras[i],palabras[j]) > 0) { // si palabras[i] es mas grande que palabras[j]
					String tmp = palabras[i];
					palabras[i] = palabras[j];
					palabras[j] = tmp;
				}
			}
		}
		return palabras;
	}
	public int comparar(String palabra1,String palabra2) {
		for(int i = 0; i < Math.min(palabra1.length(),palabra2.length()); i++) {
			if((int)palabra1.charAt(i) != (int) palabra2.charAt(i)) { //comparar valores unicode
				return (int)palabra1.charAt(i) - (int)palabra2.charAt(i);
			}
		}
		if(palabra1.length() != palabra2.length()) {
			return palabra1.length() - palabra2.length();
		}
		return 0;
	}
}