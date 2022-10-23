import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Input:  Web Page with table contains a task name, task creation date. Table has sorting on creation date
// Task: Check if current test is implemented correctly and what will be the result of current run?
public class Test2 {
    private ElementsCollection taskListCollection = $$(By.xpath("//tr[@data xpath='table item']"));
    private SelenideElement sortAscLink = $(By.xpath("//th[@data xpath='tableHeadCell sort']"));
    private List<Task> listTasks = new ArrayList<>();
    private TestPage testPage;

    @Test
    public void testForms() {
        List<Task> sortDesc = new ArrayList<>();
        List<Task> allTasksList = new LinkedList<>();

        taskListCollection.forEach(task -> {
            allTasksList.add(new Task(
                    task.$x("./*").getAttribute("name"),
                    task.$x("./*").getAttribute("endTime")));

            List<Task> list1 = allTasksList;
            List<Task> list2 = allTasksList;

            list1.sort((o1, o2) -> o1.getEndTime().compareTo(o2.getEndTime()));
            list1.sort((o1, o2) -> o2.getEndTime().compareTo(o1.getEndTime()));

            List<Task> actualAsc = testPage.sortAscending(allTasksList);
            List<Task> actualDesc = testPage.sortDescending(allTasksList);

            assertEquals(actualDesc, sortDesc);
        });
    }

    private class Task {
        private String name;
        private String endTime;

        public Task(String name, String endTime) {
            this.name = name;
            this.endTime = endTime;
        }

        public String getName() {
            return name;
        }

        public String getEndTime() {
            return endTime;
        }
    }

    private class TestPage {
        public List<Task> sortAscending(List<Task> allTasksList) {
            return new ArrayList<Task>();
        }

        public List<Task> sortDescending(List<Task> allTasksList) {
            return new ArrayList<Task>();
        }
    }
}
