package cn.xiaym.jse;

import cn.xiaym.ndos.plugins.*;
import cn.xiaym.utils.*;

import org.laziji.commons.js.model.value.env.Top;
import org.laziji.commons.js.model.value.module.SystemModuleValue;

import java.util.*;
import java.nio.file.*;
import java.nio.charset.*;

import java.lang.reflect.*;

public class main extends JavaPlugin {
  public void onCommand(String cmd) {
    ArrayList<String> args = argumentParser.parse(cmd);
    if(args.size() < 1) return;
    if("js".equals(args.get(0))) {
      if(args.size() < 2) {
        this.warn("用法: js <文件名>");
        return;
      }

      Path p = Paths.get(args.get(1));

      if(!Files.exists(p)) return;

      List<String> list;

      try {
        list = Files.readAllLines(p);
      } catch(Exception e) {
        Logger.err("读取文件失败!");
        return;
      }

      StringBuilder sb = new StringBuilder();
      for(String b : list) {
        sb.append(b).append("\n");
      }
      String merged = sb.toString();

      Top.init();
      Top.addInternalModules("sys", new SystemModuleValue());
      try {
        Top.eval(merged);
        Top.loop();
      } catch(Exception e) {
        Logger.err("编译失败!");
        Logger.err(e.getMessage());
      }
    }
  }
}
