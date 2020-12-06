package com.whuarray.compiler.utility;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Constant {
    public static final int STATUS_CE = 0; // 编译错误
    public static final int STATUS_AC = 1; // 程序运行通过
    public static final int STATUS_RE = 2; // 运行出错
    public static final int STATUS_WA = 3; // 某个测试用例出错
    public static final int STATUS_SE = 4; // 系统运行出错

    public static final int EXITCODE_SU = 0; // 脚本返回值为0，表示脚本运行成功
    public static final int EXITCODE_CE = 10; // 脚本返回值为10，表示脚本运行失败，且原因是程序运行出错
    public static final int EXITCODE_RE = 20; // 脚本返回值为20，表示脚本运行失败，且原因是程序编译出错

    public static final int TESTCASE_OP_SU = 100; // 对测试用例的增删成功了
    public static final int TESTCASE_OP_ER = 200; // 对测试用例的增删失败了

    public static final String NUMBER_REGEX = "\\d+"; // 匹配数字的正则串
    public static final String BASE_PATH = "/tmp/WHUArray_Compiler/";

    public static final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
}
