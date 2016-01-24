package cf.catchpenny.catchpennyapp.Models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("domainsSubscribed")
    @Expose
    private List<List<DomainsSubscribed>> domainsSubscribed = new ArrayList<List<DomainsSubscribed>>();
    @SerializedName("channelsSubscribed")
    @Expose
    private List<List<ChannelsSubscribed>> channelsSubscribed = new ArrayList<List<ChannelsSubscribed>>();

    /**
     * 
     * @return
     *     The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @param firstName
     *     The firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * @return
     *     The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * @param lastName
     *     The lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The domainsSubscribed
     */
    public List<List<DomainsSubscribed>> getDomainsSubscribed() {
        return domainsSubscribed;
    }

    /**
     * 
     * @param domainsSubscribed
     *     The domainsSubscribed
     */
    public void setDomainsSubscribed(List<List<DomainsSubscribed>> domainsSubscribed) {
        this.domainsSubscribed = domainsSubscribed;
    }

    /**
     * 
     * @return
     *     The channelsSubscribed
     */
    public List<List<ChannelsSubscribed>> getChannelsSubscribed() {
        return channelsSubscribed;
    }

    /**
     * 
     * @param channelsSubscribed
     *     The channelsSubscribed
     */
    public void setChannelsSubscribed(List<List<ChannelsSubscribed>> channelsSubscribed) {
        this.channelsSubscribed = channelsSubscribed;
    }

}
