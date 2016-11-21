package hex.core.parser

import com.google.common.cache.CacheBuilder
import com.google.common.cache.LoadingCache
import hex.core.util.BasicStackFunctions
import hex.core.util.BasicOperators
import hex.core.util.BasicVariableFunctions
import hex.core.util.HexSafe
import hex.core.engine.Actuator

/**
 * Created by poundex on 17/11/16.
 */
class FunctionResolver
{
	private static final List<Class> DEFAULT_CLASSES = [BasicOperators, BasicStackFunctions, BasicVariableFunctions, Math]
	private final List<ClassFunctionResolver> resolvers = []
	private final LoadingCache<String, Optional<Actuator>> cache =
			CacheBuilder.newBuilder().<String, Optional<Actuator>>build(this.&lookup)

	FunctionResolver()
	{
		DEFAULT_CLASSES.each(this.&registerProviderClass)
	}

	void registerProviderClass(Class klass)
	{
		if (klass.getAnnotation(HexSafe))
			resolvers << new HexSafeClassFunctionResolver(klass)
		else
			resolvers << new DefaultClassFunctionResolver(klass)
	}

	private Optional<Actuator> lookup(String name)
	{
		return Optional.ofNullable(resolvers.findResult { it.resolve(name) })
	}

	Actuator resolveFunction(String name)
	{
		return cache.get(name).orElse(null)
	}
}
