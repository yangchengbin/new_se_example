/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 16-5-28
 * Time: 下午6:05
 * To change this template use File | Settings | File Templates.
 */
public class StoryController {
    public static void main(String[] args) {
        Story story = new Story("1");

        StoryListener listener = new StoryListener() {
            @Override
            public void deleteStory(String id) {
                System.out.println("delete story id is " + id);
            }
        };
        story.addStoryListener(listener);

        story.deleteStory();

        story.removeStoryListener(listener);

        story.deleteStory();
    }
}
