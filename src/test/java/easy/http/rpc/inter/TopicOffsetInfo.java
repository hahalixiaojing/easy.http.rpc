package easy.http.rpc.inter;


import java.util.Map;

public class TopicOffsetInfo {

    public TopicOffsetInfo(String topic, Map<Integer, Long> offset) {
        this.topic = topic;
        this.offset = offset;
    }

    private String topic;
    private Map<Integer, Long> offset;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Map<Integer, Long> getOffset() {
        return offset;
    }

    public void setOffset(Map<Integer, Long> offset) {
        this.offset = offset;
    }

}
