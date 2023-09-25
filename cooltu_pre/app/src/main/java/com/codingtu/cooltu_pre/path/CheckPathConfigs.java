package com.codingtu.cooltu_pre.path;

import com.codingtu.cooltu.constant.FileContentType;
import com.codingtu.cooltu.constant.FileType;
import com.codingtu.cooltu.processor.annotation.path.DirPath;
import com.codingtu.cooltu.processor.annotation.path.FilePath;
import com.codingtu.cooltu.processor.annotation.path.Paths;
import com.codingtu.cooltu_pre.bean.User;

@Paths("EnvCheckData/tasks/{company}/{taskName}")
public class CheckPathConfigs {

    @DirPath
    String DeleteLabel;

    @FilePath(
            parent = "DeleteLabel",
            fileName = "i4l",
            fieldName = "i4l",
            fileType = FileType.TXT
    )
    String DeleteLabelI4l;

    @FilePath(
            parent = "DeleteLabel",
            fileName = "user",
            fieldName = "user",
            fileType = FileType.TXT,
            beanClass = User.class)
    String DeleteLabelUser;

    @DirPath(parent = "DeleteLabel", dirName = "{labelName}", fieldName = "label")
    String DeleteLabelLabel;

    @FilePath(
            parent = "DeleteLabelLabel",
            fileName = "{labelName}",
            fieldName = "label",
            fileType = FileType.TXT,
            beanClass = User.class)
    String DeleteLabelLabelLabel;

    @DirPath
    String ExtraInfo;

    @DirPath(parent = "ExtraInfo", dirName = "DeleteLabel", fieldName = "DeleteLabel")
    String ExtraInfoDeleteLabel;

    @DirPath(parent = "ExtraInfoDeleteLabel", dirName = "{labelName}", fieldName = "label")
    String ExtraInfoDeleteLabelLabel;

    @DirPath(dirName = "{labelName}")
    String label;

    @FilePath(
            parent = "label",
            fileName = "{labelName}",
            fieldName = "label",
            fileType = FileType.TXT,
            beanClass = User.class)
    String labelTxt;

    @FilePath(
            parent = "label",
            fileName = "handle",
            fieldName = "handle",
            fileType = FileType.JPG)
    String handleJpg;

    @FilePath(
            parent = "label",
            fileName = "handle.jpg",
            fieldName = "handle_jpg",
            fileType = FileType.PNC)
    String handleJpgPnc;


}
