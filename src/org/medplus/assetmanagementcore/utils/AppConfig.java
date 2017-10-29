package org.medplus.assetmanagementcore.utils;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.multipart.MultipartResolver;

@ImportResource("classpath:org/medplus/assetmanagementcore/utils/ApplicationContext.xml")
//@Configuration
public class AppConfig {


private MultipartResolver multipartResolver;
private void initMultipartResolver(ApplicationContext context)
{
  try
  {
    this.multipartResolver = ((MultipartResolver)context.getBean("multipartResolver", MultipartResolver.class));
    
    
  }
  catch (NoSuchBeanDefinitionException ex)
  {
    this.multipartResolver = null;
   
  }
}
}