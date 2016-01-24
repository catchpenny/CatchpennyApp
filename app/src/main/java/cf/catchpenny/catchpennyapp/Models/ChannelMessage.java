package cf.catchpenny.catchpennyapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChannelMessage {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("domainID")
    @Expose
    private Integer domainID;
    @SerializedName("channelID")
    @Expose
    private Integer channelID;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_by_id")
    @Expose
    private Integer createdById;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The domainID
     */
    public Integer getDomainID() {
        return domainID;
    }

    /**
     * 
     * @param domainID
     *     The domainID
     */
    public void setDomainID(Integer domainID) {
        this.domainID = domainID;
    }

    /**
     * 
     * @return
     *     The channelID
     */
    public Integer getChannelID() {
        return channelID;
    }

    /**
     * 
     * @param channelID
     *     The channelID
     */
    public void setChannelID(Integer channelID) {
        this.channelID = channelID;
    }

    /**
     * 
     * @return
     *     The createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 
     * @param createdBy
     *     The created_by
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 
     * @return
     *     The body
     */
    public String getBody() {
        return body;
    }

    /**
     * 
     * @param body
     *     The body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     * @return
     *     The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 
     * @return
     *     The createdById
     */
    public Integer getCreatedById() {
        return createdById;
    }

    /**
     * 
     * @param createdById
     *     The created_by_id
     */
    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }

}
