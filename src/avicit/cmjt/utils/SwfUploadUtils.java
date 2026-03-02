package avicit.cmjt.utils;

import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.FileUtil;
import avicit.platform6.core.properties.PlatformProperties;
import avicit.platform6.core.spring.SpringFactory;
import avicit.platform6.modules.system.sysfileupload.domain.SysFileUpload;
import avicit.platform6.modules.system.sysfileupload.service.SysFileUploadService;
import org.apache.commons.fileupload.util.Streams;

import java.io.*;
import java.util.List;

/**
 * 附件上传类，将文件列表数据存到服务器硬盘中
 */
public class SwfUploadUtils {
    public static void save2Disk(List<String> fileList, String formId, String formCode, String secretLevel) {
        for(String filePath : fileList) {
            save2Disk(new File(filePath), formId, formCode, secretLevel);
        }
    }

    public static void save2Disk(File file, String formId, String formCode, String secretLevel) {
        String fileName = null;

        try {
            String filePath = PlatformProperties.getProperty("doccenter.path");
            fileName = filePath + File.separator + "SECRETLEVEL" + secretLevel + File.separator + "group" + formCode + File.separator + "file" + formId + File.separator;// + file.getName();

            File parent = new File(fileName);

            if(!parent.exists()) {
                parent.mkdirs();
            }

            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(fileName + file.getName())));

            try {
                Streams.copy(in, out, true);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                in.close();
                out.close();
            }

            saveSysFileUpload(file.getName(), formId, formCode, file.length(), fileName + file.getName(), secretLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveSysFileUpload(String fileName, String formId, String formCode, long size, String filePath, String secretLevel) {
        SysFileUploadService sysFileUploadService = (SysFileUploadService) SpringFactory.getBean(SysFileUploadService.class);

        SysFileUpload sysFileUpload = new SysFileUpload();
            sysFileUpload.setId(ComUtil.getId());
            sysFileUpload.setFILE_NAME(fileName);
            sysFileUpload.setFILE_BUSINESS_ID(formId);
            sysFileUpload.setFILE_BUSINESS_TABLE_NAME(formCode);
            sysFileUpload.setATTRIBUTE_01("");
            sysFileUpload.setATTRIBUTE_02("");
            sysFileUpload.setATTRIBUTE_08("");
            sysFileUpload.setATTRIBUTE_09("");
            sysFileUpload.setATTRIBUTE_10("");
            sysFileUpload.setFILE_SIZE(Long.valueOf(size));
            sysFileUpload.setFILE_TYPE(FileUtil.getFileExtensionName(fileName));
            sysFileUpload.setFILE_URL(filePath);
            sysFileUpload.setATTACH_CATEGORY("");
            sysFileUpload.setSECRET_LEVEL(secretLevel);

        sysFileUploadService.insertSysFileUpload(sysFileUpload);
    }

}
