package hex.core.util

import hex.core.engine.Operator
import hex.core.engine.StackCommand

/**
 * Created by poundex on 17/11/16.
 */

class AdditionOperator extends Operator
{
	@Override
	void doWithStack(Deque<Object> stack)
	{
		stack.push(popAndUnwrap(stack) + popAndUnwrap(stack))
	}
}

class SubtractionOperator extends Operator
{
	@Override
	void doWithStack(Deque<Object> stack)
	{
		Object o = popAndUnwrap(stack)
		stack.push(popAndUnwrap(stack) - o)
	}
}

class MultiplicationOperator extends Operator
{
	@Override
	void doWithStack(Deque<Object> stack)
	{
		stack.push(popAndUnwrap(stack) * popAndUnwrap(stack))
	}
}

class DivisionOperator extends Operator
{
	@Override
	void doWithStack(Deque<Object> stack)
	{
		Object o = popAndUnwrap(stack)
		stack.push(popAndUnwrap(stack) / o)
	}
}

class DuplicateStackCommand extends StackCommand
{
	@Override
	void doWithStack(Deque<Object> stack)
	{
		stack.push(stack.peek())
	}
}

class DropStackCommand extends StackCommand
{
	@Override
	void doWithStack(Deque<Object> stack)
	{
		stack.pop()
	}
}
