package org.whutosa.appapi.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.whutosa.modulecommon.utils.FileUtil;

import java.io.IOException;

/**
 * @author bobo
 * @date 2021/4/3
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleReadVO implements ReadVO{
    public final static String MODE = "single";
    public final static String UPLOAD_IMAGE_PATH = "/home/bobo/Paddle_Text_OCR/PaddleOCR/single_results/source/";
    public final static String SINGLE_OUTPUT_PATH = "/home/bobo/Paddle_Text_OCR/PaddleOCR/single_results/";

    private String text;

    public static SingleReadVO fromResult(String filename) throws IOException {
        String txtContent =  FileUtil.readFromLocal(SINGLE_OUTPUT_PATH + filename + ".txt");
        return SingleReadVO.builder()
                .text(txtContent)
                .build();
    }
}
