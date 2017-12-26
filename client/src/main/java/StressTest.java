import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by SUN on 17/12/25.
 */
public class StressTest {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i <1000; i ++) {
            URL url = new URL("http://127.0.0.1:8089");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);        // 设置该连接是可以输出的
            httpURLConnection.setRequestMethod("POST"); // 设置请求方式
            httpURLConnection.setRequestProperty("charset", "utf-8");

            PrintWriter pw = new PrintWriter(new BufferedOutputStream(httpURLConnection.getOutputStream()));
            pw.write("name=welcome");                   // 向连接中输出数据（相当于发送数据给服务器）
            pw.write("&age=14");
            pw.flush();
            pw.close();
        }


//        BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"utf-8"));
//        String line = null;
//        StringBuilder sb = new StringBuilder();
//        while ((line = br.readLine()) != null) {    // 读取数据
//            sb.append(line + "\n");
//        }
//
//        System.out.println(sb.toString());
    }

}
