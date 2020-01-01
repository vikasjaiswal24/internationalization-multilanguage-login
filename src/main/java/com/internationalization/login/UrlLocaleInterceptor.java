package com.internationalization.login;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * These three methods provide flexibility to do all kinds of pre- and post-processing.
 *
 * prehandle() – called before the actual handler is executed, but the view is not generated yet
 * postHandle() – called after the handler is executed
 * afterCompletion() – called after the complete request has finished and view was generated
 */
@Controller("org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping")
public class UrlLocaleInterceptor extends HandlerInterceptorAdapter {

	/**
	 * This method will get call before the request handler method in Controller.
	 * @param request
	 * @param response
	 * @param handler
	 * @return true :: if the execution chain should proceed with the next interceptor or the handler itself.
	 * @return false :: DispatcherServlet assumes that this interceptor has already dealt with the response itself so no action required.
	 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // dynamic type is UrlLocaleResolver (from WebMvcConfig)
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver == null) {
            throw new IllegalStateException("No LocaleResolver found in the request");
        }
        // Getting Locale from UrlLocaleResolver (from WebMvcConfig)
        Locale locale = localeResolver.resolveLocale(request);
        localeResolver.setLocale(request, response, locale);

        return true;
    }

}