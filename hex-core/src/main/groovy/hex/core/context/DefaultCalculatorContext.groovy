package hex.core.context

import hex.core.engine.Actuator
import hex.core.parser.StringParser

/**
 * Created by poundex on 15/11/16.
 */
class DefaultCalculatorContext implements CalculatorContext
{
	private final StringParser stringParser
	private final Deque<Object> stack = new LinkedList<>()

	DefaultCalculatorContext(StringParser stringParser)
	{
		this.stringParser = stringParser
	}

	@Override
	void pushParsed(String stringToParse)
	{
		stringParser.parse(this, stringToParse)
	}

	@Override
	void pushRaw(Object object)
	{
		if (object instanceof Actuator)
			handleActuator(object)
		else
			stack.push object
	}

	private void handleActuator(Actuator actuator)
	{
		if (actuator.minimumStackSize > stack.size())
			return // TODO throw Something

		actuator.doWithStack(stack)
	}

	@Override
	Map<Integer, Object> getStackView()
	{
		int idx = 0
		return stack.descendingIterator().collectEntries { [ idx++, it ] }
	}

	@Override
	Map<Integer, Object> getListView()
	{
		int idx = stack.size() - 1
		return stack.iterator().collectEntries { [ idx--, it ] }
	}

	Object pop()
	{
		return stack.pop()
	}
}
