package me.mikolaj.messageboard.email;

public interface EmailSender {

	public void send(String to, String content);
}
