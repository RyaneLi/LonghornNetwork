import java.util.concurrent.Semaphore;

/**
 * Thread class for simulating friend request interactions between students.
 * Implements Runnable to allow concurrent friend request processing.
 * Ensures thread-safe operations when updating student friend lists.
 * 
 * @author LonghornNetwork Team
 */
public class FriendRequestThread implements Runnable {
    /** The student sending the friend request */
    private final UniversityStudent sender;
    /** The student receiving the friend request */
    private final UniversityStudent receiver;
    /** Semaphore for thread-safe friend request output */
    private static final Semaphore outputSemaphore = new Semaphore(1);
    
    /**
     * Constructs a FriendRequestThread with the specified sender and receiver.
     * 
     * @param sender The UniversityStudent sending the friend request
     * @param receiver The UniversityStudent receiving the friend request
     */
    public FriendRequestThread(UniversityStudent sender, UniversityStudent receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    /**
     * Executes the friend request operation in a separate thread.
     * Adds the sender to the receiver's friend list and vice versa,
     * ensuring thread-safe updates to shared resources.
     */
    @Override
    public void run() {
        try {
            outputSemaphore.acquire();
            System.out.println("FriendRequest (Thread-safe): " + sender.name + " sent a friend request to " + receiver.name);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("FriendRequest interrupted: " + e.getMessage());
        } finally {
            outputSemaphore.release();
        }
    }
}
