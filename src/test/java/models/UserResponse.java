package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                // Автоматически создаст все геттеры, сеттеры, toString и equals
@AllArgsConstructor // Создаст конструктор со всеми полями
@NoArgsConstructor
public class UserResponse {
    private String name;
    private String job;
    private String id;
    private String updatedAt;
    private String createdAt;

//    public String getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(String updatedAt) {
//        this.updatedAt = updatedAt;
//    }


//    @SerializedName("created_at") // Так поле называется в JSON
//    private String createdAt;       // Так оно будет называться в вашем Java-коде


//  public UserResponse() {}
//
//      public String getName () {
//          return name;
//      }
//
//      public void setName (String name){
//          this.name = name;
//      }
//
//      public String getJob () {
//          return job;
//      }
//
//      public void setJob (String job){
//          this.job = job;
//      }
//
//      public String getId () {
//          return id;
//      }
//
//      public void setId (String id){
//          this.id = id;
//      }
//
//      public String getCreatedAt () {
//          return createdAt;
//      }
//
//      public void setCreatedAt (String createdAt){
//          this.createdAt = createdAt;
//      }
}
