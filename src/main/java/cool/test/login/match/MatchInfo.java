package cool.test.login.match;

public class MatchInfo {

    private int senderId;
    private String channelKey;
    private String channelName;
    private long creatAt;

    public long getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(long creatAt) {
        this.creatAt = creatAt;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getChannelKey() {
        return channelKey;
    }

    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public String toString() {
        return "MatchInfo{" +
                "senderId=" + senderId +
                ", channelKey='" + channelKey + '\'' +
                ", channelName='" + channelName + '\'' +
                '}';
    }
}
