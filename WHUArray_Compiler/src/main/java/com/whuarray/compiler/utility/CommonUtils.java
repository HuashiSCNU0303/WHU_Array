package com.whuarray.compiler.utility;

import javax.tools.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    public static void createFile(Path path, String content) throws Exception {
        Files.createFile(path);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {
            writer.write(content);
            writer.flush();
        }
    }

    public static int matchNumber(String src) {
        Pattern pattern = Pattern.compile(Constant.NUMBER_REGEX);
        Matcher matcher = pattern.matcher(src);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(0));
        }
        return -1;
    }

    public static void deleteDir(File dir) {
        if (dir == null || !dir.exists()) {
            return;
        }
        File[] files = dir.listFiles();
        for (File f: files){
            if (f.isDirectory()) {
                deleteDir(f);
            }
            else {
                f.delete();
            }
        }
        dir.delete();
    }

    public static boolean compileJavaProgram(Path srcPath, Map<String, String> results) throws IOException {
        StandardJavaFileManager fileManager = Constant.compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iter = fileManager.getJavaFileObjects(srcPath.toFile());
        JavaCompiler.CompilationTask compilationTask = Constant.compiler.getTask(null, fileManager, new DiagnosticListener<JavaFileObject>() {
            @Override
            public void report(Diagnostic<? extends JavaFileObject> diagnostic) {
                String compileErrorInfo = diagnostic.getMessage(null);
                StatusHandler.compileErrorHandler(compileErrorInfo, results);
            }
        }, null, null, iter);
        boolean compileResult = compilationTask.call();
        fileManager.close();
        return compileResult;
    }
}
