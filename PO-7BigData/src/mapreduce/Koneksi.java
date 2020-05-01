/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapreduce;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Febrian Yudho
 */
public class Koneksi {

    public static MongoDatabase sambungDB() {
        try {
            MongoClient client = MongoClients.create();
            MongoDatabase database = client.getDatabase("bigdata");
            return database;

        } catch (Exception e) {
            System.out.println("koneksi gagal :" + e.getMessage());
        }
        return null;
    }

}
