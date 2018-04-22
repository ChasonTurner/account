package com.arnold.server.generator;

import javax.sql.DataSource;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;

/**
 * GeneratorDemo
 */
public class GeneratorDemo {
	
	public static DataSource getDataSource() {
		Prop p = PropKit.use("WebConfig.properties");
		C3p0Plugin c3p0Plugin = new C3p0Plugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));
		c3p0Plugin.start();
		return c3p0Plugin.getDataSource();
	}
	
	public static void main(String[] args) {
		// base model 所使用的包名 
		String baseModelPackageName = "com.arnold.server.model.base";
		// base model 文件保存路径
		String baseModelOutputDir = PathKit.getWebRootPath() + "/../src/com/arnold/server/model/base";
		
		// model 所使用的包名 (MappingKit 默认使用的包名)
		String modelPackageName = "com.arnold.server.model";
		// model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
		String modelOutputDir = baseModelOutputDir + "/..";
		
		// 创建生成器
		Generator gernerator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
		// 设置数据库方言
		gernerator.setDialect(new MysqlDialect());
		
		/**
		 * 在库表中有些表不需要生成model
		 * 在这里添加过滤
		 */
		// 添加不需要生成的表名
		String[] excludedTables = getExcludedTables();
		gernerator.addExcludedTable(excludedTables);  	
		
		// 设置是否在 Model 中生成 dao 对象
		gernerator.setGenerateDaoInModel(true);
		// 设置是否生成字典文件
		gernerator.setGenerateDataDictionary(false);
		// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
		gernerator.setRemovedTableNamePrefixes("tb_");
		// 生成
		gernerator.generate();
	}
	
	/**
	 * @Author PanChangGui
	 * @Email 823468425@qq.com
	 * @Time 2017年9月24日 下午4:55:49
	 * @Description 在这里添加不需要生成model的表
	 */
	private static String[] getExcludedTables() {
		String[] excludedTables = {			
				"tmp_tb_family"
				,
				"temp_family1"
				,
				"temp_family"
				,
				"tb_project_1"
				,
				"tb_family_burden_relation_copy"
				,
				"tb_type_info"
				,
				"tb_family_burden_relation_2"
				,
				"tb_family_burden_relation_waibao"
				
		}; 
		
		return excludedTables;
	}
	
}




