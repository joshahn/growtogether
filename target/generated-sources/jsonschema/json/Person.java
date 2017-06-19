
package json;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * Person Schema
 * <p>
 * The details of a particular Person
 * 
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "firstName",
    "lastName",
    "email",
    "teamId",
    "tasks",
    "totalPoints"
})
public class Person {

    /**
     * ID of a Person
     * 
     */
    @JsonProperty("id")
    private Integer id;
    /**
     * First Name of a Person
     * 
     */
    @JsonProperty("firstName")
    private String firstName;
    /**
     * Last Name of a Person
     * 
     */
    @JsonProperty("lastName")
    private String lastName;
    /**
     * Email of a Person
     * 
     */
    @JsonProperty("email")
    private String email;
    @JsonProperty("teamId")
    private Integer teamId;
    @JsonProperty("tasks")
    private List<Task> tasks = new ArrayList<Task>();
    /**
     * How many points a person has
     * 
     */
    @JsonProperty("totalPoints")
    private Integer totalPoints;

    /**
     * ID of a Person
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * ID of a Person
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Person withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * First Name of a Person
     * 
     * @return
     *     The firstName
     */
    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    /**
     * First Name of a Person
     * 
     * @param firstName
     *     The firstName
     */
    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Person withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Last Name of a Person
     * 
     * @return
     *     The lastName
     */
    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    /**
     * Last Name of a Person
     * 
     * @param lastName
     *     The lastName
     */
    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Person withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Email of a Person
     * 
     * @return
     *     The email
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    /**
     * Email of a Person
     * 
     * @param email
     *     The email
     */
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    public Person withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * 
     * @return
     *     The teamId
     */
    @JsonProperty("teamId")
    public Integer getTeamId() {
        return teamId;
    }

    /**
     * 
     * @param teamId
     *     The teamId
     */
    @JsonProperty("teamId")
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Person withTeamId(Integer teamId) {
        this.teamId = teamId;
        return this;
    }

    /**
     * 
     * @return
     *     The tasks
     */
    @JsonProperty("tasks")
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * 
     * @param tasks
     *     The tasks
     */
    @JsonProperty("tasks")
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Person withTasks(List<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    /**
     * How many points a person has
     * 
     * @return
     *     The totalPoints
     */
    @JsonProperty("totalPoints")
    public Integer getTotalPoints() {
        return totalPoints;
    }

    /**
     * How many points a person has
     * 
     * @param totalPoints
     *     The totalPoints
     */
    @JsonProperty("totalPoints")
    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public Person withTotalPoints(Integer totalPoints) {
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
