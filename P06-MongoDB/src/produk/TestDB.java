/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produk;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.UpdateResult;
import java.util.Date;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Febrian Yudho
 */
public class TestDB {
    public static void main(String[] args) {
        try {
            MongoDatabase database = Koneksi.sambungDB();
            
            System.out.println("=====================================");
            System.out.println("Daftar Tabel DAtabase");
            MongoIterable<String> tables = database.listCollectionNames();
            for (String name : tables) {
                System.out.println(name);
                
            }
            System.out.println("=====================================");
            System.out.println("menambhakan data");
            MongoCollection<Document> col = database.getCollection("produk");
            Document doc = new Document();
            doc.put("nama", "Printer INKjet");
            doc.put("harga", 12024000);
            doc.put("tanggal", new Date());
            col.insertOne(doc);
             System.out.println("Data telah disimpan dalam koleksi");
             
             ObjectId id = new ObjectId(doc.get("_id").toString());
             
             System.out.println("============================");
             System.out.println("data dalam koleksi produk:");
             MongoCursor<Document> cursor = col.find().iterator();
             while (cursor.hasNext()) {
                 System.out.println(cursor.next().toJson());
                
            }
             Document myDoc = col.find(eq("_id", id)).first();
             System.out.println("==============================");
             System.out.println("pencarian data berdasarkan id :"+id);
             System.out.println(myDoc.toJson());
             
             Document docs = new Document();
             docs.put("nama", "canon");
             Document doc_edit = new Document("$set",docs);
             UpdateResult hasil_edit = col.updateOne(eq("_id",id),doc_edit);
             System.out.println(hasil_edit.getModifiedCount());
            
             System.out.println("==========================");
             System.out.println("data dalam koleksi produk");
             cursor = col.find().iterator();
             while (cursor.hasNext()) {
                 System.out.println(cursor.next().toJson());
                
            }
            
            col.deleteOne(eq("_id",id));
            
            System.out.println("=====================");
            System.out.println("data dalam kolesi prosuk");
            cursor = col.find().iterator();
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
                
            }
        } catch (Exception e) {
            System.err.println();
        }
    }
}
