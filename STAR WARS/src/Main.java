import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int MAX_FIL_YODA = 10;
        int MAX_COL_YODA = 10;
        int MAX_FIL_DM = 10;
        int MAX_COL_DM = 10;

        char YODA = 'Y';
        char LIBRE = 'L';
        char DM = 'D';

        boolean yodaActive = true;
        boolean dmActive = false;

        char[][] tableroYoda = new char[MAX_FIL_YODA][MAX_COL_YODA];
        char[][] tableroDM = new char[MAX_FIL_DM][MAX_COL_DM];

        for (int juego = 0; juego < 2; juego++) {

            char[][] tableroACTIVO =null;

            if((yodaActive==true) && (dmActive==false)){

                for (int i = 0; i < MAX_FIL_YODA; i++) {
                    for (int j = 0; j < MAX_COL_YODA; j++) {
                        tableroYoda[i][j] = LIBRE;
                    }
                }

                // 1 YODA
                Random aleat = new Random();

                int filaAl = -1;
                int colAl = -1;
                int filaOficialYoda = -1;
                int columnaOficialYoda = -1;

                for (int i = 0; i < 1; i++) {
                    do{
                        filaAl = aleat.nextInt(MAX_FIL_YODA);
                        colAl = aleat.nextInt(MAX_COL_YODA);
                    }while(tableroYoda[filaAl][colAl]!=LIBRE);
                    tableroYoda[filaAl][colAl] = YODA;
                }

                // OFICIALIZO LAS POSICIONES DE MI YODA
                filaOficialYoda = filaAl;
                columnaOficialYoda = colAl;

                // FIN
                char MURO = 'M';
                for (int i = 0; i < 10; i++) {
                    do{
                        filaAl = aleat.nextInt(MAX_FIL_YODA);
                        colAl = aleat.nextInt(MAX_COL_YODA);
                    }while(tableroYoda[filaAl][colAl]!=LIBRE);
                    tableroYoda[filaAl][colAl] = MURO;
                }

                char VADER = 'V';
                for (int i = 0; i < 7; i++) {
                    do{
                        filaAl = aleat.nextInt(MAX_FIL_YODA);
                        colAl = aleat.nextInt(MAX_COL_YODA);
                    }while(tableroYoda[filaAl][colAl]!=LIBRE);
                    tableroYoda[filaAl][colAl] = VADER;
                }

                char POCIMA = 'P';
                for (int i = 0; i < 5; i++) {
                    do{
                        filaAl = aleat.nextInt(MAX_FIL_YODA);
                        colAl = aleat.nextInt(MAX_COL_YODA);
                    }while(tableroYoda[filaAl][colAl]!=LIBRE);
                    tableroYoda[filaAl][colAl] = POCIMA;
                }

                for (int i = 0; i < MAX_FIL_YODA; i++) {
                    for (int j = 0; j < MAX_COL_YODA; j++) {
                        System.out.print(tableroYoda[i][j] + " ");
                    }
                    System.out.println();
                }

                // EMPIEZA EL JUEGO !!!
                int vidas = 1;
                Scanner lector = new Scanner(System.in);

                do {
                    System.out.println("EMPIEZA EL JUEGO!!");
                    System.out.println("Te mueves en WASD");
                    System.out.println("Dime una letra de movimiento !!");
                    char mov = lector.next().charAt(0);
                    int filaAnteriorYoda = filaOficialYoda;
                    int colAnteriorYoda = columnaOficialYoda;
                    switch (mov){ // W, A, S o D
                        case 'W':
                            filaOficialYoda = filaOficialYoda - 1;
                            break;
                        case 'A':
                            columnaOficialYoda = columnaOficialYoda - 1;
                            break;
                        case 'S':
                            filaOficialYoda = filaOficialYoda + 1;
                            break;
                        case 'D':
                            columnaOficialYoda = columnaOficialYoda + 1;
                            break;
                    }

                    // Y SI YODA SE SALE DEL TABLERO ??????????????????
                    if(
                            filaOficialYoda < 0 ||
                                    filaOficialYoda > 9 ||
                                    columnaOficialYoda < 0 ||
                                    columnaOficialYoda > 9
                    ){
                        System.out.println("EHHHHHHHH!!!!!!!!!!!!");
                        System.out.println("LÍMITE DE TABLERO!!!!!!");
                        filaOficialYoda = filaAnteriorYoda;
                        columnaOficialYoda = colAnteriorYoda;
                    }else{
                        if(tableroYoda[filaOficialYoda][columnaOficialYoda] == MURO){
                            System.out.println("NO PUEDES PASAR!!!!!");
                            filaOficialYoda = filaAnteriorYoda;
                            columnaOficialYoda = colAnteriorYoda;
                        }else if(tableroYoda[filaOficialYoda][columnaOficialYoda] == POCIMA){
                            tableroYoda[filaOficialYoda][columnaOficialYoda] = YODA;
                            tableroYoda[filaAnteriorYoda][colAnteriorYoda] = LIBRE;
                            vidas++;
                        }else if(tableroYoda[filaOficialYoda][columnaOficialYoda] == VADER){
                            tableroYoda[filaOficialYoda][columnaOficialYoda] = YODA;
                            tableroYoda[filaAnteriorYoda][colAnteriorYoda] = LIBRE;
                            vidas--;
                        }else if(tableroYoda[filaOficialYoda][columnaOficialYoda] == LIBRE){
                            tableroYoda[filaOficialYoda][columnaOficialYoda] = YODA;
                            tableroYoda[filaAnteriorYoda][colAnteriorYoda] = LIBRE;
                        }
                    }

                    for (int i = 0; i < MAX_FIL_YODA; i++) {
                        for (int j = 0; j < MAX_COL_YODA; j++) {
                            System.out.print(tableroYoda[i][j] + " ");
                        }
                        System.out.println(" ");
                    }

                }while(vidas>0);

                yodaActive = false;
                dmActive = true;

            }else{

                System.out.println(" ");

                for (int i = 0; i < MAX_FIL_DM; i++) {
                    for (int j = 0; j < MAX_COL_DM; j++) {
                        tableroDM[i][j] = LIBRE;
                    }
                }

                Random aleat = new Random();

                int filaAl = -1;
                int colAl = -1;
                int filaOficialDm = -1;
                int columnaOficialDm = -1;

                for (int i = 0; i < 1; i++) {
                    do{
                        filaAl = aleat.nextInt(MAX_FIL_DM);
                        colAl = aleat.nextInt(MAX_COL_DM);
                    }while(tableroDM[filaAl][colAl]!=LIBRE);
                    tableroDM[filaAl][colAl] = DM;
                }

                // OFICIALIZO LAS POSICIONES DE MI DM
                filaOficialDm = filaAl;
                columnaOficialDm = colAl;

                // FIN
                char MURO = 'M';
                for (int i = 0; i < 10; i++) {
                    do{
                        filaAl = aleat.nextInt(MAX_FIL_DM);
                        colAl = aleat.nextInt(MAX_COL_DM);
                    }while(tableroDM[filaAl][colAl]!=LIBRE);
                    tableroDM[filaAl][colAl] = MURO;
                }

                char VADER = 'V';
                for (int i = 0; i < 7; i++) {
                    do{
                        filaAl = aleat.nextInt(MAX_FIL_DM);
                        colAl = aleat.nextInt(MAX_COL_DM);
                    }while(tableroDM[filaAl][colAl]!=LIBRE);
                    tableroDM[filaAl][colAl] = VADER;
                }

                char POCIMA = 'P';
                for (int i = 0; i < 5; i++) {
                    do{
                        filaAl = aleat.nextInt(MAX_FIL_DM);
                        colAl = aleat.nextInt(MAX_COL_DM);
                    }while(tableroDM[filaAl][colAl]!=LIBRE);
                    tableroDM[filaAl][colAl] = POCIMA;
                }

                for (int i = 0; i < MAX_FIL_DM; i++) {
                    for (int j = 0; j < MAX_COL_DM; j++) {
                        System.out.print(tableroDM[i][j] + " ");
                    }
                    System.out.println();
                }

                // EMPIEZA EL JUEGO !!!
                int vidas = 10;
                Scanner lector = new Scanner(System.in);

                do {
                    System.out.println("EMPIEZA EL JUEGO!!");
                    System.out.println("Te mueves en WASD");
                    System.out.println("Dime una letra de movimiento !!");
                    char mov = lector.next().charAt(0);
                    int filaAnteriorDM = filaOficialDm;
                    int colAnteriorDM = columnaOficialDm;
                    switch (mov){ // W, A, S o D
                        case 'W':
                            filaOficialDm = filaOficialDm - 1;
                            break;
                        case 'A':
                            columnaOficialDm = columnaOficialDm - 1;
                            break;
                        case 'S':
                            filaOficialDm = filaOficialDm + 1;
                            break;
                        case 'D':
                            columnaOficialDm = columnaOficialDm + 1;
                            break;
                    }

                    // Y SI DM SE SALE DEL TABLERO ??????????????????
                    if(
                            filaOficialDm < 0 ||
                                    filaOficialDm > 9 ||
                                    columnaOficialDm < 0 ||
                                    columnaOficialDm > 9
                    ){
                        System.out.println("EHHHHHHHH!!!!!!!!!!!!");
                        System.out.println("LÍMITE DE TABLERO!!!!!!");
                        filaOficialDm = filaAnteriorDM;
                        columnaOficialDm = colAnteriorDM;
                    }else{
                        if(tableroDM[filaOficialDm][columnaOficialDm] == MURO){
                            System.out.println("NO PUEDES PASAR!!!!!");
                            filaOficialDm = filaAnteriorDM;
                            columnaOficialDm = colAnteriorDM;
                        }else if(tableroDM[filaOficialDm][columnaOficialDm] == POCIMA){
                            tableroDM[filaOficialDm][columnaOficialDm] = DM;
                            tableroDM[filaAnteriorDM][colAnteriorDM] = LIBRE;
                            vidas++;
                        }else if(tableroDM[filaOficialDm][columnaOficialDm] == VADER){
                            tableroDM[filaOficialDm][columnaOficialDm] = DM;
                            tableroDM[filaAnteriorDM][colAnteriorDM] = LIBRE;
                            vidas--;
                        }else if(tableroDM[filaOficialDm][columnaOficialDm] == LIBRE){
                            tableroDM[filaOficialDm][columnaOficialDm] = DM;
                            tableroDM[filaAnteriorDM][colAnteriorDM] = LIBRE;
                        }
                    }

                    for (int i = 0; i < MAX_FIL_DM; i++) {
                        for (int j = 0; j < MAX_COL_DM; j++) {
                            System.out.print(tableroDM[i][j] + " ");
                        }
                        System.out.println(" ");
                    }

                }while(vidas>0);

                yodaActive = true;
                dmActive = false;
            }
        }


    }
}