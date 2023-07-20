package org.teasoft.gencode;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.teasoft.honey.osql.autogen.GenBean;
import org.teasoft.honey.osql.autogen.GenConfig;
import org.teasoft.honey.osql.name.NameUtil;
import org.teasoft.honey.osql.util.FileUtil;
import org.teasoft.honey.osql.util.StringUtil;

/**
 * Generate the code auto.
 * Spring boot restful back end mvc code.
 * Controller/Action + service (+ dao).
 * @author Kingstar
 * @since 2.1.7
 */
public class GenCode {

	private GenCode() {}

	public static void genRestRichWithCodeMsg(String tableName, String basePackageName,
			String constPackageName) {
		_genRestRich(tableName, basePackageName, true, constPackageName);
	}

	public static void genRestRichWithCodeMsg(String tableName, String basePackageName) {
		_genRestRich(tableName, basePackageName, true, null);
	}

	public static void genRestRich(String tableName, String basePackageName) {
		_genRestRich(tableName, basePackageName, false, null);
	}

	private static void _genRestRich(String tableName, String basePackageName,
			boolean isUseCodeMsg, String constPackageName) {

		String entityName = tableName;
//			String basePackageName="com.example.admin";

		// 1.生成Rest (Controller/Action)文件
		Map<String, String> map = new HashMap<>();
		map.put("entityName", entityName);
		map.put("packageName", basePackageName);
		if (constPackageName == null) constPackageName = GenCode.class.getPackage().getName();
		map.put("constPackageName", constPackageName);

		GenConfig config = new GenConfig();

//		String templateBasePath = "org.abc.gencode.template";
		String templateBasePath = GenCode.class.getPackage().getName() + ".template";
		templateBasePath = templateBasePath.replace(".", File.separator);

		String basePath = config.getBaseDir() + basePackageName.replace(".", File.separator);

		String entityName1 = NameUtil.firstLetterToUpperCase(entityName);

		String restTempName = "Rest.java.template";
		if (isUseCodeMsg) restTempName = "Rest2.java.template";
		// rest
		String templatePath = templateBasePath + File.separator + restTempName;

		String targetRestFilePath = basePath + File.separator + "rest" + File.separator
				+ entityName1 + "Rest.java";
		genFile(templatePath, map, targetRestFilePath);

		templatePath = templateBasePath + "\\Service.java.template";
		targetRestFilePath = basePath + "\\service\\" + entityName1 + "Service.java";
		genFile(templatePath, map, targetRestFilePath);

		templatePath = templateBasePath + "\\ServiceImpl.java.template";
		targetRestFilePath = basePath + "\\serviceimpl\\" + entityName1 + "ServiceImpl.java";
		genFile(templatePath, map, targetRestFilePath);

		// 2.生成Javabean
		config.setPackagePath(basePackageName + ".entity");
		GenBean genBean = new GenBean(config);
		// 可以使用{实体}_F来引用字段名
		config.setGenFieldFile(true); // 生成字段文件, 这样可以避免硬编码引用字段,速度也比反射快.
		genBean.genSomeBeanFile(tableName);

	}

	/**
	 * generate Javabean for one table, package name is entity
	 * @param tableName table name in database.
	 */
	public static void genOneJavabean(String tableName) {
		GenConfig config = new GenConfig();
		GenBean genBean = new GenBean(config);
		// 可以使用{实体}_F来引用字段名
		config.setGenFieldFile(true); // 生成字段文件, 这样可以避免硬编码引用字段,速度也比反射快.
		genBean.genSomeBeanFile(tableName);
	}

	private static void genFile(String templatePath, Map<String, String> map,
			String targetFilePath) {
		String txt = readTemplateFile(templatePath);
		txt = StringUtil.replaceWithMap(txt, map, "#{", "}");
		FileUtil.genFile(targetFilePath, txt);
	}

	private static String readTemplateFile(String filePath) {
		StringBuilder stringBuilder = new StringBuilder();

//		org\abc\gencode\template\Rest.java.template  //用这种格式，打包成jar后，访问会有问题.

		filePath = filePath.replace("\\", "/"); // 要转换斜杠

//		if (!filePath.trim().startsWith("/"))
//			filePath = "/" + filePath.trim();
//		try (InputStream inputStream = GenCode.class.getResourceAsStream(filePath); //不行
		try (InputStream inputStream = GenCode.class.getClassLoader()
				.getResourceAsStream(filePath);
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return stringBuilder.toString();
	}

}
