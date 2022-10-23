import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

// Input:  Web Page with table contains a task name, task creation date. Table has sorting on creation date
// Task: Check if current test is implemented correctly and what will be the result of current run?
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Test2 {
    private final ElementsCollection taskListCollection = $$(By.xpath("//tr[@data xpath='table item']"));
    private final SelenideElement sortAscLink = $(By.xpath("//th[@data xpath='tableHeadCell sort']"));
    private final List<Task> listTasks = new ArrayList<>();
    private final List<Task> allTasksList = new ArrayList<>();
    private TestPage testPage;
    private List<Task> expectedTasksListInDescendantOrder;
    private List<Task> expectedTasksListInAscendantOrder;
    private boolean ascSortedFlag;


    @BeforeAll
    public void beforeAll() {
        taskListCollection.forEach(task ->
                allTasksList.add(new Task(
                        task.$x("./*").getAttribute("name"),
                        new Date(task.$x("./*").getAttribute("endTime")))));

        expectedTasksListInAscendantOrder = new ArrayList<>(allTasksList);
        expectedTasksListInAscendantOrder.sort((o1, o2) -> o1.getEndTime().compareTo(o2.getEndTime()));

        expectedTasksListInDescendantOrder = new ArrayList<>(allTasksList);
        expectedTasksListInDescendantOrder.sort((o1, o2) -> o2.getEndTime().compareTo(o1.getEndTime()));
    }

    @Test
    public void shouldSortTasksList_whenClickedSortLink_Test() {

        List<Task> actualAsc = testPage.sortAscending();
        List<Task> actualDesc = testPage.sortDescending();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions
                .assertThat(actualDesc).isEqualTo(expectedTasksListInDescendantOrder);
        softAssertions
                .assertThat(actualAsc).isEqualTo(expectedTasksListInAscendantOrder);
    }

    private class Task {
        private final String name;
        private final Date endTime;


        public Task(String name, Date endTime) {
            this.name = name;
            this.endTime = endTime;
        }

        public String getName() {
            return name;
        }

        public Date getEndTime() {
            return endTime;
        }

    }

    private class TestPage {

        public List<Task> sortAscending() {
            List<Task> allTasksList = new ArrayList<>();

            clickForAscendantSort();

            taskListCollection.forEach(task ->
                    allTasksList.add(new Task(
                            task.$x("./*").getAttribute("name"),
                            new Date(task.$x("./*").getAttribute("endTime")))));
            ascSortedFlag = true;
            return allTasksList;
        }

        public List<Task> sortDescending() {
            List<Task> allTasksList = new ArrayList<>();
            clickForDescendantSort();

            taskListCollection.forEach(task ->
                    allTasksList.add(new Task(
                            task.$x("./*").getAttribute("name"),
                            new Date(task.$x("./*").getAttribute("endTime")))));

            return allTasksList;
        }

        private void clickForDescendantSort() {
            if (ascSortedFlag) {
                clickForAscendantSort();
            } else {
                clickForAscendantSort();
                clickForAscendantSort();
                ascSortedFlag = false;
            }
        }

        private void clickForAscendantSort() {
            sortAscLink.click();
        }
    }
}
