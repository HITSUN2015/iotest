package io.bio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by SUN on 17/12/26.
 */
public class FileIO {

    private static String BASE_FORLDER = "/Users/Sunny/temp";

    public static void createLocalPropertyFile(String  fileName) {
        File file = new File(getFullPath(fileName));
        try {
            file.createNewFile();
        } catch (IOException ex) {
//            ("创建 spark.properties 文件失败";
        }
    }

    public static void appendMethodB(String fileName,String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(getFullPath(fileName), true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            //EXTEND_POINT_TRACE_MANAGER_EXCEPTION
            e.printStackTrace();
        }
    }

    private static String getFullPath(String fileName) {
        return BASE_FORLDER + "/" + fileName;
    }

}
