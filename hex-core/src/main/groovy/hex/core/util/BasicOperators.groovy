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
		stack.push(stack.pop() + stack.pop())
	}
}

class SubtractionOperator extends Operator
{
	@Override
	void doWithStack(Deque<Object> stack)
	{
		Object o = stack.pop()
		stack.push(stack.pop() - o)
	}
}

class MultiplicationOperator extends Operator
{
	@Override
	void doWithStack(Deque<Object> stack)
	{
		stack.push(stack.pop() * stack.pop())
	}
}

class DivisionOperator extends Operator
{
	@Override
	void doWithStack(Deque<Object> stack)
	{
		Object o = stack.pop()
		stack.push(stack.pop() / o)
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
