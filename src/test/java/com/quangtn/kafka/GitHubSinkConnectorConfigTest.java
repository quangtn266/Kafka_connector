package com.quangtn.kafka;

import org.junit.Test;

public class GitHubSinkConnectorConfigTest {
  @Test
  public void doc() {
    System.out.println(GitHubSinkConnectorConfig.conf().toRst());
  }
}
