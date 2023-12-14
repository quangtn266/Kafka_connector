package com.quangtn.kafka;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.quangtn.kafka.GitHubSourceConnectorConfig.*;
import static org.junit.Assert.*;

public class GitHubSourceConnectorTest{

    private Map<String, String> initialCofig() {
        Map<String, String> baseProps = new HashMap<>();
        baseProps.put(OWNER_CONFIG, "foo");
        baseProps.put(REPO_CONFIG, "bar");
        baseProps.put(SINCE_CONFIG, "2017-04-26T01:23:45Z");
        baseProps.put(BATCH_SIZE_CONFIG, "100");
        baseProps.put(TOPIC_CONFIG, "github-issues");
        return (baseProps);
    }

    @Test
    public void taskConfigShouldReturnOneTaskConfig() {
        GitHubSourceConnector gitHubSourceConnector = new GitHubSourceConnector();
        gitHubSourceConnector.start(initialCofig());
        assertEquals(gitHubSourceConnector.taskConfigs(1).size(), 1);
        assertEquals(gitHubSourceConnector.taskConfigs(10).size(), 1);
    }
}
