import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Json {

    public static void main(String[] args) throws ParseException {
        JSONObject obj = new JSONObject();
        obj.put(1, 26);
        obj.put("name", "hello");
        System.out.println(obj.toJSONString());
        JSONParser jsonParser = new JSONParser();
        obj = (JSONObject) jsonParser.parse("{\"1\":26,\"name\":\"hello\"}");
        System.out.println(obj.get("1"));
        obj = (JSONObject) JSONValue.parse("{\"1\":25,\"name\":\"world\"}");
        System.out.println(obj.toJSONString());
    }
}
