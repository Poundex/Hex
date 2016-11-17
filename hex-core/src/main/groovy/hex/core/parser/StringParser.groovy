package hex.core.parser

import hex.core.context.CalculatorContext
import hex.core.engine.*

/**
 * Created by poundex on 15/11/16.
 */
class StringParser
{
	private final String inputString
	private final CalculatorContext calculatorContext


	StringParser(CalculatorContext calculatorContext, String inputString)
	{
		this.inputString = inputString
		this.calculatorContext = calculatorContext
	}

	void parse()
	{
		if (inputString.contains(' '))
			inputString.split(' ').each(this.&doParse)
		else
			doParse(inputString)
	}

	private void doParse(String string)
	{
		if (isOperator(string))
			calculatorContext.pushRaw OPERATORS[string].newInstance()

		if (string.isNumber())
			calculatorContext.pushRaw string.toBigDecimal()
	}

	private boolean isOperator(String string)
	{
		return (string.length() < 2 && OPERATORS.containsKey(string))
	}

	private final static Map<String, Class<? extends Actuator>> OPERATORS = [
			'+': AdditionOperator,
			'-': SubtractionOperator,
			'*': MultiplicationOperator,
			'/': DivisionOperator,

	        '': DuplicateStackCommand,
	        '<': DropStackCommand,
	]
}
