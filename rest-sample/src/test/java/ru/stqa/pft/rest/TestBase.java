package ru.stqa.pft.rest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;


/**
 * Created by Alesia on 15.04.17.
 */
public class TestBase {


  public boolean isIssueOpen(int issueId) throws IOException {

    String json = getExecutor()
            .execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId)))
            .returnContent().asString();

    JsonElement parsed = new JsonParser().parse(json);
    JsonArray issues = parsed.getAsJsonObject().get("issues").getAsJsonArray();
    JsonElement element = issues.get(0);
    String status = element.getAsJsonObject().get("state_name").getAsString();

    if(  status.equals("Open")){
      return true;
    }
    if( ! status.equals("Open")){
      return false;
    }
    return false;
  }


 public Executor getExecutor() {
    return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
  }


  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}



