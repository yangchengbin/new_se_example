import java.util.EventListener;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-5-28
 * Time: 下午5:59
 * To change this template use File | Settings | File Templates.
 */
public interface StoryListener extends EventListener {
    void deleteStory(String id);
}
