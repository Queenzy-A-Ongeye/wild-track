package models;

import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DataBase extends ExternalResource {
    @Override
    protected void before(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wild_track_test", "moringa", "avamara");
    }
    @Override
    protected void after(){
        try (Connection con = DB.sql2o.open()){
            String deleteAnimalQuery = "DELETE FROM animals *;";
            String deleteEndangeredQuery = "DELETE FROM endangered";
            con.createQuery(deleteAnimalQuery).executeUpdate();
            con.createQuery(deleteEndangeredQuery).executeUpdate();
        }
    }
}
