package rd.huma.dashboard.model.nexus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"repositoryId",
"artifactLinks"
})
public class ArtifactHits {

@JsonProperty("repositoryId")
private String repositoryId;
@JsonProperty("artifactLinks")
private List<ArtifactLinks> artifactLinks = new ArrayList<ArtifactLinks>();
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

/**
*
* @return
* The repositoryId
*/
@JsonProperty("repositoryId")
public String getRepositoryId() {
return repositoryId;
}

/**
*
* @param repositoryId
* The repositoryId
*/
@JsonProperty("repositoryId")
public void setRepositoryId(String repositoryId) {
this.repositoryId = repositoryId;
}

/**
*
* @return
* The artifactLinks
*/
@JsonProperty("artifactLinks")
public List<ArtifactLinks> getArtifactLinks() {
return artifactLinks;
}

/**
*
* @param artifactLinks
* The artifactLinks
*/
@JsonProperty("artifactLinks")
public void setArtifactLinks(List<ArtifactLinks> artifactLinks) {
this.artifactLinks = artifactLinks;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}