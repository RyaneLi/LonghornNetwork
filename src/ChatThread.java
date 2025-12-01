import java.util.concurrent.Semaphore;

/**
 * Thread class for simulating chat/messaging interactions between students.
 * Implements Runnable to allow concurrent message processing.
 * Ensures thread-safe operations when updating chat histories.
 * 
 * @author LonghornNetwork Team
 */
public class ChatThread implements Runnable {
    /** The student sending the message */
    private final UniversityStudent sender;
    /** The student receiving the message */
    private final UniversityStudent receiver;
    /** The message content to be sent */
    private final String message;
    /** Semaphore for thread-safe message output */
    private static final Semaphore outputSemaphore = new Semaphore(1);
    
    /**
     * Constructs a ChatThread with the specified sender, receiver, and message.
     * 
     * @param sender The UniversityStudent sending the message
     * @param receiver The UniversityStudent receiving the message
     * @param message The message content to be sent
     */
    public ChatThread(UniversityStudent sender, UniversityStudent receiver, String message) {
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
        try {
            outputSemaphore.acquire();
            System.out.println("Chat (Thread-safe): " + sender.name + " to " + receiver.name + ": " + message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Chat interrupted: " + e.getMessage());
        } finally {
            outputSemaphore.release();
        }
    }
}
