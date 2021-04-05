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
public class WholeReadVO implements ReadVO{
    public final static String MODE = "whole";
    public final static String RELATIVE_OUTPUT_PATH = "whutosa.coreja.com/ocr/outputImage/";
    public final static String UPLOAD_IMAGE_PATH = "/home/bobo/AdvancedEAST/whole_img_results/source/";
    public final static String TXT_OUTPUT_PATH = "/home/bobo/AdvancedEAST/whole_img_results/txt/";

    private String imageUrl;
    private String text;

    public static WholeReadVO fromResult(String filename) throws IOException {
        String txtContent =  FileUtil.readFromLocal(TXT_OUTPUT_PATH + filename + ".txt");
        return WholeReadVO.builder()
                .imageUrl(RELATIVE_OUTPUT_PATH + filename + ".jpg")
                .text(txtContent)
                .build();
    }

}
