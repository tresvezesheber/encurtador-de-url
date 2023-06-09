package br.com.hebio.encurtadordeurl.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Link extends AbstractEntity implements Serializable {

    @NotEmpty
    private String code;

    @NotEmpty
    private String url;

    @Value("0")
    private int hits;

    @JsonCreator
    public Link(@JsonProperty("url") String url) {
        this.url = url;
    }

    public Link() {

    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }
}
