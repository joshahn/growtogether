
package json;

import javax.annotation.Generated;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * Team Schema
 * <p>
 * The details of a particular Team
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "totalPoints"
})
public class Team {

    /**
     * ID of the Team
     * 
     */
    @JsonProperty("id")
    private Integer id;
    /**
     * Name of a Team
     * 
     */
    @JsonProperty("name")
    private String name;
    /**
     * How many points this team has
     * 
     */
    @JsonProperty("totalPoints")
    private Integer totalPoints;

    /**
     * ID of the Team
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * ID of the Team
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Team withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Name of a Team
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Name of a Team
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Team withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * How many points this team has
     * 
     * @return
     *     The totalPoints
     */
    @JsonProperty("totalPoints")
    public Integer getTotalPoints() {
        return totalPoints;
    }

    /**
     * How many points this team has
     * 
     * @param totalPoints
     *     The totalPoints
     */
    @JsonProperty("totalPoints")
    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Team withTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

}
