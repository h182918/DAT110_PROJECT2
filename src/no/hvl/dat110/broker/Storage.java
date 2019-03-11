package no.hvl.dat110.broker;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.common.Logger;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messagetransport.Connection;

public class Storage {

	protected ConcurrentHashMap<String, Set<String>> subscriptions;
	protected ConcurrentHashMap<String, ClientSession> clients;
	protected ConcurrentHashMap<String, Set<Message>> offlineClients;

	public Storage() {
		subscriptions = new ConcurrentHashMap<String, Set<String>>();
		clients = new ConcurrentHashMap<String, ClientSession>();
		offlineClients = new ConcurrentHashMap<String, Set<Message>>();
	}

	public Collection<ClientSession> getSessions() {
		return clients.values();
	}
	
	public Set<String> getOfflineSessions() {
		return offlineClients.keySet();
	}
	public Set<Message> getOfflineSession(String user) {
		return offlineClients.get(user);
	}
	
	public void addOfflineClient(String user, Message msg) {
		if(!getOfflineSessions().contains(user)) {
			Set<Message> newSet = new HashSet<Message>();
			offlineClients.put(user, newSet);
		}
		offlineClients.get(user).add(msg);
	}
	
	public void removeOffline(String user) {
		offlineClients.remove(user);
	}
	
	public Set<String> getTopics() {

		return subscriptions.keySet();

	}

	public ClientSession getSession(String user) {

		ClientSession session = clients.get(user);

		return session;
	}

	public Set<String> getSubscribers(String topic) {
		return (subscriptions.get(topic));
	}

	public void addClientSession(String user, Connection connection) {
		ClientSession CS = new ClientSession(user, connection);
		clients.put(user, CS);
		Logger.log("Added new ClientSession: " + user);
		if(getOfflineSessions().contains(user)) {
			Dispatcher.backOnline(getOfflineSession(user), connection);
			removeOffline(user);
		}
		// TODO: add corresponding client session to the storage
	}

	public void removeClientSession(String user) {
		clients.remove(user);
		Logger.log("Removed ClientSession: " + user);
		// TODO: remove client session for user from the storage
	}

	public void createTopic(String topic) {
		Set<String> newSet = new HashSet<String>();
		subscriptions.put(topic, newSet);
		Logger.log("Created new topic: " + topic);
	
	}

	public void deleteTopic(String topic) {
		subscriptions.remove(topic);
		Logger.log("Deleted topic: " + topic);
		
	}

	public void addSubscriber(String user, String topic) {
		System.out.println("Topic: " + topic + " User: " + user);
		subscriptions.get(topic).add(user);
		Logger.log("Added new Subscriber(" + user + ") to topic: " + topic);
		
	}

	public void removeSubscriber(String user, String topic) {
		subscriptions.get(topic).remove(user);
		Logger.log("Removed Subscriber(" + user + ") from topic: " + topic);
	}
}
