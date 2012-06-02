package test.functional;

import org.junit.*;

import java.util.*;

import play.*;
import play.mvc.*;
import play.test.*;
import play.libs.F.*;

import models.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

public class FunctionalTest {

  @Test
  public void indexTest() {
    Result result = callAction(controllers.routes.ref.Application.index());
    assertThat(status(result)).isEqualTo(SEE_OTHER);
    assertThat(redirectLocation(result)).isEqualTo("/tasks");
  }

  @Test
  public void tasksTest() {
    running(fakeApplication(), new Runnable() {
      public void run() {
        Result result = callAction(controllers.routes.ref.Application.tasks());
        assertThat(status(result)).isEqualTo(OK);
      }
    });
  }

  @Test
  public void newTaskTest() {
    running(fakeApplication(), new Runnable() {
      public void run() {
        Result result = callAction(controllers.routes.ref.Application.newTask());

        assertThat(status(result)).isEqualTo(BAD_REQUEST);

        HashMap<String, String> data = new HashMap<String, String>();
        data.put("label", "foo");

        int tasksCount = Task.all().size();

        result = callAction(
          controllers.routes.ref.Application.newTask(),
          fakeRequest().withFormUrlEncodedBody(data)
        );

        assertThat(status(result)).isEqualTo(SEE_OTHER);
        assertThat(redirectLocation(result)).isEqualTo("/tasks");
        assertThat(Task.all().size()).isEqualTo(tasksCount + 1);
      }
    });
  }

  @Test
  public void deleteTaskTest() {
    running(fakeApplication(), new Runnable() {
      public void run() {
        Task task = new Task("foo");
        Task.create(task);
        int tasksCount = Task.all().size();
        Result result = callAction(controllers.routes.ref.Application.deleteTask(task.id));
        assertThat(Task.all().size()).isEqualTo(tasksCount - 1);
      }
    });
  }

}
