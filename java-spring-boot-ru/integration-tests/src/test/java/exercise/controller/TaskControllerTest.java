package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    // BEGIN
    private Task generateTask() {
        return Instancio.of(Task.class)
                        .ignore(Select.field(Task::getId))
                        .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                        .supply(Select.field(Task::getDescription), () -> faker.lorem().sentence())
                        .create();
    }

    @Test
    public void testShow() throws Exception {
        var task = generateTask();
        taskRepository.save(task);

        var request = get("/tasks/{id}", task.getId());
        var result = mockMvc.perform(request)
                            .andExpect(status().isOk())
                            .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).and(
            json -> json.node("id").isEqualTo(task.getId()),
            json -> json.node("title").isEqualTo(task.getTitle()),
            json -> json.node("description").isEqualTo(task.getDescription())
        );
    }

    @Test
    public void testCreate() throws Exception {
        var task = generateTask();
        var request = post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(task));

        mockMvc.perform(request)
               .andExpect(status().isCreated());

        var createdTask = taskRepository.findByTitle(task.getTitle()).get();
        assertThat(createdTask).isNotNull();
        assertThat(createdTask.getDescription()).isEqualTo(task.getDescription());
    }

    @Test
    public void testUpdate() throws Exception {
        var task = generateTask();
        taskRepository.save(task);

        var newData = new HashMap<>();
        newData.put("title", faker.lorem().word());
        newData.put("description", faker.lorem().sentence());

        var request = put("/tasks/{id}", task.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(newData));

        mockMvc.perform(request)
               .andExpect(status().isOk());

        var updatedTask = taskRepository.findById(task.getId()).get();
        assertThat(updatedTask.getTitle()).isEqualTo(newData.get("title"));
        assertThat(updatedTask.getDescription()).isEqualTo(newData.get("description"));
    }

    @Test
    public void testDelete() throws Exception {
        var task = generateTask();
        taskRepository.save(task);

        var request = delete("/tasks/{id}", task.getId());
        mockMvc.perform(request)
               .andExpect(status().isOk());

        assertThat(taskRepository.existsById(task.getId())).isFalse();
    }
    // END
}
