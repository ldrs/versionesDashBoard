package rd.huma.dashboard.model.jira;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"avatarUrls"})
public class Reporter extends AJiraPerson {

}