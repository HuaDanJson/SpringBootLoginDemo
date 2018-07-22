package cool.test.login.websocket;

class WebSocketMessage {

    /**
     * data : {"friendId":67,"message":"123","type":2}
     */

    private DataBean data;

    public DataBean getData() { return data;}

    public void setData(DataBean data) { this.data = data;}

    @Override
    public String toString() {
        return "WebSocketMessage{" +
                "data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * friendId : 67
         * message : 123
         * type : 2
         */

        private long friendId;
        private long senderId;
        private String message;
        private int type;

        public long getFriendId() {
            return friendId;
        }

        public void setFriendId(long friendId) {
            this.friendId = friendId;
        }

        public long getSenderId() {
            return senderId;
        }

        public void setSenderId(long senderId) {
            this.senderId = senderId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "friendId=" + friendId +
                    ", senderId=" + senderId +
                    ", message='" + message + '\'' +
                    ", type=" + type +
                    '}';
        }
    }
}
