package api.pojo.body_request;

import api.pojo.Fields;
import api.pojo.IssueType;
import api.pojo.Project;

public class JiraIssueRequest {

    private Fields fields;

    public JiraIssueRequest(Project project, String summery, String description, IssueType issuetype) {
        this.fields = new Fields(project, summery, description, issuetype);
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }
}
