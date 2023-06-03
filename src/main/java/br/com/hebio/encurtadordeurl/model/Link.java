package br.com.hebio.encurtadordeurl.model;

import org.hibernate.annotations.Entity;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

@Entity
public class Link extends AbstractEntity implements Serializable {

    @NotEmpty
    private String code;

    @NotEmpty
    private String url;

    @Value("0")
    private int hits;


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
