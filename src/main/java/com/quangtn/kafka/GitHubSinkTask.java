package com.quangtn.kafka;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import static com.quangtn.kafka.GitHubSchemas.*;


public class GitHubSinkTask extends SinkTask {
  private static final Logger log = LoggerFactory.getLogger(GitHubSinkTask.class);
  public GitHubSourceConnectorConfig config;

  protected Instant nextQuerySince;
  protected Integer lastIssueNumber;
  protected Integer nextPageToVisit = 1;
  protected Instant lastUpdatedAt;
  GitHubAPIHttpClient gitHubHttpAPIClient;

  @Override
  public String version() {
    return VersionUtil.getVersion();
  }

  @Override
  public void start(Map<String, String> map) {
    //TODO: Create resources like database or api connections here.
    config = new GitHubSourceConnectorConfig(map);
    initializeLastVariables();
    gitHubHttpAPIClient = new GitHubAPIHttpClient(config);
  }

 Map<String, Object> lastSourceOffset;

  private void initializeLastVariables() {
    String jsonOffsetData = new Gson().toJson(lastSourceOffset);

    // TypeToken preserves the generic type information of the Map when deserializing the JSON string.
    TypeToken<Map<String, Object>> typeToken = new TypeToken<Map<String, Object>>() {} ;
    Map<String, Object> offsetData = new Gson().fromJson(jsonOffsetData, typeToken.getType());

    if (offsetData == null) {
      // we haven't fetched anything yet, so we initialize to 7 days ago.
      nextQuerySince = config.getSince();
      lastIssueNumber = -1;
    } else {
      Object updatedAt = offsetData.get(UPDATED_AT_FIELD);
      Object issueNumber = offsetData.get(NUMBER_FIELD);
      Object nextPage = offsetData.get(NEXT_PAGE_FIELD);
      if(updatedAt != null && (updatedAt instanceof  String)) {
        nextQuerySince = Instant.parse((String) updatedAt);
      }
      if(issueNumber != null && (issueNumber instanceof String)) {
        lastIssueNumber = Integer.valueOf((String) issueNumber);
      }
      if(nextPage != null && (nextPage instanceof  String)) {
        nextPageToVisit = Integer.valueOf((String) nextPage);
      }
    }
  }
/*
  @Override
  public List<SinkRecord> poll() throws InterruptedException {
    gitHubAPIHttpClient.sleepIfNeed();

    // fetch data
    final ArrayList<SinkRecord> records = new ArrayList<>();
    JSONArray issues = gitHubAPIHttpClient.getNextIssues(nextPageToVisit, nextQuerySince);
    // we'll count how many results we get with i
    int i = 0;
    for (Object obj : issues) {
      Issue issue = Issue.fromJson((JSONObject) obj);
      SinkRecord sinkRecord = generateSinkRecord(issue);
      records.add(sinkRecord);
      i += 1;
      lastUpdatedAt = issue.getUpdatedAt();
    }
    if (i > 0) log.info(String.format("Fetched %s records(s)", i));
    if (i == 100) {
      // we have reached a full batch, we need to get the next one.
      nextPageToVisit+=1;
    } else {
      nextQuerySince = lastUpdatedAt.plusSeconds(1);
      nextPageToVisit = 1;
      gitHubHttpAPIClient.sleep();
    }
    return records;
  }

  private SinkRecord generateSinkRecord(Issue issue) {
    return new SinkRecord(
            sinkPartition(),
            sinkOffset(issue.getUpdatedAt()),
            config.getTopic(),
            null, // partition will be inferred by the framewok
            KEY_SCHEMA,
            buildRocrdValue(issue),
            VALUE_SCHEMA,
            buildRecordValue(issue),
            issue.getUpdatedAt().toEpochMilli()
    );
  }*/
  @Override
  public void put(Collection<SinkRecord> collection) {
    try {
      Collection<String> recordsAsString = collection.stream().map(r -> String.valueOf(r.value())).collect(Collectors.toList());
    }
    catch (Exception e) {
      log.error("Error while processing records");
      log.error(e.toString());
    }
  }

  @Override
  public void flush(Map<TopicPartition, OffsetAndMetadata> map) {
    log.trace("Flushing the queue");
  }

  @Override
  public void stop() {
    //Close resources here.
   /* try {
        gitHubHttpAPIClient.close();
    } catch (IOException e) {
        e.printStackTrace();
    }*/
  }
}




