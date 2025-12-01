/**
 * Thread class for simulating friend request interactions between students.
 * Implements Runnable to allow concurrent friend request processing.
 * Ensures thread-safe operations when updating student friend lists.
 * 
 * @author LonghornNetwork Team
 */
public class FriendRequestThread implements Runnable {
    /** The student sending the friend request */
    private UniversityStudent sender;
    /** The student receiving the friend request */
    private UniversityStudent receiver;
    
    /**
     * Constructs a FriendRequestThread with the specified sender and receiver.
     * 
     * @param sender The UniversityStudent sending the friend request
     * @param receiver The UniversityStudent receiving the friend request
     */
    public FriendRequestThread(UniversityStudent sender, UniversityStudent receiver) {
        // constructor
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
        // TODO: Implementation to be completed
    }
}
