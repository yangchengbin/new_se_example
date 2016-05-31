import java.util.Enumeration;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-5-28
 * Time: 下午5:58
 * To change this template use File | Settings | File Templates.
 */
public class Story {

    private Vector<StoryListener> repository = new Vector<StoryListener>();
    private StoryListener listener;
    private String id;

    public Story(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void addStoryListener(StoryListener listener) {
        repository.add(listener);
    }

    public void removeStoryListener(StoryListener listener) {
        repository.remove(listener);
    }

    private void notifyStoryEvent(StoryEvent event) {
        Story story = (Story) event.getSource();
        Enumeration<StoryListener> enumeration = repository.elements();
        while (enumeration.hasMoreElements()) {
            listener = enumeration.nextElement();
            listener.deleteStory(story.getId());
        }
    }

    public void deleteStory() {
        System.out.println("exec delete story");  //执行本来方法
        notifyStoryEvent(new StoryEvent(this));     //触发删除事件
    }
}
