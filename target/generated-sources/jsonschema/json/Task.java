
package json;

import java.util.Date;
import javax.annotation.Generated;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * Task Schema
 * <p>
 * The details of a particular Task
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "name",
    "points",
    "completedDate"
})
public class Task {

    /**
     * ID of a Task
     * 
     */
    @JsonProperty("id")
    private Integer id;
    /**
     * Name of a Task
     * 
     */
    @JsonProperty("name")
    private String name;
    /**
     * How many points a task is worth
     * 
     */
    @JsonProperty("points")
    private Integer points;
    /**
     * Completed date and time
     * 
     */
    @JsonProperty("completedDate")
    private Date completedDate;

    /**
     * ID of a Task
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * ID of a Task
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Task withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Name of a Task
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Name of a Task
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Task withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * How many points a task is worth
     * 
     * @return
     *     The points
     */
    @JsonProperty("points")
    public Integer getPoints() {
        return points;
    }

    /**
     * How many points a task is worth
     * 
     * @param points
     *     The points
     */
    @JsonProperty("points")
    public void setPoints(Integer points) {
        this.points = points;
    }

    public Task withPoints(Integer points) {
        this.points = points;
        return this;
    }

    /**
     * Completed date and time
     * 
     * @return
     *     The completedDate
     */
    @JsonProperty("completedDate")
    public Date getCompletedDate() {
        return completedDate;
    }

    /**
     * Completed date and time
     * 
     * @param completedDate
     *     The completedDate
     */
    @JsonProperty("completedDate")
    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public Task withCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
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
