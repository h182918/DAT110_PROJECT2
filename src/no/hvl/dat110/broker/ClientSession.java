package no.hvl.dat110.broker;

import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.MessageUtils;
import no.hvl.dat110.messagetransport.Connection;

public class ClientSession {

	private String user;
	private Connection connection;

	public ClientSession(String user, Connection connection) {
		this.user = user;
		this.connection = connection;

	}

	public void disconnect() {

		if (connection != null) {
			connection.close();
		}
	}
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public void send(Message message) {

		MessageUtils.send(connection, message);
	}

	public boolean hasData() {

		return connection.hasData();
	}

	public Message receive() {

		Message msg = MessageUtils.receive(connection);

		return msg;
	}

}
