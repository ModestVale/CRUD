package ru.netology.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IssueManagerTest {
    @Mock
    private IssueRepository repository;

    @InjectMocks
    private IssueManager issueManager;

    private Issue issue1 = new Issue(1, "open", "Ваня", "Таня", "В работу", "На согласование");
    private Issue issue2 = new Issue(2, "open", "Маня", "Таня", "В работу", "На согласование");
    private Issue issue3 = new Issue(3, "open", "Таня", "Маня", "В работу", "На согласование");
    private Issue issue4 = new Issue(4, "close", "Саша", "Таня", "На согласование");
    private Issue issue5 = new Issue(5, "close", "Саша", "Таня");
    private Issue issue6 = new Issue(6, "close", "Ваня", "Таня", "В работу", "Уточнить");
    private Issue issue7 = new Issue(7, "open", "Ваня", "Таня", "В работу");

    private void createRepositoryMock() {
        List<Issue> issues = new ArrayList<>();
        issues.add(issue1);
        issues.add(issue2);
        issues.add(issue3);
        issues.add(issue4);
        issues.add(issue5);
        issues.add(issue6);
        issues.add(issue7);

        doReturn(issues).when(repository).getAll();
    }

    @Test
    public void shouldCloseIssueSetStatus() {
        doReturn(issue7).when(repository).getById(7);

        issueManager.closeIssue(7);
        String actual = issueManager.getIssueById(7).getStatus();
        assertEquals("close", actual);
    }

    @Test
    public void shouldOpenIssueSetStatus() {
        doReturn(issue6).when(repository).getById(6);

        issueManager.openIssue(6);
        String actual = issueManager.getIssueById(6).getStatus();
        assertEquals("open", actual);
    }

    @Test
    public void shouldAddIssue() {
        issueManager.addIssue(issue1);
        verify(repository, times(1)).add(issue1);
    }

    @Test
    public void shouldGetOpenIssues() {
        createRepositoryMock();
        List<Issue> expected = new ArrayList<>();
        expected.add(issue1);
        expected.add(issue2);
        expected.add(issue3);
        expected.add(issue7);

        List<Issue> actual = issueManager.getOpenIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetCloseIssues() {
        createRepositoryMock();
        List<Issue> expected = new ArrayList<>();
        expected.add(issue4);
        expected.add(issue5);
        expected.add(issue6);

        List<Issue> actual = issueManager.getCloseIssues();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetIssuesByAssigned() {
        createRepositoryMock();
        List<Issue> expected = new ArrayList<>();
        expected.add(issue1);
        expected.add(issue2);
        expected.add(issue4);
        expected.add(issue5);
        expected.add(issue6);
        expected.add(issue7);
        List<Issue> actual = issueManager.getIssuesByAssignee("Таня");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetIssuesBydAuthor() {
        createRepositoryMock();
        List<Issue> expected = new ArrayList<>();
        expected.add(issue4);
        expected.add(issue5);
        List<Issue> actual = issueManager.getIssuesByAuthor("Саша");
        assertEquals(expected, actual);
    }


    @Test
    public void shouldGetIssuesByLabel() {
        createRepositoryMock();
        List<Issue> expected = new ArrayList<>();
        expected.add(issue1);
        expected.add(issue2);
        expected.add(issue3);
        expected.add(issue6);
        expected.add(issue7);
        List<Issue> actual = issueManager.getIssuesByLabel("В работу");
        assertEquals(expected, actual);
    }

}
