package peaksoft.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMVCDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {JPAConfig.class};
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {JPAConfig.class};
    }
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
