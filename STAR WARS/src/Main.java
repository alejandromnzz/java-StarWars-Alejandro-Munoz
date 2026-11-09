import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String ROJO = "\u001B[31m";
        String RESET = "\u001B[0m";

        int MAX_FIL = 10;
        int MAX_COL = 10;

        char PERSONAJE;
        char YODA = 'Y';
        char DM = 'D';
        char LIBRE = 'L';

        boolean yodaActive = true;
        boolean dmActive = false;

        char[][] tableroYoda = new char[MAX_FIL][MAX_COL];
        char[][] tableroDM = new char[MAX_FIL][MAX_COL];

        char[][] tableroACTIVO = null;

        for (int juego = 0; juego < 2; juego++) {

            if((yodaActive==true) && (dmActive==false)){
                tableroACTIVO = tableroYoda;
                PERSONAJE = YODA;
                yodaActive = false;
                dmActive = true;
            }else{
                tableroACTIVO = tableroDM;
                PERSONAJE = DM;
                yodaActive = true;
                dmActive = false;
            }

            for (int i = 0; i < MAX_FIL; i++) {
                for (int j = 0; j < MAX_COL; j++) {
                    tableroACTIVO[i][j] = LIBRE;
                }
            }

            Random aleat = new Random();

            int filaAl = -1;
            int colAl = -1;
            int filaOficial = -1;
            int columnaOficial = -1;

            for (int i = 0; i < 1; i++) {
                do{
                    filaAl = aleat.nextInt(MAX_FIL);
                    colAl = aleat.nextInt(MAX_COL);
                }while(tableroACTIVO[filaAl][colAl]!=LIBRE);
                tableroACTIVO[filaAl][colAl] = PERSONAJE;
            }

            // OFICIALIZO LAS POSICIONES DE MI PERSONAJE
            filaOficial = filaAl;
            columnaOficial = colAl;

            // FIN
            char MURO = 'M';
            for (int i = 0; i < 10; i++) {
                do{
                    filaAl = aleat.nextInt(MAX_FIL);
                    colAl = aleat.nextInt(MAX_COL);
                }while(tableroACTIVO[filaAl][colAl]!=LIBRE);
                tableroACTIVO[filaAl][colAl] = MURO;
            }

            char VADER = 'V';
            for (int i = 0; i < 7; i++) {
                do{
                    filaAl = aleat.nextInt(MAX_FIL);
                    colAl = aleat.nextInt(MAX_COL);
                }while(tableroACTIVO[filaAl][colAl]!=LIBRE);
                tableroACTIVO[filaAl][colAl] = VADER;
            }

            char POCIMA = 'P';
            for (int i = 0; i < 5; i++) {
                do{
                    filaAl = aleat.nextInt(MAX_FIL);
                    colAl = aleat.nextInt(MAX_COL);
                }while(tableroACTIVO[filaAl][colAl]!=LIBRE);
                tableroACTIVO[filaAl][colAl] = POCIMA;
            }

            for (int i = 0; i < MAX_FIL; i++) {
                for (int j = 0; j < MAX_COL; j++) {
                    if (tableroACTIVO[i][j] == PERSONAJE) {
                        System.out.print(ROJO + tableroACTIVO[i][j] + RESET + " ");
                    } else {
                        System.out.print(tableroACTIVO[i][j] + " ");
                    }
                }
                System.out.println();
            }

            // EMPIEZA EL JUEGO !!!
            int vidas = 1;
            Scanner lector = new Scanner(System.in);

            do {
                System.out.println("EMPIEZA EL JUEGO!!");
                System.out.println("Te mueves en WASD");
                System.out.println("Dime una letra de movimiento; ");
                char mov = lector.next().charAt(0);
                int filaAnterior = filaOficial;
                int colAnterior = columnaOficial;
                switch (mov){ // W, A, S o D
                    case 'W':
                        filaOficial = filaOficial - 1;
                        break;
                    case 'A':
                        columnaOficial = columnaOficial - 1;
                        break;
                    case 'S':
                        filaOficial = filaOficial + 1;
                        break;
                    case 'D':
                        columnaOficial = columnaOficial + 1;
                        break;
                }

                // Y SI PERSONAJE SE SALE DEL TABLERO ??????????????????
                if(
                        filaOficial < 0 ||
                                filaOficial > 9 ||
                                columnaOficial < 0 ||
                                columnaOficial > 9
                ){
                    System.out.println("EHHHHHHHH!!!!!!!!!!!!");
                    System.out.println("LÍMITE DE TABLERO!!!!!!");
                    filaOficial = filaAnterior;
                    columnaOficial = colAnterior;
                }else{
                    if(tableroACTIVO[filaOficial][columnaOficial] == MURO){
                        System.out.println("NO PUEDES PASAR!!!!!");
                        System.out.println(" ");
                        filaOficial = filaAnterior;
                        columnaOficial = colAnterior;
                    }else if(tableroACTIVO[filaOficial][columnaOficial] == POCIMA){
                        tableroACTIVO[filaOficial][columnaOficial] = PERSONAJE;
                        tableroACTIVO[filaAnterior][colAnterior] = LIBRE;
                        System.out.println("HAS SUMADO UNA VIDA");
                        System.out.println(" ");
                        vidas++;
                    }else if(tableroACTIVO[filaOficial][columnaOficial] == VADER){
                        tableroACTIVO[filaOficial][columnaOficial] = PERSONAJE;
                        tableroACTIVO[filaAnterior][colAnterior] = LIBRE;
                        vidas--;
                        System.out.println("HAS PERDIDO UNA VIDA");
                        System.out.println(" ");
                    }else if(tableroACTIVO[filaOficial][columnaOficial] == LIBRE){
                        tableroACTIVO[filaOficial][columnaOficial] = PERSONAJE;
                        tableroACTIVO[filaAnterior][colAnterior] = LIBRE;
                    }
                }

                for (int i = 0; i < MAX_FIL; i++) {
                    for (int j = 0; j < MAX_COL; j++) {
                        if (tableroACTIVO[i][j] == PERSONAJE) {
                            System.out.print(ROJO + tableroACTIVO[i][j] + RESET + " ");
                        } else {
                            System.out.print(tableroACTIVO[i][j] + " ");
                        }
                    }
                    System.out.println(" ");
                }

            }while(vidas>0);

            System.out.println(" ");

        }
    }
}