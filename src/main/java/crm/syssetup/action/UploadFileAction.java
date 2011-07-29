package crm.syssetup.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.action.BaseAction;
import crm.common.Constants;
import crm.cust.dto.UploadFileDto;

/**
 * @author Kind Cao
 * @version $Rev$, Jul 28, 2011 2:22:43 PM
 */
@SuppressWarnings("serial")
public class UploadFileAction extends BaseAction {

    protected Logger log = LoggerFactory.getLogger(UploadFileAction.class);

    private File fileData;

    private String Filename;

    private String folder = "/uploads/";

    public String showFileList() throws Exception {
        return "file.list";
    }

    public String getFileList() throws Exception {
        File[] files = new File(getSaveDir()).listFiles();
        if (null != files) {
            Arrays.sort(files, new Comparator<File>() {

                @Override
                public int compare(File o1, File o2) {
                    return (int) (o2.lastModified() - o1.lastModified());
                }

            });
            //
            if (files.length > Constants.DEFAULT_ROWS) {
                files = Arrays.copyOfRange(files, 0, Constants.DEFAULT_ROWS);
            }
            //
            List<UploadFileDto> list = new ArrayList<UploadFileDto>();
            UploadFileDto dto = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i = 0; i < files.length; i++) {
                dto = new UploadFileDto();
                dto.setFileName(files[i].getName());
                dto.setFileURI(getFolder().substring(1) + dto.getFileName());
                dto.setFileSize(files[i].length());
                dto.setUploadDate(sdf.format(files[i].lastModified()));
                list.add(dto);
            }
            responseJsonData(list);
        }
        return NONE;
    }

    public String upload() throws Exception {
        if (null != fileData) {
            File f = new File(getSaveDir());
            if (!f.exists()) {
                f.mkdirs();
                log.info("create dir : " + f.getPath());
            }
            //
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            fileData.renameTo(new File(getSaveDir() + sdf.format(new Date()) + "_" + getFilename()));
            responseJsonData("1");
        } else {
            log.error("fileData is null");
            responseJsonData("0");
        }
        return NONE;
    }

    private String getSaveDir() {
        return request.getSession().getServletContext().getRealPath("") + getFolder();
    }

    public File getFileData() {
        return fileData;
    }

    public void setFileData(File fileData) {
        this.fileData = fileData;
    }

    public String getFilename() {
        return Filename;
    }

    public void setFilename(String filename) {
        Filename = filename;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

}
