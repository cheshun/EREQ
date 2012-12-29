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
 *
 * 如果你想使用这个注解，该类必须实现javax.servlet.Filter接口
 * 2012-12-26 下午9:50:49
 */
@Deprecated
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE})
public @interface AsFilter {
	boolean lazy() default false;
}
