package hex.core.engine

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