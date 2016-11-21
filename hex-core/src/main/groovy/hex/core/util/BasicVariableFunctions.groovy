package hex.core.util

import hex.core.engine.Symbol

/**
 * Created by poundex on 19/11/16.
 */
@HexSafe
class BasicVariableFunctions implements StackManipulator
{
	@Operator('=')
	@RequiresStackSize(2)
	static void assign(Deque<Object> stack)
	{
		Object o = stack.pop()
		if ( ! (o instanceof Symbol)) return
		o.value = stack.pop()
		stack.push o
	}
}
