package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IssueRepositoryTest {
    private IssueRepository repository;

    @BeforeEach
    public void Prepare() {
        repository = new IssueRepository();
    }

    @Nested
    public class Empty {
        @Test
        public void shouldGetIssues() {
            Issue actual = repository.getById(1);
            assertNull(actual);
        }

        @Test
        public void shouldGetAllIssues() {
            List<Issue> actual = repository.getAll();
            assertTrue(actual.isEmpty());
        }

        @Test
        public void shouldGetAddAndDeleteIssue() {
            Issue issue = new Issue(1, "open", "Женя", "Саша");
            repository.add(issue);
            assertEquals(1, repository.getAll().size());
            repository.remove(issue);
            assertTrue(repository.getAll().isEmpty());
        }
    }

    @Nested
    public class SingleItem {
        Issue issue = new Issue(1, "open", "author", "assignee");

        @BeforeEach
        public void Prepare() {
            repository.add(issue);
        }

        @Test
        public void shouldGetIssues() {
            Issue expected = issue;
            Issue actual = repository.getById(issue.getId());
            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetAllIssues() {
            List<Issue> expected = new ArrayList<>();
            expected.add(issue);
            List<Issue> actual = repository.getAll();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetDeleteIssue() {
            repository.remove(issue);
            assertTrue(repository.getAll().isEmpty());
        }
    }

    @Nested
    public class MultipleItems {
        Issue issue1 = new Issue(1, "open", "author", "assignee");
        Issue issue2 = new Issue(2, "open", "author", "assignee");
        Issue issue3 = new Issue(3, "open", "author", "assignee");

        @BeforeEach
        public void Prepare() {
            repository.add(issue1);
            repository.add(issue2);
            repository.add(issue3);
        }

        @Test
        public void shouldGetIssues() {
            Issue expected = issue3;
            Issue actual = repository.getById(issue3.getId());
            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetAllIssues() {
            List<Issue> expected = new ArrayList<>();
            expected.add(issue1);
            expected.add(issue2);
            expected.add(issue3);
            List<Issue> actual = repository.getAll();
            assertEquals(expected, actual);
        }

        @Test
        public void shouldGetDeleteIssue() {
            List<Issue> expected = new ArrayList<>();
            expected.add(issue2);
            expected.add(issue3);

            repository.remove(issue1);

            List<Issue> actual = repository.getAll();

            assertEquals(expected, actual);
        }
    }
}
