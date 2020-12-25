package ml.justep7185.fsweb.controller;

import ml.justep7185.fsweb.config.UploadFile;
import ml.justep7185.fsweb.qr.QrHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author yangjiawei
 * @date 2020-12-17 11:18
 **/
@Controller()
@RequestMapping("/upload")
public class UploadController {

    private final UploadFile uploadFile;

    private final QrHandler qrHandler;

    public UploadController(UploadFile uploadFile, QrHandler qrHandler) {
        this.uploadFile = uploadFile;
        this.qrHandler = qrHandler;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "upload";
    }

    @RequestMapping(value = "/doUploadAndStore", method = RequestMethod.POST)
    public String singleFileUploadAndStore(@RequestParam("file") MultipartFile file, Model model) {
        try {
            String fileDirPath = uploadFile.getFileDirPath();
            UUID uuid = UUID.randomUUID();
            String originalFilename = file.getOriginalFilename();
            String newFilename = uuid.toString() + originalFilename;
            file.transferTo(new File(fileDirPath + newFilename));
            model.addAttribute("message", originalFilename + " 上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "uploadStatus";
    }

    @RequestMapping(value = "/doUploadAndTransfer", method = RequestMethod.POST)
    public String singleFileUploadAndTransfer(@RequestParam("file") MultipartFile file, Model model) {
        try {
            InputStream inputStream = file.getInputStream();
            String text = qrHandler.qrDecode(inputStream);
            qrHandler.generateQr(text);
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "uploadStatus";
    }
}
