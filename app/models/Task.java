package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Task extends Model {

  @Id
  public Long id;

  @Required
  public String label;

  public Task(String label) {
    this.label = label;
  }

  public static Finder<Long,Task> find = new Finder(
    Long.class, Task.class
  );

  public static List<Task> all() {
    return find.all();
  }

  public static void create(Task task) {
    task.save();
  }

  public static void deleteTask(Long id) {
    find.ref(id).delete();
  }

}
