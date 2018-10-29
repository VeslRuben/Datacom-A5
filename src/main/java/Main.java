import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {



    public static void main(String args[]){

        String response;
        response = GET.sendGet("dkrest/test/get2");
        System.out.println(response);

        JSONObject jOb = new JSONObject(response);
        int a;
        int b;
        int c;
        a = jOb.getInt("a");
        b = jOb.getInt("b");
        c = jOb.getInt("c");

        a = (int) Math.round(Math.random() * 100);
        b = (int) Math.round(Math.random() * 100);

        JSONObject send = new JSONObject();
        send.put("a",a);
        send.put("b",b);
        System.out.println(POST.sendPost("dkrest/test/post", send));
    }



}
