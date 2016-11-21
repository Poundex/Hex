package hex.core.context

import com.google.common.collect.ImmutableMap

/**
 * Created by poundex on 19/11/16.
 */
class SymbolManager
{
	private static final Map<String, Object> constants = ImmutableMap.builder().put('PI', Math.PI).put('E', Math.E).build()
	private final Map<String, Object> values = [:]

	Object get(String name)
	{
		return constants[name] ?: values[name]
	}

	void set(String name, Object newValue)
	{
		if ( ! constants.containsKey(name))
			values[name] = newValue
	}
}
