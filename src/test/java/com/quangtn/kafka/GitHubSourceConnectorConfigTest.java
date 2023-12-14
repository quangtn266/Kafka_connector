package com.quangtn.kafka;

import org.apache.kafka.common.config.ConfigDef;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.quangtn.kafka.GitHubSourceConnectorConfig.*;
import static org.junit.Assert.*;

public class GitHubSourceConnectorConfigTest {

    private ConfigDef configDef = GitHubSourceConnectorConfig.conf();
    private Map<String, String> config;

    @Before
    public  void setUpInitialConfig() {
        config = new HashMap<>();
        config.put(OWNER_CONFIG, "foo");
        config.put(REPO_CONFIG, "bar");
        config.put(SINCE_CONFIG, "2017-04-26T01:23:45Z");
        config.put(BATCH_SIZE_CONFIG, "100");
        config.put(TOPIC_CONFIG, "github-issues");
    }

    @Test
    public void doc() {
        System.out.println(GitHubSourceConnectorConfig.conf().toRst());
    }

    @Test
    public void initialConfigIsValid() {
        assertTrue(configDef.validate(config)
                .stream()
                .allMatch(configValue -> configValue.errorMessages().size() == 0));
    }

    @Test
    public void canReadConfigCorrectly() {
        GitHubSourceConnectorConfig config = new GitHubSourceConnectorConfig(this.config);
        config.getAuthPassword();
    }
}