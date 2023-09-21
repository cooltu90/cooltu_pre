package com.codingtu.cooltu_pre.path;

import com.codingtu.cooltu.processor.annotation.path.Path;
import com.codingtu.cooltu.processor.annotation.path.Paths;

@Paths("EnvCheckData/tasks/{company}/{taskName}")
public class CheckPathConfigs {

    @Path
    String ExtraInfo;

    @Path(value = "{ExtraInfo}", fileType = "txt")
    String weather;

}
