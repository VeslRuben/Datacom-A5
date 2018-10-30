import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {


    public static void main(String args[]) throws Exception {
        String respons = LogIn();
        JSONObject jOb = new JSONObject(respons);
        int sessionId = jOb.getInt("sessionId");

        task4(sessionId);


    }

    public void test() {
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
        send.put("a", a);
        send.put("b", b);
        System.out.println(POST.sendPost("dkrest/test/post", send));
    }

    private static String LogIn(){
        String email = "rubensj@stud.ntnu.no";
        String tlf = "41337309";

        JSONObject send = new JSONObject();
        send.put("email", email);
        send.put("phone", tlf);
        String respons = POST.sendPost("dkrest/auth", send);
        System.out.println(respons);
        return respons;
    }

    private static void task1(int sessionId) {
        String respons;
        respons = GET.sendGet("dkrest/gettask/1?sessionId=" + sessionId);
        System.out.println(respons);

        JSONObject send = new JSONObject();
        send.put("msg", "Hello");
        send.put("sessionId", sessionId);
        respons = POST.sendPost("dkrest/solve", send);
        System.out.println(respons);
    }

    private static void task2(int sessionId) {
        String respons;
        respons = GET.sendGet("dkrest/gettask/2?sessionId=" + sessionId);
        System.out.println(respons);

        JSONObject jOb = new JSONObject(respons);
        respons = (String) jOb.getJSONArray("arguments").get(0);

        JSONObject send = new JSONObject();
        send.put("msg",  respons);
        send.put("sessionId", sessionId);
        respons = POST.sendPost("dkrest/solve", send);
        System.out.println(respons);
    }

    private static void task3(int sessionId) {
        String respons;
        respons = GET.sendGet("dkrest/gettask/3?sessionId=" + sessionId);
        System.out.println(respons);

        JSONObject jOb = new JSONObject(respons);
        JSONArray array = jOb.getJSONArray("arguments");
        int sum = 0;
        for (int i = 0; i < array.length(); i++) {
            sum += Integer.parseInt((String) array.get(i));
        }
        System.out.println(sum);

        JSONObject send = new JSONObject();
        send.put("result", sum);
        send.put("sessionId", sessionId);
        respons = POST.sendPost("dkrest/solve", send);
        System.out.println(respons);
    }

    private static void task4(int sessionId) throws Exception {
        String respons;
        respons = GET.sendGet("dkrest/gettask/4?sessionId=" + sessionId);
        System.out.println(respons);

        JSONObject jOb = new JSONObject(respons);
        JSONArray array = jOb.getJSONArray("arguments");


        BruteCrack crack = new BruteCrack();
        String pin = crack.crack((String) array.get(0));
        System.out.println(pin);

        JSONObject send = new JSONObject();
        send.put("pin", pin);
        send.put("sessionId", sessionId);
        respons = POST.sendPost("dkrest/solve", send);
        System.out.println(respons);
    }


}
