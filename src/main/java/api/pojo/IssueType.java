package api.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;

public class IssueType {

    private String name;

    public IssueType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
