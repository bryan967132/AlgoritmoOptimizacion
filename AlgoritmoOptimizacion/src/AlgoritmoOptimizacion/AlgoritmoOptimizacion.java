package AlgoritmoOptimizacion;
public class AlgoritmoOptimizacion {
	public static void main(String[] args) {
		//Primero
		/*int filas = 2;
		int columnas = 4;
		double intCost = 1;
		double voltCost = 1;
		String mos1 = "WBWBWWWB";
		String mos2 = "BWBWWWWW";*/
		//Tercero
		/*int filas = 1;
		int columnas = 5;
		double intCost = 1;
		double voltCost = 1000;
		String mos1 = "BBBWW";//BBBWW
		String mos2 = "WBBBB";*/
		//Segundo
		/*int filas = 3;
		int columnas = 3;
		double intCost = 1;
		double voltCost = 1;
		String mos1 = "WBBBWBWWW";//WWWWBWWWB
		String mos2 = "WWWWBWWWB";*/
		//Cuarto
		/*int filas = 4;
		int columnas = 4;
		double intCost = 2;
		double voltCost = 1;
		String mos2 = "BBBBBBBWBBBBBWBW";//BBBBBBBWBBBBBWBW
		String mos1 = "WBBBWBBBBBBWBWBB";*/
		//Quinto
		/*int filas = 5;
		int columnas = 4;
		double intCost = 1;
		double voltCost = 1;
		String mos2 = "WBWBBWBWWBWBBWBWWBWB";//BBBBBBBWBBBBBWBW
		String mos1 = "BWBWWBWBBWBWWBWBBWBW";*/
		int filas = 4;
		int columnas = 5;
		double intCost = 1;
		double voltCost = 1;
		String mos2 = "WWWBWWBWWBWWBWWWWWWW";//BBBBBBBWBBBBBWBW
		String mos1 = "BWWWWWWBWBWWWBWWWWBW";
		AlgoritmoOptimizacion d = new AlgoritmoOptimizacion();
		String[][] mosaico1 = d.getMos(mos1,filas,columnas);
		String[][] mosaico2 = d.getMos(mos2,filas,columnas);
		d.printMos(mosaico1);
		d.printMos(mosaico2);
		//// a partir de aquí en python
		int[][] pares = d.getCantCol(mosaico1,mosaico2); //listo
		//d.printPar(pares); //listo
		String color = d.getMenorC(pares); //listo
		//System.out.println(color); //listo
		int[] priSec = d.getPriSec(pares); //listo
		//System.out.println(priSec[0] + " " + priSec[1] + "\n"); //listo
		int[][] crd1 = d.getCrd(mosaico1,priSec[0],color); //listo
		//d.printCrd(color,crd1); //listo
		int[][] crd2 = d.getCrd(mosaico2,priSec[1],color); //listo
		//d.printCrd(color,crd2); //listo
		int[][] rutas = d.getRutas(crd1,crd2); //listo
		//d.printRutas(rutas); //listo
		rutas = d.optRutas(rutas); //listo
		//d.printRutas(rutas); //listo
		rutas = d.crearCaminos(mosaico1,rutas,crd2,color);
		//d.printRutas(rutas);
		int intercambios = d.getMovT(rutas,mosaico1,mosaico2,color,intCost,voltCost);
		int volteos = d.getVolT(mosaico1,mosaico2,rutas,crd2,color,intCost,voltCost);
		double minCost = d.minCost(intercambios,volteos,intCost,voltCost);
		System.out.println("Costo mínimo: Q " + minCost);
		System.out.println("Intercambios: " + intercambios);
		System.out.println("Volteos: " + volteos);
		System.out.println();
		System.out.println("Transición: ");
		d.printMos(mosaico1);
		d.transMos(rutas,mosaico1,mosaico2,color,intCost,voltCost);
	}
	void transMos(int[][] rutas,String[][] mosIni,String[][] mosFin,String color,double intCost,double voltCost) {
		String colorS = "B";
		if(color.equals("B")) colorS = "W";
		String[][] tmpMos = new String[mosIni.length][mosIni[0].length];
		for(int i = 0; i < tmpMos.length; i++) {
			for(int j = 0; j < tmpMos[i].length; j++) {
				tmpMos[i][j] = mosIni[i][j];
			}
		}
		for(int i = 0; i < rutas.length; i++) {
			for(int x = 0; x < tmpMos.length; x++) {
				boolean parar = false;
				for(int y = 0; y < tmpMos[x].length; y++) {
					int f = 0,c = 0; 
					if(x == rutas[i][0] && y == rutas[i][1]) {
						f = x;
						c = y;
						if((Math.abs(rutas[i][4]) + Math.abs(rutas[i][5])) * intCost <= 2 * voltCost) {
							if(Math.abs(rutas[i][4]) != 0) {
								for(int m = 1; m <= Math.abs(rutas[i][4]); m++) {
									int mover = 1;
									if(rutas[i][4] < 0) mover = -1;
									tmpMos[f][c] = colorS;
									tmpMos[f][c + mover] = color;
									System.out.println("Se Intercambió. Costo: " + intCost);
									printMos(tmpMos);
									if(verificar(f,c + mover,mosFin,color)) break;
									c += mover;
								}
							}
							if(Math.abs(rutas[i][5]) != 0) {
								for(int m = 1; m <= Math.abs(rutas[i][5]); m++) {
									int mover = 1;
									if(rutas[i][5] < 0) mover = -1;
									tmpMos[f][c] = colorS;
									tmpMos[f + mover][c] = color;
									System.out.println("Se Intercambió. Costo: " + intCost);
									printMos(tmpMos);
									if(verificar(f + mover,c,mosFin,color)) break;
									f += mover;
								}
							}
						}else {
							System.out.println("Se volteó. Costo: " + voltCost);
							tmpMos[rutas[i][0]][rutas[i][1]] = colorS;
							printMos(tmpMos);
							System.out.println("Se volteó. Costo: " + voltCost);
							tmpMos[rutas[i][2]][rutas[i][3]] = color;
							printMos(tmpMos);
						}
						parar = true;
						break;
					}
				}
				if(parar) break;
			}
		}
		voltear(tmpMos,mosFin,voltCost);
	}
	boolean verificar(int i,int j,String[][] mosaico,String color) {
		if(mosaico[i][j].equals(color)) return true;
		return false;
	}
	void voltear(String[][] tmpMos,String[][] mosFin,double voltCost) {
		for(int i = 0; i < tmpMos.length; i++) {
			for(int j = 0; j < tmpMos[i].length; j++) {
				if(!tmpMos[i][j].equals(mosFin[i][j])) {
					System.out.println("Se volteó. Costo: " + voltCost);
					tmpMos[i][j] = mosFin[i][j];
					printMos(tmpMos);
				}
			}
		}
	}
	double minCost(int intercambios,int volteos,double intCost,double voltCost) {
		return intercambios * intCost + volteos * voltCost;
	}
	int getVolT(String[][] mosIni,String[][] mosFin,int[][] rutas,int[][] crd2,String color,double intCost,double voltCost) {
		int volteos = 0;
		String colorS = "B";
		if(color.equals("B")) colorS = "W";
		String[][] tmpMos = new String[mosIni.length][mosIni[0].length];
		for(int i = 0; i < tmpMos.length; i++) {
			for(int j = 0; j < tmpMos[i].length; j++) {
				tmpMos[i][j] = mosIni[i][j];
			}
		}
		for(int i = 0; i < rutas.length; i++) {
			for(int x = 0; x < tmpMos.length; x++) {
				boolean parar = false;
				for(int y = 0; y < tmpMos[x].length; y++) {
					int f = 0,c = 0; 
					if(x == rutas[i][0] && y == rutas[i][1]) {
						f = x;
						c = y;
						if((Math.abs(rutas[i][4]) + Math.abs(rutas[i][5])) * intCost <= 2 * voltCost) {
							if(Math.abs(rutas[i][4]) != 0) {
								for(int m = 1; m <= Math.abs(rutas[i][4]); m++) {
									int mover = 1;
									if(rutas[i][4] < 0) mover = -1;
									tmpMos[f][c] = colorS;
									tmpMos[f][c + mover] = color;
									if(verificar(f,c + mover,mosFin,color)) break;
									c += mover;
								}
							}
							if(Math.abs(rutas[i][5]) != 0) {
								for(int m = 1; m <= Math.abs(rutas[i][5]); m++) {
									int mover = 1;
									if(rutas[i][5] < 0) mover = -1;
									tmpMos[f][c] = colorS;
									tmpMos[f + mover][c] = color;
									if(verificar(f + mover,c,mosFin,color)) break;
									f += mover;
								}
							}
						}else {
							tmpMos[rutas[i][0]][rutas[i][1]] = colorS;
							tmpMos[rutas[i][2]][rutas[i][3]] = color;
							volteos += 2;
						}
						parar = true;
						break;
					}
				}
				if(parar) break;
			}
		}
		for(int i = 0; i < tmpMos.length; i++) {
			for(int j = 0; j < tmpMos[i].length; j++) {
				if(!tmpMos[i][j].equals(mosFin[i][j])) {
					volteos ++;
				}
			}
		}
		return volteos;
	}
	int getMovT(int[][] rutas,String[][] mosIni,String[][] mosFin,String color,double intCost,double voltCost) {
		int movs = 0;
		String colorS = "B";
		if(color.equals("B")) colorS = "W";
		String[][] tmpMos = new String[mosIni.length][mosIni[0].length];
		for(int i = 0; i < tmpMos.length; i++) {
			for(int j = 0; j < tmpMos[i].length; j++) {
				tmpMos[i][j] = mosIni[i][j];
			}
		}
		for(int i = 0; i < rutas.length; i++) {
			for(int x = 0; x < tmpMos.length; x++) {
				boolean parar = false;
				for(int y = 0; y < tmpMos[x].length; y++) {
					int f = 0,c = 0; 
					if(x == rutas[i][0] && y == rutas[i][1]) {
						f = x;
						c = y;
						if((Math.abs(rutas[i][4]) + Math.abs(rutas[i][5])) * intCost <= 2 * voltCost) {
							if(Math.abs(rutas[i][4]) != 0) {
								for(int m = 1; m <= Math.abs(rutas[i][4]); m++) {
									int mover = 1;
									if(rutas[i][4] < 0) mover = -1;
									tmpMos[f][c] = colorS;
									tmpMos[f][c + mover] = color;
									movs ++;
									if(verificar(f,c + mover,mosFin,color)) break;
									c += mover;
								}
							}
							if(Math.abs(rutas[i][5]) != 0) {
								for(int m = 1; m <= Math.abs(rutas[i][5]); m++) {
									int mover = 1;
									if(rutas[i][5] < 0) mover = -1;
									tmpMos[f][c] = colorS;
									tmpMos[f + mover][c] = color;
									movs ++;
									if(verificar(f + mover,c,mosFin,color)) break;
									f += mover;
								}
							}
						}
						parar = true;
						break;
					}
				}
				if(parar) break;
			}
		}
		return movs;
	}
	int[][] crearCaminos(String[][] mosaico1,int[][] rutas,int[][] crd2,String color){
		int caminos = cantFalts(mosaico1,crd2,color);
		int[][] falts = getFalts(mosaico1,crd2,color,caminos);
		falts = getOptC(rutas,falts);
		return falts;
	}
	int cantFalts(String[][] mosaico1,int[][] crd2,String color) {
		int cant = 0;
		for(int i = 0; i < crd2.length; i++) {
			if(!mosaico1[crd2[i][0]][crd2[i][1]].equals(color)) {
				cant ++;
			}
		}
		return cant;
	}
	int[][] getFalts(String[][] mosaico1,int[][] crd2,String color,int cant) {
		int[][] faltantes = new int[cant][2];
		int falt = 0;
		for(int i = 0; i < crd2.length; i++) {
			if(!mosaico1[crd2[i][0]][crd2[i][1]].equals(color)) {
				faltantes[falt][0] = crd2[i][0];
				faltantes[falt][1] = crd2[i][1];
				falt ++;
			}
		}
		return faltantes;
	}
	int[][] getOptC(int[][] rutas,int[][] falts) {
		int[][] optimo = new int[falts.length][6];
		int c = 0;
		rutas = getMinRutas(rutas);
		for(int i = 0; i < falts.length; i++) {
			int[] punto = new int[2];
			for(int x = 0; x < rutas.length; x++) {
				if(rutas[x][2] != - 1  && rutas[x][3] != - 1 && rutas[x][2] == falts[i][0] && rutas[x][3] == falts[i][1]) {
					optimo[c][0] = rutas[x][0];
					optimo[c][1] = rutas[x][1];
					optimo[c][2] = rutas[x][2];
					optimo[c][3] = rutas[x][3];
					optimo[c][4] = rutas[x][4];
					optimo[c][5] = rutas[x][5];
					punto[0] = rutas[x][0];
					punto[1] = rutas[x][1];
					c ++;
					break;
				}
			}
			if(punto != null) {
				rutas = dscrtRutas(rutas,punto,falts[i]);
				rutas = getMinRutas(rutas);
			}
		}
		return optimo;
	}
	int[][] getMinRutas(int[][] rutas) {
		for(int i = 0; i < rutas.length - 1; i++) {
			for(int x = 0; x < rutas.length - i - 1; x++) {
				int movActual = Math.abs(rutas[x][4]) + Math.abs(rutas[x][5]);
				int movSiguiente = Math.abs(rutas[x + 1][4]) + Math.abs(rutas[x + 1][5]);
				if(movActual > movSiguiente) {
					int[] tmp = rutas[x + 1];
					rutas[x + 1] = rutas[x];
					rutas[x] = tmp;
				}
			}
		}
		return rutas;
	}
	int[][] dscrtRutas(int[][] rutas,int[] punto,int[] falts) {
		for(int x = 0; x < rutas.length; x++) {
			if((rutas[x][0] == punto[0] && rutas[x][1] == punto[1]) || (rutas[x][2] == falts[0] && rutas[x][3] == falts[1])) {
				rutas[x][0] = -1;
				rutas[x][1] = -1;
				rutas[x][2] = -1;
				rutas[x][3] = -1;
				rutas[x][4] = -1;
				rutas[x][5] = -1;
			}
		}
		return rutas;
	}
	int[][] optRutas(int[][] rutas) {
		int preC = cantNoMover(rutas);
		if(preC > 0) {
			int[][] preOpt = getNoMover(rutas,preC);
			preC = cantDescart(preOpt,rutas);
			int[][] rep = getDescart(preOpt,rutas,preC);
			preC = cantOpt(rutas,rep);
			int[][] opt = getOpt(rutas,rep,preC);
			return opt;
		}else return rutas;
	}
	int cantNoMover(int[][] rutas) {
		int cant = 0;
		for(int i = 0; i < rutas.length; i++) {
			if(Math.abs(rutas[i][4]) + Math.abs(rutas[i][5]) == 0) {
				cant ++;
			}
		}
		return cant;
	}
	int[][] getNoMover(int[][] rutas,int cant) {
		int[][] preOpt = new int[cant][2];
		cant = 0;
		for(int i = 0; i < rutas.length; i++) {
			if(Math.abs(rutas[i][4]) + Math.abs(rutas[i][5]) == 0) {
				preOpt[cant][0] = rutas[i][0];
				preOpt[cant][1] = rutas[i][1];
				cant ++;
			}
		}
		return preOpt;
	}
	int cantDescart(int[][] preOpt,int[][] rutas) {
		int cant = 0;
		for(int i = 0; i < preOpt.length; i++) {
			for(int x = 0; x < rutas.length; x++) {
				if((preOpt[i][0] == rutas[x][0] && preOpt[i][1] == rutas[x][1]) || (preOpt[i][0] == rutas[x][2] && preOpt[i][1] == rutas[x][3])) {
					cant++;
				}
			}
		}
		return cant;
	}
	int[][] getDescart(int[][] preOpt,int[][] rutas,int cant) {
		int[][] rep = new int[cant][6];
		cant = 0;
		for(int i = 0; i < preOpt.length; i++) {
			for(int x = 0; x < rutas.length; x++) {
				if((preOpt[i][0] == rutas[x][0] && preOpt[i][1] == rutas[x][1]) || (preOpt[i][0] == rutas[x][2] && preOpt[i][1] == rutas[x][3])) {
					rep[cant][0] = rutas[x][0];
					rep[cant][1] = rutas[x][1];
					rep[cant][2] = rutas[x][2];
					rep[cant][3] = rutas[x][3];
					rep[cant][4] = rutas[x][4];
					rep[cant][5] = rutas[x][5];
					cant ++;
				}
			}
		}
		return rep;
	}
	int cantOpt(int[][] rutas,int[][] rep) {
		int cant = 0;
		for(int i = 0; i < rutas.length; i++) {
			if(insertar(rep,rutas[i])) cant++;
		}
		return cant;
	}
	int[][] getOpt(int[][] rutas,int[][] rep,int cant) {
		int[][] opt = new int[cant][6];
		cant = 0;
		for(int i = 0; i < rutas.length; i++) {
			if(insertar(rep,rutas[i])) {
				opt[cant][0] = rutas[i][0];
				opt[cant][1] = rutas[i][1];
				opt[cant][2] = rutas[i][2];
				opt[cant][3] = rutas[i][3];
				opt[cant][4] = rutas[i][4];
				opt[cant][5] = rutas[i][5];
				cant ++;
			}
		}
		return opt;
	}
	boolean insertar(int[][] rep,int[] ruta) {
		for(int i = 0; i < rep.length; i++) {
			if(rep[i][0] == ruta[0] && rep[i][1] == ruta[1] && rep[i][2] == ruta[2] && rep[i][3] == ruta[3] && rep[i][4] == ruta[4] && rep[i][5] == ruta[5]) return false;
		}
		return true;
	}
	int[][] getRutas(int[][] crd1,int[][] crd2) {
		int[][] rutas = new int[crd1.length * crd2.length][6];
		int ruta = 0;
		for(int i = 0; i < crd1.length; i++) {
			for(int x = 0; x < crd2.length; x++) {
				int v = crd2[x][0] - crd1[i][0];
				int h = crd2[x][1] - crd1[i][1];
				rutas[ruta][0] = crd1[i][0];
				rutas[ruta][1] = crd1[i][1];
				rutas[ruta][2] = crd2[x][0];
				rutas[ruta][3] = crd2[x][1];
				rutas[ruta][4] = h;
				rutas[ruta][5] = v;
				ruta ++;
			}
		}
		return rutas;
	}
	void printRutas(int[][] rutas) {
		for(int i = 0; i < rutas.length; i++) {
			System.out.println(rutas[i][0] + " , " + rutas[i][1] + " -> " + rutas[i][2] + " , " + rutas[i][3] + " | PasoX: " + rutas[i][4] + " | PasoY: " +  rutas[i][5]);
		}
		System.out.println();
	}
	int[][] getCrd(String[][] mosaico,int cantidad,String color) {
		int[][] pares = new int[cantidad][2];
		int fila = 0;
		for(int i = 0; i < mosaico.length; i++) {
			for(int j = 0; j < mosaico[i].length; j++) {
				if(mosaico[i][j].equals(color)) {
					pares[fila][0] = i;
					pares[fila][1] = j;
					fila ++;
				}
			}
		}
		return pares;
	}
	void printCrd(String c,int[][] pares){
		String nMosaico = c+" Ubicados\n";
		for(int i = 0; i < pares.length; i++) {
				nMosaico += pares[i][0]+" , " + pares[i][1] + "\n";
		}
		System.out.println(nMosaico);
	}
	int[] getPriSec(int[][] pares) {
		int pri = 0;
		int sec = 0;
		if(pares[0][0] < pares[0][1]) {
			pri = pares[0][0];
			sec = pares[1][0];
		}else {
			pri = pares[0][1];
			sec = pares[1][1];
		}
		int[] mayMen = {pri,sec};
		return mayMen;
	}
	String getMenorC(int[][] pares) {
		if(pares[0][0] < pares[0][1]) return "W";
		return "B";
	}
	int[][] getCantCol(String[][] mosaico1,String[][] mosaico2){
		int w1 = 0,b1 = 0,w2 = 0,b2 = 0;
		for(int i = 0; i < mosaico1.length; i++) {
			for(int j = 0; j < mosaico1[i].length; j++) {
				if(mosaico1[i][j].equalsIgnoreCase("W")) w1++;
				else b1 ++;
				if(mosaico2[i][j].equalsIgnoreCase("W")) w2++;
				else b2 ++;
			}
		}
		int[][] pares = {{w1,b1},{w2,b2}};
		return pares;
	}
	void printPar(int[][] pares) {
		String nPares = "W B\n";
		for(int i = 0; i < pares.length; i++) {
			for(int j = 0; j < pares[i].length; j++) {
				nPares += pares[i][j]+" ";
			}
			nPares += "\n";
		}
		System.out.println(nPares);
	}
	String[][] getMos(String mosaico,int filas,int columnas) {
		String[][] matriz = new String[filas][columnas];
		int pos = 0;
		char[] mos = mosaico.toCharArray();
		for(int i = 0; i < filas; i++) {
			for(int j = 0; j < columnas; j++) {
				matriz[i][j] = String.valueOf(mos[pos]);
				pos ++;
			}
		}
		return matriz;
	}
	void printMos(String[][] mosaico) {
		String nMosaico = "";
		for(int i = 0; i < mosaico.length; i++) {
			for(int j = 0; j < mosaico[i].length; j++) {
				if(mosaico[i][j].equals("B")) {
					nMosaico += "░░";
				}else {
					nMosaico += "██";
				}
			}
			nMosaico += "\n";
		}
		System.out.println(nMosaico);
	}
}