package mysqlconnection;

import myslqconnection.model.Cinema;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {

    public static Cinema cinema = new Cinema();
    public static String driver = "com.mysql.cj.jdbc.Driver";
    public static String url = "jdbc:mysql://localhost:3306/cinema?&serverTimezone=UTC";
    public static String user = "root";
    public static String password = "root";

    public static void main(String[] args) {
        starter() ;
    }

    private static void starter() {
        int num = cinema.start();

        switch(num){
            case 1:
                peliculasEstrenadas();
                break;
            case 2:
                peliculas();
                break;
            case 3:
                insertarPelicula();
                break;
        }
    }

    private static void insertarPelicula() {
        cinema.pelisInsert();

        String query = "Insert into films(titol, dataEstrena, pais) " +
                "values('"+ cinema.getTitol() + "', '" + cinema.getData() +"', '" + cinema.getPais() + "' );";

        try {
            Class.forName(driver);
            try(Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()){
                statement.executeUpdate(query);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void peliculas() {
        String director = cinema.directorName();

        String query =  "select films.titol, films.dataEstrena from films " +
                "join film_peli ON films.idFilm = film_peli.idFilm " +
                "join director ON film_peli.idDirector = director.idDirector " +
                "where director.nom like '" + director + "';";

        try {
            Class.forName(driver);
            try(Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)){
                int intNames = getColumnNames(resultSet);
                while (resultSet.next()){
                    for (int x = 1; x <= intNames;x++){
                        System.out.println(resultSet.getString(x));
                        if(x < intNames){
                            System.out.print(", ");
                        }
                    }
                    System.out.println();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private static void peliculasEstrenadas() {
        String ano1 = cinema.ano1();
        String ano2 = cinema.ano2();
        String query = "select * from films where dataEstrena between '" + ano1 + "' and '" + ano2 + "'";

        try {
            Class.forName(driver);
            try(Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)){
                int intNames = getColumnNames(resultSet);
                while (resultSet.next()){
                    for (int x = 1; x <= intNames;x++){
                        System.out.println(resultSet.getString(x));
                        if(x < intNames){
                            System.out.print(", ");
                        }
                    }
                    System.out.println();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    public static int getColumnNames(ResultSet rs) throws SQLException {
        int numberOfColumns = 0;
        if (rs != null) {
            //create an object based on the Metadata of the result set
            ResultSetMetaData rsMetaData = rs.getMetaData();
            //Use the getColumn method to get the number of columns returned
            numberOfColumns = rsMetaData.getColumnCount();
            //get and print the column names, column indexes start from 1
            for (int i = 1; i < numberOfColumns + 1; i++) {
                String columnName = rsMetaData.getColumnName(i);
                System.out.print(columnName + ", ");
            }//endfor
        }//endif
        //place the cursor on a new line in the console
        System.out.println();
        return numberOfColumns;
    }//end method getColumnNames

}
