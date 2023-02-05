package myslqconnection.model;

import java.util.Scanner;

public class Cinema {
    Scanner sc = new Scanner(System.in);
    int num;
    String titol, data, pais;

    public int start(){
        System.out.println("Que quieres hacer");
        System.out.println("[1] Ver las peliculas estrenadas por año");
        System.out.println("[2] Ver las peliculas de directores ");
        System.out.println("[3] Insertar peliculas");
        num = sc.nextInt();
        return num;
    }

    public String ano1(){
        System.out.println("Introduzca el primer año para el intervalo");
        sc.nextLine();
        return sc.nextLine();
    }

    public String ano2(){
        System.out.println("Introduzca el segundo año para el intervalo");
        return sc.nextLine();
    }

    public String directorName(){
        System.out.println("Introduzca el nombre de un Director");
        sc.nextLine();
        return sc.nextLine();
    }

    public void pelisInsert(){
        System.out.println("Introduzca el nombre de la pelicula");
        sc.nextLine();
        titol = sc.nextLine();
        System.out.println("Introduzca la fecha de estreno");
        data = sc.nextLine();
        System.out.println("Introduzca el nombre de Pais de la pelicula");
        pais = sc.nextLine();
    }


    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
