package org.example.alphasolutions.repositories;

import org.example.alphasolutions.models.Priority;
import org.example.alphasolutions.models.Status;
import org.example.alphasolutions.models.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:h2init.sql"}
)
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    void addTask_Test() {
        // Arrange
        Task task = new Task(
                0, // task_id - auto generated
                1, // subproject_id
                "JUnit Test Task",  // match navnet i assert
                "Testing insert",
                Priority.HIGH,
                10,
                2,
                Status.NOT_STARTED
        );

        // Act
        taskRepository.addTask(task);
        List<Task> tasks = taskRepository.getTasksBySubId(1); // Hent alle tasks med subproject_id = 1

        // Assert
        boolean found = tasks.stream().anyMatch(t ->
                t.getTaskName().equals("JUnit Test Task") &&
                        t.getTaskDescription().equals("Testing insert") &&
                        t.getPriority() == Priority.HIGH &&
                        t.getEstimatedHours() == 10 &&
                        t.getActualUsedHours() == 2 &&
                        t.getStatus() == Status.NOT_STARTED
        );

        assertTrue(found, "Den indsatte task blev ikke fundet i databasen.");
    }


    @Test
    void deleteTask_Test() {
        // Arrange: Insert new task
        Task task = new Task(0, 1, "Delete Me", "Delete test", Priority.LOW, 5, 0, Status.NOT_STARTED);
        taskRepository.addTask(task);

        // Finds the inserted task
        List<Task> tasks = taskRepository.getTasksBySubId(1);
        Task insertedTask = null;
        for (Task t : tasks) {
            if (t.getTaskName().equals("Delete Me")) {
                insertedTask = t;
                break;
            }
        }

        assertNotNull(insertedTask, "New task did not get inserted correct.");

        int taskId = insertedTask.getTaskId(); // use the ID

        // Act: Deletes task
        taskRepository.deleteTask(taskId);

        // Assert:
        Task deletedTask = taskRepository.getTaskByTaskId(taskId);
        assertNull(deletedTask, "Task was not ");
    }

    @Test
    void editTask_Test() {
        // Arrange: add task
        Task originalTask = new Task(0, 1, "Original Task", "Original Description", Priority.LOW, 5, 1, Status.NOT_STARTED);
        taskRepository.addTask(originalTask);

        // Finds the inserted task
        List<Task> tasks = taskRepository.getTasksBySubId(1);
        Task insertedTask = null;
        for (Task t : tasks) {
            if (t.getTaskName().equals("Original Task")) {
                insertedTask = t;
                break;
            }
        }

        assertNotNull(insertedTask, "the original task was not inserted correct");
        int taskId = insertedTask.getTaskId();

        // Act: edit the task
        Task updatedTask = new Task(taskId, 1, "Updated Task", "Updated Description", Priority.HIGH, 10, 3, Status.IN_PROGRESS);
        taskRepository.editTask(taskId, updatedTask);

        // Assert: gets task an checks values
        Task fetchedTask = taskRepository.getTaskByTaskId(taskId);
        assertNotNull(fetchedTask, "the edited task was not found");

        assertEquals("Updated Task", fetchedTask.getTaskName());
        assertEquals("Updated Description", fetchedTask.getTaskDescription());
        assertEquals(Priority.HIGH, fetchedTask.getPriority());
        assertEquals(10, fetchedTask.getEstimatedHours());
        assertEquals(3, fetchedTask.getActualUsedHours());
        assertEquals(Status.IN_PROGRESS, fetchedTask.getStatus());
    }


    @Test
    void getTasksBySubId_returnsCorrectTasks() {
        // Arrange: Insert two tasks into subproject_id = 1
        Task task1 = new Task(0, 1, "Task A", "Description A", Priority.MEDIUM, 8, 2, Status.NOT_STARTED);
        Task task2 = new Task(0, 1, "Task B", "Description B", Priority.HIGH, 10, 4, Status.IN_PROGRESS);
        taskRepository.addTask(task1);
        taskRepository.addTask(task2);

        // Act: Retrieve tasks for subproject_id = 1
        List<Task> tasks = taskRepository.getTasksBySubId(1);

        // Assert: Check that both tasks are in the result list
        boolean foundTaskA = false;
        boolean foundTaskB = false;

        for (Task t : tasks) {
            if (t.getTaskName().equals("Task A") && t.getTaskDescription().equals("Description A")) {
                foundTaskA = true;
            }
            if (t.getTaskName().equals("Task B") && t.getTaskDescription().equals("Description B")) {
                foundTaskB = true;
            }
        }

        assertTrue(foundTaskA, "Task A was not found in the result.");
        assertTrue(foundTaskB, "Task B was not found in the result.");
    }


    @Test
    void getTasksBySubId_returnsAllTasks() {
        List<Task> tasks = taskRepository.getTasksBySubId(1);
        assertFalse(tasks.isEmpty(), "Expected to find tasks for subproject_id 1");

        // Optional: verify at least one known task from your data
        boolean foundAlpha = false;
        for (Task t : tasks) {
            if ("Task Alpha".equals(t.getTaskName())) {
                foundAlpha = true;
                break;
            }
        }
        assertTrue(foundAlpha, "Task Alpha not found for subproject 1");
    }

    @Test
    void getTaskByTaskId_Test() {
        // From your data, Task Alpha has task_id = 1 (assuming auto-increment starts there)
        Task task = taskRepository.getTaskByTaskId(1);
        assertNotNull(task, "Task with ID 1 not found");
        assertEquals("Task Alpha", task.getTaskName());
    }
}