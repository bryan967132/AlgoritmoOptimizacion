package Dijkstra;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
public class DijkstraYouTube {
	int costo[][] = new int[10][10];
	int distancia[] = new int[10];
	void calc(int nodos,int origen) {
		int flag[] = new int[nodos + 1];
		int i,k,c,minimo;
		int minpos = 1;
		//llevamos al vector distancia la primera fila de la matriz
		for(i = 1; i <= nodos; i++) {
			flag[i] = 0;
			distancia[i] = costo[origen][i];
		}
		c = 2;
		while(c <= nodos) {
			minimo = 99;
			for(k = 1; k <= nodos; k++) {
				if(distancia[k] < minimo && flag[k] != 1) {
					minimo = distancia[i];
					minpos = k;
				}
			}
			flag[minpos] = 1;
			c ++;
			for(k = 1; k <= nodos; k++) {
				if(distancia[minpos] + costo[minpos][k] < distancia[k] && flag[k] != 1) {
					distancia[k] = distancia[minpos] + costo[minpos][k];
				}
			}
		}
	}
	public static void main(String[] args) {
		JTextArea ventana = new JTextArea();
		String salida = "NODO\tA \tB   \tC  \tD\n";
		int c = 1;
		
		int nodos,origen,i,j;
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese el número de Nodos: ");
		nodos = sc.nextInt();
		DijkstraYouTube d = new DijkstraYouTube();
		System.out.println("Ingrese el costo de los pesos de la matriz separados por espacios:");
		//Llenamos la matriz
		for(i = 1; i <= nodos; i++) {
			salida += c++ + "\t";
			for(j = 1; j <= nodos; j++) {
				d.costo[i][j] = sc.nextInt();
				salida += d.costo[i][j] + "\t";
			}
			salida += "\n";
		}
		
		ventana.setText(salida);
		JOptionPane.showMessageDialog(null,ventana);
		
		System.out.print("Ingrese el Vértice de origen: ");
		origen = sc.nextInt();
		
		d.calc(nodos,origen);
		System.out.println("La ruta más corta desde el nodo \t" + origen + "\t a todos los demás es: \n");
		for(i = 1; i <= nodos; i++) {
			if(i != origen) {
				System.out.println("Origen: " + origen + "\t Destino: " + i + "\t Costo mínimo: " + d.distancia[i] + "\t");
			}
		}
		
		System.out.println("Mostrando la matriz de costos");
		for(i = 0; i <= nodos; i++) {
			for(j = 0; j <= nodos; j++) {
				System.out.print(d.costo[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	/*
	matriz de costos de 4 nodos
	0      3   999   7
3      0    4      2
999  4    0      5
7      2    5      0
	
	*/
}