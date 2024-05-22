package api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Project {


    private String key;

    public Project(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
