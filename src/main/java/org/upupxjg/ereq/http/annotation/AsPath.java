/**
 * 
 */
package org.upupxjg.ereq.http.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Xiao Jian'gang
 * 用于表示某类的某public方法可以被此path请求,该类需使用{@link @AsServlet}支持
 * 2012-12-26 下午9:31:48
 */

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD})
public @interface AsPath {
	String path();
}
