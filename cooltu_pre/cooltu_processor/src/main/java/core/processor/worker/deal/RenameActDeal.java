package core.processor.worker.deal;

import java.io.File;
import java.util.ArrayList;

import javax.lang.model.element.Element;

import cooltu.lib4j.data.bean.JavaInfo;
import cooltu.lib4j.file.FileTool;
import cooltu.lib4j.file.deal.FileLineDealer;
import cooltu.lib4j.file.delete.FileDeleter;
import core.constant.Constant;
import core.constant.Pkg;
import core.constant.Suffix;
import core.processor.annotation.rename.RenameAct;
import core.processor.lib.log.Logs;
import core.processor.lib.tools.NameTools;
import core.processor.worker.deal.base.BaseDeal;

public class RenameActDeal extends BaseDeal {
    @Override
    public void deal(Element element) {

        RenameAct renameAct = element.getAnnotation(RenameAct.class);
        String oldName = renameAct.oldName();
        String newName = renameAct.newName();

        String oldPackages = renameAct.oldPackages();
        String newPackages = renameAct.newPackages();

        if (Constant.RENAME_ACTS == null) {
            Constant.RENAME_ACTS = new ArrayList<>();
        }

        JavaInfo oldActJi = NameTools.getJavaInfoByName(oldPackages, oldName, Suffix.ACTIVITY);
        Constant.RENAME_ACTS.add(oldActJi.fullName);
        JavaInfo newActJi = NameTools.getJavaInfoByName(newPackages, newName, Suffix.ACTIVITY);
        Constant.RENAME_ACTS.add(newActJi.fullName);

        String oldLayout = NameTools.getActivityLayoutName(oldName);
        String newLayout = NameTools.getActivityLayoutName(newName);

        //Activity
        File oldFile = new File(oldActJi.path);
        if (!oldFile.exists()) {
            return;
        }
        FileLineDealer.create(oldFile).deal(new FileLineDealer.Dealer() {
            @Override
            public String deal(String line) {
                if (line.contains(oldActJi.name)) {
                    line = line.replace(oldActJi.name, newActJi.name);
                }
                if (line.contains(Pkg.R_LAYOUT + oldLayout)) {
                    line = line.replace(Pkg.R_LAYOUT + oldLayout, Pkg.R_LAYOUT + newLayout);
                }
                if (line.startsWith("package")) {
                    line = "package " + newActJi.pkg + ";";
                }
                return line;
            }
        });

        File newFile = new File(newActJi.path);
        FileTool.createFileDir(newFile);
        oldFile.renameTo(newFile);
        //ActBase，生成的时候拦截
        if (!oldName.equals(newName)) {
            //删除old
            JavaInfo oldActBaseInfo = NameTools.getActBaseInfo(oldName);
            Logs.i("delete:" + oldActBaseInfo.path);
            FileDeleter.delete(oldActBaseInfo.path);
        }
        //layout
        String oldLayoutPath = NameTools.getActivityLayoutPath(oldName);
        String newLayoutPath = NameTools.getActivityLayoutPath(newName);
        new File(oldLayoutPath).renameTo(new File(newLayoutPath));
        //Code4Request，生成的时候拦截
        //Manifest
        FileLineDealer.create(NameTools.getManifestPath()).deal(new FileLineDealer.Dealer() {
            @Override
            public String deal(String line) {
                if (line.contains(oldActJi.fullName)) {
                    line = line.replace(oldActJi.fullName, newActJi.fullName);
                }
                return line;
            }
        });
        //ActStart，生成的时候拦截

    }
}
