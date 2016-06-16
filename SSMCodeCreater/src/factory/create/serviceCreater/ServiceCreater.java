package factory.create.serviceCreater;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import factory.create.BaseCreater;
import factory.entity.Entity;
import factory.pathSetting.PathSetting;
import factory.stringCaseUtil.StringCaseUtil;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Service 实现类生成
 * 
 * @author huangkai
 * 
 */
public class ServiceCreater extends BaseCreater{

	public ServiceCreater() {
		// TODO Auto-generated constructor stub
		super.initReader();
		setPathKey("service");
		setTemplatePath(PathSetting.serviceTemplate);
	}

	@Override
	public void executeCreateTask(Entity entity) {
		// TODO Auto-generated method stub
		String className = StringCaseUtil.upcaseFirstChar(entity
				.getEntityName()) + "Service";
		createFile(className);
		fillData(className, entity);
	}
	
	protected void loadTemplateAndWriteFile(String className,String fileType,
			Map<String, Object> data) {
		try {
			Template entityTemplate = cfg.getTemplate(templatePath);
			FileOutputStream entityFos = new FileOutputStream(writePath
					+ getPackagePathWithPathKey() + StringCaseUtil.lowcaseFirstChar(className)
					+ "/" + className + "Impl" + fileType);
			entityTemplate.process(data, new OutputStreamWriter(entityFos,
					"utf-8")); //
			entityFos.flush();
			entityFos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
