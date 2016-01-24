package cf.catchpenny.catchpennyapp.Models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Channel {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("per_page")
    @Expose
    private Integer perPage;
    @SerializedName("current_page")
    @Expose
    private Integer currentPage;
    @SerializedName("last_page")
    @Expose
    private Integer lastPage;
    @SerializedName("next_page")
    @Expose
    private Integer nextPage;
    @SerializedName("prev_page")
    @Expose
    private Object prevPage;
    @SerializedName("channel_messages")
    @Expose
    private List<ChannelMessage> channelMessages = new ArrayList<ChannelMessage>();

    /**
     * 
     * @return
     *     The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 
     * @return
     *     The perPage
     */
    public Integer getPerPage() {
        return perPage;
    }

    /**
     * 
     * @param perPage
     *     The per_page
     */
    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    /**
     * 
     * @return
     *     The currentPage
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * 
     * @param currentPage
     *     The current_page
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 
     * @return
     *     The lastPage
     */
    public Integer getLastPage() {
        return lastPage;
    }

    /**
     * 
     * @param lastPage
     *     The last_page
     */
    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    /**
     * 
     * @return
     *     The nextPage
     */
    public Integer getNextPage() {
        return nextPage;
    }

    /**
     * 
     * @param nextPage
     *     The next_page
     */
    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * 
     * @return
     *     The prevPage
     */
    public Object getPrevPage() {
        return prevPage;
    }

    /**
     * 
     * @param prevPage
     *     The prev_page
     */
    public void setPrevPage(Object prevPage) {
        this.prevPage = prevPage;
    }

    /**
     * 
     * @return
     *     The channelMessages
     */
    public List<ChannelMessage> getChannelMessages() {
        return channelMessages;
    }

    /**
     * 
     * @param channelMessages
     *     The channel_messages
     */
    public void setChannelMessages(List<ChannelMessage> channelMessages) {
        this.channelMessages = channelMessages;
    }

}
