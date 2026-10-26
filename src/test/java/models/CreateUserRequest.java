package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                // Автоматически создаст все геттеры, сеттеры, toString и equals
@AllArgsConstructor // Создаст конструктор со всеми полями
@NoArgsConstructor  // Создаст пустой конструктор (нужен для Gson)
public class CreateUserRequest {

    private String name;
    private String job;
//   если не добавлять ломбок -
//   public CreateUserRequest(String name, String job){
//        this.job = job;
//        this.name= name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getJob() {
//        return job;
//    }
//
//    public void setJob(String job) {
//        this.job = job;
//    }
}
