package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.List;
import java.util.stream.Collectors;

public class IssueManager {
    private IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void addIssue(Issue issue) {
        repository.add(issue);
    }

    public List<Issue> getOpenIssues() {
        return repository
                .getAll()
                .stream()
                .filter(s -> s.getStatus() == "open")
                .collect(Collectors.toList());
    }

    public List<Issue> getCloseIssues() {
        return repository
                .getAll()
                .stream()
                .filter(s -> s.getStatus() == "close")
                .collect(Collectors.toList());
    }

    public List<Issue> getIssuesByAuthor(String author) {
        return repository
                .getAll()
                .stream()
                .filter(s -> s.getAuthor() == author)
                .collect(Collectors.toList());
    }

    public List<Issue> getIssuesByLabel(String label) {
        return repository
                .getAll()
                .stream()
                .filter(s -> s.getLabels().contains(label))
                .collect(Collectors.toList());
    }

    public List<Issue> getIssuesByAssignee(String assignee) {
        return repository
                .getAll()
                .stream()
                .filter(s -> s.getAssignee() == assignee)
                .collect(Collectors.toList());

    }

    public Issue getIssueById(int id) {
        return repository.getById(id);
    }

    public void openIssue(int id) {
        repository.getById(id).setStatus("open");
    }

    public void closeIssue(int id) {
        repository.getById(id).setStatus("close");
    }
}
