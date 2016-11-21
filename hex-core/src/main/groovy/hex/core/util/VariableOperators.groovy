package hex.core.util

import hex.core.engine.Operator
import hex.core.engine.Symbol

/**
 * Created by poundex on 19/11/16.
 */
class AssignmentOperator extends Operator
{
	@Override
	void doWithStack(Deque<Object> stack)
	{
		Object o = stack.pop()
		if ( ! (o instanceof Symbol)) return
		o.value = stack.pop()
		stack.push o
	}
}
