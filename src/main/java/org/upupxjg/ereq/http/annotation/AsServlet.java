/**
 * 
 */
package org.upupxjg.ereq.http.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.management.remote.TargetedNotification;

/**
 * @author Xiao Jian'gang
 *
 * 用于表示某类为servlet类，其中的public方法需{@link @AsPath}支持
 * 2012-12-26 下午9:25:33
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE})
public @interface AsServlet {
	boolean lazy() default false;
}
