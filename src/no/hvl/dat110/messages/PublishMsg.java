package no.hvl.dat110.messages;

public class PublishMsg extends Message {
	private String topic;
	private String msg;

	public PublishMsg(String user, String topic, String msg) {
		super(MessageType.PUBLISH, user);

		this.topic = topic;
		this.msg = msg;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMessage() {
		return msg;
	}

	public void setMessage(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "PublishMsg [topic = " + topic + ", msg = " + msg + "]";
	}
	
	// TODO: 
	// Implement objectvariables, constructor, get/set-methods, and toString method
	
}
