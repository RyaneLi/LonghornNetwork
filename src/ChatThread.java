/**
 * Thread class for simulating chat/messaging interactions between students.
 * Implements Runnable to allow concurrent message processing.
 * Ensures thread-safe operations when updating chat histories.
 * 
 * @author LonghornNetwork Team
 */
public class ChatThread implements Runnable {
    /** The student sending the message */
    private UniversityStudent sender;
    /** The student receiving the message */
    private UniversityStudent receiver;
    /** The message content to be sent */
    private String message;
    
    /**
     * Constructs a ChatThread with the specified sender, receiver, and message.
     * 
     * @param sender The UniversityStudent sending the message
     * @param receiver The UniversityStudent receiving the message
     * @param message The message content to be sent
     */
    public ChatThread(UniversityStudent sender, UniversityStudent receiver, String message) {
        // constructor
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    /**
     * Executes the chat operation in a separate thread.
     * Updates the chat history between sender and receiver,
     * ensuring thread-safe updates to shared resources.
     */
    @Override
    public void run() {
        // TODO: Implementation to be completed
    }
}
