package com.christopherpick.demoparser;

import com.christopherpick.demoparser.models.Category;
import com.christopherpick.demoparser.models.Task;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by chrispix on 5/27/16.
 */

public class ModelTests {

    private static final String CATEGORY_NAME = "Something";
    private static final String CATEGORY_ICON = "http://something.com/url/image/";
    private static final String TASK_NAME = "My Task";
    private static final String TASK_ICON = "Some Icon";

    private static final int MAX_RUNS = 10;

    /**
     * Test that categories can be updated, and they don't do anything
     * silly as a plain POJO.
     * @throws Exception
     */
    @Test
    public void testCategory() throws Exception {
        Category category = new Category();

        for (int i = 0; i < MAX_RUNS; i++) {
            category.setCategoryIcon(CATEGORY_ICON + i);
            category.setCategoryName(CATEGORY_NAME + i);
            category.setTasks(null);

            assertEquals(CATEGORY_ICON+i, category.getCategoryIcon());
            assertEquals(CATEGORY_NAME+i, category.getCategoryName());
            assertNull(category.getTasks());
        }
    }

    /**
     * Test the Task Pojo, make sure they don't do anything silly, like
     * modifying values after they have been set.
     * @throws Exception
     */
    @Test
    public void testTask() throws Exception {
        final double DELTA = 1e-15;
        Task task = new Task();
        for (int i = 0; i < MAX_RUNS; i++) {
            populateTask(task, i);

            assertEquals(CATEGORY_ICON+i, task.getIcon());
            assertEquals(CATEGORY_NAME+i, task.getTaskName());
            assertEquals((double)i, task.getEarning(), DELTA);
            assertEquals((i % 2) == 0, task.getEnabled());
        }
    }

    // TODO: Write some tests that add tasks to the category objects and validate them.


    public static void populateTask(Task task, int value) {
        task.setIcon(CATEGORY_ICON + value);
        task.setTaskName(CATEGORY_NAME + value);
        task.setEarning((double) value);
        task.setEnabled(((value % 2) == 0));
    }
}
