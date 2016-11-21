package hex.core.engine

import hex.core.util.RequiresStackSize

import java.lang.reflect.Method

/**
 * Created by poundex on 21/11/16.
 */
class StaticClassFunctionResolver implements Actuator
{
	final int minimumStackSize
	private final Method targetMethod

	StaticClassFunctionResolver(Method targetMethod)
	{
		this.targetMethod = targetMethod
		this.minimumStackSize = targetMethod.getAnnotation(RequiresStackSize)?.value() ?: 0
	}

	@Override
	void doWithStack(Deque<Object> stack)
	{
		targetMethod.invoke(null, stack)
	}
}
