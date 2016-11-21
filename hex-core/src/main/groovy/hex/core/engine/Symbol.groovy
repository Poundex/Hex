package hex.core.engine

import hex.core.context.SymbolManager

/**
 * Created by poundex on 19/11/16.
 */
class Symbol implements StackElement
{
	final String name
	private final SymbolManager symbolManager

	Symbol(String name, SymbolManager symbolManager)
	{
		this.name = name
		this.symbolManager = symbolManager
	}

	@Override
	String getDisplayValue()
	{
		return "\$${name}"
	}

	@Override
	Object getValue()
	{
		return symbolManager.get(name)
	}

	void setValue(Object newValue)
	{
		symbolManager.set(name, newValue)
	}

	@Override
	String toString()
	{
		return displayValue
	}
}
