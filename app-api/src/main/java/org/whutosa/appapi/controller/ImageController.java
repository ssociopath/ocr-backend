package org.whutosa.appapi.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.whutosa.appapi.vo.ReadVO;
import org.whutosa.appapi.vo.SingleReadVO;
import org.whutosa.appapi.vo.WholeReadVO;
import org.whutosa.modulecommon.response.ApplicationResponse;
import org.whutosa.modulecommon.response.SystemCodeEnum;
import org.whutosa.modulecommon.utils.FileUtil;

import java.io.IOException;


/**
 * @author bobo
 * @date 2021/3/25
 */

@RestController
@RequestMapping("/img")
public class ImageController {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    private String fileFinish = "";

    @PostMapping("/whole")
    public ApplicationResponse<WholeReadVO> wholeRead(MultipartFile file) throws IOException {
        //将文件传输到/upload文件夹中
        String filePath = FileUtil.writeMultipartFile(WholeReadVO.UPLOAD_IMAGE_PATH, file);
        //与模型通信
        rabbitTemplate.convertAndSend("wholeImage", filePath);
        return ApplicationResponse.succeed();
    }

    @PostMapping("/single")
    public ApplicationResponse<SingleReadVO> singleRead(MultipartFile file) throws IOException {
        //将文件传输到/upload文件夹中
        String filePath = FileUtil.writeMultipartFile(SingleReadVO.UPLOAD_IMAGE_PATH, file);
        //与模型通信
        rabbitTemplate.convertAndSend("singleImage", filePath);
        return ApplicationResponse.succeed();
    }

    @RabbitListener(queues = "output")
    public void setStatus(String message){
        fileFinish = message;
    }

    @GetMapping("/{mode}")
    public ApplicationResponse<ReadVO> returnResult(String filename, @PathVariable String mode) throws IOException {
        String name = filename.split("\\.")[0];
        if(name.equals(fileFinish)){
            fileFinish="";
            if(WholeReadVO.MODE.equals(mode)){
                return ApplicationResponse.succeed(WholeReadVO.fromResult(name));
            }else if(SingleReadVO.MODE.equals(mode)){
                return ApplicationResponse.succeed(SingleReadVO.fromResult(name));
            }

        }
        return ApplicationResponse.fail(SystemCodeEnum.FAILURE, "还未生成结果文件");
    }


}
