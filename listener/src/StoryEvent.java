import java.util.EventObject;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-5-28
 * Time: 下午5:59
 * To change this template use File | Settings | File Templates.
 */
public class StoryEvent extends EventObject {

    private Object object;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public StoryEvent(Object source) {
        super(source);
        this.object = source;
    }

    public Object getSource() {
        return object;
    }
}
