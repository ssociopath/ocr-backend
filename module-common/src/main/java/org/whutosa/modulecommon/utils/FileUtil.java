package org.whutosa.modulecommon.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author bobo
 * @date 2021/4/3
 */

public class FileUtil {
    public static String readFromLocal(String filepath) throws IOException {
        String s = "";
        InputStreamReader in = new InputStreamReader(new FileInputStream(filepath), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(in);
        StringBuilder content = new StringBuilder();
        while ((s=br.readLine())!=null){
            content.append(s).append("\n");
        }
        return content.toString();
    }

    public static String writeMultipartFile(String path, MultipartFile file) throws IOException {
        String tempFilePath = path+file.getOriginalFilename();
        File tempFile = new File(tempFilePath);
        file.transferTo(tempFile);
        return tempFilePath;
    }
}
